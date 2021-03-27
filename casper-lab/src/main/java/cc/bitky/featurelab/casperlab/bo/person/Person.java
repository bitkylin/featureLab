package cc.bitky.featurelab.casperlab.bo.person;

import cc.bitky.featurelab.casperlab.bo.work.Work;
import lombok.Data;
import lombok.Getter;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
@Data
abstract class Person implements IPerson {

    protected Work work;

    @Getter
    private String name;

    @Getter
    private int age;

    protected Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Work getWork() {
        System.out.println("不应该被调用");
        return work;
    }

    @Override
    public String getAlias() {
        getWork();
        return "DemoAlias";
    }
}
