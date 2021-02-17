# featureLab
开发过程组件及特性验证

## 1. [从零开始自实现MQ](./bitkylin-mq)

仿照Kafka，实现了Kafka中80%的基础功能。

关键词：broker、topic、producer、consumer、zookeeper、docker等

## 2. [基于 dubbo + hmily 的外汇交易系统](dubbo-with-hmily)

1. 多应用分布式一致性保证
2. 使用hmily实现TCC柔性事务

关键词：dubbo、hmily、multiModule、TCC、MySQL