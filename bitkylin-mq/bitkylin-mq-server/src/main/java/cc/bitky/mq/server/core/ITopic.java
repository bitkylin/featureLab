package cc.bitky.mq.server.core;

/**
 * @author liMingLiang
 */
public interface ITopic<T> {

    /**
     * @return topic名
     */
    String getName();

    /**
     * @return topic容量
     */
    int getCapacity();

    /**
     * 消息生产
     *
     * @param message 消息
     */
    boolean offer(T message);

    /**
     * 根据索引获取消息
     *
     * @param index 索引值
     * @return 消息
     */
    T getByIndex(int index);

    /**
     * @return 当前最大索引值
     */
    int maxIndex();
}
