package com.fala.challenge.application.validation;

import javax.validation.ConstraintValidatorContext;

import com.fala.challenge.application.validator.SkuValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SkuValidatorTest {

    @Mock
    private ConstraintValidatorContext cxt;
    private final String sku = "FAL-881898503";

    @Test
    void shouldReturnTrueWhenSku() {
        SkuValidator subject = new SkuValidator();
        boolean isValid = subject.isValid(sku, cxt);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsBad() {
        SkuValidator subject = new SkuValidator();
        boolean isValid = subject.isValid("FALA-8818", cxt);
        assertFalse(isValid);
    }

    @Test
    void shouldReturnFalseWhenUuidIsNull() {
        SkuValidator subject = new SkuValidator();
        boolean isValid = subject.isValid(null, cxt);
        assertFalse(isValid);
    }
}
