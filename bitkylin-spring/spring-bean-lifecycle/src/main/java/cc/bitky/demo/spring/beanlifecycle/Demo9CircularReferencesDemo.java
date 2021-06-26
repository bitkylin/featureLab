package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.ServiceA;
import cc.bitky.demo.spring.beanlifecycle.entity.ServiceB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * BeanFactory 循环引用（依赖）示例
 */
public class Demo9CircularReferencesDemo {

    @Bean
    public static ServiceA serviceA() {
        ServiceA serviceA = new ServiceA();
        serviceA.setName("A");
        return serviceA;
    }

    @Bean
    public static ServiceB serviceB() {
        ServiceB serviceB = new ServiceB();
        serviceB.setName("B");
        return serviceB;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(Demo9CircularReferencesDemo.class);

        // 如果设置为 false，则抛出异常信息如：currently in creation: Is there an unresolvable circular reference?
        // 默认值为 true
        context.setAllowCircularReferences(true);

        // 启动 Spring 应用上下文
        context.refresh();

        ServiceA serviceA = context.getBean(ServiceA.class);
        ServiceB serviceB = context.getBean(ServiceB.class);
        System.out.println("serviceA : " + serviceA);
        System.out.println("serviceB : " + serviceB);

        // 关闭 Spring 应用上下文
        context.close();
    }

}
