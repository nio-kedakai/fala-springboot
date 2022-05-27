package com.fala.challenge.infrastructure.entity;

import java.math.BigDecimal;

import com.fala.challenge.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fala.challenge.fixture.Creator.BRAND;
import static com.fala.challenge.fixture.Creator.NAME;
import static com.fala.challenge.fixture.Creator.PRODUCT_ID;
import static com.fala.challenge.fixture.Creator.SKU_RANDOM;
import static com.fala.challenge.fixture.Creator.createFullyProductEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class ProductEntityTest {

    private ProductEntity subject;

    @BeforeEach
    void setUp() {
        this.subject = createFullyProductEntity();
    }

    @Test
    void shouldCreateDomainFromEntity() {
        Product product = subject.toDomain();
        assertNotNull(product);
        assertEquals(product.getSku(), subject.getSku());
        assertEquals(product.getBrand(), subject.getBrand());
        assertEquals(product.getId(), subject.getId());
        assertEquals(product.getName(), subject.getName());
        assertEquals(product.getPrincipalImage(), subject.getPrincipalImage());
        assertEquals(product.getPrice(), subject.getPrice());
        assertEquals(product.getSize(), subject.getSize());
        assertNotNull(product.toString());
        assertNotNull(product.toEntity());
    }

    @Test
    void shouldUpdateEntity() {
        subject.setSku(SKU_RANDOM);
        subject.setBrand(BRAND);
        subject.setId(Long.parseLong(PRODUCT_ID));
        subject.setName(NAME);
        subject.setPrincipalImage("https://falabella.scene7.com/is/image/Falabella/8406270_1");
        subject.setPrice(new BigDecimal("429990.00"));
        subject.setSize("XL");

        ProductEntity productEntity = subject;

        assertNotNull(subject.toString());
        assertNotNull(subject.hashCode());
        assertEquals(subject, productEntity);

    }


}