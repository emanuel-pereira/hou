package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.devices.ElectricWaterHeaterType;

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

        kitDeviceList.add(fridgeA);
        kitDeviceList.add(kettleA);
        kitDeviceList.add(lampA);
        grDeviceList.add(lampA);
        grDeviceList.add(fridgeB);

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

        ElectricWaterHeaterType type = new ElectricWaterHeaterType();
        Device ehw = type.createDevice("Daikin Heater", 200);
        kitchenDeviceList.add(ehw);
        int expected=1;
        int result= ctrl.getDevicesInAllRoomsByType("ElectricWaterHeater").size();
        assertEquals(expected,result);

        ctrl.setAttribute("Daikin Heater","Volume of Water Capacity",58);
        ctrl.setAttribute("Daikin Heater","Volume of Water to Heat",150);
        ctrl.setAttribute("Daikin Heater","Hot Water Temperature",80);
        ctrl.setAttribute("Daikin Heater","Cold Water Temperature",22);
        ctrl.setAttribute("Daikin Heater","Performance Ratio",0.85);

        double expected1 = 9.27;
        double result1 = ctrl.getEstimatedEnergyConsumptionByDeviceType("ElectricWaterHeater");
        assertEquals(expected1, result1);
    }
}

