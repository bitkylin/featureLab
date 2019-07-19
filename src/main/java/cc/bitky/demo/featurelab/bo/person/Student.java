package cc.bitky.demo.featurelab.bo.person;

import cc.bitky.demo.featurelab.bo.work.Work;
import cc.bitky.demo.featurelab.bo.work.WorkEnum;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
public class Student extends Person {

    public Student(String name, int age) {
        super(name, age);
        this.work = Work.builder().workEnum(WorkEnum.STUDY).build();
    }

    @Override
    public Work getWork() {
        return work;
    }
}
