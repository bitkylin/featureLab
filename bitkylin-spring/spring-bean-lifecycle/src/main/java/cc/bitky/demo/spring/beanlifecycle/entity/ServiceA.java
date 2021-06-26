package cc.bitky.demo.spring.beanlifecycle.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author limingliang
 */
@Getter
@Setter
public class ServiceA {

    private String name;

    @Autowired
    private ServiceB serviceB;

    @Override
    public String toString() {
        return "ServiceA{" +
                "name='" + name + '\'' +
                ", serviceB=" + serviceB.getClass() +
                '}';
    }
}
