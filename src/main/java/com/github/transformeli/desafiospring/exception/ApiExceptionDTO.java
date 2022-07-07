package com.github.transformeli.desafiospring.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiExceptionDTO {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
