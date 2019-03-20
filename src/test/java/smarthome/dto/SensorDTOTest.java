package smarthome.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Reading;
import smarthome.model.ReadingList;
import smarthome.model.Sensor;
import smarthome.model.SensorType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorDTOTest {

    @Test
    @DisplayName("Ensure that getId returns sensorDTO id")
    void getId() {
        SensorDTO sensorDTO = new SensorDTO("TT1035POR", "Temperature Sensor Porto");
        String expected = "TT1035POR";
        String result = sensorDTO.getId();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getId does not return incorrect id")
    void getIDDoesNotReturn() {
        SensorDTO sensorDTO = new SensorDTO("TT1035POR", "Temperature Sensor Porto");
        String expected = "TTLIS";
        String result = sensorDTO.getId();
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getDesignation returns sensorDTO designation")
    void getDesignation() {
        SensorDTO sensorDTO = new SensorDTO("TT1035POR", "Temperature Sensor Porto");
        String expected = "Temperature Sensor Porto";
        String result = sensorDTO.getDesignation();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getDesignation does not return incorrect designation")
    void getDesignationNegativeTest() {
        SensorDTO sensorDTO = new SensorDTO("TT1035POR", "Temperature Sensor Porto");
        String expected = "Porto";
        String result = sensorDTO.getDesignation();
        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getReadingListDTO returns a list of ReadingDTOs")
    void getReadingListDTOTest() {

        ReadingList readingList = new ReadingList();
        Calendar c1 = new GregorianCalendar(2019, 1, 1);
        Calendar c2 = new GregorianCalendar(2019, 1, 2);

        Reading r1 = new Reading(2, c1);
        Reading r2 = new Reading(9001, c2);
        assertTrue(readingList.addReading(r1));
        assertTrue(readingList.addReading(r2));

        SensorType st = new SensorType("temperature");
        Sensor s = new Sensor("Sensor A", c1, st, "ºC", readingList);


        List<ReadingDTO> foo = new ArrayList<>();

        ReadingDTO readingDTO1 = r1.toDTO();
        ReadingDTO readingDTO2 = r2.toDTO();

        foo.add(readingDTO1);
        foo.add(readingDTO2);

        SensorDTO sdto = s.toDTO();

        List<ReadingDTO> result = sdto.getReadingListDTO();
        ReadingDTO result1 = result.get(0);
        ReadingDTO result2 = result.get(1);

        double result1a = result1.getReadingValue();
        double result2a = result2.getReadingValue();
        Calendar c1a = result1.getReadingDateAndTime();
        Calendar c2a = result2.getReadingDateAndTime();


        assertTrue (result1a==2);
        assertTrue (result2a==9001);
        assertTrue (c1a.equals(c1));
        assertTrue (c2a.equals(c2));

    }
}