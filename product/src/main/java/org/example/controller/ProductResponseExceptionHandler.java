package org.example.controller;

import org.example.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;

@RestControllerAdvice
public class ProductResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProductResponseExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleBusinessException(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        ResponseDTO responseDTO = createResponseDTO();
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseDTO createResponseDTO() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode("xx");
        responseDTO.setMessage("خطا در سرویس");
        responseDTO.setDate(LocalDate.now());
        responseDTO.setTime(LocalTime.now());
        return responseDTO;
    }
}
