package cc.bitky.demo.spring.beanlifecycle.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author limingliang
 */
@Slf4j
public class KyLog {

    private KyLog() {
    }

    @SuppressWarnings({"java:S3457", "java:S2629"})
    public static void log(int step, String msg) {
        log.info("LifeCycle「" + step + "」" + msg);
    }

    @SuppressWarnings({"java:S3457", "java:S2629"})
    public static void log(String msg) {
        log.info(msg);
    }

    @SuppressWarnings({"java:S3457", "java:S2629"})
    public static void log(Object obj) {
        log.info(obj.toString());
    }
}
