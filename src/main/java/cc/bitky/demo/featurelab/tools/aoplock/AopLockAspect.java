package cc.bitky.demo.featurelab.tools.aoplock;

import cc.bitky.demo.featurelab.tools.aoplock.bo.DistributedLock;
import cc.bitky.demo.featurelab.util.KyLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liMingLiang
 * @date 2018/10/22
 */
@Slf4j
@Aspect
@Component
public class AopLockAspect {

    private static final KyLog PAY_LOG = KyLog.of(log);

    private final AnnotationResolver annotationResolver;

    private DistributedLock distributedLock;

    @Autowired
    public AopLockAspect(AnnotationResolver annotationResolver) {
        this.annotationResolver = annotationResolver;
    }

    @Autowired
    public void setDistributedLock(DistributedLock distributedLock) {
        this.distributedLock = distributedLock;
    }

    @Pointcut("@annotation(aopLock)")
    public void annotationLock(AopLock aopLock) {
    }

    @Around(value = "annotationLock(aopLock)", argNames = "joinPoint, aopLock")
    public Object around(ProceedingJoinPoint joinPoint, AopLock aopLock) throws Throwable {
        String fixed = annotationResolver.resolver(joinPoint, aopLock.fixed());
        String updated = annotationResolver.resolver(joinPoint, aopLock.updated());
        if (StringUtils.isBlank(updated) || StringUtils.isBlank(fixed)) {
            PAY_LOG.error(null, "分布式锁构建失败 " + ",fixed: " + fixed + ",updated: " + updated);
            return joinPoint.proceed();
        } else {
            String lockStr = "BIT_KY:" + fixed + ":" + updated;
            distributedLock.lock(lockStr);
            try {
                return joinPoint.proceed();
            } finally {
                distributedLock.unlock(lockStr);
            }
        }
    }
}
