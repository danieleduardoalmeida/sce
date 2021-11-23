package com.sce.api.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsuarioApiExceptionHandler {

    @ExceptionHandler(UsuarioApiException.class)
    protected ResponseEntity<Object> handleEntity(UsuarioApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }

    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
