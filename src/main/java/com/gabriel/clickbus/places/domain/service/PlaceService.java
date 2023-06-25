package com.gabriel.clickbus.places.domain.service;

import com.gabriel.clickbus.places.domain.exception.PlaceNotFoundException;
import com.gabriel.clickbus.places.domain.model.Place;
import com.gabriel.clickbus.places.domain.repository.PlaceRepository;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {

    private PlaceRepository repository;
    private Slugify slugify;


    @Transactional(readOnly = true)
    public List<Place> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Place findById(Long placeId) {
        return repository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
    }

    @Transactional(readOnly = true)
    public Place findBySlug(String slug) {
        return repository.findBySlug(slug)
                .orElseThrow(() -> new PlaceNotFoundException(slug));
    }

    @Transactional(readOnly = true)
    public Place findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new PlaceNotFoundException(name));
    }

    @Transactional
    public Place save(Place place) {
        place.setSlug(slugify.slugify(place.getName()));
        return repository.save(place);
    }

    @Transactional
    public void delete(Place place) {
        repository.delete(place);
    }
}
