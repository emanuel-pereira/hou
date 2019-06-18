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
import smarthome.dto.RoomDetailDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.InternalSensor;
import smarthome.model.ReadingList;
import smarthome.model.Room;
import smarthome.model.SensorType;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.InternalSensorRepository;
import smarthome.repository.RoomRepository;
import smarthome.repository.SensorTypeRepository;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        this.startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
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
    @DisplayName("Save a sensorS01 with success")
    void createInternalSensor() {

        this.temperatureDTO.setId(1);
        this.sensorDTOT01.setRoomId("B100");

        when(internalSensorRepository.save(this.sensorT01)).thenReturn(this.sensorT01);
        when(roomRepository.existsById("B100")).thenReturn(true);
        when(sensorTypeRepository.existsById(1L)).thenReturn(true);

        InternalSensorDTO result = internalSensorService.createInternalSensor(this.sensorDTOT01);

        assertEquals(this.sensorDTOT01, result);
    }

    @Test
    @DisplayName("Find a sensor by the Id with success")
    void getSuccess() {

        when(internalSensorRepository.findById("T01")).thenReturn(Optional.of(this.sensorT01));

        InternalSensorDTO expected = this.sensorDTOT01;
        InternalSensorDTO result = internalSensorService.get("T01");

        assertEquals(expected.getSensorBehavior().getName(), result.getSensorBehavior().getName());
    }

    @Test
    @DisplayName("Fetch all sensors that belong to the room with a specified id")
    void fetchSensorsInRoom() {

        SensorType movement = new SensorType("movement");
        InternalSensor sensorM02 = new InternalSensor("I02", "SensorM02", this.startDate, movement, "Presence", this.readingList);

        SensorTypeDTO movementDTO = new SensorTypeDTO("movement");
        SensorBehaviorDTO mBehaviorDTO = new SensorBehaviorDTO("SensorM02", startDate, movementDTO, "Presence");
        InternalSensorDTO sensorDTOM02 = new InternalSensorDTO();
        sensorDTOM02.setSensorBehavior(mBehaviorDTO);

        this.temperatureDTO.setId(1);
        movementDTO.setId(2);
        sensorT01.setRoomId("B100");
        sensorM02.setRoomId("B100");

        when(roomRepository.existsById("B100")).thenReturn(true);
        when(internalSensorRepository.findAll()).thenReturn(Stream.of(this.sensorT01, sensorM02).collect(Collectors.toList()));
        when(sensorTypeRepository.existsById(1L)).thenReturn(true);
        when(sensorTypeRepository.existsById(2L)).thenReturn(true);

        List<InternalSensorDTO> result = internalSensorService.fetchSensorsInRoom("B100");

        assertEquals(2, result.size());
    }

    //Because we are using a specific constructor for tests this one has no coverage
    @Test
    @DisplayName("Test used constructor in running mode")
    void realConstructor() {

        InternalSensorService internalSensorService = new InternalSensorService();

        assertThat(internalSensorService).isInstanceOf(InternalSensorService.class);
    }


}