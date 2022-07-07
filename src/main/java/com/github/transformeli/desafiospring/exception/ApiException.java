package com.github.transformeli.desafiospring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class ApiException extends RuntimeException {
    private String title;
    private HttpStatus status;
    private String messageData;
    private LocalDateTime timestamp;

    public ApiException(String message) {
        super(message);
        this.setTitle(message);
        this.setStatus(HttpStatus.MULTI_STATUS);
        this.setMessageData(message);
        this.setTimestamp(LocalDateTime.now());
    }

    public ApiExceptionDTO getDTO() {
        return ApiExceptionDTO.builder()
                .title(this.getTitle())
                .status(this.getStatus().value())
                .message(this.getMessageData())
                .timestamp(this.getTimestamp())
                .build();
    }
}
