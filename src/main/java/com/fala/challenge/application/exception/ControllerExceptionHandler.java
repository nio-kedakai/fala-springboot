package com.fala.challenge.application.exception;


import com.fala.challenge.domain.exception.BusinessException;
import com.fala.challenge.infrastructure.exception.ResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
@ComponentScan("com.fala.challenge.application.exception")
public class ControllerExceptionHandler {

    private final String exceptionWithTraceMessage = "Exception while processing request : {}, full stack trace :: {}";

    @ExceptionHandler(value = {ResourceException.class})
    @ResponseStatus(value = NOT_FOUND)
    public ErrorMessage resourceException(ResourceException e) {
        log.error(exceptionWithTraceMessage, e.getMessage(), ExceptionUtils.getStackTrace(e));
        String description = ExceptionUtils.getStackTrace(e);
        return getExceptionErrorMessage(e, NOT_FOUND, description);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerException(Exception e) {
        log.error(exceptionWithTraceMessage, e.getMessage(), ExceptionUtils.getStackTrace(e));
        String description = ExceptionUtils.getStackTrace(e);
        return getExceptionErrorMessage(e, INTERNAL_SERVER_ERROR, description);
    }

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorMessage duplicateProductException(BusinessException e) {
        log.error(exceptionWithTraceMessage, e.getMessage(), ExceptionUtils.getStackTrace(e));
        String description = ExceptionUtils.getStackTrace(e);
        return getExceptionErrorMessage(e, INTERNAL_SERVER_ERROR, description);
    }

    @ExceptionHandler(value = {MissingPathVariableException.class})
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorMessage missingBodyException(MissingPathVariableException e) {
        log.error(exceptionWithTraceMessage, e.getMessage(), ExceptionUtils.getStackTrace(e));
        String description = ExceptionUtils.getStackTrace(e);
        return getExceptionErrorMessage(e, BAD_REQUEST, description);
    }

    @ExceptionHandler(value = {ApiException.class})
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorMessage missingPathVariableException(ApiException e) {
        log.error(exceptionWithTraceMessage, e.getMessage(), ExceptionUtils.getStackTrace(e));
        String description = ExceptionUtils.getStackTrace(e);
        return getExceptionErrorMessage(e, BAD_REQUEST, description);
    }


    private ErrorMessage getExceptionErrorMessage(Exception e, HttpStatus status, String description) {
        return ErrorMessage.builder()
                .statusCode(status.value())
                .localDateTimeNow(com.fala.challenge.application.util.Utils.getLocalDateTimeNowFormatted())
                .message(e.getMessage())
                .description(description)
                .build();
    }

}
