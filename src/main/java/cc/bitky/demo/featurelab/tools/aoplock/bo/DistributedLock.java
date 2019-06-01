package cc.bitky.demo.featurelab.tools.aoplock.bo;

import cc.bitky.demo.featurelab.util.PayLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Slf4j
@Component
public class DistributedLock {

    private static final PayLog PAY_LOG = PayLog.of(log);

    public void lock(String str) {
        PAY_LOG.info(null, "已加锁，key:" + str);
    }

    public void unlock(String str) {
        PAY_LOG.info(null, "已解锁，key:" + str);
    }
}
