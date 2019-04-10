package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.House.getHouseRoomList;


class GetEnergyConsumptionOfWaterHeatingCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);


    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getDevicesInAllRoomsByType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        RoomList roomList = getHouseRoomList();
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL();

        Room kitchen = new Room("R01","Kitchen", 0, 8, 8, 3);
        Room garage = new Room("R02","Living Room", 0, 5, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList = kitchen.getDeviceList();
        DeviceList grDeviceList = garage.getDeviceList();

        Device fridgeA = kitDeviceList.newDevice("LG Fridge", "Fridge", 150);
        Device kettleA = kitDeviceList.newDevice("Daijutsu", "Kettle", 250);
        Device lampA = kitDeviceList.newDevice("Philips Smart Bulb", "Lamp", 15);
        Device fridgeB = kitDeviceList.newDevice("Samsung Fridge", "Fridge", 250);

        kitDeviceList.addDevice(fridgeA);
        kitDeviceList.addDevice(kettleA);
        kitDeviceList.addDevice(lampA);
        grDeviceList.addDevice(lampA);
        grDeviceList.addDevice(fridgeB);

        int expected = 2;
        int result = ctrl.getDevicesInAllRoomsByType("Fridge").size();

        assertEquals(expected, result);
    }

    @Test
    void getEnergyConsumptionByDeviceType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL();

        RoomList roomList = getHouseRoomList();
        Room kitchen = new Room("R01","Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("R02","Garage", 0, 6, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitchenDeviceList = kitchen.getDeviceList();
        DeviceList garageDeviceList = garage.getDeviceList();

        //TODO upon EWH device class creation, this Fridge test object should be replaced by the equivalent EWH
        Device ewhA = kitchenDeviceList.newDevice("LG EWH K", "Fridge", 150);
        Device ewhB = garageDeviceList.newDevice("LG EWH G", "Fridge", 150);

        kitchenDeviceList.addDevice(ewhA);
        garageDeviceList.addDevice(ewhB);

        double expected = 0;
        //TODO replace Fridge for EWH
        double result = ctrl.getEnergyConsumptionByDeviceType("Fridge");
        assertEquals(expected, result);
    }

    @Test
    void setAttribute() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        GetEnergyConsumptionOfWaterHeatingCTRL ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL();

        RoomList roomList = getHouseRoomList();
        Room kitchen = new Room("R01","Kitchen", 0, 6, 3.5, 3);
        Room garage = new Room("R02","Garage", 0, 6, 4, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitchenDeviceList = kitchen.getDeviceList();
        DeviceList garageDeviceList = garage.getDeviceList();

        //TODO upon EWH device class creation, this Fridge test object should be replaced by the equivalent EWH
        Device ewhA = kitchenDeviceList.newDevice("LG EWH K", "Fridge", 150);
        Device ewhB = garageDeviceList.newDevice("LG EWH G", "Fridge", 150);

        kitchenDeviceList.addDevice(ewhA);
        garageDeviceList.addDevice(ewhB);

        /*String volumeOfWater = "Volume of water capacity";
        String hotWaterTemperature = "Hot water temperature";
        String coldWaterTemperature = "Cold water temperature";
        String performanceRatio = "Performance Ratio";
        String volumeOfWaterToHeat = "Volume of water to heat";
        ctrl.setAttribute(ewhA, volumeOfWaterToHeat, "55");
        ctrl.setAttribute(ewhA, coldWaterTemperature, "15");
        ctrl.setAttribute(ewhB, volumeOfWaterToHeat, "45");
        ctrl.setAttribute(ewhB, coldWaterTemperature, "12");*/

        String annualEnergyConsumption = "Annual Energy Consumption";
        ctrl.setAttribute(ewhA, annualEnergyConsumption, "55");

        double expected = 55;
        double result = ewhA.getDeviceSpecs().getAttributeValue("Annual Energy Consumption");
        assertEquals(expected, result);
    }
}

