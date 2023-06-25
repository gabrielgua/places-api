package com.gabriel.clickbus.places.place;

import com.gabriel.clickbus.places.domain.exception.PlaceNotFoundException;
import com.gabriel.clickbus.places.domain.model.Place;
import com.gabriel.clickbus.places.domain.repository.PlaceRepository;
import com.gabriel.clickbus.places.domain.service.PlaceService;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class PlaceServiceTest {

    @Mock
    private PlaceRepository testRepository;
    private Slugify slg;

    private PlaceService testService;

    private Place place;


    @BeforeEach
    void setUp() {
        testService = new PlaceService(testRepository, slg);
        place = new Place();
        place.setName("World Trade Center");
        place.setSlug("New York");
        place.setCity("world-trade-center");
        place.setState("NY");
        place = testRepository.save(place);
    }



    @Test
    void canGetAllPlaces() {
        //when
        testService.listAll();
        //then
        verify(testRepository).findAll();
    }

    @Test
    void canFindById() {
        //given
        Long id = 1L;
        place = new Place();

        //when
        given(testRepository.findById(id)).willReturn(Optional.of(place));
        testService.findById(id);

        //then
        var argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(testRepository).findById(argumentCaptor.capture());

        var expectedId = argumentCaptor.getValue();
        assertThat(expectedId).isEqualTo(id);
    }

    @Test
    void mustThrowPlaceNotFoundException_whenIdDoesNotExists() {
        Long id = 1L;

        given(testRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> testService.findById(id))
                .isInstanceOf(PlaceNotFoundException.class)
                .hasMessageContaining(String.format("Place not found for id: %d", id));
    }

    @Test
    @Disabled
    void findBySlug() {
        //given
        String slug = "new-york";
        //when
        testService.findBySlug(slug);
        //then
        verify(testRepository).findBySlug(slug);
    }

    @Test
    @Disabled
    void findByName() {
        //given
        String name = "New York";
        //when
        testService.findByName(name);
        //then
        verify(testRepository).findByName(name);
    }

    @Test
    @Disabled
    void save() {
    }

    @Test
    void delete() {
        //given
        var place = new Place();
        //when
        testService.delete(place);
        //then
        verify(testRepository).delete(place);
    }
}