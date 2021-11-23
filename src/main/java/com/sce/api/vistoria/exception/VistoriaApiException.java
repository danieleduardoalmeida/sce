package com.sce.api.vistoria.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class VistoriaApiException extends Exception {

    private static final long serialVersionUID = -9147441443532215463L;
    private HttpStatus httpStatus;

    public VistoriaApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public VistoriaApiException(String message, BindingResult validationResult) {
        super(message);
        this.httpStatus = BAD_REQUEST;
    }

    public VistoriaApiException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
