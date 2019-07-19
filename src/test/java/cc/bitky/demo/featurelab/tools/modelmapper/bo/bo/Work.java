package cc.bitky.demo.featurelab.tools.modelmapper.bo.bo;

import cc.bitky.demo.featurelab.constants.StatusEnum;
import cc.bitky.demo.featurelab.constants.WorkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liMingLiang
 * @date 2019-07-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private WorkType type;
    private String desc;
    private Date time;
    private StatusEnum statusEnum;
}
