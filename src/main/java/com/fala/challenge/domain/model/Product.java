package com.fala.challenge.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fala.challenge.infrastructure.entity.ProductEntity;

import com.fala.challenge.infrastructure.mapper.ProductDomainMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product implements Serializable {

    private Long id;
    private String sku;
    private String name;
    private String brand;
    private String size;
    private BigDecimal price;
    private String principalImage;
    //private List otherImages;

    public ProductEntity toEntity() {
        return ProductDomainMapper.INSTANCE.toEntity(this);
    }

}
