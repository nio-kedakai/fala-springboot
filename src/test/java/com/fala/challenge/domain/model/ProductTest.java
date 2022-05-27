package com.fala.challenge.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.fala.challenge.fixture.Creator.createFullyProduct;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = createFullyProduct();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test() {
        assertNotNull(this.product);
        assertNotNull(this.product.getId());
    }

}