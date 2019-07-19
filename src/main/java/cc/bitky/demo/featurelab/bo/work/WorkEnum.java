package cc.bitky.demo.featurelab.bo.work;

import lombok.Getter;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
public enum WorkEnum {
    /**
     * WorkEnum
     */
    SLEEP("睡觉"),
    STUDY("学习"),
    WORK("工作"),
    COOK("做饭"),
    PLAY("玩耍");

    @Getter
    private String caption;

    WorkEnum(String caption) {
        this.caption = caption;
    }
}
