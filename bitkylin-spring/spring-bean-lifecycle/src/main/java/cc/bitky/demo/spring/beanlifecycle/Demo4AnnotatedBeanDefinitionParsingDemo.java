package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 1. 基于注解注册BeanDefinition
 * 2. 基于BeanName进行依赖查找
 */
//@Configuration
@Getter
@Setter
public class Demo4AnnotatedBeanDefinitionParsingDemo {

    @Resource(name = "userTest")
    private User userAutowired;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类（Configuration Class）
        int beanDefinitionCountBefore = applicationContext.getBeanDefinitionCount();
        applicationContext.register(Demo4AnnotatedBeanDefinitionParsingDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        int beanDefinitionCountAfter = applicationContext.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        // 依赖查找集合对象
        System.out.println("已加载 BeanDefinition 数量：" + beanDefinitionCount);
        // 普通的 Class 作为 Component 注册到 Spring IoC 容器后，通常 Bean 名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator，注解实现 AnnotationBeanNameGenerator
        Demo4AnnotatedBeanDefinitionParsingDemo demo = applicationContext.getBean("demo4AnnotatedBeanDefinitionParsingDemo",
                Demo4AnnotatedBeanDefinitionParsingDemo.class);
        User userTest = applicationContext.getBean("userTest", User.class);
        System.out.println(demo);
        System.out.println(userTest);
        System.out.println("userTest == userAutowired = " + (userTest == demo.getUserAutowired()));
    }

    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User userTest() {
        User user = new User();
        user.setName("userTest");
        return user;
    }
}
