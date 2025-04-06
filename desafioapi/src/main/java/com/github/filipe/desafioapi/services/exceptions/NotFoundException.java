package com.github.filipe.desafioapi.services.exceptions;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message);
    }
}
