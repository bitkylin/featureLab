package cc.bitky.demo.featurelab.util.log.bo;

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
    private String msg;
    private Object load;

    public static LogPayLoad of(String msg, Object load) {
        LogPayLoad self = new LogPayLoad();
        self.load = load;
        self.msg = msg;
        return self;
    }
}
