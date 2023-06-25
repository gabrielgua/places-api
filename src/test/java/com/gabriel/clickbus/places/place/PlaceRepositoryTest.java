package com.gabriel.clickbus.places.place;

import com.gabriel.clickbus.places.domain.model.Place;
import com.gabriel.clickbus.places.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository testRepository;

    @AfterEach
    void tearDown() {
        testRepository.deleteAll();
    }

    @Test
    void mustReturnBodyWithAnNotNullId_whenSavingValidPlace() {
        // given
        Place validPlace = new Place();

        validPlace.setName("World Trade Center");
        validPlace.setSlug("New York");
        validPlace.setCity("world-trade-center");
        validPlace.setState("NY");

        // when
        var savedPlace = testRepository.save(validPlace);

        assertThat(savedPlace.getId()).isNotNull();
    }

    @Test
    void mustReturnBodyWithAnNotNullCreatedAtAndUpdatedAtDate_whenSavingValidPlace() {
        // given
        Place validPlace = new Place();

        validPlace.setName("World Trade Center");
        validPlace.setSlug("New York");
        validPlace.setCity("world-trade-center");
        validPlace.setState("NY");

        // when
        var savedPlace = testRepository.save(validPlace);

        assertThat(savedPlace.getCreatedAt()).isNotNull();
        assertThat(savedPlace.getUpdatedAt()).isNotNull();
    }
}
