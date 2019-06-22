package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.dto.*;
import smarthome.exceptions.ExternalSensorNotFoundException;
import smarthome.exceptions.GeographicalAreaNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.mapper.ExternalSensorMapper;
import smarthome.model.ExternalSensor;
import smarthome.model.Location;
import smarthome.model.SensorType;
import smarthome.repository.ExternalSensorRepository;
import smarthome.repository.GeoRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.repository.TypeGARepository;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ExternalSensorServiceTest {

    @Mock
    private ExternalSensorRepository externalSensorRepository;
    private ExternalSensorService externalSensorService;

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    private SensorTypeService sensorTypeService;

    @Mock
    private GeoRepository geoRepository;
    private GeoAreaService geoAreaService;

    @Mock
    private TypeGARepository typeGARepository;
    private GaTypesService gaTypesService;

    private LocationDTO locationDTO;
    private SensorTypeDTO sensorTypeDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private ExternalSensorDTO sensorDTO1;
    private ExternalSensorDTO sensorDTO2;
    private ExternalSensorDTO sensorDTO3;
    private TypeGADTO typeGADTO;
    private ExternalSensorMapper mapper;
    private ExternalSensor sensor1;
    private ExternalSensor sensor2;
    private ExternalSensor sensor3;
    private SensorType temperature;
    private Location location;



    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.gaTypesService);
        this.externalSensorService = new ExternalSensorService(this.externalSensorRepository, this.geoAreaService, this.sensorTypeService);
        this.externalSensorService = new ExternalSensorService(this.externalSensorRepository, this.geoAreaService, this.sensorTypeService);
        this.locationDTO = new LocationDTO(22, 12, 45);
        this.sensorTypeDTO = new SensorTypeDTO("temperature");
        sensorBehaviorDTO = new SensorBehaviorDTO("Temperature Sensor", new GregorianCalendar(2019, Calendar.MAY, 5), sensorTypeDTO, "C");
        sensorDTO1 = new ExternalSensorDTO("TEMP2", locationDTO, sensorBehaviorDTO, "POR");
        sensorDTO2 = new ExternalSensorDTO("TEMP1", locationDTO, sensorBehaviorDTO, "LIS");
        sensorDTO3 = new ExternalSensorDTO("TEMP3", locationDTO, sensorBehaviorDTO, "POR");
        typeGADTO = new TypeGADTO("city");
        typeGADTO.setId(2L);
        sensorDTO1 = new ExternalSensorDTO("TEMP2", locationDTO, sensorBehaviorDTO, "POR");
        mapper = new ExternalSensorMapper();
        sensor1 = mapper.toEntity(sensorDTO1);
        sensor1.getSensorBehavior().getSensorType().setId(1L);
        sensor2=mapper.toEntity(sensorDTO2);
        sensor2.getSensorBehavior().getSensorType().setId(1L);
        sensor3=mapper.toEntity(sensorDTO3);
        sensor3.getSensorBehavior().getSensorType().setId(1L);
        temperature = new SensorType("temperature");
        location = new Location(25, 32, 45);

    }


    @Test
    void whenRepositoryHasTwoSensorsThenReturnSize2() {
        when(this.externalSensorRepository.findAll()).thenReturn(Stream.of(sensor1,sensor2)
                .collect(Collectors.toList()));
        assertEquals(2, externalSensorService.findAll().size());


    }

    @Test
    void whenRepositoryFindByIdReturnsSensorThenServiceGetMethodReturnsSensorId() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(sensor2));
        String result = externalSensorService.get("TEMP1").getId();
        assertEquals("TEMP1", result);
    }

    @Test
    void whenRepositoryFindByIdReturnsNullThenServiceGetMethodForThatIdThrowsExternalSensorNotFoundException() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(null);
        Assertions.assertThrows(ExternalSensorNotFoundException.class, () -> externalSensorService.get("TEMP1"));
    }

    @Test
    void whenRepositorySavesExternalSensorThenServiceCreateMethodReturnsRespectiveExternalSensorDTO() {
        when(this.externalSensorRepository.save(sensor1)).thenReturn(sensor1);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByType("temperature")).thenReturn(true);
        when(this.sensorTypeService.findByType("temperature")).thenReturn(sensor1.getSensorBehavior().getSensorType());
        ExternalSensorDTO result = externalSensorService.createExternalSensor(sensorDTO1);
        assertEquals(sensorDTO1, result);
    }

    @Test
    void whenRepositorySaveReturnsFalseWithNonExistentGeoAreaThenServiceCreateMethodThrowsGeographicalAreaNotFoundException() {
        when(this.typeGARepository.existsByType(typeGADTO.getType())).thenReturn(true);
        when(this.externalSensorRepository.existsById("TEMP2")).thenReturn(false);
        Assertions.assertThrows(GeographicalAreaNotFoundException.class, () -> externalSensorService.createExternalSensor(sensorDTO2));
    }

    @Test
    void whenRepositorySaveReturnsFalseWithUnexistentSensorTypeThenServiceCreateMethodThrowsSensorTypeNotFoundException() {
        when(this.externalSensorRepository.existsById("TEMP2")).thenReturn(false);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        Assertions.assertThrows(SensorTypeNotFoundException.class, () -> externalSensorService.createExternalSensor(sensorDTO1));
    }


    @Test
    void repositorySuccessfullyDeletesExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(sensor1)).thenReturn(null);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        String deletedExternalSensorId = externalSensorService.removeSensor("TEMP2").getId();
        assertEquals("TEMP2", deletedExternalSensorId);
    }

    @Test
    void repositorySuccessfullyDeactivatesExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(sensor1));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        boolean result = externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7)).getSensorBehaviorDTO().isActive();
        assertFalse(result);
    }

    @Test
    void serviceThrowsIllegalArgumentExceptionWhenDeactivatingSameSensorTwice() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(sensor1));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7));
        Assertions.assertThrows(IllegalArgumentException.class, () -> externalSensorService.deactivate("TEMP2", new GregorianCalendar(2019, 7, 7)));
    }

    @Test
    void serviceThrowsInvalidParameterExceptionWhenPauseDateBeforeStartDate() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(sensor1));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        Assertions.assertThrows(InvalidParameterException.class, () -> externalSensorService.deactivate("TEMP2", new GregorianCalendar(2016, 7, 7)));
    }

    @Test
    public void testInstanceOf() {
        ExternalSensorService externalSensorService = new ExternalSensorService();
        assertThat(externalSensorService).isInstanceOf(ExternalSensorService.class);
    }

    @Test
    public void testInit() {
        externalSensorRepository = null;
        ExternalSensorService externalSensorService1 = new ExternalSensorService();
        externalSensorService1.init();
        assertThat(externalSensorRepository).isNull();
    }

    @Test
    void fetchSensorsInGeoAreaSuccess() {
        when(geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(externalSensorRepository.findAll()).thenReturn(Stream.of(sensor1,sensor3)
                .collect(Collectors.toList()));
        when(sensorTypeService.existsByID(1L)).thenReturn(true);

        List<ExternalSensorDTO> result = externalSensorService.fetchSensorsInGeoArea("POR");

        assertEquals(2, result.size());
    }

    @Test
    void fetchSensorsInGeoAreaWithoutSensorsReturnsGeographicalAreaNotFoundException() {

        when(geoAreaService.checkIfIdExists("BCN")).thenReturn(false);

        Assertions.assertThrows(GeographicalAreaNotFoundException.class, () -> externalSensorService.fetchSensorsInGeoArea("BCN"));
    }


}
