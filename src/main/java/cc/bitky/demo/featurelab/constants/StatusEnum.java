package cc.bitky.demo.featurelab.constants;

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
    PROCESS("处理中"),
    UNKNOWN("未知"),
    ;

    private String caption;

    StatusEnum(String caption) {
        this.caption = caption;
    }
}
