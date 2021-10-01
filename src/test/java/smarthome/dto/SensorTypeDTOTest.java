package smarthome.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeDTOTest {

    @Test
    void getSensorType() {
        SensorTypeDTO typeDto = new SensorTypeDTO();

        String type = "i ma a type";

        typeDto.setType(type);

        String result = typeDto.getType();

        assertEquals(type,result);
    }
}