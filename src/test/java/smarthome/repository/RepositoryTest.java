package smarthome.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.controller.NewGeographicalAreaCTRL;
import smarthome.controller.RemoveGASensorCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest

public class RepositoryTest {

    @Test
    @DisplayName("Ensure that the typeGA repository has 3 types of GAs persisted")
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
    @DisplayName("Ensure that the typeGA repository has zero types of GAs persisted")
    void testNrOfElementsInTypeGARepositoryIsZero() {
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertEquals(0, typeGARepSize);
    }

    @Test
    @DisplayName("Ensure that the sensorType repository has 2 types persisted")
    void testNrOfElementsInSensorTypeRepository() {
        SensorType temperature = new SensorType("temperature");
        Repositories.getSensorTypeRepository().save(temperature);
        SensorType wind = new SensorType("wind");
        Repositories.getSensorTypeRepository().save(wind);
        long repSize = Repositories.getSensorTypeRepository().count();
        assertEquals(2, repSize);
    }

    @Test
    @DisplayName("Ensure that the typeGA repository has zero types persisted if no sensor types instances are saved into the repository")
    void testNrOfElementsInSensorTypeRepositoryIsZero() {
        long typeGARepSize = Repositories.getTypeGARepository().count();
        assertEquals(0, typeGARepSize);
    }


    @Test
    @DisplayName("Ensure that the occupation area repository has 3 elements persisted after deleting one ")
    void occupationAreaRepSize() {

        OccupationArea oa1 = new OccupationArea(25, 30);
        Repositories.getOccupationAreaRepository().save(oa1);
        OccupationArea oa2 = new OccupationArea(25, 30);
        Repositories.getOccupationAreaRepository().save(oa2);
        OccupationArea oa3 = new OccupationArea(25, 30);
        Repositories.getOccupationAreaRepository().save(oa3);
        OccupationArea oa4 = new OccupationArea(25, 30);
        Repositories.getOccupationAreaRepository().save(oa4);
        Repositories.getOccupationAreaRepository().delete(oa3);
        long repSize = Repositories.getOccupationAreaRepository().count();
        assertEquals(3, repSize);
    }

    @Test
    @DisplayName("Ensure that the occupation area repository has zero types persisted if no occupationArea instances are saved into the repository")
    void testNrOfElementsInOccupationAreaRepositoryIsZero() {
        long repSize = Repositories.getOccupationAreaRepository().count();
        assertEquals(0, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has 2 elements persisted")
    void locationRepSizeIsTwo() {

        Location loc1 = new Location(25, 30, 17);
        Repositories.getLocationRepository().save(loc1);
        Location loc2 = new Location(15, 32, 22);
        Repositories.getLocationRepository().save(loc2);
        Location loc3 = new Location(22, 12, 1);
        Repositories.getLocationRepository().save(loc3);
        //testing if deletion of loc1 returns a repository size of 2 elements (loc2 and loc3)
        Repositories.getLocationRepository().delete(loc1);
        long repSize = Repositories.getLocationRepository().count();
        assertEquals(2, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has 5 elements persisted")
    void locationSizeIsZero() {
        Location loc1 = new Location(25, 30, 17);
        Repositories.getLocationRepository().save(loc1);
        Location loc2 = new Location(15, 32, 22);
        Repositories.getLocationRepository().save(loc2);
        Location loc3 = new Location(22, 12, 1);
        Repositories.getLocationRepository().save(loc3);
        Location loc4 = new Location(2, 66, 4);
        Repositories.getLocationRepository().save(loc4);
        Location loc5 = new Location(44, 5, 5);
        Repositories.getLocationRepository().save(loc5);
        Repositories.getLocationRepository().delete(loc1);
        long repSize = Repositories.getLocationRepository().count();
        assertEquals(4, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has zero elements persisted if no location instances are saved into the repository")
    void testNrOfElementsInLocationRepositoryIsZero() {
        long repSize = Repositories.getLocationRepository().count();
        assertEquals(0, repSize);
    }

    @Test
    @DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGeoRepository() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Repositories.getOccupationAreaRepository().save(oaP);
        Location locP = new Location(22, 66, 20);
        Repositories.getLocationRepository().save(locP);
        TypeGA city = new TypeGA("City");
        Repositories.getTypeGARepository().save(city);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.getGeoRepository().save(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Repositories.getOccupationAreaRepository().save(oaL);
        Location locL = new Location(72, 26, 2);
        Repositories.getLocationRepository().save(locL);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        Repositories.getGeoRepository().save(lisbon);
        long typeGARepSize = Repositories.getGeoRepository().count();
        assertEquals(2, typeGARepSize);
    }

    @Test
    @DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGeoRepositoryWithSaveGAMethod() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Location locP = new Location(22, 66, 20);
        TypeGA city = new TypeGA("City");
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.saveGA(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Location locL = new Location(72, 26, 2);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        SensorList lSensorList = lisbon.getSensorListInGA();
        SensorType temperature = new SensorType("temperature");
        Sensor sensor = new Sensor("TT1023", "Temperature Sensor", new GregorianCalendar(2019, 2, 2), locL, temperature, "Celsius", new ReadingList());
        lSensorList.addSensor(sensor);
        Repositories.saveGA(lisbon);
        long geoRepSize = Repositories.getGeoRepository().count();
        assertEquals(2, geoRepSize);
        long sensorRepSize = Repositories.getSensorRepository().count();
        assertEquals(1, sensorRepSize);
    }

    @Test
    @DisplayName("Ensure that the geographical area repository and sensor repository are not empty")
    void geoRepositoryIsNotEmpty() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Location locP = new Location(22, 66, 20);
        TypeGA city = new TypeGA("City");
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.saveGA(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Location locL = new Location(72, 26, 2);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        SensorList lSensorList = lisbon.getSensorListInGA();
        SensorType temperature = new SensorType("temperature");
        Sensor sensor = new Sensor("TT1023", "Temperature Sensor", new GregorianCalendar(2019, 2, 2), locL, temperature, "Celsius", new ReadingList());
        lSensorList.addSensor(sensor);
        Repositories.saveGA(lisbon);
        long typeGARepSize = Repositories.getGeoRepository().count();
        assertNotEquals(0, typeGARepSize);

        long sensorRepSize = Repositories.getSensorRepository().count();
        assertEquals(1, sensorRepSize);
    }


    @Test
    @DisplayName("Ensure that the sensor repository has 1 sensor persisted")
    void testNrOfElementsInSensorRepository() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        Repositories.getLocationRepository().save(locS1);
        SensorType wind = new SensorType("wind");
        Repositories.getSensorTypeRepository().save(wind);
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        Repositories.getSensorRepository().save(sensor1);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        assertTrue(reading.setSensor(sensor1));
        String sensorId = reading.getSensor().getId();
        assertEquals("MV12345", sensorId);
        long repSize = Repositories.getSensorRepository().count();
        assertEquals(1, repSize);
    }


    @Test
    @DisplayName("Ensure that the sensor repository has 1 sensor persisted")
    void testNrOfElementsInSensorRepositoryWithSaveSensorMethod() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        SensorType wind = new SensorType("wind");
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        assertTrue(reading.setSensor(sensor1));
        Repositories.saveSensor(sensor1);
        String sensorId = reading.getSensor().getId();
        assertEquals("MV12345", sensorId);
        long repSize = Repositories.getSensorRepository().count();
        assertEquals(1, repSize);
    }

    @Test
    @DisplayName("Ensure that the sensor repository has 1 sensor persisted")
    void testNrOfElementsInSensorRepositoryWithSaveSensorMethod1() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        SensorType wind = new SensorType("wind");
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        assertTrue(reading.setSensor(sensor1));
        Repositories.saveSensor(sensor1);
        String sensorId = reading.getSensor().getId();
        assertEquals("MV12345", sensorId);
        long repSize = Repositories.getSensorRepository().count();
        assertNotEquals(0, repSize);
    }


    @Test
    @DisplayName("Ensure that the geographical area repository has 1 sensor persisted")
    void newGA() {
        GAList list = new GAList();
        TypeGAList typeGAList = new TypeGAList();
        TypeGA district = new TypeGA("district");
        typeGAList.addTypeGA(district);
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(list, typeGAList);
        OccupationArea occupationArea = new OccupationArea(25, 12);
        Location location = new Location(23, 12, 7);
        ctrl.newGA("Pt", "Porto", 0, occupationArea, location);
        long repSize = Repositories.getGeoRepository().count();
        assertEquals(1, repSize);
    }

    @Test
    @DisplayName("Ensure that removeSensor method removes sensor from the sensor list of Lisbon")
    void removeSensor() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new Sensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
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
        long resultingReadingSize = Repositories.getReadingRepository().count();
        assertEquals(expectedReadingSize, resultingReadingSize);

        long expectedSensorRepSize = 0;
        long resultingSensorRepSize = Repositories.getSensorRepository().count();
        assertEquals(expectedSensorRepSize, resultingSensorRepSize);
    }

    @Test
    @DisplayName("Ensure that sensor and reading repository size is different from 1")
    void removeSensor2() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();
        //sensor created and added to lisSensorList
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new Sensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
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
        long resultingReadingSize = Repositories.getReadingRepository().count();
        assertNotEquals(expectedReadingSize, resultingReadingSize);

        long expectedSensorRepSize = 1;
        long resultingSensorRepSize = Repositories.getSensorRepository().count();
        assertNotEquals(expectedSensorRepSize, resultingSensorRepSize);
    }


}