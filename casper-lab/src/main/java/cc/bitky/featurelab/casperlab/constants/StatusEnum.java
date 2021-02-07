package cc.bitky.featurelab.casperlab.constants;

import lombok.Getter;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
public enum StatusEnum {
    /**
     *
     */
    SUCCESS("成功"),
    FAILURE("失败"),
    PROCESSING("处理中"),
    UNKNOWN("未知"),
    ;

    @Getter
    private final String caption;

    StatusEnum(String caption) {
        this.caption = caption;
    }
}
