package com.gabriel.clickbus.places.api.controller;

import com.gabriel.clickbus.places.api.assembler.PlaceAssembler;
import com.gabriel.clickbus.places.api.model.PlaceModel;
import com.gabriel.clickbus.places.api.model.PlaceRequest;
import com.gabriel.clickbus.places.domain.model.Place;
import com.gabriel.clickbus.places.domain.service.PlaceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("places")
public class PlaceController {

    private PlaceService service;
    private PlaceAssembler assembler;

    @GetMapping
    public List<PlaceModel> list() {
        return assembler.toCollectionModel(service.listAll());
    }

    @GetMapping(params = "id")
    public PlaceModel findById(@RequestParam("id") Long placeId) {
        return assembler.toModel(service.findById(placeId));
    }

    @GetMapping(params = "slug")
    public PlaceModel findBySlug(@RequestParam String slug) {
        return assembler.toModel(service.findBySlug(slug));
    }

    @GetMapping(params = "name")
    public PlaceModel findByNome(@RequestParam String name) {
        return assembler.toModel(service.findByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceModel save(@RequestBody @Valid PlaceRequest request) {
        var place = assembler.toEntity(request);
        return assembler.toModel(service.save(place));
    }

    @PutMapping("/{placeId}")
    public PlaceModel edit(@PathVariable Long placeId, @RequestBody @Valid PlaceRequest request) {
        var place = service.findById(placeId);
        assembler.copyToEntity(request, place);
        return assembler.toModel(service.save(place));
    }

    @DeleteMapping("/{placeId}")
    public void delete(@PathVariable Long placeId) {
        var place = service.findById(placeId);
        service.delete(place);
    }
}
