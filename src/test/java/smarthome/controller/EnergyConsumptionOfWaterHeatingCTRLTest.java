
package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class EnergyConsumptionOfWaterHeatingCTRLTest {

    @Test
    void getDevicesInAllRoomsByType() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        EnergyConsumptionOfWaterHeatingCTRL ctrl = new EnergyConsumptionOfWaterHeatingCTRL(house);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 75, 65, 1);
        Device dEWH1 = new Device("EWH", ewh1, 150);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 100, 65, 1);
        Device dEWH2 = new Device("EWH", ewh2, 150);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 25);
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);
        kitchen.getDeviceList().addDevice(microwave);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        int expected = 2;
        int result = ctrl.getDevicesInAllRoomsByType().size();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceAttributesInString() {
        House house = new House();
        EnergyConsumptionOfWaterHeatingCTRL ctrl = new EnergyConsumptionOfWaterHeatingCTRL(house);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 75, 65, 1);
        Device dEWH1 = new Device("EWH", ewh1, 150);
        String expected = "1 - Device Name : EWH\n" +
                "2 - Device Nominal Power : 150.0\n" +
                "3 - Device Type : Electric Water Heater\n" +
                "4 - Volume of water capacity (l): 75.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold Water temperature : 0.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Volume of water to heat : 0.0\n" +
                "9 - Daily Energy Consumption : 0.0 KWh\n";
        String result = ctrl.showDeviceAttributesInString(dEWH1);
        assertEquals(expected, result);
    }


    @Test
    void getEnergyConsumptionByDeviceType() {
        House house = new House();
        EnergyConsumptionOfWaterHeatingCTRL ctrl = new EnergyConsumptionOfWaterHeatingCTRL(house);

        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitchenDeviceList= kitchen.getDeviceList();
        DeviceList garageDeviceList= garage.getDeviceList();

        ElectricWaterHeater ewh1 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 75, 65, 1);
        Device dEWH1 = new Device("EWH", ewh1, 150);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 25);
        ElectricWaterHeater ewh2 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 75, 65, 1);
        Device dEWH2 = new Device("EWH", ewh2, 150);

        kitchenDeviceList.addDevice(dEWH1);
        kitchenDeviceList.addDevice(microwave);
        garageDeviceList.addDevice(dEWH2);

        String volumeOfWaterToHeat = "volumeOfWaterToHeat";
        String coldWater = "coldWaterTemperature";
        ctrl.setAttribute(dEWH1,volumeOfWaterToHeat, "55");
        ctrl.setAttribute(dEWH1,coldWater, "15");
        ctrl.setAttribute(dEWH2,volumeOfWaterToHeat, "45");
        ctrl.setAttribute(dEWH2,coldWater, "12");

        double expected = 5.97;
        double result = ctrl.getEnergyConsumptionByDeviceType(DeviceType.ELECTRIC_WATER_HEATER);
        assertEquals(expected, result);
    }

}