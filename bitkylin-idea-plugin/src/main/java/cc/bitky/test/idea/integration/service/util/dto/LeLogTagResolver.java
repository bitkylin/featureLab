package cc.bitky.test.idea.integration.service.util.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author limingliang
 */
public interface LeLogTagResolver {

    List<String> fetchTagList();

    void setTagList(List<String> tagList);

    default void optimizeTagList() {
        List<String> tagList = fetchTagList().stream().map(item -> {
            if (StringUtils.startsWith(item, "「")) {
                return item;
            }
            return "「" + item + "」";
        }).collect(Collectors.toList());
        setTagList(tagList);
    }

    default void addTag(String tag) {
        List<String> tagList = fetchTagList();
        tagList.add(tag);
    }
}
