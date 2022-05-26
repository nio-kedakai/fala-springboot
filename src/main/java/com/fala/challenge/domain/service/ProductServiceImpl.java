package com.fala.challenge.domain.service;

import java.util.List;

import com.fala.challenge.domain.exception.BusinessException;
import com.fala.challenge.domain.model.Product;
import com.fala.challenge.domain.port.ProductPersistencePort;
import com.fala.challenge.domain.port.ProductServicePort;
import com.fala.challenge.infrastructure.exception.ResourceException;
import com.fala.challenge.infrastructure.mapper.ProductDomainMapper;


public class ProductServiceImpl implements ProductServicePort {

    private ProductPersistencePort productPersistencePort;

    public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public Product saveProduct(Product product) {
        return productPersistencePort.saveProduct(product).toDomain();
    }

    @Override
    public Product findProductBySku(String sku) {
        return productPersistencePort.findBySku(sku).toDomain();
    }

    @Override
    public List<Product> findAllProducts() {
        return ProductDomainMapper.INSTANCE.listToDomain(productPersistencePort.findAllProducts());
    }


    @Override
    public boolean validProductForCreation(String sku) {
        try {
            return productPersistencePort.validProductForCreation(sku);
        } catch (ResourceException e) {
            throw BusinessException.invalidProduct();
        }

    }

    @Override
    public Long deleteProductById(Long productId) {
        return productPersistencePort.deleteProductById(productId);
    }
}
