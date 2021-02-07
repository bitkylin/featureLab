package cc.bitky.featurelab.casperlab.tools.modelmapper.input;

import cc.bitky.featurelab.casperlab.bo.work.Work;
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
public class PersonInput {
    private String name;
    private Integer age;
    private Work work;
}
