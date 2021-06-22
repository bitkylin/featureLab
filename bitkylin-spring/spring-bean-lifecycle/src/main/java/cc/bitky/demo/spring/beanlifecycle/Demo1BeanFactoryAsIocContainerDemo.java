package cc.bitky.demo.spring.beanlifecycle;

import cc.bitky.demo.spring.beanlifecycle.domain.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author bitkylin
 */
@Slf4j
public class Demo1BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        log.info("Bean 定义加载的数量：" + beanDefinitionsCount);
        // 依赖查找指定Bean对象
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, BaseUser> users = listableBeanFactory.getBeansOfType(BaseUser.class);
            String[] alias = listableBeanFactory.getAliases("baseUser");
            String[] alias2 = listableBeanFactory.getAliases("baseUser-alias1");
            log.info("查找到的所有的 User 集合对象：" + users);
            log.info(alias + " : " + alias2);
        }
    }

}
