package cc.bitky.featurelab.casperlab.service.java.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liMingLiang
 * @date 2020/8/29
 */
public class BitKylinTypeTest<T, V extends @BitkylinCustom Number & Serializable> {

    public T t;
    public V v;
    public List<T> list = new ArrayList<>();
    public Map<String, T> map = new HashMap<>();

    public T[] tArray;
    public List<T>[] ltArray;

    public BitKylinTypeTest testClass;
    public BitKylinTypeTest<T, Integer> testClass2;

    public Map<? super String, ? extends Number> mapWithWildcard;

    private Number number;

    /**
     * 泛型构造函数,泛型参数为X
     */
    public <X extends Number> BitKylinTypeTest(X x, T t) {
        number = x;
        this.t = t;
    }

    /**
     * 泛型方法，泛型参数为Y
     */
    public <Y extends T> void method(Y y) {
        t = y;
    }
}
