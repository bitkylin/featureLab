package cc.bitky.demo.featurelab.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KyClientException extends KyException {

    private static final String TITLE = "clientError";

    public KyClientException(Integer code, String message, String developerMessage, Throwable cause) {
        super(code, message, developerMessage, cause);
    }

    public KyClientException(Integer code, String message, String developerMessage) {
        super(code, message, developerMessage);
    }

    public KyClientException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public KyClientException(Integer code, String message) {
        super(code, message);
    }

    public KyClientException() {
        super(400, TITLE);
    }
}
