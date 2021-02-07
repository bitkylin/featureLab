package cc.bitky.featurelab.casperlab.util;

import cc.bitky.featurelab.casperlab.constants.IErrorRepCode;
import cc.bitky.featurelab.casperlab.util.exception.KyClientException;
import cc.bitky.featurelab.casperlab.util.exception.KyServerException;

/**
 * @author limingliang
 */
public class KyExceptionUtils {

    public static KyServerException newServerExceptionWithDevMsg(IErrorRepCode errorRspCode, String msg) {
        if (errorRspCode != null) {
            return new KyServerException(errorRspCode.getCode(), errorRspCode.getMsg(), msg);
        }
        return new KyServerException();
    }

    public static KyClientException newClientExceptionWithDevMsg(IErrorRepCode errorRspCode, String msg) {
        if (errorRspCode != null) {
            return new KyClientException(errorRspCode.getCode(), errorRspCode.getMsg(), msg);
        }
        return new KyClientException();
    }
}
