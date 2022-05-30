package com.fala.challenge.application.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validator.UrlListValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlListValidatorTest {

    @Mock
    private ConstraintValidatorContext cxt;


    @Test
    void shouldReturnTrueWhenSku() {
        List<String> url = Arrays.asList("http://falabella.com", "http://falabella.com/1");
        UrlListValidator subject = new UrlListValidator();
        boolean isValid = subject.isValid(url, cxt);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsBad() {
        List<String> url = Arrays.asList("http://falabella.com", "");
        UrlListValidator subject = new UrlListValidator();
        boolean isValid = subject.isValid(url, cxt);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnTrueWhenUuidIsNull() {
        UrlListValidator subject = new UrlListValidator();
        boolean isValid = subject.isValid(null, cxt);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsEmpty() {
        List<String> url = new ArrayList<>();
        UrlListValidator subject = new UrlListValidator();
        boolean isValid = subject.isValid(url, cxt);
        assertFalse(isValid);
    }
}
