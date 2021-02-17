package cc.bitky.mq.server.core.impl;

import cc.bitky.mq.api.vo.MessageResp;
import cc.bitky.mq.server.core.IConsumerGroup;
import cc.bitky.mq.server.core.ITopic;
import lombok.Getter;

import java.util.Optional;

/**
 * @author liMingLiang
 */
public class KyConsumerGroup<T> implements IConsumerGroup<T> {

    /**
     * name
     */
    @Getter
    private String name;

    /**
     * topic
     */
    private ITopic<T> topic;

    /**
     * 偏移量
     */
    @Getter
    private volatile int offset;

    private KyConsumerGroup(String name, ITopic<T> topic) {
        this.name = name;
        this.topic = topic;
    }

    public static <T> IConsumerGroup<T> create(String name, ITopic<T> topic) {
        return new KyConsumerGroup<>(name, topic);
    }

    @Override
    public ITopic<T> topic() {
        return topic;
    }

    @Override
    public boolean updateOffset(int offset) {
        this.offset = offset;
        return true;
    }

    @Override
    public Optional<MessageResp<T>> pull() {
        int currentOffset = offset;
        if (currentOffset > topic.maxIndex()) {
            return Optional.empty();
        }

        MessageResp<T> msgResp = new MessageResp<>();
        msgResp.setMessage(topic.getByIndex(currentOffset));
        msgResp.setIndex(currentOffset);
        return Optional.of(msgResp);
    }
}
