
package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class GetEnergyConsumptionOfWaterHeatingCTRLTest {

    @Test
    @DisplayName("Ensure that ")
    void getDevicesInAllRoomsByType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        House house = new House();
        RoomList roomList = house.getRoomList();
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL(house);

        DeviceType ewh = new DeviceType("ElectricWaterHeater");
        DeviceType microwave = new DeviceType("Microwave");

        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);

        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        Device ewh1 = kitchen.getDeviceList().newDeviceV2(ewh);
        Device ewh2 = garage.getDeviceList().newDeviceV2(ewh);
        Device micro = kitchen.getDeviceList().newDeviceV2(microwave);

        kitchen.getDeviceList().addDevice(micro);
        kitchen.getDeviceList().addDevice(ewh1);
        garage.getDeviceList().addDevice(ewh2);


        int expected = 2;
        int result = ctrl.getDevicesInAllRoomsByType("ElectricWaterHeater").size();

        assertEquals(expected, result);
    }

    @Test
    void showDeviceAttributesInString() {
        House house = new House();
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL(house);
        ElectricWaterHeater ewh1 = new ElectricWaterHeater(75, 65, 1);
        Device dEWH1 = new Device("EWH", ewh1, 150);
        dEWH1.getDeviceSpecs().setAttributeValue("Device Name", "EWH");
        dEWH1.getDeviceSpecs().setAttributeValue("Device Nominal Power", "150");
        DeviceType deviceType = new DeviceType("ElectricWaterHeater");
        dEWH1.getDeviceSpecs().setType(deviceType);
        String expected = "" + ewh1.getDeviceType().getDeviceTypeName() +
                "\n1 - Device Name : EWH\n2 - Device Nominal Power : 150.0\n3 - Volume of water capacity : 75.0\n4 - Hot water temperature : 65.0\n5 - Cold water temperature : 0.0\n6 - Performance Ratio : 1.0\n7 - Volume of water to heat : 0.0\n";
        String result = ctrl.showDeviceAttributesInString(dEWH1);
        assertEquals(expected, result);
    }


    @Test
    void getEnergyConsumptionByDeviceType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        House house = new House();
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL(house);

        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("Garage", 0, 6, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);
        DeviceType ewh = new DeviceType("ElectricWaterHeater");
        DeviceType microwave = new DeviceType("Microwave");
        DeviceList kitchenDeviceList = kitchen.getDeviceList();
        DeviceList garageDeviceList = garage.getDeviceList();

        Device ewh1 = kitchen.getDeviceList().newDeviceV2(ewh);
        Device ewh2 = garage.getDeviceList().newDeviceV2(ewh);
        Device micro = kitchen.getDeviceList().newDeviceV2(microwave);

        kitchenDeviceList.addDevice(micro);
        kitchenDeviceList.addDevice(ewh1);
        garageDeviceList.addDevice(ewh2);

        String volumeOfWater = "Volume of water capacity";
        String hotWaterTemperature = "Hot water temperature";
        String coldWaterTemperature = "Cold water temperature";
        String performanceRatio = "Performance Ratio";
        String volumeOfWaterToHeat = "Volume of water to heat";
        ctrl.setAttribute(ewh1, volumeOfWaterToHeat, "55");
        ctrl.setAttribute(ewh1, coldWaterTemperature, "15");
        ctrl.setAttribute(ewh2, volumeOfWaterToHeat, "45");
        ctrl.setAttribute(ewh2, coldWaterTemperature, "12");

        double expected = 0;
        double result = ctrl.getEnergyConsumptionByDeviceType("ElectricWaterHeater");
        assertEquals(expected, result);
    }

}