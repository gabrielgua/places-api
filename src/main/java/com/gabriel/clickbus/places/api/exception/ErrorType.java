package com.gabriel.clickbus.places.api.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    GENERIC_ERROR("/generic-error", "Generic error"),
    INVALID_DATA("/invalid-data", "Invalid data"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");


    private final String uri;
    private final String title;

    ErrorType(String path, String title) {
        this.title = title;
        this.uri = "https://api.places/errors" + path;
    }
}
