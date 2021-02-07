package cc.bitky.featurelab.casperlab.bo.person;

import cc.bitky.featurelab.casperlab.bo.work.Work;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
public interface IPerson {
    String getName();

    int getAge();

    Work getWork();
}
