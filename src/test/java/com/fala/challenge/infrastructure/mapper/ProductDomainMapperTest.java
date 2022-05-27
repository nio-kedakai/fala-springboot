package com.fala.challenge.infrastructure.mapper;

import java.util.List;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.fala.challenge.fixture.Creator.createListProductEntity;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ProductDomainMapperTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldTransformListEntityToDomainList() {
        List<ProductEntity> entities = createListProductEntity();
        List<Product> products = ProductDomainMapper.INSTANCE.listToDomain(entities);
        assertNotNull(entities);
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertFalse(entities.isEmpty());
    }

}