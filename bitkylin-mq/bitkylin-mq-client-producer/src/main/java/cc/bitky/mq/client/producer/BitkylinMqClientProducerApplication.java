package cc.bitky.mq.client.producer;

import cc.bitky.mq.api.BitkylinMqClient;
import cc.bitky.mq.api.vo.MessageOfferReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@RestController
@RequestMapping("/mq/producer")
@ComponentScan(basePackages = {"cc.bitky"})
@EnableFeignClients(basePackages = {"cc.bitky.mq.api"})
@SpringBootApplication
public class BitkylinMqClientProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BitkylinMqClientProducerApplication.class, args);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessageOfferReq req) {
        bitkylinMqClient.sendMessage(req.getTopicName(), req.getMessage());
    }

    @Autowired
    private BitkylinMqClient bitkylinMqClient;

    @Override
    public void run(String... args) throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        LongAdder adder = new LongAdder();
        Runnable topic1 = () -> bitkylinMqClient.sendMessage("topic-1", "topic-1-msg:" + adder.toString());
        Runnable topic2 = () -> bitkylinMqClient.sendMessage("topic-2", "topic-2-msg:" + adder.toString());

        //noinspection AlibabaThreadPoolCreation
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            adder.increment();
            if (random.nextBoolean()) {
                topic1.run();
            } else {
                topic2.run();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
