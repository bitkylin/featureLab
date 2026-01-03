package cc.bitky.webserver.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 异常体系测试
 */
@DisplayName("异常体系测试")
class FileOperationExceptionTest {

    @Test
    @DisplayName("异常基类应能携带错误码和消息")
    void exceptionShouldCarryCodeAndMessage() {
        FileOperationException exception = new FileOperationException("FILE_NOT_FOUND", "文件不存在");
        assertEquals("FILE_NOT_FOUND", exception.getCode());
        assertEquals("文件不存在", exception.getMessage());
    }

    @Test
    @DisplayName("异常应支持 cause 参数")
    void exceptionShouldSupportCause() {
        Throwable cause = new RuntimeException("原始异常");
        FileOperationException exception = new FileOperationException("ERROR", "错误", cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    @DisplayName("FileNotFoundException 默认错误码应为 FILE_NOT_FOUND")
    void fileNotFoundExceptionShouldHaveDefaultCode() {
        FileNotFoundException exception = new FileNotFoundException("/path/to/file");
        assertEquals("FILE_NOT_FOUND", exception.getCode());
        assertEquals("/path/to/file", exception.getMessage());
    }

    @Test
    @DisplayName("FileAccessDeniedException 默认错误码应为 ACCESS_DENIED")
    void fileAccessDeniedExceptionShouldHaveDefaultCode() {
        FileAccessDeniedException exception = new FileAccessDeniedException("/path/to/file");
        assertEquals("ACCESS_DENIED", exception.getCode());
    }

    @Test
    @DisplayName("DirectoryNotFoundException 默认错误码应为 DIRECTORY_NOT_FOUND")
    void directoryNotFoundExceptionShouldHaveDefaultCode() {
        DirectoryNotFoundException exception = new DirectoryNotFoundException("/path/to/dir");
        assertEquals("DIRECTORY_NOT_FOUND", exception.getCode());
    }

    @Test
    @DisplayName("PathValidationException 默认错误码应为 PATH_VALIDATION_FAILED")
    void pathValidationExceptionShouldHaveDefaultCode() {
        PathValidationException exception = new PathValidationException("路径无效");
        assertEquals("PATH_VALIDATION_FAILED", exception.getCode());
    }

    @Test
    @DisplayName("ZipCreationException 默认错误码应为 ZIP_CREATION_FAILED")
    void zipCreationExceptionShouldHaveDefaultCode() {
        ZipCreationException exception = new ZipCreationException("创建失败");
        assertEquals("ZIP_CREATION_FAILED", exception.getCode());
    }
}
