package cc.bitky.demo.featurelab.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

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
        System.out.println(JSON.toJSONString(null));
        System.out.println(JSON.toJSONString(null));

    }
}
