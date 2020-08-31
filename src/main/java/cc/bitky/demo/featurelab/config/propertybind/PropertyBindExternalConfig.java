package cc.bitky.demo.featurelab.config.propertybind;

import cc.bitky.demo.featurelab.bo.config.BitkyEnv;
import cc.bitky.demo.featurelab.bo.config.BitkyEnvExternal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@Getter
@Setter
@Component
@PropertySource(value = "classpath:properties/external.properties")
@ConfigurationProperties(prefix = "cn.bitky.auto-bind-ext")
public class PropertyBindExternalConfig {

    private String value;

    private BitkyEnv env;

    private BitkyEnvExternal external;
}
