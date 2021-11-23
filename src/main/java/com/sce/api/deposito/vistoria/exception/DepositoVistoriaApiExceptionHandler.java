package com.sce.api.deposito.vistoria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepositoVistoriaApiExceptionHandler {

    @ExceptionHandler(DepositoVistoriaApiException.class)
    protected ResponseEntity<Object> handleEntity(DepositoVistoriaApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }

    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
