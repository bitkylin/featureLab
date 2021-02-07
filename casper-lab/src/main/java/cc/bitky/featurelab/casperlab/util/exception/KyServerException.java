package cc.bitky.featurelab.casperlab.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KyServerException extends KyException {

    private static final String TITLE = "clientError";

    public KyServerException(Integer code, String message, String developerMessage, Throwable cause) {
        super(code, message, developerMessage, cause);
    }

    public KyServerException(Integer code, String message, String developerMessage) {
        super(code, message, developerMessage);
    }

    public KyServerException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public KyServerException(Integer code, String message) {
        super(code, message);
    }

    public KyServerException() {
        super(500, TITLE);
    }
}
