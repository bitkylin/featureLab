package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.User;
import cc.bitky.demo.spring.beanlifecycle.entity.UserContainer;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * {@link ObjectFactory} 延迟依赖查找示例
 */
public class Demo8LazyLookupDemo {

    @Bean
    @Lazy
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }

    @Bean
    public static UserContainer userContainer() {
        return new UserContainer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(Demo8LazyLookupDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        UserContainer userContainer = context.getBean(UserContainer.class);
        System.out.println("userContainer = " + userContainer.getUser());


        // 关闭 Spring 应用上下文
        context.close();
    }

}
