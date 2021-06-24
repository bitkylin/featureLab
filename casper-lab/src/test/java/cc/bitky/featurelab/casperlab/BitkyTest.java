package cc.bitky.featurelab.casperlab;

import com.google.common.collect.Lists;
import org.assertj.core.util.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author limingliang
 */
public class BitkyTest {
    public static void main(String[] args) {
        Map<String, Object> map1 = Maps.newHashMap("age", 19);
        Map<String, Object> map2 = Maps.newHashMap("age", 24);
        Map<String, Object> map3 = Maps.newHashMap("age", 21);
        List<Map<String, Object>> list = Lists.newArrayList(map1, map2, map3);

        List<Map<String, Object>> list2 = list.stream().sorted((item1, item2) -> (int) item2.get("age") - (int) item1.get("age"))
                .collect(Collectors.toList());
        System.out.println();
    }
}
