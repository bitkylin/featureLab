package cc.bitky.webserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件基准路径配置属性
 */
@Component
@ConfigurationProperties(prefix = "app.file")
public class FileBasePathProperties {

    private String basePath = System.getProperty("user.home");

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
