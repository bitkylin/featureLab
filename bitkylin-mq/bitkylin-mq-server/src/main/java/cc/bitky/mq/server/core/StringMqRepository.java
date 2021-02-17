package cc.bitky.mq.server.core;

import cc.bitky.mq.api.vo.MessageResp;
import cc.bitky.mq.server.config.CommonConfig;
import cc.bitky.mq.server.core.impl.KyConsumerGroup;
import cc.bitky.mq.server.core.impl.KyTopic;
import cc.bitky.mq.server.exception.KyMqException;
import cc.bitky.mq.server.pojo.ConsumerGroupDefinition;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

/**
 * @author liMingLiang
 */
@Repository
public class StringMqRepository {

    @Getter
    private final Map<ConsumerGroupDefinition, IConsumerGroup<String>> consumerGroupMap = Maps.newConcurrentMap();
    private final Map<String, ITopic<String>> topicMap = Maps.newConcurrentMap();

    public ITopic<String> createTopic(String topicName) {
        return topicMap.computeIfAbsent(topicName, name -> KyTopic.create(name, CommonConfig.DEFAULT_TOPIC_CAPACITY));
    }

    public IConsumerGroup<String> createConsumerGroup(String groupName, ITopic<String> topic) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topic.getName())
                .build();
        return consumerGroupMap.computeIfAbsent(consumerGroupDefinition, definition -> KyConsumerGroup.create(definition.getGroupName(), topic));
    }

    public boolean offerMessage(String topicName, String message) {
        return Optional.ofNullable(topicMap.get(topicName)).orElse(createTopic(topicName))
                .offer(message);
    }

    public boolean updateOffset(String groupName, String topicName, int offset) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topicName)
                .build();
        return Optional.ofNullable(consumerGroupMap.get(consumerGroupDefinition)).orElseThrow(() -> new KyMqException("没有找到指定的消费者组: " + groupName))
                .updateOffset(offset);
    }

    public Optional<MessageResp<String>> pullMessage(String groupName, String topicName) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topicName)
                .build();
        return Optional.ofNullable(consumerGroupMap.get(consumerGroupDefinition)).orElseThrow(() -> new KyMqException("没有找到指定的消费者组: " + groupName))
                .pull();
    }
}
