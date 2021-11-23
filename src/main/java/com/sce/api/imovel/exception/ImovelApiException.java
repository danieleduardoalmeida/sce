package com.sce.api.imovel.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ImovelApiException extends Exception {

    private static final long serialVersionUID = -7745851778955579127L;
    private HttpStatus httpStatus;

    public ImovelApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ImovelApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public ImovelApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
