package cc.bitky.featurelab.casperlab.bo.person;

import cc.bitky.featurelab.casperlab.bo.work.WorkEnum;
import cc.bitky.featurelab.casperlab.bo.work.Work;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
public class Engineer extends Person {

    public Engineer(String name, int age) {
        super(name, age);
        this.work = Work.builder().workEnum(WorkEnum.WORK).build();
    }

    @Override
    public Work getWork() {
        return work;
    }
}
