package com.fala.challenge.application.validator;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validation.UrlListValidation;
import org.springframework.stereotype.Component;


@Component
public class UrlListValidator implements ConstraintValidator<UrlListValidation, List<String>> {

    @Override
    public void initialize(UrlListValidation url) {
    }


    @Override
    public boolean isValid(List<String> urlList, ConstraintValidatorContext cxt) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern pattern = Pattern.compile(regex);

        if (urlList == null) {
            return true;
        }

        if (urlList.isEmpty()) {
            return false;
        }

        return urlList.stream().filter(url -> {
            Matcher matcher = pattern.matcher(url);
            return matcher.matches();
        }).collect(Collectors.toList()).size() == urlList.size();

    }
}
