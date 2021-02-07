package cc.bitky.featurelab.casperlab.service.games;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

/**
 * 求笛卡尔积
 *
 * @author liMingLiang
 * @date 2019-05-11
 */
@Slf4j
public class CartesianProduct {

    public static void main(String[] args) {

        reduce(Lists.newArrayList(1, 2, 3, 4),
                Lists.newArrayList("a", "b", "c"));
    }

    private static <E, F> void reduce(List<E> a, List<F> b) {
        a.stream().flatMap(i -> b.stream()
                .map(j -> new ImmutablePair<>(i, j)))
                .forEach(item -> log.info(item.left + ":" + item.right));
    }
}
