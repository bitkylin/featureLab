package cc.bitky.demo.spring.beanlifecycle.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author bitkylin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class User extends BaseUser {

    private String address;

}
