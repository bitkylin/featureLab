package cc.bitky.featurelab.casperlab.service.jmx.mbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author limingliang
 */
@Component
@ManagedResource(objectName = "cc.bitky:type=demo,name=BitKy ApplicationContext", description = "比特麒麟的ApplicationContext数据统计哦")
public class JmxApplicationContext {

    @Autowired
    private ApplicationContext applicationContext;

    private int operationCount = 0;

    private int beanDefinitionCount = -1;


    @ManagedAttribute(description = "获取比特麒麟的列表")
    @ManagedOperationParameter(name = "beanName", description = "bean名")
    public String fetchBean(String beanName) {
        operationCount++;
        return applicationContext.getBean(beanName).toString();
    }

    @ManagedAttribute(description = "获取所有定义的Bean的数量")
    public int fetchBeanDefinitionCount() {
        operationCount++;
        beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        return beanDefinitionCount;
    }

    @ManagedAttribute(description = "获取比特麒麟的列表")
    public String[] fetchBeanDefinitionNames() {
        operationCount++;
        return applicationContext.getBeanDefinitionNames();
    }

    @ManagedAttribute
    public int getBeanDefinitionCount() {
        return beanDefinitionCount;
    }

    @ManagedAttribute
    public int getOperationCount() {
        return operationCount;
    }

}
