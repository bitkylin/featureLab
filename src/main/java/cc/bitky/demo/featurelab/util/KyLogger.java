package cc.bitky.demo.featurelab.util;

import cc.bitky.demo.featurelab.util.bo.LogBo;
import cc.bitky.demo.featurelab.util.exception.KyClientException;
import cc.bitky.demo.featurelab.util.threadlocal.KyThreadLocal;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author limingliang
 * @date 2018/9/3 15:52
 */
@Slf4j
public class KyLogger {

    private static final KyLogger INSTANCE = new KyLogger();

    private KyLogger() {
    }

    public static KyLogger getInstance() {
        return INSTANCE;
    }

    /**
     * 打印 error 日志
     * 注：根据异常进行判断，智能降低报警级别，所以可能最终打印 warn 日志
     */
    public void logError(Throwable e, String errorMsg, String uid, String requestId) {
        LogBo logBo = LogBo.builder()
                .traceId(requestId)
                .uid(uid)
                .msg(errorMsg)
                .build();

        if (e != null) {
            logNormalize(JSON.toJSONString(logBo), e);
        } else {
            log.error(JSON.toJSONString(logBo));
        }
    }

    /**
     * 打印正确级别的日志
     */
    private void logNormalize(String errorMsg, Throwable e) {
        if (e instanceof KyClientException) {
            log.warn(errorMsg, e);
        } else {
            log.error(errorMsg, e);
        }
    }

    public void logWarn(Throwable e, String warnMsg, String uid, String traceId) {
        LogBo logBo = LogBo.builder()
                .traceId(traceId)
                .uid(uid)
                .msg(warnMsg)
                .build();

        if (e != null) {
            log.warn(JSON.toJSONString(logBo), e);
        } else {
            log.warn(JSON.toJSONString(logBo));
        }
    }

    public void logError(Throwable e, String errorMsg) {
        logError(e, errorMsg, KyThreadLocal.getUid(), KyThreadLocal.getTraceId());
    }

    public void logError(Throwable e, String errorMsg, String uid) {
        logError(e, errorMsg, uid, KyThreadLocal.getTraceId());
    }

    public void logWarn(Throwable e, String warnMsg) {
        logWarn(e, warnMsg, KyThreadLocal.getUid(), KyThreadLocal.getTraceId());
    }

    public void logWarn(Throwable e, String warnMsg, String uid) {
        logWarn(e, warnMsg, uid, KyThreadLocal.getTraceId());
    }

    public void logInfo(Throwable e, String infoMsg, String uid, String traceId) {
        LogBo logBo = LogBo.builder()
                .traceId(traceId)
                .uid(uid)
                .msg(infoMsg)
                .build();

        if (e != null) {
            log.info(JSON.toJSONString(logBo), e);
        } else {
            log.info(JSON.toJSONString(logBo));
        }
    }

    public void logInfo(Throwable e, String infoMsg) {
        logInfo(e, infoMsg, KyThreadLocal.getUid(), KyThreadLocal.getTraceId());
    }

    public void logInfo(Throwable e, String infoMsg, String uid) {
        logInfo(e, infoMsg, uid, KyThreadLocal.getTraceId());
    }

}
