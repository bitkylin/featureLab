package cc.bitky.featurelab.casperlab.tools.modelmapper.req;

import cc.bitky.featurelab.casperlab.tools.modelmapper.bo.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liMingLiang
 * @date 2019-07-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonReq {
    private String name;
    private Integer age;
    private Work work;
}
