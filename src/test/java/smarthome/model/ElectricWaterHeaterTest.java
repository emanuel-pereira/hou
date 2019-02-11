package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectricWaterHeaterTest {

    @Test
    void setAndGetValuesOfAllAttributes() {
        ElectricWaterHeater ewh = new ElectricWaterHeater( 0, 18, 0.9);
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
        ElectricWaterHeater ewh = new ElectricWaterHeater( 150, 18, 0.9);
        ewh.setType(new DeviceType("ElectricWaterHeater"));
        String result = ewh.showDeviceSpecsListAttributesInString();
        String expected = "3 - DeviceType : ElectricWaterHeater\n" +
                "4 - Volume of water capacity (l) : 150.0\n" +
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

    @Test
    void setAttributeValueTest() {
        ElectricWaterHeater ewh = new ElectricWaterHeater( 150, 18, 0.9);
        String volumeOfWater = "Volume of water capacity";
        String hotWaterTemperature = "Hot water temperature";
        String coldWaterTemperature = "Cold water temperature";
        String performanceRatio = "Performance Ratio";
        String volumeOfWaterToHeat = "Volume of water to heat";
        ewh.setAttributeValue(volumeOfWater, "200");
        ewh.setAttributeValue(hotWaterTemperature, "55");
        ewh.setAttributeValue(coldWaterTemperature, "10");
        ewh.setAttributeValue(performanceRatio, "0.8");
        ewh.setAttributeValue(volumeOfWaterToHeat, "50");
        assertEquals(200, ewh.getVolumeOfWater());
        assertEquals(55, ewh.getHotWaterTemperature());
        assertEquals(10, ewh.getColdWaterTemperature());
        assertEquals(0.8, ewh.getPerformanceRatio());
        assertEquals(50, ewh.getVolumeOfWaterToHeat());

    }

    @Test
    @DisplayName("gf")
    void getEnergyConsumption() {
        ElectricWaterHeater ewh = new ElectricWaterHeater( 45, 50, 0.9);
        ewh.setColdWaterTemperature(30);
        ewh.setVolumeOfWaterToHeat(100);
        double expected = 2.09;
        double result = ewh.getEnergyConsumption();
        assertEquals(expected, result);
    }
    @Test
    void setAndGetTypeTest(){
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater();
        DeviceType deviceType = new DeviceType("ElectricWaterHeater");
        electricWaterHeater.setType(deviceType);
        String result = electricWaterHeater.getDeviceType().getDeviceTypeName();
        assertEquals("ElectricWaterHeater",result);

    }



}