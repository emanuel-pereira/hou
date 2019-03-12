package smarthome.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorDTOTest {

    @Test
    @DisplayName("Ensure that getId returns sensorDTO id")
    void getId() {
        SensorDTO sensorDTO= new SensorDTO("TT1035POR","Temperature Sensor Porto");
        String expected= "TT1035POR";
        String result=sensorDTO.getId();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that getId does not return incorrect id")
    void getIDDoesNotReturn() {
        SensorDTO sensorDTO= new SensorDTO("TT1035POR","Temperature Sensor Porto");
        String expected = "TTLIS";
        String result=sensorDTO.getId();
        assertNotEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that getDesignation returns sensorDTO designation")
    void getDesignation() {
        SensorDTO sensorDTO= new SensorDTO("TT1035POR","Temperature Sensor Porto");
        String expected = "Temperature Sensor Porto";
        String result=sensorDTO.getDesignation();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that getDesignation does not return incorrect designation")
    void getDesignationNegativeTest() {
        SensorDTO sensorDTO= new SensorDTO("TT1035POR","Temperature Sensor Porto");
        String expected = "Porto";
        String result=sensorDTO.getDesignation();
        assertNotEquals(expected,result);
    }

}