package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTest {

    @Test
    @DisplayName("Ensure getType from ElectricWaterHeater instance returns correct type")
    void getType() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 50, 75, 18, 0.9);

        String expected = "Electric Water Heater";
        String result = ewh.getType();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure getTypeFromIndex from ElectricWaterHeater instance returns correct type")
    void getTypeFromIndex() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 50, 75, 18, 0.9);

        String expected = "Dishwasher";
        String result = ewh.getTypeFromIndex(2);

        assertEquals(expected, result);
    }

    @Test
    void setAndGetValuesOfAllAttributes() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 50, 75, 18, 0.9);
        ewh.setVolumeOfWater(80);
        double result = ewh.getVolumeOfWater();
        assertEquals(80, result);

        ewh.setHotWaterTemperature(87);
        double result2 = ewh.getHotWaterTemperature();
        assertEquals(87, result2);

        ewh.setColdWaterTemperature(20);
        double result3 = ewh.getColdWaterTemperature();
        assertEquals(20, result3);

        ewh.setPerformanceRatio(2);
        double result4 = ewh.getPerformanceRatio();
        assertEquals(2, result4);
    }

    @Test
    void showDeviceSpecsListAttributesInStringTest() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 50, 75, 18, 0.9);
        String result = ewh.showDeviceSpecsListAttributesInString();
        String expected = "4 - Volume of water : " + ewh.getVolumeOfWater() + "\n5 - Hot water temperature : " + ewh.getHotWaterTemperature() + "\n6 - Cold water temperature : " + ewh.getColdWaterTemperature() + "\n7 - Performance Ratio : " + ewh.getPerformanceRatio() + "\n";
        assertEquals(expected, result);
    }
}