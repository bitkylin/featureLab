package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于@Bean注解，与XML配置Bean的异同
 * @author bitkylin
 */
public class Demo6AnnotatedBeanLookupDemo {

    @Bean
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(Demo6AnnotatedBeanLookupDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println("user = " + context.getBean(User.class));

        // 关闭 Spring 应用上下文
        context.close();
    }

}
