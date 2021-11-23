package com.sce.api.autenticacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AutenticacaoApiExceptionHandler {

    @ExceptionHandler(AutenticacaoApiException.class)
    protected ResponseEntity<Object> handleEntity(AutenticacaoApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }

    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
