package cc.bitky.mq.server.service;

import cc.bitky.mq.api.vo.MessageResp;
import cc.bitky.mq.api.vo.MqOverviewVo;

/**
 * @author liMingLiang
 */
public interface IMqBrokerService {
    /**
     * @return 优雅展示MQ概览
     */
    MqOverviewVo prettyOverview();

    /**
     * 创建消费者组
     *
     * @param groupName 消费者组名
     * @param topicName topic名
     */
    void createConsumerGroup(String groupName, String topicName);

    /**
     * 消息生产
     *
     * @param topicName topic名
     * @param message   消息
     * @return 消息生产是否成功
     */
    boolean offerMessage(String topicName, String message);

    /**
     * 消息消费
     *
     * @param groupName 消费者组名
     * @param topicName topic名
     * @return 获取到的消息
     */
    MessageResp<String> pullMessage(String groupName, String topicName);

    /**
     * 消息消费，自动更新偏移量
     *
     * @param groupName 消费者组名
     * @param topicName topic名
     * @return 获取到的消息
     */
    MessageResp<String> simplePullMessage(String groupName, String topicName);

    /**
     * 消息消费偏移量更新
     *
     * @param groupName 消费者组名
     * @param topicName topic名
     * @param offset    更新的偏移量
     * @return 更新是否成功
     */
    boolean updateOffset(String groupName, String topicName, int offset);
}
