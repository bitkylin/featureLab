package cc.bitky.mq.server.controller;

import cc.bitky.mq.api.vo.*;
import cc.bitky.mq.server.service.IMqBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liMingLiang
 */
@RestController
@RequestMapping("/mq/broker")
public class KyMqServerController {

    @Autowired
    private IMqBrokerService mqService;

    /**
     * @return 优雅展示MQ概览
     */
    @GetMapping("overview")
    public MqOverviewVo prettyOverview() {
        return mqService.prettyOverview();
    }

    /**
     * 创建消费者组
     */
    @PostMapping("consumer-group/create")
    public void createConsumerGroup(@RequestBody CreateConsumerGroupReq req) {
        mqService.createConsumerGroup(req.getGroupName(), req.getTopicName());
    }

    /**
     * 消息生产
     */
    @PostMapping("message/offer")
    public boolean offerMessage(@RequestBody MessageOfferReq req) {
        return mqService.offerMessage(req.getTopicName(), req.getMessage());
    }

    /**
     * 消息消费
     */
    @PostMapping("message/pull")
    public MessageResp<String> pullMessage(@RequestBody MessagePullReq req) {
        return mqService.pullMessage(req.getGroupName(), req.getTopicName());
    }


    /**
     * 消息消费，自动更新偏移量
     */
    @PostMapping("message/simple-pull")
    public MessageResp<String> simplePullMessage(@RequestBody MessagePullReq req) {
        return mqService.simplePullMessage(req.getGroupName(), req.getTopicName());
    }

    /**
     * 消息消费偏移量更新
     */
    @PostMapping("message/offset/update")
    public Boolean updateOffset(@RequestBody UpdateOffsetReq req) {
        return mqService.updateOffset(req.getGroupName(), req.getTopicName(), req.getOffset());
    }
}
