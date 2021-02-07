package cc.bitky.featurelab.casperlab.constants;

import lombok.Getter;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
public enum TestStatusEnum {
    /**
     *
     */
    SUCCESS("成功"),
    FAILURE("失败"),
    PROCESS("处理中"),
    UNKNOWN2("未知"),
    ;

    @Getter
    private final String caption;

    TestStatusEnum(String caption) {
        this.caption = caption;
    }
}
