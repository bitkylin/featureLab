package cc.bitky.test.idea.integration.service.dto;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author limingliang
 */
public interface IConfig {
    /**
     * 主键
     *
     * @return key
     */
    String getKey();

    /**
     * 配置数据
     *
     * @return 配置value
     */
    Object getValue();

    /**
     * 数据版本
     *
     * @return 配置版本
     */
    Integer getVersion();

    default Set<String> getConfigNames(){
        return Sets.newHashSet(getKey());
    }
}
