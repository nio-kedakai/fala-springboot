package com.fala.challenge.domain.services;


import com.fala.challenge.domain.model.Product;
import com.fala.challenge.domain.port.ProductPersistencePort;
import com.fala.challenge.domain.service.ProductServiceImpl;
import com.fala.challenge.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static com.fala.challenge.fixture.Creator.createFullyProduct;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductPersistencePort productPersistencePort;

    private Product product;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        this.product = createFullyProduct();
        this.productEntity = this.product.toEntity();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void shouldSaveAndReturnFullyProductEntityWhenSaveProduct() {
        given(productPersistencePort.saveProduct(any())).willReturn(Mono.just(productEntity));

        Mono<Product> productMono = productServiceImpl.saveProduct(this.product);

        verify(productPersistencePort, times(1)).saveProduct(any());
        assertNotNull(productMono);

    }

    @Test
    void shouldReturnIfProductIsValidForCreation() {
        given(productPersistencePort.validProductForCreation(any())).willReturn(Mono.just(Boolean.TRUE));

        Mono<Boolean> productMono = productServiceImpl.validProductForCreation(product.getSku());

        verify(productPersistencePort, times(1)).validProductForCreation(any());
        assertNotNull(productMono);

    }

    @Test
    void shouldReturnFullyProductEntityWhenFindAllProducts() {
        given(productPersistencePort.findAllProducts()).willReturn(Flux.just(productEntity));

        Flux<Product> productFlux = productServiceImpl.findAllProducts();

        verify(productPersistencePort, times(1)).findAllProducts();
        assertNotNull(productFlux);

    }


    @Test
    void shouldReturnFullyProductEntityWhenFindProductBySku() {
        given(productPersistencePort.findBySku(any())).willReturn(Mono.just(productEntity));

        Mono<Product> productMono = productServiceImpl.findProductBySku(this.product.getSku());

        verify(productPersistencePort, times(1)).findBySku(any());
        assertNotNull(productMono);

    }


    @Test
    void shouldReturnVoidWhenDeleteProductById() {
        given(productPersistencePort.deleteProductById(any())).willReturn(Mono.empty().then());

        Mono<Void> productMono = productServiceImpl.deleteProductById(this.product.getId());

        verify(productPersistencePort, times(1)).deleteProductById(any());
        assertNotNull(productMono);

    }
}