package cc.bitky.demo.featurelab.test;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Slf4j
public class TService {

    public static void main(String[] args) {
        String[] strings = test("aa", "bb");
        log.info(strings.toString());
    }

    private static <T> T[] test(T a, T b) {
        return toArray(a, b);
    }

    private static <T> T[] toArray(T... args) {
        return args;
    }
}
