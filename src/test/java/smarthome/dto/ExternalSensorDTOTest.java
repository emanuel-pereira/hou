package smarthome.dto;

import org.junit.jupiter.api.Test;
import smarthome.model.Location;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ExternalSensorDTOTest {

    @Test
    void getSensorBehavior() {
        ExternalSensorDTO dto = new ExternalSensorDTO();
        SensorBehaviorDTO field = new SensorBehaviorDTO();

        dto.setSensorBehavior(field);

        SensorBehaviorDTO result = dto.getSensorBehavior();

        assertEquals(field,result);
    }

    @Test
    void getId() {
        ExternalSensorDTO dto = new ExternalSensorDTO("id","name",new GregorianCalendar(2019, Calendar.MAY,28),new Location(12,34,56),new SensorTypeDTO("type"),"unit",new ArrayList<>());
        String field = "new id";

        dto.setId(field);

        String result = dto.getId();

        assertEquals(field,result);
    }

    @Test
    void getLocation() {
        ExternalSensorDTO dto = new ExternalSensorDTO();
        Location field = new Location();

        dto.setLocation(field);

        Location result = dto.getLocation();

        assertEquals(field,result);

    }
}