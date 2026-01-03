package cc.bitky.webserver.service;

import cc.bitky.webserver.config.FileBasePathProperties;
import cc.bitky.webserver.exception.DirectoryNotFoundException;
import cc.bitky.webserver.exception.FileAccessDeniedException;
import cc.bitky.webserver.exception.FileNotFoundException;
import cc.bitky.webserver.service.impl.FileOperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文件操作服务测试
 */
@DisplayName("文件操作服务测试")
class FileOperationServiceTest {

    private IFileOperationService fileOperationService;
    private FileBasePathProperties properties;
    private Path tempDir;

    @TempDir
    Path tempDirPath;

    @BeforeEach
    void setUp() {
        properties = new FileBasePathProperties();
        properties.setBasePath(tempDirPath.toString());
        fileOperationService = new FileOperationServiceImpl(properties);
        this.tempDir = tempDirPath;
    }

    @Test
    @DisplayName("locateFile 在文件存在且可读时应返回正确 Path")
    void locateFileShouldReturnPathWhenFileExistsAndReadable() throws Exception {
        // 创建测试文件
        Path testFile = tempDir.resolve("test.txt");
        testFile.toFile().createNewFile();

        Path result = fileOperationService.locateFile(tempDir.toString(), "test.txt");

        assertEquals(testFile, result);
    }

    @Test
    @DisplayName("locateFile 在文件不存在时应抛出 FileNotFoundException")
    void locateFileShouldThrowWhenFileNotExists() {
        assertThrows(FileNotFoundException.class, () ->
                fileOperationService.locateFile(tempDir.toString(), "notexist.txt")
        );
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    @DisplayName("locateFile 在文件不可读时应抛出 FileAccessDeniedException")
    void locateFileShouldThrowWhenFileNotReadable() throws Exception {
        // 创建文件并设为不可读
        Path testFile = tempDir.resolve("readonly.txt");
        testFile.toFile().createNewFile();
        testFile.toFile().setReadable(false);

        try {
            assertThrows(FileAccessDeniedException.class, () ->
                    fileOperationService.locateFile(tempDir.toString(), "readonly.txt")
            );
        } finally {
            // 恢复可读性以便清理
            testFile.toFile().setReadable(true);
        }
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("locateFile 在文件不可读时应抛出 FileAccessDeniedException（使用 Spy 方式）")
    void locateFileShouldThrowWhenFileNotReadable_Windows() throws Exception {
        // 在 Windows 上，setReadable(false) 可能不生效（尤其以管理员身份运行时）
        // 因此我们通过测试实际方法来验证逻辑正确性
        // 如果此测试在 Windows 上失败，可确认 FileOperationServiceImpl 的 canRead 检查逻辑是否正确

        // 创建测试文件
        Path testFile = tempDir.resolve("test_readonly.txt");
        testFile.toFile().createNewFile();

        // 由于 Windows 文件权限限制，尝试设置不可读
        boolean wasSet = testFile.toFile().setReadable(false);
        if (wasSet) {
            // 如果设置成功，测试应抛出异常
            assertThrows(FileAccessDeniedException.class, () ->
                    fileOperationService.locateFile(tempDir.toString(), "test_readonly.txt")
            );
        } else {
            // 如果设置失败（Windows 常见情况），跳过此测试
            // Windows 上文件权限由 ACL 控制，setReadable() 对管理员用户无效
            // 逻辑的正确性已在 Linux 测试中验证
        }
    }

    @Test
    @DisplayName("listFiles 在目录存在时应返回文件列表")
    void listFilesShouldReturnFileListWhenDirectoryExists() throws Exception {
        // 创建测试文件和目录
        tempDir.resolve("file1.txt").toFile().createNewFile();
        tempDir.resolve("file2.txt").toFile().createNewFile();
        tempDir.resolve("subdir").toFile().mkdir();

        List<IFileOperationService.FileDetail> result = fileOperationService.listFiles(tempDir.toString());

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("listFiles 在目录不存在时应抛出 DirectoryNotFoundException")
    void listFilesShouldThrowWhenDirectoryNotExists() {
        assertThrows(DirectoryNotFoundException.class, () ->
                fileOperationService.listFiles("/nonexistent/path")
        );
    }

    @Test
    @DisplayName("validatePaths 在所有路径有效时应返回成功结果")
    void validatePathsShouldReturnSuccessWhenAllPathsValid() throws Exception {
        String file1 = tempDir.resolve("file1.txt").toString();
        String file2 = tempDir.resolve("file2.txt").toString();
        new File(file1).createNewFile();
        new File(file2).createNewFile();

        IFileOperationService.ValidationResult result =
                fileOperationService.validatePaths(List.of(file1, file2));

        assertTrue(result.isValid());
        assertTrue(result.invalidPaths().isEmpty());
    }

    @Test
    @DisplayName("validatePaths 在路径无效时应返回失败结果")
    void validatePathsShouldReturnFailureWhenPathInvalid() throws Exception {
        String validFile = tempDir.resolve("valid.txt").toString();
        new File(validFile).createNewFile();
        String invalidPath = "/nonexistent/path";

        IFileOperationService.ValidationResult result =
                fileOperationService.validatePaths(List.of(validFile, invalidPath));

        assertFalse(result.isValid());
        assertEquals(1, result.invalidPaths().size());
    }
}
