package cc.bitky.featurelab.casperlab.service.springlab;

import cc.bitky.featurelab.casperlab.config.propertybind.PropertyBindConfig;
import cc.bitky.featurelab.casperlab.config.propertybind.PropertyBindExternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@Service
public class AutoConfigService implements EnvironmentAware {

    @Autowired
    private PropertyBindConfig propertyBindConfig;

    @Autowired
    private PropertyBindExternalConfig propertyBindExternalConfig;
    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public PropertyBindConfig getPropertyBindConfig() {
        return propertyBindConfig;
    }

    public PropertyBindExternalConfig getPropertyBindExternalConfig() {
        return propertyBindExternalConfig;
    }
}
