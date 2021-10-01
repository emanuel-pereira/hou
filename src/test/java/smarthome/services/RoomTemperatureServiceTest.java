package smarthome.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import smarthome.model.validations.Name;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.InternalSensorRepository;
import smarthome.repository.RoomRepository;

import static org.junit.jupiter.api.Assertions.*;

class RoomTemperatureServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ModelMapper mapper;
    private Name temp = new Name("temperature");
    private InternalSensorService sensorService;
    private SensorTypeService sensorTypeService;
    private RoomTemperatureService roomTemperatureService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.roomTemperatureService = new RoomTemperatureService();}




    @Test
    void getRoomSensors() {



    }

    @Test
    void getBestSensorReadings() {
    }

    @Test
    void checkIfSensorHasReadings() {
    }

    @Test
    void checkIfSensorHasReadingsInDay() {
    }

    @Test
    void getCurrentTempInRoom() {
    }

    @Test
    void getMaxTempInRoom() {
    }

    @Test
    void convertStringToCalendar() {
    }
}