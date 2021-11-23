package com.sce.api.exemplar.analise.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ExemplarAnaliseApiException extends Exception {

    private static final long serialVersionUID = 3941204484243142181L;
    private HttpStatus httpStatus;

    public ExemplarAnaliseApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ExemplarAnaliseApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public ExemplarAnaliseApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
