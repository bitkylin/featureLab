package cc.bitky.transfer.order.configuration;

import cc.bitky.transfer.account.common.BitkylinConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Configuration
public class BitkyConfiguration {

    @Bean(name = "bitkyconfig")
    @ConfigurationProperties(prefix = "bitkyconfig.demo")
    public BitkylinConfig bitkylinConfig() {
        return new BitkylinConfig();
    }
}
