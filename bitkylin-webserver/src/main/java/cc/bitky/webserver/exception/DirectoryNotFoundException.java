package cc.bitky.webserver.exception;

/**
 * 目录不存在异常
 */
public class DirectoryNotFoundException extends FileOperationException {

    private static final String DEFAULT_CODE = "DIRECTORY_NOT_FOUND";

    public DirectoryNotFoundException(String path) {
        super(DEFAULT_CODE, path);
    }

    public DirectoryNotFoundException(String path, Throwable cause) {
        super(DEFAULT_CODE, path, cause);
    }
}
