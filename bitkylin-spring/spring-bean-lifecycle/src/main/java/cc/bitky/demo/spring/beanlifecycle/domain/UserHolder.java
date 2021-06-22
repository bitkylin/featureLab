package cc.bitky.demo.spring.beanlifecycle.domain;

import cc.bitky.demo.spring.beanlifecycle.util.KyLog;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author bitkylin
 */
@Data
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton, DisposableBean {

    private User user;

    @Override
    public void setBeanName(String name) {
        KyLog.log(21, "BeanNameAware#setBeanName");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        KyLog.log(22, "BeanClassLoaderAware#setBeanClassLoader");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        KyLog.log(23, "BeanFactoryAware#setBeanFactory");
    }

    @Override
    public void setEnvironment(Environment environment) {
        KyLog.log(22, "EnvironmentAware#setEnvironment");
    }

    @PostConstruct
    public void initPostConstruct() {
        KyLog.log(41, "@PostConstruct - CommonAnnotationBeanPostProcessor");
    }

    @Override
    public void afterPropertiesSet() {
        KyLog.log(42, "InitializingBean#afterPropertiesSet");
    }

    public void init() {
        KyLog.log(43, "xml: init-method=\"init\"");
    }

    @PreDestroy
    public void preDestroy() {
        KyLog.log(81, "@PreDestroy - CommonAnnotationBeanPostProcessor");
    }

    @Override
    public void destroy() {
        KyLog.log(82, "DisposableBean#destroy");
    }

    public void doDestroy() {
        KyLog.log(83, "xml: destroy-method=\"doDestroy\"");
    }

    @Override
    public void afterSingletonsInstantiated() {
        KyLog.log(61, "SmartInitializingSingleton#afterSingletonsInstantiated");

    }

    @Override
    protected void finalize() {
        KyLog.log(10, "java.lang.Object#finalize");
    }
}
