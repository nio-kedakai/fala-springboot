package com.fala.challenge.application.request;


import java.util.Arrays;

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
        ProductRequest productRequest = new ProductRequest("http://www.falabella.com",
                Arrays.asList("http://www.falabella.com/1", "http://www.falabella.com/2"));
        assertNotNull(productRequest);
    }

}