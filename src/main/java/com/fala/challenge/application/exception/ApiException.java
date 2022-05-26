package com.fala.challenge.application.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String MISSING_BODY_REQUEST = "Missing the required parameter 'body' when calling ";

    private static final String MISSING_PATH_REQUEST = "Missing the required parameter 'body' when calling ";

    public ApiException(String msg) {
        super(msg);
    }

    public static ApiException bodyMissing(String method) {
        return new ApiException(MISSING_BODY_REQUEST + method);
    }

    public static ApiException pathMissing(String method) {
        return new ApiException(MISSING_PATH_REQUEST + method);
    }
}
