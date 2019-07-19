package cc.bitky.demo.featurelab.test;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author liMingLiang
 * @date 2019-07-13
 */
public class SimpleAopService extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return super.getClassFilter();
    }
}
