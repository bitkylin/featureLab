package cc.bitky.demo.featurelab.config.propertybind;

import cc.bitky.demo.featurelab.bo.config.BitkyEnv;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@Data
@Component
@ConfigurationProperties(prefix = "cn.bitky.auto-bind")
public class PropertyBindConfig {

    private BitkyEnv env;
}
