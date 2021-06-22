package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.beanpostprocessor.MyDestructionAwareBeanPostProcessor;
import cc.bitky.demo.spring.beanlifecycle.beanpostprocessor.MyInstantiationAwareBeanPostProcessor;
import cc.bitky.demo.spring.beanlifecycle.domain.BaseUser;
import cc.bitky.demo.spring.beanlifecycle.domain.User;
import cc.bitky.demo.spring.beanlifecycle.domain.UserHolder;
import cc.bitky.demo.spring.beanlifecycle.util.KyLog;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @author bitkylin
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        KyLog.log("已加载 BeanDefinition 数量：" + beanNumbers);

        // 显示地执行 preInstantiateSingletons()
        // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        KyLog.log("");
        KyLog.log("--- bean初始化完成 ---");
        KyLog.log("");

        // 通过 Bean Id 和类型进行依赖查找
        BaseUser baseUser = beanFactory.getBean("baseUser", BaseUser.class);
        KyLog.log(baseUser);

        User user = beanFactory.getBean("user", User.class);
        KyLog.log(user);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        KyLog.log(userHolder);

        KyLog.log("");
        KyLog.log("--- bean准备销毁 ---");
        KyLog.log("");

        // 执行 Bean 销毁（容器内）
        beanFactory.destroyBean("userHolder", userHolder);

        // Bean 销毁并不意味着 Bean 垃圾回收了
        KyLog.log(userHolder);

        // 销毁 BeanFactory 中的单例 Bean
        beanFactory.destroySingletons();

        KyLog.log("");
        KyLog.log("--- gc ---");
        KyLog.log("");

        // 强制 GC
        System.gc();
        // 等待一段时间
        Thread.sleep(2000L);
        // 强制 GC
        System.gc();
        // 等待一段时间
        Thread.sleep(2000L);
    }
}
