package com.fala.challenge.infrastructure.exception;

public class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String EXISTING_ERROR_SAVE = "Error produced when has been passed an illegal or inappropriate argument";
    private static final String EXISTING_PRODUCT_MORE_RECENT = "Latest product sku is present in DB";
    private static final String NOT_EXISTING_PRODUCT = "Product not found";


    public ResourceException(String msg) {
        super(msg);
    }

    public static ResourceException notExistingProduct() {
        return new ResourceException(NOT_EXISTING_PRODUCT);
    }

    public static ResourceException illegalArgument() {
        return new ResourceException(EXISTING_ERROR_SAVE);
    }

    public static ResourceException existingProduct() {
        return new ResourceException(EXISTING_PRODUCT_MORE_RECENT);
    }
}
