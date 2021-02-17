package cc.bitky.mq.api;

import cc.bitky.mq.api.feign.BitkylinMqBrokerFeignClient;
import cc.bitky.mq.api.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Service
public class BitkylinMqClient {

    @Autowired
    private BitkylinMqBrokerFeignClient brokerFeignClient;

    /**
     * 发送消息
     *
     * @param topicName topic名
     * @param message   消息
     */
    public void sendMessage(String topicName, String message) {
        brokerFeignClient.offerMessage(MessageOfferReq.builder()
                .topicName(topicName)
                .message(message)
                .build());
    }

    /**
     * 消息消费订阅
     *
     * @param groupName       消费者组名
     * @param topicName       topic名
     * @param messageConsumer 消息监听器
     */
    public void subscribe(String groupName, String topicName, Consumer<String> messageConsumer) {
        brokerFeignClient.createConsumerGroup(CreateConsumerGroupReq.builder()
                .groupName(groupName)
                .topicName(topicName)
                .build());

        //noinspection AlibabaThreadPoolCreation
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            MessageResp<String> messageResp = brokerFeignClient.pullMessage(MessagePullReq.builder()
                    .groupName(groupName)
                    .topicName(topicName)
                    .build());
            if (messageResp.getPullSuccess()) {
                messageConsumer.accept(messageResp.getMessage());
                brokerFeignClient.updateOffset(UpdateOffsetReq.builder()
                        .groupName(groupName)
                        .topicName(topicName)
                        .offset(messageResp.getIndex() + 1)
                        .build());
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

}
