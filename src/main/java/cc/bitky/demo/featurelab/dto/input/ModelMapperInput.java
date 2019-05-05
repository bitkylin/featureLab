package cc.bitky.demo.featurelab.dto.input;

import cc.bitky.demo.featurelab.constants.StatusEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.time.Instant;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Data
public class ModelMapperInput {

    private Instant expireDate;

    private String name;

    private Integer price;

    private String category;

    private JSONObject extend;

    private StatusEnum status;
}
