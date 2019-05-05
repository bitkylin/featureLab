package cc.bitky.demo.featurelab.util.threadlocal;

import lombok.Data;
import org.springframework.core.NamedThreadLocal;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Data
public class KyThreadLocal {

    private static final String UID = "uid";
    private static final String TRACE_ID = "traceId";

    private static ThreadLocal<String> uid = new NamedThreadLocal<>(UID);

    private static ThreadLocal<String> traceId = new NamedThreadLocal<>(TRACE_ID);

    private static void set(String uid, String traceId) {
        setUid(uid);
        setTraceId(traceId);
    }

    public static void remove() {
        KyThreadLocal.uid.remove();
        KyThreadLocal.traceId.remove();
    }

    public static String getUid() {
        return uid.get();
    }

    public static void setUid(String uid) {
        KyThreadLocal.uid.set(uid);
    }

    public static String getTraceId() {
        return traceId.get();
    }

    public static void setTraceId(String traceId) {
        KyThreadLocal.traceId.set(traceId);
    }
}
