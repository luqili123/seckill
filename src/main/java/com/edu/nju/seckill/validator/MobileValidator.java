package com.edu.nju.seckill.validator;

import com.edu.nju.seckill.annotation.Mobile;
import com.edu.nju.seckill.utils.ValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author LiuWen
 * @version 1.0
 * @name MobileValidator
 * @description 手机格式校验器
 * @date 2020-4-15 0:43
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    private boolean required = false;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtils.isMobile(s);
        }
        return true;
    }
    @Override
    public void initialize(Mobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }
}
