package com.fala.challenge.infrastructure.exception;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceExceptionTest {

    @Test
    void shouldThrowBadRequest() {
        ResourceException actual = ResourceException.existingProduct();
        assertEquals("Latest product sku is present in DB", actual.getMessage());
    }


}
