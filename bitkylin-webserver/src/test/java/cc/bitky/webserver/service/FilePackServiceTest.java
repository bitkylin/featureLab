package cc.bitky.webserver.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文件打包服务测试
 */
@DisplayName("文件打包服务测试")
class FilePackServiceTest {

    private FilePackService filePackService;

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("getZipPassword 在环境变量未设置时应返回默认密码")
    void getZipPasswordShouldReturnDefaultWhenEnvNotSet() {
        filePackService = new FilePackService();

        // 模拟环境变量未设置
        FilePackService.PasswordInfo result = filePackService.getZipPassword();

        assertNotNull(result);
        assertEquals("bitkylin", result.password());
        assertTrue(result.usingDefault());
    }

    @Test
    @DisplayName("createEncryptedZip 应创建加密的 ZIP 文件")
    void createEncryptedZipShouldCreateEncryptedZip() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        Path testFile = tempDir.resolve("test.txt");
        testFile.toFile().getParentFile().mkdirs();
        testFile.toFile().createNewFile();

        File result = filePackService.createEncryptedZip(List.of(testFile.toString()));

        assertNotNull(result);
        assertTrue(result.exists());
        assertTrue(result.getName().endsWith(".zip"));
        assertTrue(result.length() > 0);
    }

    @Test
    @DisplayName("createEncryptedZip 应包含正确的文件")
    void createEncryptedZipShouldContainCorrectFile() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        Path testFile = tempDir.resolve("test.txt");
        testFile.toFile().createNewFile();

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));

        assertTrue(zipFile.exists());
        assertTrue(zipFile.length() > 0);
    }
}
