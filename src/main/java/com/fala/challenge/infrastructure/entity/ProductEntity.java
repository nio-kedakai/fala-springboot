package com.fala.challenge.infrastructure.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.mapper.ProductDomainMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


@Data
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "other_images")
    private List<String> otherImages;


    public Product toDomain() {
        return ProductDomainMapper.INSTANCE.toDomain(this);
    }
}
