package com.sce.api.deposito.vistoria.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class DepositoVistoriaApiException extends Exception {

    private static final long serialVersionUID = -7642373619903632117L;
    private HttpStatus httpStatus;

    public DepositoVistoriaApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public DepositoVistoriaApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public DepositoVistoriaApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
