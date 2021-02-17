package cc.bitky.mq.server.exception;

/**
 * @author liMingLiang
 */
public class KyMqException extends RuntimeException {

    public KyMqException(String message) {
        super(message);
    }

    public KyMqException(String message, Throwable cause) {
        super(message, cause);
    }

    public KyMqException(Throwable cause) {
        super(cause);
    }
}
