package com.fala.challenge.infrastructure.configuration;


import com.fala.challenge.domain.port.ProductPersistencePort;
import com.fala.challenge.domain.port.ProductServicePort;
import com.fala.challenge.domain.service.ProductServiceImpl;
import com.fala.challenge.infrastructure.adapter.ProductJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductPersistencePort productPersistence() {
        return new ProductJpaAdapter();
    }

    @Bean
    public ProductServicePort productService() {
        return new ProductServiceImpl(productPersistence());
    }
}
