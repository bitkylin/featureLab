package cc.bitky.mq.server.core.impl;

import cc.bitky.mq.server.exception.KyMqException;
import cc.bitky.mq.server.core.ITopic;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 Topic
 *
 * @author liMingLiang
 */
public class KyTopic<T> implements ITopic<T> {

    /**
     * topic name
     */
    @Getter
    private final String name;

    /**
     * 容量
     */
    @Getter
    private final int capacity;

    /**
     * 队列
     */
    private final List<T> queue;

    private KyTopic(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
    }

    public static <T> KyTopic<T> create(String name, int capacity) {
        return new KyTopic<>(name, capacity);
    }

    @Override
    public boolean offer(T message) {
        if (maxIndex() + 1 >= capacity) {
            return false;
        }
        queue.add(message);
        return true;
    }

    @Override
    public T getByIndex(int index) {
        if (index >= queue.size()) {
            throw new KyMqException("topic越界:" + name + ", index: " + index);
        }
        return queue.get(index);
    }

    @Override
    public int maxIndex() {
        return queue.size() - 1;
    }

}
