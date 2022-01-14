package cc.bitky.test.idea.integration.service.util;

import cc.bitky.test.idea.integration.service.util.dto.LogOption;
import cc.bitky.test.idea.integration.service.util.dto.LogPackageSimple;
import cc.bitky.test.idea.integration.service.util.dto.LogPayLoad;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * 日志打印工具类
 *
 * @author limingliang
 * @version 3.0
 */
public final class LELog {

    private LELog() {
    }

    private static final String TAG_DEFAULT = "LeLogTag";

    /**
     * @param errorMsgSupplier 推荐实例化 {@link LogPayLoad}
     */

    public static void debug(final Logger logger, final Supplier<Object> errorMsgSupplier) {
        debug(logger,null, errorMsgSupplier, null);
    }

    @SuppressWarnings("java:S2629")
    public static void debug(final Logger logger, final Throwable e, final Supplier<Object> errorMsgSupplier, LogOption option) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        LogPackageSimple logBo = buildLogPackageSimple(errorMsgSupplier.get());
        String msg = serialize(logBo, option);
        if (e != null) {
            logger.debug(msg, e);
        } else {
            logger.debug(msg);
        }
    }

    /**
     * @param errorMsgSupplier 推荐实例化 {@link LogPayLoad}
     */
    public static void info(final Logger logger, final Supplier<Object> errorMsgSupplier) {
        info(logger, null, errorMsgSupplier, null);
    }

    @SuppressWarnings("java:S2629")
    public static void info(final Logger logger, final Throwable e, final Supplier<Object> errorMsgSupplier, LogOption option) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        LogPackageSimple logBo = buildLogPackageSimple(errorMsgSupplier.get());
        String msg = serialize(logBo, option);
        if (e != null) {
            logger.info(msg, e);
        } else {
            logger.info(msg);
        }
    }

    /**
     * @param errorMsgSupplier 推荐实例化 {@linkplain LogPayLoad}
     */
    @SuppressWarnings("java:S2629")
    public static void warn(final Logger logger, final Throwable e, final Supplier<Object> errorMsgSupplier) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        LogPackageSimple logBo = buildLogPackageSimple(errorMsgSupplier.get());

        if (e != null) {
            logger.warn(JSON.toJSONString(logBo), e);
        } else {
            logger.warn(JSON.toJSONString(logBo));
        }
    }

    /**
     * @param errorMsgSupplier 推荐实例化 {@linkplain LogPayLoad}
     */
    @SuppressWarnings("java:S2629")
    public static void error(final Logger logger, final Throwable e, final Supplier<Object> errorMsgSupplier) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        LogPackageSimple logBo = buildLogPackageSimple(errorMsgSupplier.get());

        if (e != null) {
            logger.error(JSON.toJSONString(logBo), e);
        } else {
            logger.error(JSON.toJSONString(logBo));
        }
    }

    private static String serialize(LogPackageSimple logBo, LogOption option) {
        if (option != null) {
            if (option.getFeatures() != null) {
                return JSON.toJSONString(logBo, option.getFeatures());
            }
        }
        return JSON.toJSONString(logBo);
    }

    private static LogPackageSimple buildLogPackageSimple(Object obj) {
        LogPackageSimple logPackage = new LogPackageSimple();
        logPackage.addTag(TAG_DEFAULT);
        logPackage.setMsg(obj);
        logPackage.optimize();
        return logPackage;
    }
}
