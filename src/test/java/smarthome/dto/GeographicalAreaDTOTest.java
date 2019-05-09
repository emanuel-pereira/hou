package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicalAreaDTOTest {

    @Test
    void getIdentification() {
        List<SensorDTO> sensorDTOS = new ArrayList<>();
        GeographicalAreaDTO geoArea = new GeographicalAreaDTO("id","name",sensorDTOS);
        String id = "I am an id";
        geoArea.setIdentification(id);
        String result = geoArea.getIdentification();
        assertEquals(id,result);
    }

    @Test
    void getDesignation() {
        GeographicalAreaDTO geoArea = new GeographicalAreaDTO();
        String designation = "I am a designation";
        geoArea.setDesignation(designation);
        String result = geoArea.getDesignation();
        assertEquals(designation,result);
    }

    @Test
    void getSensorListDTO() {
        GeographicalAreaDTO geoArea = new GeographicalAreaDTO();
        SensorDTO sensor1 = new SensorDTO();
        SensorDTO sensor2 = new SensorDTO();

        List<SensorDTO> sensorList = Arrays.asList(sensor1, sensor2);

        geoArea.setSensorListDTO(sensorList);
        List<SensorDTO> result = geoArea.getSensorListDTO();
        assertEquals(sensorList,result);
    }

    @Test
    void equalsObjectTest(){
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO();
        String id1 = "I am an id";
        geoArea1.setIdentification(id1);

        boolean result = geoArea1.equals(geoArea1);

        assertTrue(result);
    }

    @Test
    void equalsIDTest(){
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO();
        String id1 = "I am an id";
        geoArea1.setIdentification(id1);

        GeographicalAreaDTO geoArea2 = new GeographicalAreaDTO();
        geoArea2.setIdentification(id1);

        boolean result = geoArea1.equals(geoArea2);

        assertTrue(result);
    }

    @Test
    void equalsFalseTest(){
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO();
        String id1 = "I am an id";
        geoArea1.setIdentification(id1);

        GeographicalAreaDTO geoArea2 = new GeographicalAreaDTO();
        String id2 = "Im am another id";
        geoArea2.setIdentification(id2);

        boolean result = geoArea1.equals(geoArea2);

        assertFalse(result);
    }

    @Test
    void equalsFalseObjectInstanceTest(){
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO();
        String id1 = "I am an id";
        geoArea1.setIdentification(id1);

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        SensorDTO sensorDTO = new SensorDTO("id01","name",readingDTOS);

        boolean result = geoArea1.equals(sensorDTO);

        assertFalse(result);
    }

    @Test
    void hashcodeTest(){
        List<SensorDTO> sensorDTOS = new ArrayList<>();
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO("id01","dto",sensorDTOS);

        GeographicalAreaDTO geoArea2 = new GeographicalAreaDTO("id01","dto",sensorDTOS);

        assertEquals(geoArea1.hashCode(),geoArea2.hashCode());
    }

    @Test
    void hashcodeNotEqualsTest(){
        List<SensorDTO> sensorDTOS = new ArrayList<>();
        List<SensorDTO> sensorDTOS1 = new ArrayList<>();
        GeographicalAreaDTO geoArea1 = new GeographicalAreaDTO("id01","A",sensorDTOS);

        GeographicalAreaDTO geoArea2 = new GeographicalAreaDTO("id02","B",sensorDTOS1);

        assertNotEquals(geoArea1.hashCode(),geoArea2.hashCode());
    }
}