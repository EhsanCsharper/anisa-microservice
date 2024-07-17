package org.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class ProductResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProductResponseExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleBusinessException(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("خطا در سرویس", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
