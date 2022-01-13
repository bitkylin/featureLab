package cc.bitky.test.idea.integration.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author limingliang
 */
@Data
public class EnableSimbusiness {
    private String config;
    private String configName;
    private String type;
    private Boolean dynamic;
    private List<EnableSimbusinessValue> values;
    private List<EnabledRule> rules;
    //版本号
    private Integer version;
}