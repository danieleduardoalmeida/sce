package com.sce.api.analise.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class AnaliseApiException extends Exception {

    private static final long serialVersionUID = 7046809174942355870L;
    private HttpStatus httpStatus;

    public AnaliseApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public AnaliseApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public AnaliseApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
