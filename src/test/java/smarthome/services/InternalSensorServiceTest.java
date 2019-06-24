package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.dto.InternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.exceptions.InternalSensorNotFoundException;
import smarthome.exceptions.RoomNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.model.InternalSensor;
import smarthome.model.ReadingList;
import smarthome.model.SensorList;
import smarthome.model.SensorType;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.InternalSensorRepository;
import smarthome.repository.RoomRepository;
import smarthome.repository.SensorTypeRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class InternalSensorServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    @Mock
    InternalSensorRepository internalSensorRepository;

    @Mock
    SensorTypeRepository sensorTypeRepository;

    private InternalSensorService internalSensorService;
    private RoomService roomService;
    private SensorTypeService sensorTypeService;

    private SensorType temperature;
    private GregorianCalendar startDate;
    private ReadingList readingList;
    private InternalSensor sensorT01;

    private SensorTypeDTO temperatureDTO;
    private SensorBehaviorDTO sensorBehaviorDTO;
    private InternalSensorDTO sensorDTOT01;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.roomService = new RoomService(roomRepository, gridRepository);
        this.sensorTypeService = new SensorTypeService(sensorTypeRepository);
        this.internalSensorService = new InternalSensorService(internalSensorRepository, roomService, sensorTypeService);

        //Internal Sensor T01
        this.temperature = new SensorType("temperature");
        this.startDate = new GregorianCalendar(2018, Calendar.SEPTEMBER, 1, 9, 0);
        this.readingList = new ReadingList();
        this.sensorT01 = new InternalSensor("T01", "SensorT01", this.startDate, this.temperature, "Celsius", this.readingList);

        //Internal Sensor DTO T01
        this.temperatureDTO = new SensorTypeDTO("temperature");
        this.sensorBehaviorDTO = new SensorBehaviorDTO("SensorT01", startDate, temperatureDTO, "Celsius");
        this.sensorDTOT01 = new InternalSensorDTO();
        this.sensorDTOT01.setSensorBehavior(sensorBehaviorDTO);


    }

    @Test
    @DisplayName("Have 2 sensors and return the right number of sensors")
    void findAll() {
        SensorType movement = new SensorType("movement");

        when(internalSensorRepository.findAll()).thenReturn(Stream.of(this.sensorT01, new InternalSensor("I02", "SensorM02", this.startDate, movement, "Presence", this.readingList)).collect(Collectors.toList()));

        assertEquals(2, this.internalSensorService.findAll().size());

    }

    @Test
    @DisplayName("Create a sensor with success")
    void createInternalSensorSuccess() {


        this.sensorDTOT01.setRoomId("B100");

        when(internalSensorRepository.save(this.sensorT01)).thenReturn(this.sensorT01);
        when(roomService.checkIfIDExists("B100")).thenReturn(true);
        when(sensorTypeService.existsByType("temperature")).thenReturn(true);
        when(sensorTypeService.findByType("temperature")).thenReturn(temperature);

        InternalSensorDTO result = internalSensorService.createInternalSensor(this.sensorDTOT01);

        assertEquals(this.sensorDTOT01, result);
    }

    @Test
    @DisplayName("Create a sensor for a room that doesnt exist and get RoomNotFoundException ")
    void createSensorNonexistentRoom() {
        when(roomService.checkIfIDExists("B300")).thenReturn(false);
        Assertions.assertThrows(RoomNotFoundException.class, () -> internalSensorService.createInternalSensor(sensorDTOT01));
    }

    @Test
    @DisplayName("Create a sensor with a sensor type that doesnt exist and get SensorTypeNotFoundException ")
    void createSensorNonexistentType() {
        sensorDTOT01.setRoomId("B100");
        when(roomService.checkIfIDExists("B100")).thenReturn(true);
        when(sensorTypeService.existsByID(1L)).thenReturn(false);
        Assertions.assertThrows(SensorTypeNotFoundException.class, () -> internalSensorService.createInternalSensor(sensorDTOT01));
    }

    @Test
    @DisplayName("Find a sensor by the Id with success")
    void getSuccess() {

        when(internalSensorRepository.findById("T01")).thenReturn(Optional.of(this.sensorT01));

        this.sensorDTOT01.setId("T01");
        String expected = this.sensorDTOT01.getId();
        String result = internalSensorService.get("T01").getId();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Try to find a sensor that doesnt exist and get InternalSensorNotFoundException")
    void getIdDontExist() {
        when(internalSensorRepository.existsById("T01")).thenReturn(false);
        Assertions.assertThrows(InternalSensorNotFoundException.class, () -> internalSensorService.get("T01"));
    }


    @Test
    @DisplayName("Fetch all sensors that belong to the room with a specified id")
    void fetchSensorsInRoomSuccess() {

        SensorType movement = new SensorType("movement");
        InternalSensor sensorM02 = new InternalSensor("I02", "SensorM02", this.startDate, movement, "Presence", this.readingList);

        SensorTypeDTO movementDTO = new SensorTypeDTO("movement");
        SensorBehaviorDTO mBehaviorDTO = new SensorBehaviorDTO("SensorM02", startDate, movementDTO, "Presence");
        InternalSensorDTO sensorDTOM02 = new InternalSensorDTO();
        sensorDTOM02.setSensorBehavior(mBehaviorDTO);

        sensorT01.setRoomId("B100");
        sensorM02.setRoomId("B100");
        sensorDTOT01.setRoomId("B100");
        sensorDTOM02.setRoomId("B100");

        when(roomService.checkIfIDExists("B100")).thenReturn(true);
        when(internalSensorRepository.findAll()).thenReturn(Stream.of(this.sensorT01, sensorM02).collect(Collectors.toList()));
        when(sensorTypeService.existsByID(1L)).thenReturn(true);
        when(sensorTypeService.existsByID(2L)).thenReturn(true);

        List<InternalSensorDTO> result = internalSensorService.fetchSensorsInRoom("B100");

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Fetch all sensors of the room that doesnt exist and get RoomNotFoundException")
    void fetchSensorsNonexistentRoom() {

        when(roomService.checkIfIDExists("B300")).thenReturn(false);

        Assertions.assertThrows(RoomNotFoundException.class, () -> internalSensorService.fetchSensorsInRoom("B300"));
    }

    @Test
    void injectRepository() {
        internalSensorRepository = null;
        InternalSensorService internalSensorService = new InternalSensorService();
        internalSensorService.injectRepository();
        assertThat(internalSensorRepository).isNull();
    }

    //Because we are using a specific constructor for tests this one has no coverage
    @Test
    @DisplayName("Test used constructor in running mode")
    void realConstructor() {

        InternalSensorService internalSensorService = new InternalSensorService();

        assertThat(internalSensorService).isInstanceOf(InternalSensorService.class);
    }

    @Test
    @DisplayName("Find sensors by room with a specified id")
    void findByRoom() {

        SensorType movement = new SensorType("movement");
        InternalSensor sensorM02 = new InternalSensor("I02", "SensorM02", this.startDate, movement, "Presence", this.readingList);

        sensorT01.setRoomId("B100");
        sensorM02.setRoomId("B100");

        when(internalSensorRepository.findAll()).thenReturn(Stream.of(this.sensorT01, sensorM02).collect(Collectors.toList()));

        SensorList list = new SensorList();
        list.addSensor(this.sensorT01);
        list.addSensor(sensorM02);

        SensorList result = internalSensorService.findByRoom("B100");

        assertEquals(list.getLastSensor(), result.getLastSensor());
    }

}
