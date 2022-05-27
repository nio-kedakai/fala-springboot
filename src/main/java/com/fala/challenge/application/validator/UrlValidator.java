package com.fala.challenge.application.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validation.UrlValidation;
import org.springframework.stereotype.Component;


@Component
public class UrlValidator implements ConstraintValidator<UrlValidation, String> {

    @Override
    public void initialize(UrlValidation url) {
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext cxt) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        try {
            Pattern pattern = Pattern.compile(regex);

            if (url != null) {
                Matcher matcher = pattern.matcher(url);
                return matcher.matches();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
