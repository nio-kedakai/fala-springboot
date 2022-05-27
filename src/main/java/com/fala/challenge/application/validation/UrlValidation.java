package com.fala.challenge.application.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fala.challenge.application.validator.UrlValidator;


@Documented
@Constraint(validatedBy = {UrlValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlValidation {
    String message() default "Invalid Image URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
