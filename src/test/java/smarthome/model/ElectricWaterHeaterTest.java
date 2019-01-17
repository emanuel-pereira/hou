package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectricWaterHeaterTest {

    @Test
    void setAndGetValuesOfAllAttributes() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(0, 18, 0.9);
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

        ewh.setVolumeOfWaterToHeat(125);
        double result5 = ewh.getVolumeOfWaterToHeat();
        assertEquals(125, result5);
    }

    @Test
    void showDeviceSpecsListAttributesInStringTest() {
        ElectricWaterHeater ewh = new ElectricWaterHeater(150,  18, 0.9);
        String result = ewh.showDeviceSpecsListAttributesInString();
        String expected = "4 - Volume of water capacity (l) : 150.0\n" +
                "5 - Hot water temperature : 18.0\n" +
                "6 - Cold water temperature : 0.0\n" +
                "7 - Performance Ratio : 0.9\n" +
                "8 - Volume of water to heat: 0.0\n" +
                "9 - Daily Energy Consumption: 0.0 KWh\n";
        assertEquals(expected, result);
    }

    @Test
    void getVolumeOfWaterToHeat() {
    }
}