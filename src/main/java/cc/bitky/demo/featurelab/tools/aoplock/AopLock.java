package cc.bitky.demo.featurelab.tools.aoplock;

import java.lang.annotation.*;

/**
 * 自定义注解<p>
 * 用于自动设置分布式锁，传入固定值作为锁名，动态值作为锁的变量<p>
 * 例：<blockquote>@AopLock(fixed = "onLoanAudited",updated = "lendBiz.lendInput.payBo.orderNo")</blockquote>
 * 则注解的方法将以<blockquote>"LENDENGINE:onLoanAudited:{orderNo}"</blockquote>
 * 字符串作为key，设置分布式锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopLock {
    /**
     * 固定值，用于标识某一类分布式锁，作为锁名
     *
     */
    String fixed();

    /**
     * 需更新的值，用于标识唯一的分布式锁
     *
     */
    String updated();
}
