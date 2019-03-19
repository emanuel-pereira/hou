package smarthome.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        List<ReadingDTO> foo = new ArrayList<>();

        Calendar c = new GregorianCalendar(2019, 1, 1);
        ReadingDTO r1 = new ReadingDTO(3.1415926, c);
        ReadingDTO r2 = new ReadingDTO(1.00001, c);

        foo.add(r1);
        foo.add(r2);

        SensorDTO sdto = new SensorDTO("meh", "blah", foo);

        List<ReadingDTO> result = new ArrayList<>();
        result = sdto.getReadingListDTO();

        boolean expected = result.contains(r1) && result.contains(r2);
        assertTrue(expected);
    }
}