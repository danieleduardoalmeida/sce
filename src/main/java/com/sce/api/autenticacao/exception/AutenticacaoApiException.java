package com.sce.api.autenticacao.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class AutenticacaoApiException extends Exception {

    private static final long serialVersionUID = 2063519553795636587L;
    private HttpStatus httpStatus;

    public AutenticacaoApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public AutenticacaoApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public AutenticacaoApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
