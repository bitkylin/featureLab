package cc.bitky.testky.testbitkylin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestBitkylinApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBitkylinApplication.class, args);
    }

    public String abc() {
        return "abc";
    }
}
