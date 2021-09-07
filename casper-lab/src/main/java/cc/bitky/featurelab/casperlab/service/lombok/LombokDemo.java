package cc.bitky.featurelab.casperlab.service.lombok;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author limingliang
 */
@Slf4j
public class LombokDemo {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        log.info("test-start");
        test2();
    }

    private static void test2() {
        test3();
    }

    @SneakyThrows(IOException.class)
    private static void test3() {
        throw new IOException("test-ex");
    }
}
