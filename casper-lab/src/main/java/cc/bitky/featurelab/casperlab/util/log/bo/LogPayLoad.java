package cc.bitky.featurelab.casperlab.util.log.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liMingLiang
 * @date 2019-05-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogPayLoad {

    /**
     * 简短描述
     */
    private String msg;

    /**
     * 对象负载
     */
    private Object load;

    public static LogPayLoad of(String msg, Object load) {
        LogPayLoad self = new LogPayLoad();
        self.load = load;
        self.msg = msg;
        return self;
    }
}
