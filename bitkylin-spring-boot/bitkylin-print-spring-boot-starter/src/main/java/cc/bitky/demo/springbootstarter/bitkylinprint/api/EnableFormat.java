package cc.bitky.demo.springbootstarter.bitkylinprint.api;

import cc.bitky.demo.springbootstarter.bitkylinprint.config.FormatterEnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author bitkylin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FormatterEnableAutoConfiguration.class)
public @interface EnableFormat {
}
