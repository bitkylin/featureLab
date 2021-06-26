package cc.bitky.demo.spring.beanlifecycle.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author bitkylin
 */
@Data
@ToString(callSuper = true)
public class UserContainer {

    @Autowired
    private User user;

}
