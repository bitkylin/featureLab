package cc.bitky.demo.featurelab.tools.aoplock;

import cc.bitky.demo.featurelab.util.KyLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author liMingLiang
 * @date 2018/10/22
 */
@Component
@Slf4j
public class AnnotationResolver {

    private static final KyLog PAY_LOG = KyLog.of(log);
    /**
     * 复杂表达式解析器
     * 能解析类似 #{pojo.field} 或者 {pojoParent.pojoChild.field} 的表达式<p>
     */
    private static String complexResolver(JoinPoint joinPoint, String str) throws Exception {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String[] strs = str.split("\\.");
        for (int i = 0; i < names.length; i++) {
            if (strs[0].equals(names[i])) {
                return getValue(args[i], 0, strs);
            }
        }
        return null;
    }

    private static String getValue(Object obj, int index, String[] strs) throws Exception {
        while (obj != null && index < strs.length - 1) {
            Method method = obj.getClass().getDeclaredMethod(getMethodName(strs[index + 1]), null);
            obj = method.invoke(obj);
            index++;
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static String getMethodName(String name) {
        return "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }

    /**
     * 简单表达式解析器
     * 能解析类似 #{field} 的表达式<p>
     */
    private static String simpleResolver(JoinPoint joinPoint, String str) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] names = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < names.length; i++) {
            if (str.equals(names[i])) {
                return args[i].toString();
            }
        }
        return null;
    }

    /**
     * 解析注解上的值<p>
     * 根据表达式 #{field}、#{pojo.field}、{pojoParent.pojoChild.field} 等解析方法的入参对象，并获取对象中指定字段的值<p>
     * 例1：方法的签名为：
     * <blockquote>temp(String name)</blockquote>
     * 若要取出name变量引用的字符串，可配置注解为：
     * <blockquote>@AopLock(fixed = "lockName", updated = "#{name}")</blockquote>
     * 例2：方法的签名为：
     * <blockquote>temp(Person person)</blockquote>
     * 若要取出person对象中的name字段，可配置注解为：
     * <blockquote>@AopLock(fixed = "findPersonName", updated = "#{person.name}")</blockquote>
     * 注1：通过以上表达式解析时，字段必须有getter方法<p>
     * 注2：pojo中，通过表达式取出的字段，其字段名必须为英文字母<p>
     * 注3：AOP注解只可用于public方法<p>
     * 注4：AOP注解不可用于同一个Class中，两方法之间的调用<p>
     *
     * @param joinPoint
     * @param str       需要解析的字符串
     * @return
     */
    public String resolver(JoinPoint joinPoint, String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String value = null;
        if (str.matches("#\\{\\D*}")) {
            // 如果匹配上了#{},则取出指定字段的值，并以字符串返回
            String newStr = str.replaceAll("#\\{", "").replaceAll("}", "");
            try {
                if (newStr.contains(".")) {
                    value = complexResolver(joinPoint, newStr);
                } else {
                    value = simpleResolver(joinPoint, newStr);
                }
            } catch (Exception e) {
                PAY_LOG.error(e, "自定义注解AopLock表达式解析失败");
            }
        } else {
            // 作为普通字符串
            value = str;
        }
        return value;
    }
}
