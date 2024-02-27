package cc.bitky.webserver;

import cc.bitky.webserver.controller.ImaginaryEnemyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserverApplication {

    public static void main(String[] args) {
        ImaginaryEnemyController.setAlley(args[0]);
        SpringApplication.run(WebserverApplication.class, args);
    }

}
