package com.algaworks.algafood.infrastructure.service.storage;

public class StorageException extends RuntimeException {

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}