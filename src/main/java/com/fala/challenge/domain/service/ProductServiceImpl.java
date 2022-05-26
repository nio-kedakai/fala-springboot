package com.fala.challenge.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fala.challenge.domain.exception.BusinessException;
import com.fala.challenge.domain.model.Product;
import com.fala.challenge.domain.port.ProductPersistencePort;
import com.fala.challenge.domain.port.ProductServicePort;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import com.fala.challenge.infrastructure.exception.ResourceException;
import com.fala.challenge.infrastructure.mapper.ProductDomainMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ProductServiceImpl implements ProductServicePort {

    private ProductPersistencePort productPersistencePort;

    public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return productPersistencePort.saveProduct(product).map(p -> p.toDomain());
    }

    @Override
    public Mono<Product> findProductBySku(String sku) {
        return productPersistencePort.findBySku(sku).map(p -> p.toDomain());
    }

    @Override
    public Flux<Product> findAllProducts() {
        List<ProductEntity> productEntities = productPersistencePort.findAllProducts().toStream().collect(Collectors.toList());
        List<Product> products = ProductDomainMapper.INSTANCE.listToDomain(productEntities);
        return Flux.defer(() -> Flux.fromIterable(products));
    }


    @Override
    public Mono<Boolean> validProductForCreation(String sku) {
        try {
            return productPersistencePort.validProductForCreation(sku);
        } catch (ResourceException e) {
            throw BusinessException.invalidProduct();
        }

    }

    @Override
    public Mono<Void> deleteProductById(Long productId) {
        productPersistencePort.deleteProductById(productId);
        return Mono.empty().then();
    }
}
