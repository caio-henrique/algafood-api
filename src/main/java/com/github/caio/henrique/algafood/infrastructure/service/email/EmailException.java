package com.github.caio.henrique.algafood.infrastructure.service.email;

public class EmailException extends RuntimeException {

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
