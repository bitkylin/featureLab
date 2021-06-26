package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.User;
import cc.bitky.demo.spring.beanlifecycle.entity.UserContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * 延迟依赖查找
 * Spring启动时并不进行Bean的装配，在首次依赖查找时进行Bean的装配
 */
public class Demo7LazyLookupDemo {

    @Bean
    @Lazy
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }

    @Bean
    @Lazy
    public static UserContainer userContainer() {
        return new UserContainer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(Demo7LazyLookupDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        UserContainer userContainer = context.getBean(UserContainer.class);
        System.out.println("userContainer = " + userContainer.getUser());


        // 关闭 Spring 应用上下文
        context.close();
    }

}
