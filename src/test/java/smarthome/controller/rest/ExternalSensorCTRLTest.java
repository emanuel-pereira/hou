package smarthome.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.Link;
import smarthome.dto.*;
import smarthome.mapper.ExternalSensorMapper;
import smarthome.model.*;
import smarthome.repository.ExternalSensorRepository;
import smarthome.repository.GeoRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.repository.TypeGARepository;
import smarthome.services.ExternalSensorService;
import smarthome.services.GaTypesService;
import smarthome.services.GeoAreaService;
import smarthome.services.SensorTypeService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ExternalSensorCTRLTest {

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

    private ExternalSensorCTRL ctrl;

    private LocationDTO locationDTO;
    private SensorTypeDTO sensorTypeDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private ExternalSensorDTO externalSensorDTO;
    private ExternalSensorDTO externalSensorDTO2;
    private GeographicalAreaDTO geographicalAreaDTO;
    private TypeGADTO typeGADTO;
    private ExternalSensorMapper mapper;
    private ExternalSensor externalSensor;
    private SensorType temperature;
    private Location location;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.geoAreaService = new GeoAreaService(this.geoRepository, this.gaTypesService);
        this.externalSensorService = new ExternalSensorService(this.externalSensorRepository, this.geoAreaService, this.sensorTypeService);
        this.locationDTO = new LocationDTO(22, 12, 45);
        this.sensorTypeDTO = new SensorTypeDTO("temperature");
        sensorBehaviorDTO = new SensorBehaviorDTO("Temperature Sensor", new GregorianCalendar(2019, Calendar.MAY, 5), sensorTypeDTO, "C");
        externalSensorDTO = new ExternalSensorDTO("TEMP1", locationDTO, sensorBehaviorDTO, "NONEXITENTGA");
        typeGADTO = new TypeGADTO("city");
        typeGADTO.setId(2L);
        geographicalAreaDTO = new GeographicalAreaDTO("POR", "Porto", typeGADTO, new OccupationArea(25, 32), new Location(22, 32, 45));
        externalSensorDTO2 = new ExternalSensorDTO("TEMP2", locationDTO, sensorBehaviorDTO, "POR");
        mapper = new ExternalSensorMapper();
        externalSensor = mapper.toEntity(externalSensorDTO2);
        externalSensor.getSensorBehavior().getSensorType().setId(1L);
        temperature = new SensorType("temperature");
        location = new Location(25, 32, 45);
        ctrl = new ExternalSensorCTRL(externalSensorService);

    }


    @Test
    void findAllReturnsSelfRefLinksForEachResourceInBody() {
        when(this.externalSensorRepository.findAll()).thenReturn(Stream.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12), location,
                temperature, "C", new ReadingList()), new ExternalSensor("TEMP2", "Temperature Sensor Porto",
                new GregorianCalendar(2019, Calendar.APRIL, 12), location, temperature, "C", new ReadingList()))
                .collect(Collectors.toList()));
        List<Link> expected1 = Arrays.asList(new Link("/externalSensors").withRel("self"));
        List<Link> result1 = ctrl.findAll().getBody().getLinks();
        assertEquals(expected1, result1);
    }

    @Test
    void findAllReturns2ExternalSensorDTOsInResourceContent() {
        when(this.externalSensorRepository.findAll()).thenReturn(Stream.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12), location,
                temperature, "C", new ReadingList()), new ExternalSensor("TEMP2", "Temperature Sensor Porto",
                new GregorianCalendar(2019, Calendar.APRIL, 12), location, temperature, "C", new ReadingList()))
                .collect(Collectors.toList()));
        int expected = 2;
        int result = ctrl.findAll().getBody().getContent().size();
        assertEquals(expected, result);
    }

    @Test
    void findAllOfEmptyRepositoryReturnsZero() {
        when(this.externalSensorRepository.findAll()).thenReturn(new ArrayList<>());
        long expected = 0;
        long result = ctrl.findAll().getBody().getContent().size();
        assertEquals(expected, result);
    }

    @Test
    void getReturnsHttpStatus200ToPersistedExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        int expected = 200;
        int result = ctrl.get("TEMP1").getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void getReturnsExpectedURILink() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        List<Link> expected1 = Arrays.asList(new Link("/externalSensors").withRel("ExternalSensors"));
        List<Link> result1 = ctrl.get("TEMP1").getBody().getLinks();
        assertEquals(expected1, result1);
    }

    @Test
    void getReturnsObjectOfTypeExternalSensorDTOInBodyContent() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        String expected1 = "smarthome.dto.ExternalSensorDTO";
        String result1 = ctrl.get("TEMP1").getBody().getContent().getClass().getName();
        assertEquals(expected1, result1);
    }

    @Test
    void getReturns404ToNonPersistedExternalSensor() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        int expected = 404;
        int result = ctrl.get("NonPersistedId").getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void getReturns4xxErrorToNullRepository() {
        when(this.externalSensorRepository.findById("TEMP1")).thenReturn(null);
        assertTrue(ctrl.get("UnpersistedId").getStatusCode().is4xxClientError());
    }

    @Test
    void whenSensorIsSuccessfullyRemovedThenHTTPStatusCodeIs204() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected = 204;
        int result = ctrl.removeSensor("TEMP2").getStatusCodeValue();
        assertEquals(expected, result);
    }


    @Test
    void whenTryingToRemoveNonExistentSensorThenHTTPStatusCodeIs404() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected = 404;
        int result = ctrl.removeSensor("NonExistentId").getStatusCodeValue();
        assertEquals(expected, result);
    }


    @Test
    void whenSensorIsSuccessfullyDeactivatedThenHTTPStatusCodeIs200() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected = 200;
        int result = ctrl.deactivate("TEMP2", new GregorianCalendar(2019, 4, 15)).getStatusCodeValue();
        assertEquals(expected, result);
    }


    @Test
    void whenTryingToDeactivateSameSensorTwiceThenHttpStatusIsPreconditionFailed() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected = 412;
        ctrl.deactivate("TEMP2", new GregorianCalendar(2019, 4, 15)).getStatusCodeValue();
        int result = ctrl.deactivate("TEMP2", new GregorianCalendar(2019, 4, 15)).getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void whenSensorDeactivationFailsThenHTTPStatusCodeIs412() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected = 412;
        int result = ctrl.deactivate("TEMP2", new GregorianCalendar(2018, 4, 15)).getStatusCodeValue();
        assertEquals(expected, result);
    }


    @Test
    void whenSensorIsSuccessfullyDeactivatedThenReturnsObjectOfTypeExternalSensorDTOInBodyContent() {
        when(this.externalSensorRepository.findById("TEMP2")).thenReturn(java.util.Optional.of(new ExternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, 4, 12), location,
                temperature, "C", new ReadingList())));
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        String expected = "smarthome.dto.ExternalSensorDTO";
        String result = ctrl.deactivate("TEMP2", new GregorianCalendar(2019, 4, 15)).getBody().getContent().getClass().getName();
        assertEquals(expected, result);
    }


    @Test
    void whenANewExternalSensorIsPersistedThenHttpStatusIsOk() {
        when(this.externalSensorRepository.save(externalSensor)).thenReturn(externalSensor);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByType("temperature")).thenReturn(true);
        int expected = 200;
        int result = ctrl.add(externalSensorDTO2).getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void whenANewExternalSensorIsPersistedThenReturnsAnObjectOfTypeExternalSensorDTOInBodyContent() {
        when(this.externalSensorRepository.save(externalSensor)).thenReturn(externalSensor);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        when(this.sensorTypeService.existsByType("temperature")).thenReturn(true);
        String expected = "smarthome.dto.ExternalSensorDTO";
        String result = ctrl.add(externalSensorDTO2).getBody().getContent().getClass().getName();
        assertEquals(expected, result);
    }

    @Test
    void whenTryingToPersistSensorWithoutGAThenHttpStatusIsPreconditionFailed() {
        when(this.externalSensorRepository.save(externalSensor)).thenReturn(externalSensor);
        when(this.sensorTypeService.existsByType("temperature")).thenReturn(true);
        int expected = 412;
        int result = ctrl.add(externalSensorDTO2).getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void whenTryingToPersistSensorWithoutSensorTypeThenSHttpStatusIsPreconditionFailed() {
        when(this.externalSensorRepository.save(externalSensor)).thenReturn(externalSensor);
        when(this.geoAreaService.checkIfIdExists("POR")).thenReturn(true);
        int expected = 412;
        int result = ctrl.add(externalSensorDTO2).getStatusCodeValue();
        assertEquals(expected, result);
    }
}
