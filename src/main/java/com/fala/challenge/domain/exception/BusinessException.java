package com.fala.challenge.domain.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String EXISTING_PRODUCT_MORE_RECENT = "Latest product sku is present in DB";

    public BusinessException(String msg) {
        super(msg);
    }

    public static BusinessException invalidProduct() {
        return new BusinessException(EXISTING_PRODUCT_MORE_RECENT);
    }
}
