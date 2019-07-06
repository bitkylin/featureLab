package cc.bitky.demo.featurelab.dto.req;

import cc.bitky.demo.featurelab.constants.CategoryEnum;
import cc.bitky.demo.featurelab.constants.TestStatusEnum;
import cc.bitky.demo.featurelab.dto.bo.Work;
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
