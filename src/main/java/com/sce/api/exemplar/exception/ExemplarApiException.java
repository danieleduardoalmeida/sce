package com.sce.api.exemplar.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ExemplarApiException extends Exception {

    private static final long serialVersionUID = 3941204484243142181L;
    private HttpStatus httpStatus;

    public ExemplarApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ExemplarApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public ExemplarApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
