package cc.bitky.mq.client.consumer;

import cc.bitky.mq.api.BitkylinMqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = {"cc.bitky"})
@EnableFeignClients(basePackages = {"cc.bitky.mq.api"})
@SpringBootApplication
public class BitkylinMqClientConsumerApplication implements CommandLineRunner {

    @Autowired
    private BitkylinMqClient bitkylinMqClient;

    public static void main(String[] args) {
        SpringApplication.run(BitkylinMqClientConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bitkylinMqClient.subscribe("spring-group-1", "topic-1", message -> {
            log.info("收到消息：spring-group-1: " + message);
        });

        bitkylinMqClient.subscribe("spring-group-2", "topic-2", message -> {
            log.info("收到消息：spring-group-2: " + message);
        });
    }

}
