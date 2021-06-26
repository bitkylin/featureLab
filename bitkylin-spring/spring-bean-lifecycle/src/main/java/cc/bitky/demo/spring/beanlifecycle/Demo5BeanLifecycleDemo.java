package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.processor.MyDestructionAwareBeanPostProcessor;
import cc.bitky.demo.spring.beanlifecycle.processor.MyInstantiationAwareBeanPostProcessor;
import cc.bitky.demo.spring.beanlifecycle.entity.BaseUser;
import cc.bitky.demo.spring.beanlifecycle.entity.User;
import cc.bitky.demo.spring.beanlifecycle.entity.UserHolder;
import cc.bitky.demo.spring.beanlifecycle.util.KyLog;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * LifeCycle「11」InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
 * <p>
 * --- 执行：bean实例化 ---
 * <p>
 * LifeCycle「12」InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation
 * <p>
 * LifeCycle「13」InstantiationAwareBeanPostProcessor#postProcessProperties
 * <p>
 * --- 执行：bean属性赋值 ---
 * <p>
 * --- 注：一堆aware方法 ---
 * <p>
 * LifeCycle「21」BeanNameAware#setBeanName
 * LifeCycle「22」BeanClassLoaderAware#setBeanClassLoader
 * LifeCycle「23」BeanFactoryAware#setBeanFactory
 * <p>
 * LifeCycle「31」BeanPostProcessor#postProcessBeforeInitialization
 * <p>
 * --- 注：一堆初始化方法 ---
 * <p>
 * LifeCycle「41」@PostConstruct - CommonAnnotationBeanPostProcessor
 * LifeCycle「42」InitializingBean#afterPropertiesSet
 * LifeCycle「43」xml: init-method="init"
 * <p>
 * LifeCycle「51」BeanPostProcessor#postProcessAfterInitialization
 * <p>
 * --- 注：准备进行收尾 ---
 * <p>
 * LifeCycle「61」SmartInitializingSingleton#afterSingletonsInstantiated
 * <p>
 * --- 注：bean初始化完成 ---
 * <p>
 * BaseUser(id=520, name=bitkylin, age=8)
 * User(super=BaseUser(id=520, name=bitkylin, age=18), address=shangHai)
 * UserHolder(user=User(super=BaseUser(id=520, name=bitkylin, age=18), address=shangHai))
 * <p>
 * --- 执行：bean销毁 ---
 * <p>
 * LifeCycle「71」DestructionAwareBeanPostProcessor#postProcessBeforeDestruction
 * <p>
 * LifeCycle「81」@PreDestroy - CommonAnnotationBeanPostProcessor
 * LifeCycle「82」DisposableBean#destroy
 * LifeCycle「83」xml: destroy-method="doDestroy"
 *
 * @author bitkylin
 */
public class Demo5BeanLifecycleDemo {

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
        //  beanFactory.destroyBean("userHolder", userHolder);

        // 销毁 BeanFactory 中的单例 Bean，其中会调用方法 {beanFactory.destroyBean}
        beanFactory.destroySingletons();

        // Bean 销毁并不意味着 Bean 垃圾回收了
        KyLog.log(userHolder);

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
