package cc.bitky.featurelab.casperlab.util.log.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liMingLiang
 * @date 2018-12-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogPackage {
    private String traceNo;
    private String id;
    private Object msg;
}
