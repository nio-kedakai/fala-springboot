package com.fala.challenge.application.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fala.challenge.application.validator.SkuValidator;


@Documented
@Constraint(validatedBy = {SkuValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkuValidation {
    String message() default "Invalid SKU";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
