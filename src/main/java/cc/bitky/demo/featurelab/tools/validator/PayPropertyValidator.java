package cc.bitky.demo.featurelab.tools.validator;

import cc.bitky.demo.featurelab.util.log.KyLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;

/**
 * @author liMingLiang
 * @date 2019-05-19
 */
@Slf4j
@Component
public class PayPropertyValidator {

    private static final KyLog PAY_LOG = KyLog.of(log);

    private final SmartValidator validator;

    public PayPropertyValidator(SmartValidator validator) {
        this.validator = validator;
    }

    /**
     * 校验入参
     */
    public void validate(Object obj) {
        BindingResult bindingResult = new BeanPropertyBindingResult(obj, obj.getClass().getName());
        validator.validate(obj, bindingResult);
        if (CollectionUtils.isEmpty(bindingResult.getFieldErrors())) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String errorMsg = fieldError.getDefaultMessage();
            if (StringUtils.isNotBlank(errorMsg)) {
                builder.append(errorMsg);
            } else {
                builder.append(fieldError.getField()).append(" > ").append(fieldError.getCode());
            }
            builder.append(";");
        }
        RuntimeException exception = new IllegalArgumentException(builder.toString());
        PAY_LOG.warn(exception, obj);
        throw exception;
    }

    /**
     * 返回已校验的入参
     */
    public <T> T requireVerified(T obj) {
        validate(obj);
        return obj;
    }
}
