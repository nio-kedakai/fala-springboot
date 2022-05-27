package com.fala.challenge.domain.port;


import com.fala.challenge.domain.model.Product;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductPersistencePort {

    Mono<ProductEntity> saveProduct(Product product);

    Mono<ProductEntity> findBySku(String sku);

    Flux<ProductEntity> findAllProducts();

    Mono<Boolean> validProductForCreation(String sku);

    Mono<Void> deleteProductById(Long productId);
}
