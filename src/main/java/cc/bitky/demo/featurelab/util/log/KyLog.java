package cc.bitky.demo.featurelab.util.log;

import cc.bitky.demo.featurelab.util.log.bo.LogPackage;
import cc.bitky.demo.featurelab.util.log.bo.LogPayLoad;
import cc.bitky.demo.featurelab.util.threadlocal.KyThreadLocal;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

/**
 * @author limingliang
 */
public class KyLog {

    private final Logger log;

    private KyLog(Logger logger) {
        log = logger;
    }

    public static KyLog of(Logger logger) {
        return new KyLog(logger);
    }

    public void error(Throwable e, Object errorMsg) {
        error(e, errorMsg, getDefaultId(), getDefaultTraceNo());
    }

    public void error(Throwable e, Object errorMsg, String id) {
        error(e, errorMsg, id, getDefaultTraceNo());
    }

    public void error(Throwable e, Object errorMsg, String id, String traceId) {
        LogPackage logBo = LogPackage.builder()
                .traceNo(traceId)
                .id(id)
                .msg(errorMsg)
                .build();

        if (e != null) {
            getLogger().error(JSON.toJSONString(logBo), e);
        } else {
            getLogger().error(JSON.toJSONString(logBo));
        }
    }

    public void warn(Throwable e, Object warnMsg) {
        warn(e, warnMsg, getDefaultId(), getDefaultTraceNo());
    }

    public void warn(Throwable e, Object warnMsg, String id) {
        warn(e, warnMsg, id, getDefaultTraceNo());
    }

    public void warn(Throwable e, Object warnMsg, String id, String traceNo) {
        LogPackage logBo = LogPackage.builder()
                .traceNo(traceNo)
                .id(id)
                .msg(warnMsg)
                .build();

        if (e != null) {
            getLogger().warn(JSON.toJSONString(logBo), e);
        } else {
            getLogger().warn(JSON.toJSONString(logBo));
        }
    }

    /**
     * @param infoMsg 推荐实例化 {@link LogPayLoad}
     */
    public void info(Object infoMsg) {
        info(null, infoMsg, getDefaultId(), getDefaultTraceNo());
    }

    /**
     * @param infoMsg 推荐实例化 {@link LogPayLoad}
     */
    public void info(Object infoMsg, String id) {
        info(null, infoMsg, id, getDefaultTraceNo());
    }

    public void info(Object infoMsg, String id, String traceNo) {
        info(null, infoMsg, id, traceNo);
    }

    public void info(Throwable e, Object infoMsg, String id, String traceNo) {
        LogPackage logBo = LogPackage.builder()
                .traceNo(traceNo)
                .id(id)
                .msg(infoMsg)
                .build();

        if (e != null) {
            getLogger().info(JSON.toJSONString(logBo), e);
        } else {
            getLogger().info(JSON.toJSONString(logBo));
        }
    }

    private String getDefaultId() {
        return KyThreadLocal.getUid();
    }

    private String getDefaultTraceNo() {
        return KyThreadLocal.getTraceId();
    }

    private Logger getLogger() {
        return log;
    }
}
