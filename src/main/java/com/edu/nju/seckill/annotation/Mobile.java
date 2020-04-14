package com.edu.nju.seckill.annotation;

import com.edu.nju.seckill.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author LiuWen
 * @version 1.0
 * @name Mobile
 * @description 手机格式注解
 * @date 2020-4-15 0:45
 */
@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Mobile {
    boolean required() default true;

    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
