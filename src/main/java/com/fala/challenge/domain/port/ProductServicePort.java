package com.fala.challenge.domain.port;


import java.util.List;

import com.fala.challenge.domain.model.Product;


public interface ProductServicePort {

    Product saveProduct(Product product);

    Product findProductBySku(String sku);

    List<Product> findAllProducts();

    boolean validProductForCreation(String sku);

    Long deleteProductById(Long productId);
}
