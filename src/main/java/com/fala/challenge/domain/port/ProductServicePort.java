package com.fala.challenge.domain.port;


import com.fala.challenge.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductServicePort {

    Mono<Product> saveProduct(Product product);

    Mono<Product> findProductBySku(String sku);

    Flux<Product> findAllProducts();

    Mono<Boolean> validProductForCreation(String sku);

    Mono<Void> deleteProductById(Long productId);
}
