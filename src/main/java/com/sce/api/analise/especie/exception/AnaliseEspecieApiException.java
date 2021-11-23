package com.sce.api.analise.especie.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class AnaliseEspecieApiException extends Exception {

    private static final long serialVersionUID = -2940945999533589198L;
    private HttpStatus httpStatus;

    public AnaliseEspecieApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public AnaliseEspecieApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public AnaliseEspecieApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
