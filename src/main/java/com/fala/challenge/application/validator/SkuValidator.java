package com.fala.challenge.application.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validation.SkuValidation;
import org.springframework.stereotype.Component;


@Component
public class SkuValidator implements ConstraintValidator<SkuValidation, String> {

    @Override
    public void initialize(SkuValidation sku) {
    }

    @Override
    public boolean isValid(String sku, ConstraintValidatorContext cxt) {
        String regex = "(F{1}A{1}L{1}-[1-9]{1}[0-9]{6,8}$)+";

        try {
            Pattern pattern = Pattern.compile(regex);

            if (sku != null) {
                Matcher matcher = pattern.matcher(sku);

                return matcher.matches();

            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
