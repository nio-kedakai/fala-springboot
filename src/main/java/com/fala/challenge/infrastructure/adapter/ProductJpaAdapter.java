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

@Service
public class ProductJpaAdapter implements ProductPersistencePort {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(Product product) {
        try {
            return productRepository.save(product.toEntity());
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }


    @Override
    public ProductEntity findBySku(String sku) {
        try {
            ProductEntity productEntity = productRepository.findBySku(sku);
            Optional optProductEntity = Optional.ofNullable(productEntity);
            if (!optProductEntity.isPresent() || optProductEntity.isEmpty()) {
                throw ResourceException.notExistingProduct();
            }
            return productEntity;
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }

    @Override
    public List<ProductEntity> findAllProducts() {
        try {
            List<ProductEntity> productEntities = productRepository.findAll();
            Optional optProductEntities = Optional.ofNullable(productEntities);
            if (!optProductEntities.isPresent() || productEntities.isEmpty()) {
                throw ResourceException.notExistingProduct();
            }
            return productEntities;
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

    }

    @Override
    public boolean validProductForCreation(String sku) {
        try {
            ProductEntity productEntity = productRepository.findBySku(sku);
            Optional optProductEntity = Optional.ofNullable(productEntity);
            if (optProductEntity.isPresent() || !optProductEntity.isEmpty()) {
                throw ResourceException.existingProduct();
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }
    }

    @Override
    public Long deleteProductById(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (IllegalArgumentException e) {
            throw ResourceException.illegalArgument();
        }

        return productId;
    }
}
