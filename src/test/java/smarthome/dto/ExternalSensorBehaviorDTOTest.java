package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalSensorBehaviorDTOTest {

    @Test
    void getName() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        GenericNameDTO name = new GenericNameDTO("name");
        dto.setName(name);
        GenericNameDTO result = dto.getName();

        assertEquals(name,result);
    }

    @Test
    void getSensorType() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        SensorTypeDTO field = new SensorTypeDTO("temperature");
        dto.setSensorType(field);
        SensorTypeDTO result = dto.getSensorType();

        assertEquals(field,result);
    }

    @Test
    void getStartDate() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        GregorianCalendar field = new GregorianCalendar(2019, Calendar.MAY,28);
        dto.setStartDate(field);
        Calendar result = dto.getStartDate();

        assertEquals(field,result);
    }

    @Test
    void getPauseDate() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        GregorianCalendar field = new GregorianCalendar(2019, Calendar.MAY,28);
        dto.setPauseDate(field);
        Calendar result = dto.getPauseDate();

        assertEquals(field,result);
    }

    @Test
    void getUnit() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        String field = "C";
        dto.setUnit(field);
        String result = dto.getUnit();

        assertEquals(field,result);
    }

    @Test
    void isActive() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO();
        dto.setActive(true);
        boolean result = dto.isActive();
        assertTrue(result);
        dto.setActive(false);
        boolean result1 = dto.isActive();
        assertFalse(result1);

    }

    @Test
    void getReadingList() {
        ExternalSensorBehaviorDTO dto = new ExternalSensorBehaviorDTO(new GenericNameDTO("name"),new GregorianCalendar(2019, Calendar.MAY,28),new SensorTypeDTO("temperature"),"C",new ArrayList<>());
        List<ExternalReadingDTO> field = new ArrayList<>();
        dto.setReadingList(field);
        List<ExternalReadingDTO> result = dto.getReadingList();

        assertEquals(field,result);
    }
}