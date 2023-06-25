package com.gabriel.clickbus.places.api.assembler;

import com.gabriel.clickbus.places.api.model.PlaceModel;
import com.gabriel.clickbus.places.api.model.PlaceRequest;
import com.gabriel.clickbus.places.domain.model.Place;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PlaceModel toModel(Place place) {
        return modelMapper.map(place, PlaceModel.class);
    }

    public List<PlaceModel> toCollectionModel(List<Place> places) {
        return places.stream()
                .map(this::toModel)
                .toList();
    }

    public Place toEntity(PlaceRequest request) {
        return modelMapper.map(request, Place.class);
    }

    public void copyToEntity(PlaceRequest request, Place place) {
        modelMapper.map(request, place);
    }
}
