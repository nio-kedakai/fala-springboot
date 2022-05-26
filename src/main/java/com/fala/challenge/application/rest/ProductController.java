package com.fala.challenge.application.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import com.fala.challenge.application.exception.ApiException;
import com.fala.challenge.application.request.ProductRequest;
import com.fala.challenge.domain.model.Product;
import com.fala.challenge.domain.port.ProductServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {

    static final String AUTHORIZATION = "authorization";
    static final String TOTAL_TIME_TAKEN = "Total Time Taken :: {}";
    static final String FIND_PRODUCT_LOG = "find Product :: {}";
    static final String PRODUCT_FOUNDED = "Product :: {} founded:: {}";
    static final String SKU_PATH = "/{sku}";
    static final String ALL_PRODUCTS_PATH = "/getAllProducts";


    @Autowired
    private ProductServicePort productServicePort;


    @PostMapping()
    @ResponseStatus(CREATED)
    public ResponseEntity createProduct(@RequestHeader(name = AUTHORIZATION, required = false) String authorization,
                                        @RequestBody ProductRequest request) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            log.info("Init createProduct Product :: {}", request.toString());

            log.info("Validating if Product :: {} exist in DB", request.getName());

            boolean valid = productServicePort.validProductForCreation(request.getSku());
            log.info("Valid Product for creation :: {} ", valid);

            Product productSave = productServicePort.saveProduct(request);

            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Finish createProduct successfully for Product :: {}", productSave.getName());

            return ResponseEntity.status(CREATED).body(productSave);
        } finally {
            MDC.clear();
        }

    }

    @GetMapping(ALL_PRODUCTS_PATH)
    public ResponseEntity findAllProducts(@RequestHeader(name = AUTHORIZATION, required = false) String authorization) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            log.info("Init findAllProducts");

            List<Product> products = productServicePort.findAllProducts();
            log.info("Products founded:: {}", products.size());

            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Finish Successfully find All Products");

            return ResponseEntity.status(OK).body(products);
        } finally {
            MDC.clear();
        }

    }

    @GetMapping(value = SKU_PATH)
    public ResponseEntity findProductBySku(@RequestHeader(name = AUTHORIZATION, required = false) String authorization,
                                           @PathVariable @NotNull String sku) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            log.info("Init findProductBySku Product :: {}", sku);

            log.info(FIND_PRODUCT_LOG, sku);
            Product product = productServicePort.findProductBySku(sku);
            log.info(PRODUCT_FOUNDED, sku, product != null);

            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Finish Successfully find Product by sku:: {}", sku);

            return ResponseEntity.status(OK).body(product);
        } finally {
            MDC.clear();
        }

    }

    @PutMapping(value = SKU_PATH)
    @ResponseStatus(OK)
    public ResponseEntity updateProduct(@RequestHeader(name = AUTHORIZATION, required = false) String authorization,
                                        @PathVariable String sku, @Valid @RequestBody ProductRequest request) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            log.info("Init updateProduct Product :: {}", sku);

            log.info(FIND_PRODUCT_LOG, sku);
            Product product = productServicePort.findProductBySku(sku);
            log.info(PRODUCT_FOUNDED, sku, product != null);

            request.setId(product.getId());

            log.info("updating Product :: {}", sku);
            Product productUpdate = productServicePort.saveProduct(request);

            log.info("Product {} updated :: {}", sku, productUpdate != null);

            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Finish Successfully updateProduct, Product :: {}", productUpdate.toString());

            return ResponseEntity.status(OK).body(productUpdate);
        } finally {
            MDC.clear();
        }

    }

    @PatchMapping(value = SKU_PATH)
    @ResponseStatus(OK)
    public ResponseEntity patchProduct(@RequestHeader(name = AUTHORIZATION, required = false) String authorization,
                                       @PathVariable String sku, @Valid @RequestBody ProductRequest request) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            log.info("Init patchProduct Product :: {}", sku);

            log.info(FIND_PRODUCT_LOG, sku);
            Product product = productServicePort.findProductBySku(sku);
            log.info(PRODUCT_FOUNDED, sku, product != null);

            request.setName(sku);

            log.info("patchProduct Product :: {}", sku);
            Product productUpdate = productServicePort.saveProduct(request);

            log.info("patchProduct Product :: {}", sku);

            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Finish Successfully patchProduct, Product :: {}", productUpdate.toString());

            return ResponseEntity.status(OK).body(productUpdate);
        } finally {
            MDC.clear();
        }

    }

    @DeleteMapping(value = SKU_PATH)
    @ResponseStatus(OK)
    public ResponseEntity deleteProduct(@RequestHeader(name = AUTHORIZATION, required = false) String authorization,
                                        @PathVariable String sku) {
        try {
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            if (sku.trim().isEmpty()) {
                log.error("Finish deleteProduct, Product :: {} not found", sku);
                throw ApiException.pathMissing("deleteProduct");
            }

            log.info("Init deleteProduct Product :: {}", sku);

            log.info(FIND_PRODUCT_LOG, sku);
            Product product = productServicePort.findProductBySku(sku);
            log.info(PRODUCT_FOUNDED, sku, product != null);

            log.info("deleteProduct Product :: {}", sku);
            Long productDeleteId = productServicePort.deleteProductById(product.getId());

            log.info("deleteProduct Product :: {}", sku);
            totalTime.stop();
            MDC.put(TOTAL_TIME_TAKEN, String.valueOf(totalTime.getTime()));
            log.info("Successfully deleteProduct :: {}", productDeleteId);

            return ResponseEntity.status(OK).build();
        } finally {
            MDC.clear();
        }

    }

}
