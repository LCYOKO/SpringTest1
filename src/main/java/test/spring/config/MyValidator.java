package test.spring.config;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @Author l
 * @Date 2021/1/6 9:59
 * @Version 1.0
 */
public class MyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
