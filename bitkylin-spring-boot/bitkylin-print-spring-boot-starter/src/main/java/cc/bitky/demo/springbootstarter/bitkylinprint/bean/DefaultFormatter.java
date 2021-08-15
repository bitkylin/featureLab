package cc.bitky.demo.springbootstarter.bitkylinprint.bean;

import cc.bitky.demo.springbootstarter.bitkylinprint.api.Formatter;

/**
 * @author bitkylin
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return "「Default」" + object;
    }
}
