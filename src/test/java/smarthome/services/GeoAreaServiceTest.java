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
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.OccupationArea;
import smarthome.model.TypeGA;
import smarthome.repository.GeoRepository;
import smarthome.repository.TypeGARepository;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class GeoAreaServiceTest {

    @Mock
    private GeoRepository geoRepository;

    @Mock
    private TypeGARepository typeGARepository;

    private GeoAreaService geoAreaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.typeGARepository);
    }

    @Test
    void addNewGeoArea() {
        TypeGA type = new TypeGA("city");

        GeographicalArea area = new GeographicalArea("POR", "Porto", type,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.typeGARepository.existsByType("city")).thenReturn(true);
        when(this.typeGARepository.findByType("city")).thenReturn(type);
        when(this.geoRepository.save(area)).thenReturn(area);

        assertEquals(dto, this.geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddAreaTypeDoesNotExist() {
        TypeGA type = new TypeGA("urban area");

        GeographicalArea area = new GeographicalArea("POR", "Porto", type,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.typeGARepository.existsByType("urban area")).thenReturn(false);
        when(this.typeGARepository.findByType("urban area")).thenReturn(null);
        when(this.geoRepository.save(area)).thenReturn(area);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullName() {
        TypeGA type = new TypeGA("city");

        GeographicalArea area = new GeographicalArea("POR", null, type,
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", null,
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)),
                (new Location(3, 4, 3)));

        when(this.typeGARepository.existsByType("city")).thenReturn(true);
        when(this.typeGARepository.findByType("city")).thenReturn(type);
        when(this.geoRepository.save(area)).thenReturn(area);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullOccupationArea() {
        TypeGA type = new TypeGA("city");

        GeographicalArea area = new GeographicalArea("POR", "Porto", type,
                null,
                (new Location(3, 4, 3)));

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                null,
                (new Location(3, 4, 3)));

        when(this.typeGARepository.existsByType("city")).thenReturn(true);
        when(this.typeGARepository.findByType("city")).thenReturn(type);
        when(this.geoRepository.save(area)).thenReturn(area);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullLocation() {
        TypeGA type = new TypeGA("city");

        GeographicalArea area = new GeographicalArea("POR", "Porto", type,
                (new OccupationArea(30, 20)), null);

        GeographicalAreaDTO dto = new GeographicalAreaDTO("POR", "Porto",
                (new TypeGADTO("city")),
                (new OccupationArea(30, 20)), null);

        when(this.typeGARepository.existsByType("city")).thenReturn(true);
        when(this.typeGARepository.findByType("city")).thenReturn(type);
        when(this.geoRepository.save(area)).thenReturn(area);

        Assertions.assertThrows(InvalidParameterException.class, () -> geoAreaService.addNewGeoArea(dto));
    }

    @Test
    void notAddInvalidAreaNullParameters() {
        TypeGA type = new TypeGA("city");

        GeographicalArea area = new GeographicalArea(null, null, type, null, null);

        GeographicalAreaDTO dto = new GeographicalAreaDTO(null, null, (new TypeGADTO("city")),null, null);

        when(this.typeGARepository.existsByType("city")).thenReturn(true);
        when(this.typeGARepository.findByType("city")).thenReturn(type);
        when(this.geoRepository.save(area)).thenReturn(area);

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

        GeographicalAreaDTO dto = new GeographicalAreaDTO("LIS", "Lisboa",
                (new TypeGADTO("urban area")),
                (new OccupationArea(150, 45)),
                (new Location(3, 4, 3)));

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

}

