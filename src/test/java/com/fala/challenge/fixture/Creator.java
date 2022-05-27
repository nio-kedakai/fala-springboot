package com.fala.challenge.fixture;

import java.math.BigDecimal;
import java.util.List;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import org.apache.commons.lang3.RandomStringUtils;


public class Creator {

    public static final String PRODUCT_ID = RandomStringUtils.randomNumeric(2);
    public static final String BRAND = RandomStringUtils.randomAlphabetic(10);
    public static final String NAME = RandomStringUtils.randomAlphabetic(10);
    public static final String SKU_RANDOM = "FAL-840627022";


    public static Product createFullyProduct() {
        return Product.builder()
                .sku(SKU_RANDOM)
                .brand(BRAND)
                .id(Long.parseLong(PRODUCT_ID))
                .name(NAME)
                .principalImage("https://falabella.scene7.com/is/image/Falabella/8406270_1")
                .price(new BigDecimal("429990.00"))
                .size("37")
                .build();
    }

    public static ProductEntity createFullyProductEntity() {
        return ProductEntity.builder()
                .sku(SKU_RANDOM)
                .brand(BRAND)
                .id(Long.parseLong(PRODUCT_ID))
                .name(NAME)
                .principalImage("https://falabella.scene7.com/is/image/Falabella/8406270_1")
                .price(new BigDecimal("429990.00"))
                .size("37")
                .build();
    }

    public static List<ProductEntity> createListProductEntity() {
        return List.of(ProductEntity.builder()
                .sku(SKU_RANDOM)
                .brand(BRAND)
                .id(Long.parseLong(PRODUCT_ID))
                .name(NAME)
                .principalImage("https://falabella.scene7.com/is/image/Falabella/8406270_1")
                .price(new BigDecimal("429990.00"))
                .size("37")
                .build());
    }

}
