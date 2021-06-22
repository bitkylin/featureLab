package cc.bitky.demo.spring.beanlifecycle.beanpostprocessor;

import cc.bitky.demo.spring.beanlifecycle.domain.UserHolder;
import cc.bitky.demo.spring.beanlifecycle.util.KyLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * @author bitkylin
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserHolder) {
            KyLog.log(71, "DestructionAwareBeanPostProcessor#postProcessBeforeDestruction");
        }
    }
}
