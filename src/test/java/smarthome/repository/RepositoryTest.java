package smarthome.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.controller.cli.NewGeographicalAreaCTRL;
import smarthome.controller.cli.RemoveGASensorCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;
import static smarthome.model.TypeGAList.addTypeGA;
import static smarthome.model.TypeGAList.getTypeGAListInstance;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {
    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance1 = House.class.getDeclaredField("theHouse");
        instance1.setAccessible(true);
        instance1.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    //@DisplayName("Ensure that the typeGA repository has 3 types of GAs persisted")
    void testNrOfElementsInTypeGARepository() {
        TypeGA city = new TypeGA("city");
        Repositories.getTypeGARepository().save(city);
        TypeGA district = new TypeGA("district");
        Repositories.getTypeGARepository().save(district);
        TypeGA country = new TypeGA("country");
        Repositories.getTypeGARepository().save(country);
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertEquals(3, typeGARepSize);
    }


    @Test
    //@DisplayName("Ensure that the typeGA repository has zero types of GAs persisted")
    void testNrOfElementsInTypeGARepositoryIsZero() {
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertEquals(0, typeGARepSize);
    }

    @Test
    //@DisplayName("Ensure that the sensorType repository has 2 types persisted")
    void testNrOfElementsInSensorTypeRepository() {
        SensorType temperature = new SensorType("temperature");
        Repositories.getSensorTypeRepository().save(temperature);
        SensorType wind = new SensorType("wind");
        Repositories.getSensorTypeRepository().save(wind);
        long repSize = Repositories.getSensorTypeRepository().count();
        assertEquals(2, repSize);
    }

    @Test
    //@DisplayName("Ensure that the typeGA repository has zero types persisted if no sensor types instances are saved into the repository")
    void testNrOfElementsInSensorTypeRepositoryIsZero() {
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertEquals(0, typeGARepSize);
    }


    @Test
    //@DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGeoRepository() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Location locP = new Location(22, 66, 20);
        TypeGA city = new TypeGA("City");
        Repositories.getTypeGARepository().save(city);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.getGeoRepository().save(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Location locL = new Location(72, 26, 2);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        Repositories.getGeoRepository().save(lisbon);
        long typeGARepSize = Repositories.getGeoRepository().count();
        assertEquals(2, typeGARepSize);
    }

    @Test
    //@DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGeoRepositoryWithSaveGAMethod() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Location locP = new Location(22, 66, 20);
        TypeGA city = new TypeGA("City");
        TypeGAList.addTypeGA(city);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.saveGA(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Location locL = new Location(72, 26, 2);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        SensorList lSensorList = lisbon.getSensorListInGa();
        SensorType temperature = new SensorType("temperature");
        Sensor sensor = new ExternalSensor("TT1023", "Temperature Sensors", new GregorianCalendar(2019, 2, 2), locL, temperature, "Celsius", new ReadingList());
        lSensorList.addSensor(sensor);
        Repositories.saveGA(lisbon);
        long geoRepSize = Repositories.getGeoRepository().count();
        assertEquals(2, geoRepSize);
        long sensorRepSize = Repositories.getExternalSensorRepository().count();
        assertEquals(1, sensorRepSize);
    }

    @Test
    //@DisplayName("Ensure that the geographical area repository and sensor repository are not empty")
    void geoRepositoryIsNotEmpty() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Location locP = new Location(22, 66, 20);
        TypeGA city = new TypeGA("City");
        TypeGAList.addTypeGA(city);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.saveGA(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Location locL = new Location(72, 26, 2);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        SensorList lSensorList = lisbon.getSensorListInGa();
        SensorType temperature = new SensorType("temperature");
        Sensor sensor = new ExternalSensor("TT1023", "Temperature Sensors", new GregorianCalendar(2019, 2, 2), locL, temperature, "Celsius", new ReadingList());
        lSensorList.addSensor(sensor);
        Repositories.saveGA(lisbon);
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertNotEquals(0, typeGARepSize);

        long sensorRepSize = Repositories.getExternalSensorRepository().count();
        assertEquals(1, sensorRepSize);
    }


    @Test
    //@DisplayName("Ensure that the sensor repository has 1 sensor persisted")
    void testNrOfElementsInSensorRepository() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        SensorType wind = new SensorType("wind");
        Repositories.getSensorTypeRepository().save(wind);
        ReadingList readingList = new ReadingList();
        ExternalSensor sensor1 = new ExternalSensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        Repositories.getExternalSensorRepository().save(sensor1);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        long repSize = Repositories.getExternalSensorRepository().count();
        assertEquals(1, repSize);
    }


    @Test
    //@DisplayName("Ensure that the sensor repository has 1 sensor persisted with save sensor method")
    void testNrOfElementsInSensorRepositoryWithSaveSensorMethod() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        SensorType wind = new SensorType("wind");
        ReadingList readingList = new ReadingList();
        ExternalSensor sensor1 = new ExternalSensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        Repositories.saveExternalSensor(sensor1);
        long repSize = Repositories.getExternalSensorRepository().count();
        assertEquals(1, repSize);
    }

    @Test
    //@DisplayName("Ensure that the sensor repository has 1 sensor persisted save sensor alternative")
    void testNrOfElementsInSensorRepositoryWithSaveSensorMethod1() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        SensorType wind = new SensorType("wind");
        ReadingList readingList = new ReadingList();
        ExternalSensor sensor1 = new ExternalSensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        Repositories.saveExternalSensor(sensor1);
        long repSize = Repositories.getExternalSensorRepository().count();
        assertNotEquals(0, repSize);
    }


    @Test
    //@DisplayName("Ensure that the geographical area repository has 1 sensor persisted")
    void newGA() {
        GAList list = new GAList();
        TypeGA district = new TypeGA("district");
        assertTrue(addTypeGA(district));
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(list);
        OccupationArea occupationArea = new OccupationArea(25, 12);
        Location location = new Location(23, 12, 7);
        ctrl.newGA("Pt", "Porto", 0, occupationArea, location);
        long repSize = Repositories.getGeoRepository().count();
        assertEquals(1, repSize);
    }

    @Test
    //@DisplayName("Ensure that removeSensor method removes sensor from the sensor list of Lisbon")
    void removeSensor() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        //created sensorDTO from sensor
        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        //created geographical area DTO with same id as geographicalArea lisbon
        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensorDTO.getId();

        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        ctrl.removeSensor(gaDTOId, sensorDTOId);

        long expectedReadingSize = 0;

        long expectedSensorRepSize = 0;
        long resultingSensorRepSize = Repositories.getExternalSensorRepository().count();
        assertEquals(expectedSensorRepSize, resultingSensorRepSize);
    }

    @Test
    //@DisplayName("Ensure that sensor and reading repository size is different from 1")
    void removeSensor2() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        //created sensorDTO from sensor
        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        //created geographical area DTO with same id as geographicalArea lisbon
        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensorDTO.getId();

        RemoveGASensorCTRL ctrl = new RemoveGASensorCTRL(gaList);
        ctrl.removeSensor(gaDTOId, sensorDTOId);

        long expectedReadingSize = 1;

        long expectedSensorRepSize = 1;
        long resultingSensorRepSize = Repositories.getExternalSensorRepository().count();
        assertNotEquals(expectedSensorRepSize, resultingSensorRepSize);
    }

    @Test
    //@DisplayName("Ensure that the room repository has 1 element persisted saved in Repository")
    void numberElementsRoomRepositorySaveInRepository() {

        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        Repositories.getRoomRepository().save(bedroom);

        long roomRepSize = Repositories.getRoomRepository().count();

        assertEquals(1, roomRepSize);
    }

    @Test
    //@DisplayName("Ensure that the room repository has 1 element persisted saved in Repository save Room")
    void numberElementsRoomRepositorySaveInRepositoryCallSaveRoom() {
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        Repositories.saveRoom(bedroom);

        long roomRepSize = Repositories.getRoomRepository().count();

        assertEquals(1, roomRepSize);
    }


    @Test
    //@DisplayName("Ensure that the room repository has 2 element persisted saved in RoomList")
    void numberElementsRoomRepositorySaveInRoomList() {

        RoomList rList = new RoomList();
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        Room garden = new Room("R2", "Garden", 0, 3, 2, 0);
        rList.addRoom(bedroom);
        rList.addRoom(garden);

        long roomRepSize = Repositories.getRoomRepository().count();

        assertEquals(2, roomRepSize);
    }


    @Test
    //@DisplayName("Ensure that the room repository has 0 elements persisted")
    void numberElementsRoomRepositoryIsZero() {

        long roomRepSize = Repositories.getRoomRepository().count();

        assertEquals(0, roomRepSize);
    }

    @Test
    //@DisplayName("Ensure that the room repository has 1 element and 2 sensors persisted")
    void numberElementsRoomAndSensorsRepository() {
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        SensorType temperature = new SensorType("temperature");
        SensorList sList = bedroom.getSensorListInRoom();
        InternalSensor sensor1 = new InternalSensor("S1", "Sensor1", new GregorianCalendar(2019, 2, 2), temperature, "C", new ReadingList());
        InternalSensor sensor2 = new InternalSensor("S2", "Sensor2", new GregorianCalendar(2019, 3, 4), temperature, "C", new ReadingList());

        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        getHouseRoomList().addRoom(bedroom);

        long roomRepSize = Repositories.getRoomRepository().count();
        assertEquals(1, roomRepSize);

        long sensorRepSize = Repositories.getInternalSensorRepository().count();
        assertEquals(2, sensorRepSize);
    }

    @Test
    //@DisplayName("Ensure that the room repository has 1 element and 2 sensors persisted deactivate sensor")
    void numberElementsRoomAndSensorsRepositoryDeactivateSensor() {
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        SensorType temperature = new SensorType("temperature");
        SensorList sList = bedroom.getSensorListInRoom();
        Sensor sensor1 = new InternalSensor("S1", "Sensor1", new GregorianCalendar(2019, 2, 2), temperature, "C", new ReadingList());
        Sensor sensor2 = new InternalSensor("S2", "Sensor2", new GregorianCalendar(2019, 3, 4), temperature, "C", new ReadingList());

        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        getHouseRoomList().addRoom(bedroom);

        long roomRepSize = Repositories.getRoomRepository().count();
        assertEquals(1, roomRepSize);

        long sensorRepSize = Repositories.getInternalSensorRepository().count();
        assertEquals(2, sensorRepSize);

        GregorianCalendar pauseDate = new GregorianCalendar(2019,05,03);
        sList.deactivateSensor(sensor1.getId(),pauseDate);

        assertEquals(2, sensorRepSize);
    }

    @Test
    //@DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGridRepository() {
        HouseGrid grid01 = new HouseGrid("main");
        HouseGrid grid02 = new HouseGrid("backup");
        Repositories.getGridsRepository().save(grid01);
        Repositories.getGridsRepository().save(grid02);

        long gridRepSize = Repositories.getGridsRepository().count();
        assertEquals(2, gridRepSize);
    }

}