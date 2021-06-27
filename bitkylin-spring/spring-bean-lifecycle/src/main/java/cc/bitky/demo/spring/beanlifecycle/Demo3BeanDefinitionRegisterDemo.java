package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 直接将指定类注册BeanDefinition
 * 2. 基于BeanName进行依赖查找
 */
public class Demo3BeanDefinitionRegisterDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @Component class）
        beanDefinitionReader.register(Demo3BeanDefinitionRegisterDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
        // 普通的 Class 作为 Component 注册到 Spring IoC 容器后，通常 Bean 名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotationBeanNameGenerator
        Demo3BeanDefinitionRegisterDemo demo = beanFactory.getBean("demo3BeanDefinitionRegisterDemo",
                Demo3BeanDefinitionRegisterDemo.class);
        System.out.println(demo);
    }
}
