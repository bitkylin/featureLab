package cc.bitky.demo.spring.beanlifecycle.beanpostprocessor;

import cc.bitky.demo.spring.beanlifecycle.domain.UserHolder;
import cc.bitky.demo.spring.beanlifecycle.util.KyLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author bitkylin
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.isAssignableFrom(UserHolder.class)) {
            KyLog.log(11, "InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserHolder) {
            KyLog.log(12, "InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws
            BeansException {
        if (bean instanceof UserHolder) {
            KyLog.log(13, "InstantiationAwareBeanPostProcessor#postProcessProperties");
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserHolder) {
            KyLog.log(31, "BeanPostProcessor#postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserHolder) {
            KyLog.log(51, "BeanPostProcessor#postProcessAfterInitialization");
        }
        return bean;
    }
}
