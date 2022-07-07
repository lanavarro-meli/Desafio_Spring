package com.github.transformeli.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiException {

    public InternalServerException(String message) {
        super(message);
        this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        this.setTitle("Internal Server Error");
    }
}
