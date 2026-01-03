package cc.bitky.webserver.exception;

/**
 * 文件访问被拒绝异常
 */
public class FileAccessDeniedException extends FileOperationException {

    private static final String DEFAULT_CODE = "ACCESS_DENIED";

    public FileAccessDeniedException(String path) {
        super(DEFAULT_CODE, path);
    }

    public FileAccessDeniedException(String path, Throwable cause) {
        super(DEFAULT_CODE, path, cause);
    }
}
