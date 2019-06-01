package cc.bitky.demo.featurelab.util;

import cc.bitky.demo.featurelab.util.bo.LogPackage;
import cc.bitky.demo.featurelab.util.bo.LogPayLoad;
import cc.bitky.demo.featurelab.util.threadlocal.KyThreadLocal;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

/**
 * @author limingliang
 */
public class PayLog {

    private final Logger log;

    private PayLog(Logger logger) {
        log = logger;
    }

    public static PayLog of(Logger logger) {
        return new PayLog(logger);
    }

    public void error(Throwable e, Object errorMsg) {
        error(e, errorMsg, getDefaultId(), getDefaultTraceNo());
    }

    public void error(Throwable e, Object errorMsg, String id) {
        error(e, errorMsg, id, getDefaultTraceNo());
    }

    /**
     * 打印 error 日志
     * 注：根据异常进行判断，智能降低报警级别，所以可能最终打印 warn 日志
     */
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
