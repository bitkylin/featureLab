package cc.bitky.demo.springboot;

import cc.bitky.demo.springbootstarter.bitkylinprint.api.EnableFormat;
import cc.bitky.demo.springbootstarter.bitkylinprint.api.Formatter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bitkylin
 */
@EnableFormat
@SpringBootApplication
public class BitkylinSpringBootApplication implements CommandLineRunner {

    @Autowired
    private Formatter formatter;

    @Autowired
    private BeanFactory beanFactory;

    public static void main(String[] args) {
        SpringApplication.run(BitkylinSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(formatter.format("hello world"));

//        beanFactory.getBean("");

    }
}
