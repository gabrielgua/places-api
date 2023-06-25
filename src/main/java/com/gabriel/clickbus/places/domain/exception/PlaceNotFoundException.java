package com.gabriel.clickbus.places.domain.exception;

public class PlaceNotFoundException extends EntityNotFoundException{

    public PlaceNotFoundException(Long placeId) {
        super(String.format("Place not found for id: %d", placeId));
    }

    public PlaceNotFoundException(String nameOrSlug) {
        super(String.format("Place not found for name or slug: '%s'", nameOrSlug));
    }
}
