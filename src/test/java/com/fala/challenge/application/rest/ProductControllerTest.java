package com.fala.challenge.application.rest;

import java.io.IOException;


import com.fala.challenge.application.request.ProductRequest;
import com.fala.challenge.domain.port.ProductServicePort;
import com.fala.challenge.infrastructure.exception.ResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static com.fala.challenge.fixture.TestFixtures.buildDeleteRequest;
import static com.fala.challenge.fixture.TestFixtures.buildGetRequest;
import static com.fala.challenge.fixture.TestFixtures.buildObjectMapper;
import static com.fala.challenge.fixture.TestFixtures.buildPostRequest;
import static com.fala.challenge.fixture.TestFixtures.buildPostRequestWithoutBody;
import static com.fala.challenge.fixture.TestFixtures.buildPutRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private static final String URL_BASE_PRODUCT = "/v1/product";
    private static final String REPLACE_PARAM_ONE = "$1";
    private static final String PATH_PARAM_ONE = "/$1";
    private static final String PATH_JSON_FILE = "json/request_product.json";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServicePort productService;

    private ProductRequest product;

    @BeforeEach
    void setUp() throws IOException {
        this.product = buildObjectMapper(PATH_JSON_FILE, ProductRequest.class);
    }


    @Test
    void shouldReturnStatusCreatedWhenCreateProduct() throws Exception {

        given(productService.findProductBySku(any())).willReturn(null);
        given(productService.validProductForCreation(any())).willReturn(Mono.just(Boolean.TRUE));
        given(productService.saveProduct(any())).willReturn(Mono.just(this.product));

        mvc.perform(buildPostRequest("json/create_product.json", URL_BASE_PRODUCT))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void shouldReturnProductWhenFindProductBySku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willReturn(Mono.just(product));

        mvc.perform(buildGetRequest(pathParam))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnAllProduct() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, "products");

        given(productService.findAllProducts()).willReturn(Flux.just(product));

        mvc.perform(buildGetRequest(pathParam))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnInternalServerErrorWhenCreateProduct() throws Exception {

        given(productService.findProductBySku(any())).willReturn(null);
        given(productService.validProductForCreation(any())).willReturn(Mono.just(Boolean.FALSE));
        given(productService.saveProduct(any())).willReturn(Mono.just(this.product));

        mvc.perform(buildPostRequestWithoutBody(URL_BASE_PRODUCT))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    void shouldReturnResourceDuplicateExceptionWhenCreateProduct() throws Exception {

        this.product.setSku("FAL-881898503");
        given(productService.findProductBySku(any())).willReturn(Mono.just(this.product));
        given(productService.validProductForCreation(any())).willReturn(Mono.just(Boolean.FALSE));

        mvc.perform(buildPostRequest("json/create_product.json", URL_BASE_PRODUCT))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }


    @Test
    void shouldReturnStatusOkWhenUpdateProductBySku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willReturn(Mono.just(this.product));
        given(productService.saveProduct(any())).willReturn(Mono.just(this.product));

        mvc.perform(buildPutRequest("json/update_product.json", pathParam))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenUpdateProductBySku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willThrow(ResourceException.illegalArgument());

        mvc.perform(buildPutRequest("json/update_product.json", pathParam))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenFailUpdateProductBySku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willReturn(Mono.just(this.product));
        given(productService.saveProduct(any())).willThrow(ResourceException.illegalArgument());

        mvc.perform(buildPutRequest("json/update_product.json", pathParam))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    @Test
    void shouldReturnStatusOkWhenDeleteProductBySku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willReturn(Mono.just(this.product));
        given(productService.deleteProductById(any())).willReturn(Mono.empty().then());

        mvc.perform(buildDeleteRequest(pathParam))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldReturnResourceNotFoundExceptionWhenDeleteSku() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, this.product.getSku());

        given(productService.findProductBySku(any())).willThrow(ResourceException.notExistingProduct());

        mvc.perform(buildDeleteRequest(pathParam))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void shouldReturnMissingPathVariableException() throws Exception {

        String pathParam = URL_BASE_PRODUCT + PATH_PARAM_ONE;
        pathParam = pathParam.replace(REPLACE_PARAM_ONE, " ");

        given(productService.findProductBySku(any())).willReturn(null);

        mvc.perform(buildDeleteRequest(pathParam))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }


}