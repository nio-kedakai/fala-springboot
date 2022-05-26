package com.fala.challenge.infrastructure.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.mapper.ProductDomainMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String name;

    private String brand;

    private String size;

    private BigDecimal price;

    @Column(name = "principal_image")
    private String principalImage;

//    @Column(name = "other_images")
//    private List otherImages;

    public Product toDomain() {
        return ProductDomainMapper.INSTANCE.toDomain(this);
    }
}
