package cc.bitky.mq.server.core;

import cc.bitky.mq.api.vo.MessageResp;

import java.util.Optional;

/**
 * @author liMingLiang
 */
public interface IConsumerGroup<T> {
    /**
     * @return 消费者组名
     */
    String getName();

    /**
     * @return 消费偏移量
     */
    int getOffset();

    /**
     * @return 关联的topic
     */
    ITopic<T> topic();

    /**
     * 更新偏移量
     *
     * @param offset 更新的偏移量
     * @return 是否更新成功
     */
    boolean updateOffset(int offset);

    /**
     * 消息消费
     *
     * @return 消息
     */
    Optional<MessageResp<T>> pull();
}
