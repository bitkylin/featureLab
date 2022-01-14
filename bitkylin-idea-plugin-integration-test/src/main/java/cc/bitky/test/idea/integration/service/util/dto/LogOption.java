package cc.bitky.test.idea.integration.service.util.dto;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * @author liMingLiang
 */
@Data
public class LogOption {

    private SerializerFeature[] features;

    public static LogOption of(SerializerFeature... features) {
        LogOption option = new LogOption();
        option.setFeatures(features);
        return option;
    }
}
