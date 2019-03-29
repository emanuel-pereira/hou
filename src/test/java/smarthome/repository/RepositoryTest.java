package smarthome.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest

public class RepositoryTest {


    @Test
    @DisplayName("Ensure that the typeGA repository has 3 types of GAs persisted")
    void testNrOfElementsInTypeGARepository() {
        TypeGA city = new TypeGA("city");
        Repositories.typeGARepository.save(city);
        TypeGA district = new TypeGA("district");
        Repositories.typeGARepository.save(district);
        TypeGA country = new TypeGA("country");
        Repositories.typeGARepository.save(country);
        long typeGARepSize = Repositories.typeGARepository.count();
        assertEquals(3, typeGARepSize);
    }


    @Test
    @DisplayName("Ensure that the typeGA repository has zero types of GAs persisted")
    void testNrOfElementsInTypeGARepositoryIsZero() {
        long typeGARepSize = Repositories.typeGARepository.count();
        assertEquals(0, typeGARepSize);
    }

    @Test
    @DisplayName("Ensure that the sensorType repository has 2 types persisted")
    void testNrOfElementsInSensorTypeRepository() {
        SensorType temperature = new SensorType("temperature");
        Repositories.sensorTypeRepository.save(temperature);
        SensorType wind = new SensorType("wind");
        Repositories.sensorTypeRepository.save(wind);
        long repSize = Repositories.sensorTypeRepository.count();
        assertEquals(2, repSize);
    }

    @Test
    @DisplayName("Ensure that the typeGA repository has zero types persisted if no sensor types instances are saved into the repository")
    void testNrOfElementsInSensorTypeRepositoryIsZero() {
        long typeGARepSize = Repositories.typeGARepository.count();
        assertEquals(0, typeGARepSize);
    }


    @Test
    @DisplayName("Ensure that the occupation area repository has 3 elements persisted after deleting one ")
    void occupationAreaRepSize() {

        OccupationArea oa1 = new OccupationArea(25, 30);
        Repositories.occupationAreaRepository.save(oa1);
        OccupationArea oa2 = new OccupationArea(25, 30);
        Repositories.occupationAreaRepository.save(oa2);
        OccupationArea oa3 = new OccupationArea(25, 30);
        Repositories.occupationAreaRepository.save(oa3);
        OccupationArea oa4 = new OccupationArea(25, 30);
        Repositories.occupationAreaRepository.save(oa4);
        Repositories.occupationAreaRepository.delete(oa3);
        long repSize = Repositories.occupationAreaRepository.count();
        assertEquals(3, repSize);
    }

    @Test
    @DisplayName("Ensure that the occupation area repository has zero types persisted if no occupationArea instances are saved into the repository")
    void testNrOfElementsInOccupationAreaRepositoryIsZero() {
        long repSize = Repositories.occupationAreaRepository.count();
        assertEquals(0, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has 2 elements persisted")
    void locationRepSizeIsTwo() {

        Location loc1 = new Location(25, 30,17);
        Repositories.locationRepository.save(loc1);
        Location loc2 = new Location(15, 32,22);
        Repositories.locationRepository.save(loc2);
        Location loc3 = new Location(22, 12,1);
        Repositories.locationRepository.save(loc3);
        //testing if deletion of loc1 returns a repository size of 2 elements (loc2 and loc3)
        Repositories.locationRepository.delete(loc1);
        long repSize = Repositories.locationRepository.count();
        assertEquals(2, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has 5 elements persisted")
    void locationSizeIsZero() {
        Location loc1 = new Location(25, 30,17);
        Repositories.locationRepository.save(loc1);
        Location loc2 = new Location(15, 32,22);
        Repositories.locationRepository.save(loc2);
        Location loc3 = new Location(22, 12,1);
        Repositories.locationRepository.save(loc3);
        Location loc4 = new Location(2, 66,4);
        Repositories.locationRepository.save(loc4);
        Location loc5 = new Location(44, 5,5);
        Repositories.locationRepository.save(loc5);
        Repositories.locationRepository.delete(loc1);
        long repSize = Repositories.locationRepository.count();
        assertEquals(4, repSize);
    }

    @Test
    @DisplayName("Ensure that the location repository has zero elements persisted if no location instances are saved into the repository")
    void testNrOfElementsInLocationRepositoryIsZero() {
        long repSize = Repositories.locationRepository.count();
        assertEquals(0, repSize);
    }


    @Test
    @DisplayName("Ensure that the geographical area repository has 2 elements persisted ")
    void testNrOfElementsInGeoRepository() {
        OccupationArea oaP = new OccupationArea(25, 30);
        Repositories.occupationAreaRepository.save(oaP);
        Location locP = new Location(22, 66, 20);
        Repositories.locationRepository.save(locP);
        TypeGA city = new TypeGA("City");
        Repositories.typeGARepository.save(city);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, oaP, locP);
        Repositories.geoRepository.save(porto);
        OccupationArea oaL = new OccupationArea(32, 38);
        Repositories.occupationAreaRepository.save(oaL);
        Location locL = new Location(72, 26, 2);
        Repositories.locationRepository.save(locL);
        GeographicalArea lisbon = new GeographicalArea("PT", "Lisboa", city, oaL, locL);
        Repositories.geoRepository.save(lisbon);
        long typeGARepSize = Repositories.geoRepository.count();
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
        Repositories.saveGA(lisbon);
        long typeGARepSize = Repositories.geoRepository.count();
        assertEquals(2, typeGARepSize);
    }


    @Test
    @DisplayName("Ensure that the sensor repository has 1 sensor persisted")
    void testNrOfElementsInSensorRepository() {
        GregorianCalendar startDate = new GregorianCalendar(2016, Calendar.NOVEMBER, 15);
        Location locS1 = new Location(72, 26, 2);
        Repositories.locationRepository.save(locS1);
        SensorType wind = new SensorType("wind");
        Repositories.sensorTypeRepository.save(wind);
        ReadingList readingList = new ReadingList();
        Sensor sensor1 = new Sensor("MV12345", "Meteo station ISEP", startDate, locS1, wind, "m/s", readingList);
        Repositories.sensorRepository.save(sensor1);
        GregorianCalendar r1Date = new GregorianCalendar(2016, Calendar.NOVEMBER, 15, 9, 15);
        Reading reading = new Reading(22, r1Date);
        reading.setSensor(sensor1);
        String sensorId = reading.getSensor().getId();
        assertEquals("MV12345", sensorId);
        long repSize = Repositories.sensorRepository.count();
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
        reading.setSensor(sensor1);
        Repositories.saveSensor(sensor1);
        String sensorId = reading.getSensor().getId();
        assertEquals("MV12345", sensorId);
        long repSize = Repositories.sensorRepository.count();
        assertEquals(1, repSize);
    }

}