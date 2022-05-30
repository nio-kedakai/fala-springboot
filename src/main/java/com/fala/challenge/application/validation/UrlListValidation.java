package com.fala.challenge.application.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fala.challenge.application.validator.UrlListValidator;


@Documented
@Constraint(validatedBy = {UrlListValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlListValidation {
    String message() default "Invalid URL List";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
