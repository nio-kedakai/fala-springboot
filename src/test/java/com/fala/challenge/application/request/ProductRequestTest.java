package com.fala.challenge.application.request;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ProductRequestTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test() {
        ProductRequest productRequest = new ProductRequest("http://www.falabella.com");
        assertNotNull(productRequest);
    }

}