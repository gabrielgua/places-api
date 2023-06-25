package com.gabriel.clickbus.places.domain.repository;

import com.gabriel.clickbus.places.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);

    Optional<Place> findBySlug(String slug);
}

