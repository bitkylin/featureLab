package cc.bitky.featurelab.casperlab.tools.modelmapper.req;

import cc.bitky.featurelab.casperlab.constants.CategoryEnum;
import cc.bitky.featurelab.casperlab.constants.TestStatusEnum;
import cc.bitky.featurelab.casperlab.tools.modelmapper.bo.Work;
import com.alibaba.fastjson.JSONObject;
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
public class ModelMapperReq {

    private Date expireDate;

    private Instant expireInstant;

    private String name;

    private Integer price;

    private CategoryEnum category;

    private Work work;

    private JSONObject extend;

    private String status1;

    private TestStatusEnum status2;
}
