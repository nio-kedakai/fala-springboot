package com.fala.challenge.fixture;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import org.apache.commons.lang3.RandomStringUtils;


public class Creator {

    public static final String PRODUCT_ID = RandomStringUtils.randomNumeric(2);
    public static final String BRAND = RandomStringUtils.randomAlphabetic(10);
    public static final String NAME = RandomStringUtils.randomAlphabetic(10);
    public static final String SKU_RANDOM = "FAL-840627022";
    public static final List<String> OTHER_IMAGES =
            Arrays.asList("https://falabella.scene7.com/is/image/Falabella/8406270_1",
                    "https://falabella.scene7.com/is/image/Falabella/8406270_2");

    public static Product createFullyProduct() {
        return Product.builder()
                .sku(SKU_RANDOM)
                .brand(BRAND)
                .id(Long.parseLong(PRODUCT_ID))
                .name(NAME)
                .principalImage(OTHER_IMAGES.get(0))
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
                .principalImage(OTHER_IMAGES.get(0))
                .price(new BigDecimal("429990.00"))
                .size("37")
                .otherImages(OTHER_IMAGES)
                .build();
    }

    public static List<ProductEntity> createListProductEntity() {
        return Arrays.asList(ProductEntity.builder()
                .sku(SKU_RANDOM)
                .brand(BRAND)
                .id(Long.parseLong(PRODUCT_ID))
                .name(NAME)
                .principalImage(OTHER_IMAGES.get(0))
                .price(new BigDecimal("429990.00"))
                .size("37")
                .otherImages(OTHER_IMAGES)
                .build());
    }

}
