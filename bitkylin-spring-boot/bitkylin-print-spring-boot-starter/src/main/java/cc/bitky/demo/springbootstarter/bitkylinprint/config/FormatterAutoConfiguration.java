package cc.bitky.demo.springbootstarter.bitkylinprint.config;

import cc.bitky.demo.springbootstarter.bitkylinprint.api.Formatter;
import cc.bitky.demo.springbootstarter.bitkylinprint.bean.BitkylinFormatter;
import cc.bitky.demo.springbootstarter.bitkylinprint.bean.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bitkylin
 */
@Configuration
@ConditionalOnBean(FormatterEnableAutoConfiguration.class)
public class FormatterAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "formatter", name = "enabled", havingValue = "true")
    public Formatter bitkylinFormatter() {
        return new BitkylinFormatter();
    }

    @Bean
    @ConditionalOnMissingBean(name = {"bitkylinFormatter"})
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
