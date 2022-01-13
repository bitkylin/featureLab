package cc.bitky.test.idea.integration.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author limingliang
 */
@Data
public class EnabledRule {
    private String rule;
    private Set<String> customParams;
    private Integer sort;
    private Integer version;
}
