package cc.bitky.test.idea.integration.service.dto;

import com.google.common.collect.Sets;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author limingliang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SimbusinessKvInfo extends ApplicationKvInfo {

    private List<EnableSimbusiness> enableConfigs;

    @Override
    public Object getValue() {
        return enableConfigs;
    }

    @Override
    public Set<String> getConfigNames() {
        if (CollectionUtils.isEmpty(enableConfigs)) {
            return Sets.newHashSet();
        }
        return enableConfigs.stream().map(EnableSimbusiness::getConfig).collect(Collectors.toSet());
    }

}
