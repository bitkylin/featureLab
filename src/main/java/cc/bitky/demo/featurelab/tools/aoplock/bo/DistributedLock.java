package cc.bitky.demo.featurelab.tools.aoplock.bo;

import cc.bitky.demo.featurelab.util.KyLogger;
import org.springframework.stereotype.Component;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Component
public class DistributedLock {

    public void lock(String str) {
        KyLogger.getInstance().logInfo(null, "已加锁，key:" + str);
    }

    public void unlock(String str) {
        KyLogger.getInstance().logInfo(null, "已解锁，key:" + str);
    }
}
