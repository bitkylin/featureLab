# 搭建基于 docker 的 Kafka 集群及Spring Boot应用访问

## 需求

1. 搭建一个 3 节点 kafka 集群，测试功能和性能
2. 实现 Spring kafka 下对 kafka 集群的操作

## 一、搭建精简版 kafka 集群

参考排名第一的 kafka 镜像 [wurstmeister/kafka](https://hub.docker.com/r/wurstmeister/kafka) 的官方文档，进行如下操作：

1. 创建 docker-compose.yml 文件，内容如下：

```yaml
version: '1'

services:
  zookeeper:
    image: zookeeper
    container_name: bitkylin-kafka-zookeeper
    ports:
      - "2181:2181"

  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092"
    environment:
      kafka_ADVERTISED_HOST_NAME: 172.17.0.1
      kafka_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

关键点：未指定 kafka 容器名、未暴露 kafka 相关端口，便于 docker-compose 命令自伸缩扩展容器。

2. 集群操作

在 docker-compose.yml 文件所在目录下，可执行如下操作：

```bash
# 启动单 kafka 节点的集群
docker-compose up -d

# 启动三个 kafka 节点的集群
 docker-compose up --scale kafka=3

# 销毁集群
docker-compose stop
```

## 二、完整版 kafka 集群

精简版集群创建比较简单，但是并不实用，以下创建可用版的集群。

`wurstmeister/kafka` 镜像搞了半天，搞出的集群无法被容器外部访问，这为调试、开发带来不便，现更换镜像重搞一次。

更换为镜像：[bitnami-docker-kafka](https://github.com/bitnami/bitnami-docker-kafka)

1. 创建单节点 kafka 集群：

```yaml
version: '1'

services:
  zookeeper:
    image: zookeeper
    container_name: bitkylin-kafka-zookeeper
    ports:
      - "2181:2181"

  kafka:
    image: 'bitnami/kafka'
    ports:
      - '9092:9092'
    environment:
      - kafka_BROKER_ID=1
      - kafka_LISTENERS=PLAINTEXT://:9092
      - kafka_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - kafka_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper
```

2. 创建 3 节点 kafka 集群：

```yaml
version: '2'

services:
  zookeeper:
    image: zookeeper
    container_name: bitkylin-kafka-zookeeper
    ports:
      - "2181:2181"

  kafka-1:
    image: 'bitnami/kafka'
    container_name: bitkylin-kafka-1
    ports:
      - '9081:9081'
    environment:
      - kafka_BROKER_ID=11
      - kafka_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
      - kafka_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
      - kafka_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9081
      - kafka_CFG_ADVERTISED_LISTENERS=CLIENT://:9092,EXTERNAL://localhost:9081
      - kafka_INTER_BROKER_LISTENER_NAME=CLIENT
    depends_on:
      - zookeeper

  kafka-2:
    image: 'bitnami/kafka'
    container_name: bitkylin-kafka-2
    ports:
      - '9082:9082'
    environment:
      - kafka_BROKER_ID=12
      - kafka_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
      - kafka_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
      - kafka_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9082
      - kafka_CFG_ADVERTISED_LISTENERS=CLIENT://:9092,EXTERNAL://localhost:9082
      - kafka_INTER_BROKER_LISTENER_NAME=CLIENT
    depends_on:
      - zookeeper

  kafka-3:
    image: 'bitnami/kafka'
    container_name: bitkylin-kafka-3
    ports:
      - '9083:9083'
    environment:
      - kafka_BROKER_ID=13
      - kafka_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
      - kafka_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
      - kafka_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9083
      - kafka_CFG_ADVERTISED_LISTENERS=CLIENT://:9092,EXTERNAL://localhost:9083
      - kafka_INTER_BROKER_LISTENER_NAME=CLIENT
    depends_on:
      - zookeeper
```

2. 启动三节点 kafka 集群

```bash
docker-compose up -d
```

3. 操作

```shell
# --- 容器集群内 - 基于节点 1 发送消息 ---
# 进入其中一个 kafka 容器
docker exec -it bitkylin-kafka-1 /bin/bash

# 从节点 1 发送消息
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-bitkylin

# --- 容器集群外 - 基于节点 3 接收消息 ---
# 进入 kafka 安装目录
cd /opt/kafka_2.13-2.7.0/bin

# 从节点 3 监听指定 topic
 ./kafka-console-consumer.bat --bootstrap-server localhost:9083 --topic test-bitkylin --from-beginning
```

经过以上操作，可以验证发送消息、接收消息均正常。尤其验证了容器外访问 kafka 容器是正常的，这是难点！！！

通过 Spring boot + Spring kafka 对 kafka 集群消息发送、接收的示例，见如下代码：
[bitkylin-kafka](https://github.com/bitkylin/featureLab/tree/master/bitkylin-kafka)

启动 Spring boot 应用后，仍然使用 bitkylin-kafka-1 容器中的消息生产者发送消息，可以看到 Spring boot 应用和连接 bitkylin-kafka-3 的外部消费者均全量消费了消息。

## 备忘

1. kafka 需配置完整的代理 `IP:PORT`，容器集群外要访问 kafka 容器时，必须通过暴露的 `IP:PORT` 进行访问，不能进行任何变更。此处设计据说是为了防止中间人攻击。

2. 容器外访问 kafka 容器确实很麻烦，研究了很久，[bitnami-docker-kafka](https://github.com/bitnami/bitnami-docker-kafka) 镜像的官方文档写的很好，可以研读。

### 参考链接

1. [kafka 的 Docker 镜像使用说明(wurstmeister-kafka)](http://cloud.tencent.com/developer/article/1436734)
2. [docker image：wurstmeister/kafka](https://hub.docker.com/r/wurstmeister/kafka)
3. [docker image：bitnami-docker-kafka](https://github.com/bitnami/bitnami-docker-kafka)