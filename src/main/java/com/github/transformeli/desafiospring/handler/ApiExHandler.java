package com.github.transformeli.desafiospring.handler;

import com.github.transformeli.desafiospring.exception.ApiException;
import com.github.transformeli.desafiospring.exception.ApiExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionDTO> handlerApiException(ApiException ex)
    {
        return new ResponseEntity<>(ex.getDTO(), ex.getStatus());
    }
}
