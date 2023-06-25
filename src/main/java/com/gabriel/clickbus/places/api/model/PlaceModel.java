package com.gabriel.clickbus.places.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PlaceModel {

    private Long id;
    private String name;
    private String slug;
    private String city;
    private String state;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
