package cc.bitky.demo.springboot;

import cc.bitky.demo.springbootstarter.bitkylinprint.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BitkylinSpringBootApplication implements CommandLineRunner {

    @Autowired
    private Formatter formatter;

    public static void main(String[] args) {
        SpringApplication.run(BitkylinSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(formatter.format("hello world"));
    }
}
