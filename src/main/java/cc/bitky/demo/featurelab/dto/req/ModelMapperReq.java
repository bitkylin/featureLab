package cc.bitky.demo.featurelab.dto.req;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Data
public class ModelMapperReq {

    private Date expireDate;

    private String name;

    private Integer price;

    private String category;

    private JSONObject extend;

    private String status;
}
