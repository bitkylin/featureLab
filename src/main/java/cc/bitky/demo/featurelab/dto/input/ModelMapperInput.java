package cc.bitky.demo.featurelab.dto.input;

import cc.bitky.demo.featurelab.constants.StatusEnum;
import cc.bitky.demo.featurelab.dto.bo.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelMapperInput {

    private Date expireDate;

    private Instant expireInstant;

    private String name;

    private Integer price;

    private String category;

    private String extend;

    private Work work;

    private StatusEnum status1;

    private StatusEnum status2;
}
