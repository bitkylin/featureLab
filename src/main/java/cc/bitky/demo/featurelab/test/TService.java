package cc.bitky.demo.featurelab.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Slf4j
public class TService {
    private static final ConcurrentMap<String, Integer> fileFlagMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        byte[] b = new byte[10];
        String s = new String(b);
        System.out.println();
    }

    /**
     * 元字符串转成Long类型
     * 单位分
     * "10.00" 转成 1000分
     * "10" 转成 1000分
     */
    public static Long yuanStr2FenLong(String yuan) {
        if (yuan == null) {
            return null;
        }
        yuan = StringUtils.replace(yuan, ",", "");
        if (yuan.contains(".")) {
            BigDecimal dy = new BigDecimal(yuan);
            return dy.multiply(new BigDecimal(100)).longValue();
        }
        return Long.parseLong(yuan) * 100;
    }
}
