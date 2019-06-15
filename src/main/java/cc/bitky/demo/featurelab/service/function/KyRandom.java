package cc.bitky.demo.featurelab.service.function;

import cc.bitky.demo.featurelab.util.log.KyLog;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liMingLiang
 * @date 2019-06-01
 */
@Slf4j
public class KyRandom {

    private static final KyLog PAY_LOG = KyLog.of(log);

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            PAY_LOG.info(ThreadLocalRandom.current().nextInt(10));
        }
        PAY_LOG.info("----------------");
        for (int i = 0; i < 50; i++) {
            ThreadLocalRandom.current()
                    .ints()
                    .limit(50)
                    .forEach(PAY_LOG::info);
        }
    }
}
