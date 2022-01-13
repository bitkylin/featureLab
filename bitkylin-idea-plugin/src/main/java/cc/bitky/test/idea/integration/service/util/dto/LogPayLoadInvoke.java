package cc.bitky.test.idea.integration.service.util.dto;

import lombok.Data;

/**
 * @author liMingLiang
 * @date 2020-05-06
 */
@Data
public class LogPayLoadInvoke {

    /**
     * 简短描述
     */
    private String msg;

    /**
     * 请求
     */
    private Object req;

    /**
     * 响应
     */
    private Object resp;

    public static LogPayLoadInvoke of(String msg, Object req, Object resp) {
        LogPayLoadInvoke self = new LogPayLoadInvoke();
        self.msg = msg;
        self.req = req;
        self.resp = resp;
        return self;
    }
}
