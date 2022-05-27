package com.fala.challenge.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fala.challenge.application.validation.SkuValidation;
import com.fala.challenge.application.validation.UrlValidation;
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

    private static final long serialVersionUID = 1L;

    private Long id;

    @Valid
    @NotNull(message = "SKU can't be null")
    @SkuValidation
    private String sku;

    @Valid
    @NotBlank(message = "brand can't be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Valid
    @NotBlank(message = "brand can't be empty")
    @Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters")
    private String brand;

    @Valid
    @NotBlank(message = "brand can't be empty")
    private String size;

    @Valid
    @Min(value = 1, message = "price should not be less than 1.00")
    @Max(value = 99999999, message = "price should not be greater than 99999999.00")
    private BigDecimal price;


    @Valid
    @UrlValidation
    private String principalImage;
    //private List otherImages;

    public ProductEntity toEntity() {
        return ProductDomainMapper.INSTANCE.toEntity(this);
    }

}
