package cc.bitky.webserver.exception;

/**
 * ZIP 创建失败异常
 */
public class ZipCreationException extends FileOperationException {

    private static final String DEFAULT_CODE = "ZIP_CREATION_FAILED";

    public ZipCreationException(String message) {
        super(DEFAULT_CODE, message);
    }

    public ZipCreationException(String message, Throwable cause) {
        super(DEFAULT_CODE, message, cause);
    }
}
