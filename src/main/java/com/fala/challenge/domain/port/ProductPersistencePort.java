package com.fala.challenge.domain.port;


import java.util.List;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;


public interface ProductPersistencePort {

    ProductEntity saveProduct(Product product);

    ProductEntity findBySku(String sku);

    List<ProductEntity> findAllProducts();

    boolean validProductForCreation(String sku);

    Long deleteProductById(Long productId);
}
