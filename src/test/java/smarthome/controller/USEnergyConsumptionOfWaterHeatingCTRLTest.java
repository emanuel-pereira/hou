/*
package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class USEnergyConsumptionOfWaterHeatingCTRLTest {

    @Test
    @DisplayName("Ensure that method sets volume of water for all electric water heater devices installed in all rooms of the house")
    void setVolumeOfWaterInGlobalEWHList() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,110, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,110, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh1, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setVolumeOfWaterToHeatInAllEWH(70);

        double expected = 70;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getVolumeOfWaterToHeat();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getVolumeOfWaterToHeat();
        assertEquals(expected, resultEWH2);
    }

    @Test
    @DisplayName("Ensure that method sets cold water temperature for all electric water heater devices installed in all rooms of the house")
    void setColdWaterTemperatureInGlobalEWHList() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setColdWaterTemperatureInAllEWH(15);

        double expected = 15;
        double resultEWH1 = ((ElectricWaterHeater) dEWH1.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH1);

        double resultEWH2 = ((ElectricWaterHeater) dEWH2.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected, resultEWH2);
    }

    @Test
    void getEnergyConsumptionOfEWHGlobalList() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setColdWaterTemperatureInAllEWH(15);
        ctrl.setVolumeOfWaterToHeatInAllEWH(70);

        double expected = 1768.22;
        double result = ctrl.getEnergyConsumptionOfEWHGlobalList();
        assertEquals(expected, result, 0.01);

    }

    @Test
    void showElectricWaterHeaterList() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,150, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        house.setVolumeOfWaterInEWHList(65);
        house.setColdWaterTemperatureInEWHList(20);
        String expected = "ELECTRIC WATER HEATER \n" +
                "Room: Kitchen\n" +
                "1 - Device Name : Daikin - Electric Water Heater1\n" +
                "2 - Device Room : Kitchen\n" +
                "3 - Device Nominal Power : 3.0\n" +
                "4 - Volume of water capacity (l): 120.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold Water temperature : 20.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Volume of water to heat : 65.0\n" +
                "9 - Daily Energy Consumption : 816.4259999999999 KWh\n" +
                "\n" +
                "ELECTRIC WATER HEATER \n" +
                "Room: Garage\n" +
                "1 - Device Name : Daikin - Electric Water Heater2\n" +
                "2 - Device Room : Garage\n" +
                "3 - Device Nominal Power : 2.5\n" +
                "4 - Volume of water capacity (l): 150.0\n" +
                "5 - Hot water temperature : 60.0\n" +
                "6 - Cold Water temperature : 20.0\n" +
                "7 - Performance Ratio : 0.9\n" +
                "8 - Volume of water to heat : 65.0\n" +
                "9 - Daily Energy Consumption : 653.1408 KWh\n\n";
        String result = ctrl.showElectricWaterHeaterList();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that negative value returns false")
    void negativeValueReturnsFalse() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        double value = -0.5;
        boolean result = ctrl.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that 0 returns false")
    void zeroReturnsFalse() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        double value = 0;
        boolean result = ctrl.valueIsPositive(value);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that positive value returns true")
    void zeroReturnsTrue() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        double value = 15;
        boolean result = ctrl.valueIsPositive(value);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that isValidColdWaterTemperature() method returns false if cold water temperature is higher than any hot water temperature")
    void isLowerThanHotWater() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        Room bedroom = new Room("Bedroom", 1, 5, 5, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        house.getRoomList().addRoom(bedroom);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,150, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,120, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        boolean result = ctrl.isValidColdWaterTemperature(61);
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that isValidColdWaterTemperature() method returns true if cold water temperature is lower than any hot water temperature")
    void isLowerThanHotWater1() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        Room bedroom = new Room("Bedroom", 1, 5, 5, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        house.getRoomList().addRoom(bedroom);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,115, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        boolean result = ctrl.isValidColdWaterTemperature(55);
        assertTrue(result);

    }

    @Test
    @DisplayName("Ensure that isValidVolumeOfWater() method returns true when volume of water to heat per water heater does not exceed" +
            "any water heater capacity")
    void isValidVolumeOfWater() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        Room bedroom = new Room("Bedroom", 1, 5, 5, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        house.getRoomList().addRoom(bedroom);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,115, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        boolean result = ctrl.isValidVolumeOfWater(55);
        assertTrue(result);
    }
    @Test
    @DisplayName("Ensure that isValidVolumeOfWater() method returns false when volume of water to heat per water heater exceeds" +
            "a water heater capacity")
    void isValidVolumeOfWaterReturnsFalseIfExceedsCapacity() {
        House house = new House();
        USEnergyConsumptionOfWaterHeatingCTRL ctrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        Room bedroom = new Room("Bedroom", 1, 5, 5, 3);
        house.getRoomList().addRoom(kitchen);
        house.getRoomList().addRoom(garage);
        house.getRoomList().addRoom(bedroom);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,115, 65, 1);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER,130, 60, 0.9);
        Device dEWH1 = new Device("Daikin - Electric Water Heater1", ewh1, kitchen, 3);
        Device dEWH2 = new Device("Daikin - Electric Water Heater2", ewh2, garage, 2.5);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        boolean result = ctrl.isValidVolumeOfWater(116);
        assertFalse(result);
    }
}*/
