package cc.bitky.featurelab.casperlab.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
class KyException extends RuntimeException {

    private final Integer code;

    private final String developerMessage;

    KyException(Integer code, String message, String developerMessage, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.developerMessage = developerMessage;
    }

    KyException(Integer code, String message, String developerMessage) {
        super(message);
        this.code = code;
        this.developerMessage = developerMessage;
    }

    KyException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.developerMessage = null;
    }

    KyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.developerMessage = null;
    }
}
