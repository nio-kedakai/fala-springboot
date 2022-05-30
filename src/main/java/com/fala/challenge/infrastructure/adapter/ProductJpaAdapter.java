package com.fala.challenge.infrastructure.adapter;


import java.util.List;
import java.util.Optional;

import com.fala.challenge.domain.model.Product;
import com.fala.challenge.domain.port.ProductPersistencePort;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import com.fala.challenge.infrastructure.exception.ResourceException;
import com.fala.challenge.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductJpaAdapter implements ProductPersistencePort {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Mono<ProductEntity> saveProduct(Product product) {
        try {
            return Mono
                    .defer(() -> Mono.just(productRepository.save(product.toEntity())));
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }


    @Override
    public Mono<ProductEntity> findBySku(String sku) {
        try {
            ProductEntity productEntity = productRepository.findBySku(sku);

            Optional optProductEntity = Optional.ofNullable(productEntity);
            if (!optProductEntity.isPresent()) {
                throw ResourceException.notExistingProduct();
            }

            return Mono.defer(() -> Mono.just(productEntity));

        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }


    @Override
    public Flux<ProductEntity> findAllProducts() {
        try {
            List<ProductEntity> productEntities = productRepository.findAll();


            Optional optProductEntities = Optional.ofNullable(productEntities);
            if (!optProductEntities.isPresent()) {
                throw ResourceException.notExistingProduct();
            }

            return Flux.defer(() -> Flux.fromIterable(productEntities));
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }

    @Override
    public Mono<Boolean> validProductForCreation(String sku) {
        try {
            ProductEntity productEntity = productRepository.findBySku(sku);
            Optional optProductEntity = Optional.ofNullable(productEntity);
            if (optProductEntity.isPresent()) {
                throw ResourceException.existingProduct();
            }
            return Mono.just(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }
    }

    @Override
    @Transactional
    public Mono<Void> deleteProductById(Long productId) {
        try {
            productRepository.deleteById(productId);
            return Mono.empty().then();
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }
    }
}
