package cc.bitky.webserver.handler;

import cc.bitky.webserver.exception.FileAccessDeniedException;
import cc.bitky.webserver.exception.FileNotFoundException;
import cc.bitky.webserver.exception.DirectoryNotFoundException;
import cc.bitky.webserver.exception.FileOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理文件操作异常
     */
    @ExceptionHandler(FileOperationException.class)
    public ResponseEntity<Map<String, String>> handleFileOperationException(FileOperationException e) {
        log.warn("文件操作异常: code={}, message={}", e.getCode(), e.getMessage());

        HttpStatus status = switch (e) {
            case FileNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case FileAccessDeniedException ignored -> HttpStatus.FORBIDDEN;
            case DirectoryNotFoundException ignored -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_REQUEST;
        };

        return ResponseEntity.status(status)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "code", e.getCode(),
                        "message", e.getMessage()
                ));
    }

    /**
     * 处理参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("参数异常: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "code", "INVALID_ARGUMENT",
                        "message", e.getMessage()
                ));
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        log.error("未知异常", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(Map.of(
                        "code", "INTERNAL_ERROR",
                        "message", "服务器内部错误"
                ));
    }
}
