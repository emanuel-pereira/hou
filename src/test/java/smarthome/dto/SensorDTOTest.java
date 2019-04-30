package smarthome.dto;

import org.junit.jupiter.api.Test;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorDTOTest {

    @Test
    void getDesignation() {
        SensorDTO sensor = new SensorDTO();

        String designation = "station";

        sensor.setDesignation(designation);

        String result = sensor.getDesignation();

        assertEquals(designation,result);

    }

    @Test
    void getId() {
        List<ReadingDTO> readings = new ArrayList<>();
        SensorDTO sensor = new SensorDTO("id00","meteo station",readings);

        String id = "new id";

        sensor.setId(id);

        String result = sensor.getId();

        assertEquals(id,result);
    }

    @Test
    void getSensorType() {
        List<ReadingDTO> readings = new ArrayList<>();
        SensorDTO sensor = new SensorDTO("id00","meteo station",readings);

        String type = "rainfall";

        SensorTypeDTO typeDTO = new SensorTypeDTO();

        typeDTO.setSensorType(type);

        sensor.setSensorType(typeDTO);

        SensorTypeDTO result = sensor.getSensorType();

        assertEquals(typeDTO,result);
    }

    @Test
    void getReadingListDTO() {
        List<ReadingDTO> readings = new ArrayList<>();
        SensorDTO sensor = new SensorDTO("id00","meteo station",readings);

        List<ReadingDTO> result = sensor.getReadingListDTO();

        assertEquals(readings,result);
    }

    @Test
    void getStartDate() {
        List<ReadingDTO> readings = new ArrayList<>();
        SensorDTO sensor = new SensorDTO("id00","meteo station",readings);

        GregorianCalendar sDate = new GregorianCalendar(2111,Calendar.NOVEMBER,11);

        sensor.setStartDate(sDate);

        Calendar result = sensor.getStartDate();

        assertEquals(sDate,result);
    }

    @Test
    void isActive() {

        List<ReadingDTO> readings = new ArrayList<>();
        SensorDTO sensor = new SensorDTO("id00","meteo station",readings);

        boolean result = sensor.isActive();

        assertTrue(result);
    }
}