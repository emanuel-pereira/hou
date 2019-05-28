package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorBehaviorDTOTest {

    @Test
    void getName() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        GenericNameDTO name = new GenericNameDTO("name");
        dto.setName(name);
        GenericNameDTO result = dto.getName();

        assertEquals(name,result);
    }

    @Test
    void getSensorType() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        SensorTypeDTO field = new SensorTypeDTO("temperature");
        dto.setSensorType(field);
        SensorTypeDTO result = dto.getSensorType();

        assertEquals(field,result);
    }

    @Test
    void getStartDate() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        GregorianCalendar field = new GregorianCalendar(2019, Calendar.MAY,28);
        dto.setStartDate(field);
        Calendar result = dto.getStartDate();

        assertEquals(field,result);
    }

    @Test
    void getPauseDate() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        GregorianCalendar field = new GregorianCalendar(2019, Calendar.MAY,28);
        dto.setPauseDate(field);
        Calendar result = dto.getPauseDate();

        assertEquals(field,result);
    }

    @Test
    void getUnit() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        String field = "C";
        dto.setUnit(field);
        String result = dto.getUnit();

        assertEquals(field,result);
    }

    @Test
    void isActive() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO();
        dto.setActive(true);
        boolean result = dto.isActive();
        assertTrue(result);
        dto.setActive(false);
        boolean result1 = dto.isActive();
        assertFalse(result1);

    }

    @Test
    void getReadingList() {
        SensorBehaviorDTO dto = new SensorBehaviorDTO(new GenericNameDTO("name"),new GregorianCalendar(2019, Calendar.MAY,28),new SensorTypeDTO("temperature"),"C",new ArrayList<>());
        List<ExternalReadingDTO> field = new ArrayList<>();
        dto.setReadingList(field);
        List<ExternalReadingDTO> result = dto.getReadingList();

        assertEquals(field,result);
    }
}