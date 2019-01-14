package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTest {

    @Test
    @DisplayName("Ensure getType from ElectricWaterHeater instance returns correct type")
    void getType() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,50,75,18,0.9);

        String expected = "Electric Water Heater";
        String result = ewh.getType();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure getTypeFromIndex from ElectricWaterHeater instance returns correct type")
    void getTypeFromIndex() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,50,75,18,0.9);

        String expected= "Dishwasher";
        String result=ewh.getTypeFromIndex(2);

        assertEquals(expected,result);
    }
}