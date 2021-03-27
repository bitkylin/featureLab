# featureLab
开发过程组件及特性验证

## 1. [从零开始自实现MQ](./bitkylin-mq)

仿照 kafka，实现了 kafka 中 80% 的基础功能。

关键词：broker、topic、producer、consumer、zookeeper、docker 等

## 2. [基于 dubbo + hmily 的外汇交易系统](dubbo-with-hmily)

1. 多应用分布式一致性保证
2. 使用 hmily 实现 TCC 柔性事务

关键词：dubbo、hmily、multiModule、TCC、MySQL

## 3. [搭建基于 docker 的 Kafka 集群及Spring Boot应用访问](bitkylin-kafka)

1. 搭建一个 3 节点 kafka 集群，测试功能和性能
2. 实现 Spring kafka 下对 kafka 集群的操作

关键词：kafka、docker、Spring boot
