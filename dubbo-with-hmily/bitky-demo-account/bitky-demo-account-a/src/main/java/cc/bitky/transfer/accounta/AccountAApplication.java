package cc.bitky.transfer.accounta;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cc.bitky.transfer.account", "cc.bitky.transfer.accounta"})
@MapperScan("cc.bitky.transfer.account.common.repository")
@EnableDubbo(scanBasePackages = "cc.bitky.transfer.account.common.service")
@SpringBootApplication
public class AccountAApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountAApplication.class, args);
    }

}
