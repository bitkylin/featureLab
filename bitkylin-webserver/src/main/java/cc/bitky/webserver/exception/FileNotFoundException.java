package cc.bitky.webserver.exception;

/**
 * 文件不存在异常
 */
public class FileNotFoundException extends FileOperationException {

    private static final String DEFAULT_CODE = "FILE_NOT_FOUND";

    public FileNotFoundException(String path) {
        super(DEFAULT_CODE, path);
    }

    public FileNotFoundException(String path, Throwable cause) {
        super(DEFAULT_CODE, path, cause);
    }
}
