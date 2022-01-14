package cc.bitky.test.idea.integration.service.util.dto;

import lombok.Data;

/**
 * @author liMingLiang
 */
@Data
public class LogPayLoad {

    /**
     * 简短描述
     */
    private String msg;

    /**
     * 对象负载
     */
    private Object load;

    /**
     * 对象负载2
     */
    private Object load2;

    /**
     * 对象负载3
     */
    private Object load3;

    public static LogPayLoad of(String msg, Object load) {
        LogPayLoad self = new LogPayLoad();
        self.msg = msg;
        self.load = load;
        return self;
    }

    public static LogPayLoad of(String msg, Object load, Object load2) {
        LogPayLoad self = LogPayLoad.of(msg, load);
        self.load2 = load2;
        return self;
    }

    public static LogPayLoad of(String msg, Object load, Object load2, Object load3) {
        LogPayLoad self = LogPayLoad.of(msg, load, load2);
        self.load3 = load3;
        return self;
    }
}
