package cc.bitky.demo.featurelab.service.guavalab;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author liMingLiang
 * @date 2019-05-11
 */
public class MyGuava {
    public static void paramCheck() {
        Preconditions.checkState(true);
        Preconditions.checkNotNull("");
    }

    public static void main(String[] args) {
        sub(Long.MAX_VALUE, 500);
        sub(Long.MAX_VALUE / 100, 500);

        System.out.println();

        sub(456000L, 500);
        sub(343278456495347L, 500);
    }

    private static void sub(Long aLong, int sub) {
        Double aDouble = aLong.doubleValue();
        aDouble = aDouble - sub;
        System.out.println(aLong - aDouble);

        Long bLong = aDouble.longValue();
        System.out.println(aLong - bLong);
    }

    public void test() {
    }
}
