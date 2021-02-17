package cc.bitky.mq.api.feign;

import cc.bitky.mq.api.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author liMingLiang
 */
@FeignClient(name = "bitkylin-mq-broker", url = "http://localhost:8080/mq/broker")
public interface BitkylinMqBrokerFeignClient {


    /**
     * @return 优雅展示MQ概览
     */
    @GetMapping("overview")
    MqOverviewVo prettyOverview();


    /**
     * 创建消费者组
     */
    @PostMapping("consumer-group/create")
    void createConsumerGroup(@RequestBody @Valid CreateConsumerGroupReq req);


    /**
     * 消息生产
     */
    @PostMapping("message/offer")
    boolean offerMessage(@RequestBody @Valid MessageOfferReq req);

    /**
     * 消息消费
     */
    @PostMapping("message/pull")
    MessageResp<String> pullMessage(@RequestBody @Valid MessagePullReq req);

    /**
     * 消息消费，自动更新偏移量
     */
    @PostMapping("message/simple-pull")
    MessageResp<String> simplePullMessage(@RequestBody MessagePullReq req);

    /**
     * 消息消费偏移量更新
     */
    @PostMapping("message/offset/update")
    Boolean updateOffset(@RequestBody @Valid UpdateOffsetReq req);
}
