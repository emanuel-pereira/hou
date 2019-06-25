package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.TypeGADTO;
import smarthome.model.*;
import smarthome.repository.GeoRepository;
import smarthome.repository.TypeGARepository;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class GeoAreaServiceTest {

    @Mock
    private GeoRepository geoRepository;

    @Mock
    private TypeGARepository typeGARepository;


    private GeoAreaService geoAreaService;
    private GaTypesService gaTypesService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.gaTypesService);
    }

    @Test
    void addNewGeoArea() {
        TypeGA type = new TypeGA("city");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.gaTypesService.existsByType(type.getType())).thenReturn(true);
        when(this.gaTypesService.findByType(type.getType())).thenReturn(type);

        assertEquals(dto, this.geoAreaService.addNewGeoArea(dto));
    }


    @Test
    void notAddAreaTypeDoesNotExist() {
        TypeGA type = new TypeGA("urban area");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.gaTypesService.existsByType("urban area")).thenReturn(false);
        when(this.gaTypesService.findByType(type.getType())).thenReturn(type);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullName() {
        TypeGA type = new TypeGA("city");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", null,
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.gaTypesService.existsByType("city")).thenReturn(true);
        when(this.gaTypesService.findByType("city")).thenReturn(type);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullOccupationArea() {
        TypeGA type = new TypeGA("city");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                null,
                (new Location(3, 4, 3)));

        when(this.gaTypesService.existsByType("city")).thenReturn(true);
        when(this.gaTypesService.findByType("city")).thenReturn(type);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullLocation() {
        TypeGA type = new TypeGA("city");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)), null);

        when(this.gaTypesService.existsByType("city")).thenReturn(true);
        when(this.gaTypesService.findByType("city")).thenReturn(type);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullParameters() {
        TypeGA type = new TypeGA("city");

        GeographicalAreaDTO dto = new GeographicalAreaDTO(null, null, (new TypeGADTO("city")), null, null);

        when(this.gaTypesService.existsByType("city")).thenReturn(true);
        when(this.gaTypesService.findByType("city")).thenReturn(type);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }


    @Test
    void findById() throws NoSuchFieldException {
        TypeGA type = new TypeGA("urban area");

        GeographicalAreaDTO dto = new GeographicalAreaDTO("LIS", "Lisboa",
                (new TypeGADTO("urban area")),
                (new OccupationArea(150, 45)),
                (new Location(3, 4, 3)));

        when(this.geoRepository.findById("LIS")).thenReturn(java.util.Optional.of
                (new GeographicalArea("LIS", "Lisboa", type,
                        (new OccupationArea(150, 45)),
                        (new Location(3, 4, 3)))));

        GeographicalAreaDTO resultDto = geoAreaService.findById("LIS");

        assertEquals(dto, resultDto);
    }

    @Test
    void findByIdAreaDoesNotExist() {
        TypeGA type = new TypeGA("urban area");

        when(this.geoRepository.findById("LIS")).thenReturn(java.util.Optional.of
                (new GeographicalArea("LIS", "Lisboa", type,
                        (new OccupationArea(150, 45)),
                        (new Location(3, 4, 3)))));

        Assertions.assertThrows(NoSuchFieldException.class, () -> geoAreaService.findById("GND"));
    }


    @Test
    void findAll() {
        TypeGA type = new TypeGA("country");
        TypeGA type2 = new TypeGA("city");
        TypeGA type3 = new TypeGA("urban area");

        when(this.geoRepository.findAll()).thenReturn((Stream.of(
                (new GeographicalArea("PT", "Portugal", type,
                        (new OccupationArea(1500, 450)),
                        (new Location(20, 90, 2000)))),
                (new GeographicalArea("LIS", "Lisboa", type2,
                        (new OccupationArea(150, 45)),
                        (new Location(53, 41, 300)))),
                (new GeographicalArea("GND", "Gondomar", type3,
                        (new OccupationArea(100, 34)),
                        (new Location(80, 123, 304))))))
                .collect(Collectors.toList()));

        int size = geoAreaService.findAll().size();
        assertEquals(3, size);
    }


    @Test
    void setParentGaWebCTRLSave() {


        TypeGA type = new TypeGA("country");
        TypeGA type2 = new TypeGA("city");

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("LIS", "Lisboa", type2, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("PT", "Portugal", type, oc, loc);


        GA1.setParentGa(GA2);
        geoRepository.save(GA1);
        geoRepository.save(GA2);


        String result = GA1.getParentGa().getDesignation();
        String expectedResult = "Portugal";
        assertEquals(expectedResult, result);

    }


    @Test
    void setParentGaWebCTRLTrue() {
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

        boolean result = geoAreaService.setParentGaWebCTRL("PT", "POR");


        assertTrue(result);
    }

    @Test
    void setParentGaWebCTRLFalse() {

        TypeGA city = new TypeGA("city");

        GeographicalArea porto = new GeographicalArea("POR", "Porto", city,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));


        when(this.geoRepository.findById("POR")).thenReturn(Optional.of(porto));
        when(this.geoRepository.findById("POR")).thenReturn(Optional.of(porto));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(false);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(false);

        boolean result = geoAreaService.setParentGaWebCTRL("POR", "POR");


        assertFalse(result);
    }


    @Test
    void checkIfIdExists() {


        when(this.geoRepository.existsById("PT")).thenReturn(true);
        assertTrue(geoAreaService.checkIfIdExists("PT"));

    }

    @Test
    void size() {


        when(this.geoRepository.count()).thenReturn(3L);
        assertEquals(3, geoAreaService.size());

    }


}

