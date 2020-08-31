package cc.bitky.demo.featurelab.service.java.generic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author liMingLiang
 * @date 2020/8/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ANNOTATION_TYPE, CONSTRUCTOR, FIELD,
        METHOD, PACKAGE, PARAMETER, TYPE, TYPE_PARAMETER, TYPE_USE})
public @interface BitkylinCustom {
}
