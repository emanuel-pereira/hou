/*
package smarthome.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.ExternalReadingDTO;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
class DailySensorDataServiceTest {

    @BeforeEach
    public void resetRepositories(){
        Repositories.getTypeGARepository().save(new TypeGA("city"));
        TypeGA typeGA = Repositories.getTypeGARepository().findByType("city");
        Location loc = new Location(20, 20, 2);
        OccupationArea oc = new OccupationArea(2, 5);
        GeographicalArea g1 = new GeographicalArea("PT", "Porto", typeGA, oc, loc);
        Repositories.getGeoRepository().save(g1);
        Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
        House house = House.getHouseInstance(a1, g1);
        Repositories.getSensorTypeRepository().save(new SensorType("temperature"));
        g1.getSensorListInGa().getSensorList().clear();
    }

    @Test
    void getBestSensor() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name("temperature"));

        String startDate = "20190410";
        String endDate = "20190413";

        Location location = new Location(12,34,56);
        GregorianCalendar activationDate = new GregorianCalendar(2019,Calendar.APRIL,01);

        Reading r1 = new Reading(12,new GregorianCalendar(2019,Calendar.APRIL,13),"C");
        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        ExternalSensor s1 = new ExternalSensor("t00","sensor one",activationDate,location,sType,"C",rL1);

        Reading r2 = new Reading(22,new GregorianCalendar(2019,Calendar.APRIL,12),"C");
        ReadingList rL2 = new ReadingList();
        rL2.addReading(r2);
        ExternalSensor s2 = new ExternalSensor("t02","sensor two", activationDate,location,sType,"C",rL2);

        SensorList sL = House.getHouseGA().getSensorListInGa();
        sL.addSensor(s1);
        sL.addSensor(s2);

        double expected = r1.returnValue();
        double result = service.getBestSensorReadings(startDate,endDate).getLastReading().returnValue();

        assertEquals(expected,result);

        Calendar expected2 = r1.getDateAndTime();
        Calendar result2 = service.getBestSensorReadings(startDate,endDate).getLastReading().getDateAndTime();

        assertEquals(expected2,result2);

        int expected3 = rL1.size();
        int result3 = service.getBestSensorReadings(startDate,endDate).size();

        assertEquals(expected3,result3);
    }

    @Test
    void displayAmplitude() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name("temperature"));

        String startDate = "20190410";
        String endDate = "20190413";

        Location location = new Location(12,34,56);
        GregorianCalendar activationDate = new GregorianCalendar(2019,Calendar.APRIL,01);

        Reading r0 = new Reading(13,new GregorianCalendar(2019,Calendar.APRIL,13,10,00),"C");
        Reading r1 = new Reading(7,new GregorianCalendar(2019,Calendar.APRIL,13,22,00),"C");
        Reading r2 = new Reading(10,new GregorianCalendar(2019,Calendar.APRIL,12,10,00),"C");
        Reading r3 = new Reading(9,new GregorianCalendar(2019,Calendar.APRIL,12,22,00),"C");
        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        ExternalSensor s1 = new ExternalSensor("t01","sensor one",activationDate,location,sType,"C",rL1);
        SensorList sL = House.getHouseGA().getSensorListInGa();
        sL.addSensor(s1);

        ExternalReadingDTO expected = new ExternalReadingDTO(6,new GregorianCalendar(2019,Calendar.APRIL,13));
        ExternalReadingDTO result = service.displayAmplitude(startDate,endDate);

        assertEquals(expected.getValue(),result.getValue());
        assertEquals(expected.getDateAndTime(),result.getDateAndTime());
    }

    @Test
    void displayMaximum() throws ParseException{
        DailySensorDataService service = new DailySensorDataService();

        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name("temperature"));

        String startDate = "20190410";
        String endDate = "20190413";

        Location location = new Location(12,34,56);
        GregorianCalendar activationDate = new GregorianCalendar(2019,Calendar.APRIL,01);

        Reading r0 = new Reading(13,new GregorianCalendar(2019,Calendar.APRIL,13,10,00),"C");
        Reading r1 = new Reading(7,new GregorianCalendar(2019,Calendar.APRIL,13,22,00),"C");
        Reading r2 = new Reading(10,new GregorianCalendar(2019,Calendar.APRIL,12,10,00),"C");
        Reading r3 = new Reading(9,new GregorianCalendar(2019,Calendar.APRIL,12,22,00),"C");
        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        ExternalSensor s1 = new ExternalSensor("t01","sensor one",activationDate,location,sType,"C",rL1);
        SensorList sL = House.getHouseGA().getSensorListInGa();
        sL.addSensor(s1);

        ExternalReadingDTO expected = new ExternalReadingDTO(13,new GregorianCalendar(2019,Calendar.APRIL,13));
        ExternalReadingDTO result = service.displayMaximum(startDate,endDate);

        assertEquals(expected.getValue(),result.getValue());
        assertEquals(expected.getDateAndTime(),result.getDateAndTime());
    }

    @Test
    void displayMinimum() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        SensorType sType = Repositories.getSensorTypeRepository().findByType(new Name("temperature"));

        String startDate = "20190410";
        String endDate = "20190413";

        Location location = new Location(12,34,56);
        GregorianCalendar activationDate = new GregorianCalendar(2019,Calendar.APRIL,01);

        Reading r0 = new Reading(13,new GregorianCalendar(2019,Calendar.APRIL,13,10,00),"C");
        Reading r1 = new Reading(7,new GregorianCalendar(2019,Calendar.APRIL,13,22,00),"C");
        Reading r2 = new Reading(10,new GregorianCalendar(2019,Calendar.APRIL,12,10,00),"C");
        Reading r3 = new Reading(9,new GregorianCalendar(2019,Calendar.APRIL,12,22,00),"C");
        ReadingList rL1 = new ReadingList();
        rL1.addReading(r0);
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        ExternalSensor s1 = new ExternalSensor("t01","sensor one",activationDate,location,sType,"C",rL1);
        SensorList sL = House.getHouseGA().getSensorListInGa();
        sL.addSensor(s1);

        ExternalReadingDTO expected = new ExternalReadingDTO(7,new GregorianCalendar(2019,Calendar.APRIL,13));
        ExternalReadingDTO result = service.displayMinimum(startDate,endDate);

        assertEquals(expected.getValue(),result.getValue());
        assertEquals(expected.getDateAndTime(),result.getDateAndTime());
    }

    @Test
    void convertStringToCalendar() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        String dateStr = "20190410";

        Calendar expected = new GregorianCalendar(2019,Calendar.APRIL,10);
        Calendar result = service.convertStringToCalendar(dateStr);

        assertEquals(expected,result);
    }

    @Test
    void convertStringToCalendarInvalidString() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        String dateStr = "210";

        Calendar expected = new GregorianCalendar(0000,00,00);
        Calendar result = service.convertStringToCalendar(dateStr);

        assertEquals(expected,result);
    }

    @Test
    void convertStringToCalendarInvalidStringNotEquals() throws ParseException {
        DailySensorDataService service = new DailySensorDataService();

        String dateStr = "210";

        Calendar expected = new GregorianCalendar(2019,Calendar.APRIL,10);
        Calendar result = service.convertStringToCalendar(dateStr);

        assertNotEquals(expected,result);
    }
}*/
