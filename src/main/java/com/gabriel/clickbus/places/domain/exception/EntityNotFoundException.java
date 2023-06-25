package com.gabriel.clickbus.places.domain.exception;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Long id) {
        super(String.format("Entity not found for id: %d", id));
    }
}
