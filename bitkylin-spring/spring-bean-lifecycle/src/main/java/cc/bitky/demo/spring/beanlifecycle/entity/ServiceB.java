package cc.bitky.demo.spring.beanlifecycle.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author limingliang
 */
@Getter
@Setter
public class ServiceB {

    private String name;

    @Autowired
    private ServiceA serviceA;

    @Override
    public String toString() {
        return "ServiceB{" +
                "name='" + name + '\'' +
                ", serviceA=" + serviceA.getClass() +
                '}';
    }
}
