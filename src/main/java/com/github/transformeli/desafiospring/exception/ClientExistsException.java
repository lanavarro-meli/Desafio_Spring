package com.github.transformeli.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class ClientExistsException extends ApiException {

    public ClientExistsException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_ACCEPTABLE);
        this.setTitle("Client Already Exists");
    }

}
