package cc.bitky.transfer.account.common.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Configuration
public class BItkyConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setConfiguration(configuration);
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}
