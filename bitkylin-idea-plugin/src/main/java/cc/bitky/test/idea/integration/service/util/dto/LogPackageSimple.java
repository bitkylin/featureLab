package cc.bitky.test.idea.integration.service.util.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author liMingLiang
 */
@Data
public class LogPackageSimple implements LeLogTagResolver {

    /**
     * 可 JSON 序列化的对象负载
     */
    private Object msg;

    /**
     * 标签
     */
    private List<String> tagList;

    public void optimize() {
        optimizeTagList();
    }

    @Override
    public List<String> fetchTagList() {
        if (tagList == null) {
            tagList = Lists.newArrayList();
        }
        return tagList;
    }
}
