package cc.bitky.webserver.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 配置测试
 */
@DisplayName("配置测试")
@SpringJUnitConfig
@EnableConfigurationProperties(FileBasePathProperties.class)
@TestPropertySource(properties = {
        "app.file.base-path=/test/path"
})
class FileBasePathPropertiesTest {

    @Autowired
    private FileBasePathProperties properties;

    @Test
    @DisplayName("应能正确绑定 base-path 配置")
    void shouldBindBasePathProperty() {
        assertEquals("/test/path", properties.getBasePath());
    }
}
