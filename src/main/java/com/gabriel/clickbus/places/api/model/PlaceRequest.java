package com.gabriel.clickbus.places.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String state;
}
