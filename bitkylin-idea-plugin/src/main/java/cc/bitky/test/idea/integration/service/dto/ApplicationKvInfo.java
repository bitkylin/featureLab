package cc.bitky.test.idea.integration.service.dto;

import lombok.Data;

/**
 * @author limingliang
 */
@Data
public abstract class ApplicationKvInfo implements IConfig {
    private ConfigType configType;
    private String application;
    private String env;
    private Integer version;

    @Override
    public String getKey() {
        return configType.name() + application + env;
    }

    @Override
    public Integer getVersion() {
        return version;
    }
}
