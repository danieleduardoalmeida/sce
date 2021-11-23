package com.sce.api.vistoria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VistoriaApiExceptionHandler {

    @ExceptionHandler(VistoriaApiException.class)
    protected ResponseEntity<Object> handleEntity(VistoriaApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }

    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
