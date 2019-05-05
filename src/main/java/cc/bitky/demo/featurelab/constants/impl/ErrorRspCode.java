package cc.bitky.demo.featurelab.constants.impl;

import cc.bitky.demo.featurelab.constants.IErrorRepCode;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
public enum ErrorRspCode implements IErrorRepCode {
    ;

    private int code;
    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
