package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.entity.BaseUser;
import cc.bitky.demo.spring.beanlifecycle.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 获取bean"user"时，进行了BeanDefinition合并
 */
public class Demo3MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        // 基于 ClassPath 加载 XML 资源
        Resource resource = new ClassPathResource(location);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
        // 通过 Bean Id 和类型进行依赖查找
        BaseUser baseUser = beanFactory.getBean("baseUser", BaseUser.class);
        System.out.println(baseUser);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
