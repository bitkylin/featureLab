package cc.bitky.featurelab.casperlab.service.jmx.mbean;

import com.google.common.collect.Lists;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author limingliang
 */
@Component
@ManagedResource(objectName = "cc.bitky:type=demo,name=BitKylin list", description = "比特麒麟的列表哦")
public class JmxMyList {

    private final List<String> list = Lists.newArrayList("test1", "test2", "test3");
    private int operationCount = 0;

    @ManagedAttribute(description = "获取比特麒麟的列表")
    public String[] getBitKylinList() {
        operationCount++;
        return list.toArray(new String[0]);
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "item", description = "向比特麒麟的列表中添加一项")
    public void addOne(String item) {
        operationCount++;
        list.add(item);
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "itemOne", description = "添加的第一项")
    @ManagedOperationParameter(name = "itemTwo", description = "添加的第二项")
    @ManagedOperationParameter(name = "itemThree", description = "添加的第三项")
    @ManagedOperationParameter(name = "itemFour", description = "添加的第四项")
    public void addFour(String itemOne, String itemTwo, String itemThree, String itemFour) {
        operationCount++;
        list.add(itemOne);
        list.add(itemTwo);
        list.add(itemThree);
        list.add(itemFour);
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "itemList", description = "向比特麒麟的列表中添加好多项")
    public void addMore(List<String> itemList) {
        operationCount++;
        list.addAll(itemList);
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "item", description = "从比特麒麟的列表中删除指定的一项")
    public void removeOne(String item) {
        operationCount++;
        list.remove(item);
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "item", description = "指定列表项是否包含在比特麒麟的列表中")
    public boolean listContained(String item) {
        operationCount++;
        return list.contains(item);
    }
}
