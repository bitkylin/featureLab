package cc.bitky.test.idea.integration.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author limingliang
 */
@Getter
@Setter
public class SysGroupDto implements IConfig {


    private Integer version;

    private String context;

    private String groupCode;

    private String groupType;

    private List<PropPairDto> propPairs = new ArrayList<>();

    @Override
    public String getKey() {
        return context + "+" + groupCode;
    }

    @Override
    public Object getValue() {
        return this;
    }

}
