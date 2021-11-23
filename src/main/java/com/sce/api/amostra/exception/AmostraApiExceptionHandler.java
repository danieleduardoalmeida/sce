package com.sce.api.amostra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AmostraApiExceptionHandler {

    @ExceptionHandler(AmostraApiException.class)
    protected ResponseEntity<Object> handleEntity(AmostraApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }

    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
