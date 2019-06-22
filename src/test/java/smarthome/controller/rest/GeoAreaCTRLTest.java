package smarthome.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.hateoas.Link;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.TypeGADTO;
import smarthome.model.*;
import smarthome.repository.GeoRepository;
import smarthome.repository.TypeGARepository;
import smarthome.services.GaTypesService;
import smarthome.services.GeoAreaService;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GeoAreaCTRLTest {

    @Mock
    private GeoRepository geoRepository;

    @Mock
    private TypeGARepository typeGARepository;

    private GeoAreaService geoAreaService;
    private GaTypesService gaTypesService;
    private GeoAreaCTRL geoAreaCTRL;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.gaTypesService);
        this.geoAreaCTRL = new GeoAreaCTRL(geoAreaService);

    }


    @Test
    @DisplayName("Tests if the GET request of all geographical areas returns the list of geographical area DTOs as a resource")
    void findAllLinks() throws NoSuchFieldException {
        TypeGA city = new TypeGA("city");
        TypeGA urbanArea = new TypeGA("urban area");

        when(this.geoRepository.findAll()).thenReturn(Stream.of(
                new GeographicalArea("POR", "Porto", city,
                        (new OccupationArea(30, 20)),
                        (new Location(3, 4, 3))),
                new GeographicalArea("LIS", "Lisboa", urbanArea,
                        (new OccupationArea(150, 45)),
                        (new Location(53, 41, 300)))
        )
                .collect(Collectors.toList()));

        Link expected1 = new Link("/geoareas").withSelfRel();
        Link result1 = geoAreaCTRL.findAllGeoAreas().getBody().getLink("self");

        assertEquals(expected1, result1);
    }

    @Test
    @DisplayName("Tests if the GET request of all geographical areas returns three geographical area DTOs")
    void findAllContent() throws NoSuchFieldException {
        TypeGA city = new TypeGA("city");
        TypeGA country = new TypeGA("country");
        TypeGA urbanArea = new TypeGA("urban area");


        when(this.geoRepository.findAll()).thenReturn(Stream.of(
                new GeographicalArea("POR", "Porto", city,
                        (new OccupationArea(30, 20)),
                        (new Location(3, 4, 3))),
                new GeographicalArea("PT", "Portugal", country,
                        (new OccupationArea(150, 45)),
                        (new Location(53, 41, 300))),
                new GeographicalArea("GND", "Gondomar", urbanArea,
                        (new OccupationArea(100, 34)),
                        (new Location(80, 123, 304))))
                .collect(Collectors.toList()));

        int expected = 3;
        int result = geoAreaCTRL.findAllGeoAreas().getBody().getContent().size();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Tests that there is no content when there are no geographical areas")
    void findAllNoGeoAreas() throws NoSuchFieldException {
        when(this.geoRepository.findAll()).thenReturn(new ArrayList<>());

        long expected = 0;
        long result = geoAreaCTRL.findAllGeoAreas().getBody().getContent().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if the GET request of all geographical areas returns an HTTP status OK")
    void findAllStatusOK() throws NoSuchFieldException {
        TypeGA city = new TypeGA("city");
        TypeGA urbanArea = new TypeGA("urban area");

        when(this.geoRepository.findAll()).thenReturn(Stream.of(
                new GeographicalArea("POR", "Porto", city,
                        (new OccupationArea(30, 20)),
                        (new Location(3, 4, 3))),
                new GeographicalArea("LIS", "Lisboa", urbanArea,
                        (new OccupationArea(150, 45)),
                        (new Location(53, 41, 300)))
        )
                .collect(Collectors.toList()));

        int expected = 200;
        int result = geoAreaCTRL.findAllGeoAreas().getStatusCodeValue();
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Tests if the GET request of one geographical area returns the geographical area DTO as a resource")
    void findGeoAreaLinks() throws NoSuchFieldException {
        TypeGA city = new TypeGA("city");

        when(this.geoRepository.findById("POR")).thenReturn(java.util.Optional.of(
                new GeographicalArea("POR", "Porto", city,
                        (new OccupationArea(30, 20)),
                        (new Location(3, 4, 3)))));

        List<Link> expected = Arrays.asList(new Link("/geoareas/POR").withSelfRel(), new Link("/geoareas").withRel("geoAreas"));
        List<Link> result = geoAreaCTRL.findGeoArea("POR").getBody().getLinks();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if the GET request of one geographical area returns the correct geographical area DTO")
    void findGeoAreaContent() throws NoSuchFieldException {
        TypeGA country = new TypeGA("country");
        TypeGADTO countryDto = new TypeGADTO("country");


        when(this.geoRepository.findById("PT")).thenReturn(Optional.of(
                new GeographicalArea("PT", "Portugal", country,
                        (new OccupationArea(150, 45)),
                        (new Location(53, 41, 300)))));


        GeographicalAreaDTO expected = new GeographicalAreaDTO("PT", "Portugal", countryDto,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));
        GeographicalAreaDTO result = geoAreaCTRL.findGeoArea("PT").getBody().getContent();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if the GET request of one geographical area returns an HTTP status OK")
    void findGeoAreaStatusOK() throws NoSuchFieldException {
        TypeGA city = new TypeGA("city");

        when(this.geoRepository.findById("POR")).thenReturn(java.util.Optional.of(
                new GeographicalArea("POR", "Porto", city,
                        (new OccupationArea(30, 20)),
                        (new Location(3, 4, 3)))));

        int expected = 200;
        int result = geoAreaCTRL.findGeoArea("POR").getStatusCodeValue();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to add GA returns the correct geographical area DTO")
    void addGeoAreaContent() throws InvalidParameterException {
        TypeGA country = new TypeGA("country");
        TypeGADTO countryDto = new TypeGADTO("country");

        when(this.gaTypesService.existsByType("country")).thenReturn(true);
        when(this.gaTypesService.findByType("country")).thenReturn(country);

        GeographicalAreaDTO geoAreaDto = new GeographicalAreaDTO("PT", "Portugal", countryDto,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        String expected = "smarthome.dto.GeographicalAreaDTO";
        String result = geoAreaCTRL.addGeoArea(geoAreaDto).getBody().getClass().getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to add GA with invalid type returns empty String")
    void addGeoAreaContentNull() throws InvalidParameterException {
        GeographicalAreaDTO geoAreaDto = new GeographicalAreaDTO("PT", "Portugal", null,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        String expected = "";
        String result = geoAreaCTRL.addGeoArea(geoAreaDto).getBody().toString();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to add GA returns HTTP status Created")
    void addGeoAreaStatusCreated() throws InvalidParameterException {
        TypeGA country = new TypeGA("country");
        TypeGADTO countryDto = new TypeGADTO("country");

        when(this.gaTypesService.existsByType("country")).thenReturn(true);
        when(this.gaTypesService.findByType("country")).thenReturn(country);

        GeographicalAreaDTO geoAreaDto = new GeographicalAreaDTO("PT", "Portugal", countryDto,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        int expected = 201;
        int result = geoAreaCTRL.addGeoArea(geoAreaDto).getStatusCodeValue();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to add GA with invalid type returns HTTP status Precondition Failed")
    void addGeoAreaStatusPreconditionFailed() throws InvalidParameterException {
        GeographicalAreaDTO geoAreaDto = new GeographicalAreaDTO("PT", "Portugal", null,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        int expected = 412;
        int result = geoAreaCTRL.addGeoArea(geoAreaDto).getStatusCodeValue();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Tests if POST request to set parent GA returns the correct geographical area DTO")
    void setParentOfGA() {
        TypeGA country = new TypeGA("country");
        TypeGA city = new TypeGA("city");

        GeographicalArea porto = new GeographicalArea("POR", "Porto", city,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalArea portugal = new GeographicalArea("PT", "Portugal", country,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        when(this.geoRepository.findById("PT")).thenReturn(Optional.of(portugal));
        when(this.geoRepository.findById("POR")).thenReturn(Optional.of(porto));
        when(this.geoAreaService.checkIfIdExists("PT")).thenReturn(true);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);

        String expected = "The Geographical area was set to another";
        Object result = geoAreaCTRL.setParentOfGAWebCTRL("PT","POR").getBody().toString();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to set parent GA returns HTTP status Created")
    void setParentOfGAStatusAccepted() {
        TypeGA country = new TypeGA("country");
        TypeGA city = new TypeGA("city");

        GeographicalArea porto = new GeographicalArea("POR", "Porto", city,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalArea portugal = new GeographicalArea("PT", "Portugal", country,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        when(this.geoRepository.findById("PT")).thenReturn(Optional.of(portugal));
        when(this.geoRepository.findById("POR")).thenReturn(Optional.of(porto));
        when(this.geoAreaService.checkIfIdExists("PT")).thenReturn(true);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);

        int expected = 202;
        int result = geoAreaCTRL.setParentOfGAWebCTRL("PT","POR").getStatusCodeValue();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Tests if POST request to set parent GA returns HTTP status Bad Request")
    void setParentOfGAStatusBadRequest() {
        TypeGA country = new TypeGA("country");
        TypeGA city = new TypeGA("city");

        GeographicalArea porto = new GeographicalArea("POR", "Porto", city,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalArea portugal = new GeographicalArea("PT", "Portugal", country,
                (new OccupationArea(150, 45)),
                (new Location(53, 41, 300)));

        when(this.geoRepository.findById("PT")).thenReturn(Optional.of(portugal));
        when(this.geoRepository.findById("POR")).thenReturn(Optional.of(porto));
        when(this.geoAreaService.checkIfIdExists("PT")).thenReturn(false);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);

        int expected = 400;
        int result = geoAreaCTRL.setParentOfGAWebCTRL("PT","POR").getStatusCodeValue();

        assertEquals(expected, result);
    }





}