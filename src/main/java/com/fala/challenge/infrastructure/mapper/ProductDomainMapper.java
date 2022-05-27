package com.fala.challenge.infrastructure.mapper;

import java.util.List;


import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDomainMapper {

    ProductDomainMapper INSTANCE = Mappers.getMapper(ProductDomainMapper.class);

    @Mappings({})
    Product toDomain(ProductEntity productEntity);

    @Mappings({})
    ProductEntity toEntity(Product product);

    @Mappings({})
    List<Product> listToDomain(List<ProductEntity> productEntities);

}
