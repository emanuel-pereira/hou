package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.*;
import smarthome.services.ComfortLevelService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseGA;
import static smarthome.model.House.getHouseRoomList;

class ComfortLevelCTRLTest {

    private Location loc = new Location(20, 20, 2);
    private Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    private OccupationArea oc = new OccupationArea(2, 5);
    private GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    private ComfortLevelCTRL controller;

    @BeforeEach
    private void resetMySingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {

        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);

        this.controller = new ComfortLevelCTRL();

    }

    @Test
    @DisplayName("Check if GA has sensors with readings and return true")
    void gAHasTemperatureSensorWithReadings() {

        GregorianCalendar startDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Location loc = new Location(20, 20, 20);

        ExternalSensor sensor = new ExternalSensor("101", "A", startDate, loc, sensorType, "C", readingList);

        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGa().getSensorList().add(sensor);

        boolean result = this.controller.validateGeoAreaHasTemperatureSensorWithReadings();
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if GA has sensors with readings and return false")
    void gAHasntTemperatureSensorWithReadings() {

        GregorianCalendar startDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
        SensorType sensorType = new SensorType("rainfall");

        ReadingList readingList = new ReadingList();
        Location loc = new Location(20, 20, 20);

        ExternalSensor sensor = new ExternalSensor("101", "A", startDate, loc, sensorType, "C", readingList);

        GeographicalArea geographicalArea = getHouseGA();
        geographicalArea.getSensorListInGa().getSensorList().add(sensor);

        boolean result = this.controller.validateGeoAreaHasTemperatureSensorWithReadings();
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if house has room as return true")
    void houseHasRooms() {

        RoomList roomList = getHouseRoomList();

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        roomList.getRoomList().add(room);

        boolean result = this.controller.validateHouseHasRooms();
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if house has room as return false")
    void houseHasNoRooms() {

        assertEquals(0, getHouseRoomList().getRoomList().size());

        boolean result = this.controller.validateHouseHasRooms();
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if rooms have temperature sensors")
    void roomsHaveTemperatureSensors() {
        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().getRoomList().add(room);
        GregorianCalendar startDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
        SensorType sensorType = new SensorType("temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new InternalSensor("101", "A", startDate, sensorType, "C", readingList);
        room.getSensorListInRoom().getSensorList().add(sensor);

        assertTrue(this.controller.validateRoomsHaveTemperatureSensors());
        assertTrue(this.controller.validateTemperatureSensorsHaveReadings());
    }

    @Test
    @DisplayName("Check size of RoomListDTO")
    void getRoomListDTO() {

        GregorianCalendar startDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, startDate, "C");
        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().getSensorList().add(sensor1);

        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);

        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType1, "C", readingList);
        room2.getSensorListInRoom().getSensorList().add(sensor2);

        roomList.getRoomList().add(room1);
        roomList.getRoomList().add(room2);

        double result = this.controller.getRoomListDTO().size();

        assertEquals(2, result);

    }

    @Test
    @DisplayName("Calculate thermal comfort (max)")
    void calculateThermalComfort() {

        GregorianCalendar startDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);

        RoomList roomList = getHouseRoomList();
        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        ReadingList readingList = new ReadingList();
        Reading reading = new Reading(30, startDate, "C");

        readingList.addReading(reading);

        SensorType sensorType1 = new SensorType("temperature");
        Sensor sensor1 = new InternalSensor("101", "A", startDate, sensorType1, "C", readingList);
        room1.getSensorListInRoom().getSensorList().add(sensor1);

        Room room2 = getHouseRoomList().createNewRoom("R02", "room", 1, 2, 2.5, 2);

        ReadingList readingList1 = new ReadingList();
        SensorType sensorType2 = new SensorType("humidity");
        Sensor sensor2 = new InternalSensor("102", "B", startDate, sensorType2, "C", readingList1);
        room2.getSensorListInRoom().getSensorList().add(sensor2);

        roomList.getRoomList().add(room1);
        roomList.getRoomList().add(room2);

        RoomMapper mapper = new RoomMapper();
        RoomDTO roomDTO = mapper.toDto(room1);

        GeographicalArea geographicalArea = getHouseGA();
        SensorList sensorListGA = geographicalArea.getSensorListInGa();
        ReadingList readingsGA = new ReadingList();
        Reading readingGA = new Reading(30, startDate, "C");
        readingsGA.addReading(readingGA);

        Sensor sensorGA = new ExternalSensor("101EX", "ExtSensor", startDate, loc, sensorType1, "C", readingsGA);
        sensorListGA.getSensorList().add(sensorGA);


        boolean maxOrMin = true;
        int category = 1;

        GregorianCalendar resultStartDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 1);
        GregorianCalendar resultEndDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 3);

        String result = this.controller.calculateThermalComfort(roomDTO, maxOrMin, category, resultStartDate, resultEndDate);
        String expected = "No readings outside the comfort level were found in the selected time interval.\n";
        assertEquals(expected, result);
    }

}
