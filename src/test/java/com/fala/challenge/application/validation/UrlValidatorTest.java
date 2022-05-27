package com.fala.challenge.application.validation;

import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validator.UrlValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlValidatorTest {

    @Mock
    private ConstraintValidatorContext cxt;
    private final String url = "http://falabella.com";

    @Test
    void shouldReturnTrueWhenSku() {
        UrlValidator subject = new UrlValidator();
        boolean isValid = subject.isValid(url, cxt);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsBad() {
        UrlValidator subject = new UrlValidator();
        boolean isValid = subject.isValid("htt://something", cxt);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsNull() {
        UrlValidator subject = new UrlValidator();
        boolean isValid = subject.isValid(null, cxt);
        assertFalse(isValid);
    }
}
