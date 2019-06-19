package smarthome.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smarthome.dto.InternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.mapper.InternalSensorMapper;
import smarthome.model.InternalSensor;
import smarthome.model.ReadingList;
import smarthome.model.SensorType;
import smarthome.repository.*;
import smarthome.services.GaTypesService;
import smarthome.services.InternalSensorService;
import smarthome.services.RoomService;
import smarthome.services.SensorTypeService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class InternalSensorCTRLTest {


    @Mock
    private InternalSensorRepository internalSensorRepository;
    private InternalSensorService internalSensorService;

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    private SensorTypeService sensorTypeService;

    @Mock
    private RoomRepository roomRepository;
    private RoomService roomService;

    @Mock
    private TypeGARepository typeGARepository;
    private GaTypesService gaTypesService;

    @Mock
    private HouseGridRepository houseGridRepository;

    private InternalSensorCTRL ctrl;

    private SensorTypeDTO sensorTypeDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private InternalSensorDTO sensorDTO;
    private InternalSensorDTO sensorDTO2;
    private InternalSensorMapper mapper;
    private InternalSensor sensor;
    private InternalSensor sensor2;

    private SensorType temperature;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.sensorTypeService = new SensorTypeService(this.sensorTypeRepository);
        this.gaTypesService = new GaTypesService(this.typeGARepository);
        this.roomService = new RoomService(this.roomRepository, this.houseGridRepository);
        this.internalSensorService = new InternalSensorService(this.internalSensorRepository, this.roomService, this.sensorTypeService);
        this.sensorTypeDTO = new SensorTypeDTO("temperature");
        sensorTypeDTO.setId(1L);
        sensorBehaviorDTO = new SensorBehaviorDTO("Temperature Sensor", new GregorianCalendar(2019, Calendar.MAY, 5), sensorTypeDTO, "C");
        sensorDTO = new InternalSensorDTO("TEMP2", sensorBehaviorDTO, "KIT1");
        sensorDTO2 = new InternalSensorDTO("TEMP3", sensorBehaviorDTO, "KIT1");
        mapper = new InternalSensorMapper();
        sensor = mapper.toEntity(sensorDTO);
        sensor2=mapper.toEntity(sensorDTO2);
        temperature = new SensorType("temperature");
        ctrl = new InternalSensorCTRL(internalSensorService);

    }


    @Test
    void findAllReturnsSelfRefLinksForEachResourceInBody() {

        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(new InternalSensor("TEMP1",
                "Temperature Sensor Kitchen", new GregorianCalendar(2019, Calendar.APRIL, 12),temperature, "C", new ReadingList()), new InternalSensor("TEMP2", "Temperature Sensor Bedroom",
                new GregorianCalendar(2019, Calendar.APRIL, 12), temperature, "C", new ReadingList()))
                .collect(Collectors.toList()));
        List<Link> expected1 = Arrays.asList(new Link("/internalSensors").withRel("self"));
        List<Link> result1 = ctrl.findAll().getBody().getLinks();
        assertEquals(expected1, result1);
    }

    @Test
    void findAllReturns2ExternalSensorDTOsInResourceContent() {
        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(new InternalSensor("TEMP1",
                "Temperature Sensor Kitchen", new GregorianCalendar(2019, Calendar.APRIL, 12),temperature, "C", new ReadingList()), new InternalSensor("TEMP2", "Temperature Sensor Bedroom",
                new GregorianCalendar(2019, Calendar.APRIL, 12), temperature, "C", new ReadingList()))
                .collect(Collectors.toList()));
        int expected = 2;
        int result = ctrl.findAll().getBody().getContent().size();
        assertEquals(expected, result);
    }

    @Test
    void findAllOfEmptyRepositoryReturnsZero() {
        when(this.internalSensorRepository.findAll()).thenReturn(new ArrayList<>());
        long expected = 0;
        long result = ctrl.findAll().getBody().getContent().size();
        assertEquals(expected, result);
    }

    @Test
    void getReturnsHttpStatus200ToPersistedExternalSensor() {
        when(this.internalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new InternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12),
                temperature, "C", new ReadingList())));
        int expected = 200;
        int result = ctrl.get("TEMP1").getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void getReturnsExpectedURILink() {
        when(this.internalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new InternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12),
                temperature, "C", new ReadingList())));
        List<Link> expected1 = Arrays.asList(new Link("/internalSensors").withRel("InternalSensors"));
        List<Link> result1 = ctrl.get("TEMP1").getBody().getLinks();
        assertEquals(expected1, result1);
    }

    @Test
    void getReturnsObjectOfTypeExternalSensorDTOInBodyContent() {
        when(this.internalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new InternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12),
                temperature, "C", new ReadingList())));
        String expected1 = "smarthome.dto.InternalSensorDTO";
        String result1 = ctrl.get("TEMP1").getBody().getContent().getClass().getName();
        assertEquals(expected1, result1);
    }

    @Test
    void getReturns404ToNonPersistedExternalSensor() {
        when(this.internalSensorRepository.findById("TEMP1")).thenReturn(java.util.Optional.of(new InternalSensor("TEMP1",
                "Temperature Sensor ISEP", new GregorianCalendar(2019, Calendar.APRIL, 12),
                temperature, "C", new ReadingList())));
        int expected = 404;
        int result = ctrl.get("NonPersistedId").getStatusCodeValue();
        assertEquals(expected, result);
    }

    @Test
    void getReturns4xxErrorToNullRepository() {
        when(this.internalSensorRepository.findById("TEMP1")).thenReturn(null);
        assertTrue(ctrl.get("NonPersistedId").getStatusCode().is4xxClientError());
    }

    @Test
    void whenANewExternalSensorIsPersistedThenHttpStatusIsOk() {
        when(this.internalSensorRepository.save(sensor)).thenReturn(sensor);
        when(this.roomService.checkIfIDExists("KIT1")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected=200;
        int result=ctrl.create(sensorDTO).getStatusCodeValue();
        assertEquals(expected,result);
    }


    @Test
    void whenANewExternalSensorIsPersistedThenReturnsAnObjectOfTypeExternalSensorDTOInBodyContent() {
        when(this.internalSensorRepository.save(sensor)).thenReturn(sensor);
        when(this.roomService.checkIfIDExists("KIT1")).thenReturn(true);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        String expected= "smarthome.dto.InternalSensorDTO";
        String result=ctrl.create(sensorDTO).getBody().getContent().getClass().getName();
        assertEquals(expected,result);
    }

    @Test
    void whenTryingToPersistSensorWithoutGAThenHttpStatusIsPreconditionFailed() {
        when(this.internalSensorRepository.save(sensor)).thenReturn(sensor);
        when(this.sensorTypeService.existsByID(1L)).thenReturn(true);
        int expected=412;
        int result=ctrl.create(sensorDTO).getStatusCodeValue();
        assertEquals(expected,result);
    }

    @Test
    void whenTryingToPersistSensorWithoutSensorTypeThenSHttpStatusIsPreconditionFailed() {
        when(this.internalSensorRepository.save(sensor)).thenReturn(sensor);
        when(this.roomService.checkIfIDExists("KIT1")).thenReturn(true);
        int expected=412;
        int result=ctrl.create(sensorDTO).getStatusCodeValue();
        assertEquals(expected,result);
    }

    @Test
    void fetchSensorsInRoomReturnsSelfRefLinksForEachResourceInBody() {
        when(roomService.checkIfIDExists("KIT1")).thenReturn(true);
        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(sensor,sensor2)
                .collect(Collectors.toList()));
        List<Link> expected1 = Arrays.asList(new Link("/internalSensors").withRel("self"));
        List<Link> result1 = ctrl.fetchSensorsInRoom("KIT1").getBody().getLinks();
        assertEquals(expected1, result1);
    }


    @Test
    void fetchSensorsInRoomReturns2ResourcesInContentBody() {
        when(roomService.checkIfIDExists("KIT1")).thenReturn(true);
        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(sensor,sensor2)
                .collect(Collectors.toList()));
        int expected = 2;
        int result = ctrl.fetchSensorsInRoom("KIT1").getBody().getContent().size();
        assertEquals(expected, result);
    }

    @Test
    void fetchSensorsInNonExistentRoomReturnsEmptyArrayOfLinks() {
        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(sensor,sensor2)
                .collect(Collectors.toList()));
        List<Link> expected1 = Arrays.asList();
        List<Link> result1 = ctrl.fetchSensorsInRoom("NonExistentRoom").getBody().getLinks();
        assertEquals(expected1, result1);
    }

    @Test
    void fetchSensorsInRoomReturnsEmptyListOfResourcesInContentBody() {
        when(roomService.checkIfIDExists("KIT1")).thenReturn(true);
        when(this.internalSensorRepository.findAll()).thenReturn(Stream.of(sensor,sensor2)
                .collect(Collectors.toList()));
        int expected = 0;
        int result = ctrl.fetchSensorsInRoom("NonExistentRoom").getBody().getContent().size();
        assertEquals(expected, result);
    }


}