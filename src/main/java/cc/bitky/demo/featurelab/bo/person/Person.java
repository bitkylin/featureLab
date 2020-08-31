package cc.bitky.demo.featurelab.bo.person;

import cc.bitky.demo.featurelab.bo.work.Work;
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
}
