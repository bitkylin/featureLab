package cc.bitky.webserver.exception;

/**
 * 路径校验失败异常
 */
public class PathValidationException extends FileOperationException {

    private static final String DEFAULT_CODE = "PATH_VALIDATION_FAILED";

    public PathValidationException(String message) {
        super(DEFAULT_CODE, message);
    }

    public PathValidationException(String message, Throwable cause) {
        super(DEFAULT_CODE, message, cause);
    }
}
