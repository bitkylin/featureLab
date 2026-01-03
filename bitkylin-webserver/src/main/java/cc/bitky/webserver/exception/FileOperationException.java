package cc.bitky.webserver.exception;

/**
 * 文件操作异常基类
 */
public class FileOperationException extends RuntimeException {

    private final String code;

    public FileOperationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public FileOperationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
