package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.devices.FridgeType;
import smarthome.model.devices.Fridge;
import smarthome.model.devices.Lamp;
import smarthome.model.validations.NameValidations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.getHouseRoomList;

class EditDevicesCTRLTest {


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
    @DisplayName("Ensure showRoomListInString method returns a list of two rooms: Kitchen and Bathroom")
    void showRoomListInString() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        RoomList roomList = getHouseRoomList();

        Room kitchen = new Room("R01", "Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 3, 2, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that RoomList has a size of 2 even though trying to add the same two rooms twice to the RoomList")
    void getRoomListTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        RoomList roomList = getHouseRoomList();
        Room kitchen = new Room("R01", "Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 3, 2, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getRoomListSize();
        assertEquals(expected, result);

        int result2 = ctrl.getRoomList().getRoomListSize();
        int expected2 = 2;
        assertEquals(expected2, result2);
    }

    @Test
    void addDeviceToRoom() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        getHouseRoomList().addRoom(kitchen);
        DeviceList kitchenDL = kitchen.getDeviceList();

        ctrl.addDevice(1, "LG Fridge", "Fridge", 2);

        String expected = "LG Fridge";
        String result = kitchenDL.get(0).getDeviceName();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceListInString() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        getHouseRoomList().addRoom(kitchen);
        DeviceList kitchenDL = kitchen.getDeviceList();

        Device fridge = kitchenDL.newDevice("LG Fridge", "Fridge", 150);
        kitchenDL.add(fridge);

        Device lamp = kitchenDL.newDevice("Philips", "Lamp", 15);
        kitchenDL.add(lamp);


        List<String> expected = new ArrayList<>();
        expected.add("Fridge (LG Fridge) [Active]");
        expected.add("Lamp (Philips) [Active]");

        List<String> result = ctrl.showDeviceListInString(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        String name = "Fridge 1 - Sony";
        boolean result = ctrl.alphanumericName(name);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method returns false to empty string")
    void emptyStringReturnsFalse() {
        getHouseRoomList().getRoomList().clear();

        NameValidations n = new NameValidations();
        String name = " ";
        boolean result = n.alphanumericName(name);
        assertFalse(result);
    }

    @Test
    void getDeviceListTest() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        getHouseRoomList().addRoom(kitchen);
        DeviceList kitchenDL = kitchen.getDeviceList();

        ctrl.addDevice(1, "LG Fridge", "Fridge", 2);

        double expected = 1;
        double result = kitchenDL.size();
        assertEquals(expected, result);
    }

    // @Test
    void getDeviceAttribute() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        getHouseRoomList().addRoom(kitchen);
        DeviceList kitchenDL = kitchen.getDeviceList();

        ctrl.addDevice(1, "LG Fridge", "Fridge", 2);

        Device fridge = kitchenDL.get(0);
        //TODO correct getDeviceAttribute method implementation to return also name and nominal power,
        // currently it only returns device specs
        String result = ctrl.getDeviceAttribute(fridge, 0);
        String expected = "Device Name";
        assertEquals(expected, result);
    }

    @Test
    void setAttributeTest() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        getHouseRoomList().addRoom(kitchen);
        getHouseRoomList().addRoom(livingRoom);

        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(2, "Philips Lamp", "Lamp", 20);

        DeviceList livingRoomDeviceList = livingRoom.getDeviceList();

        Device lamp = livingRoomDeviceList.get(0);

        assertEquals(Double.NaN, lamp.getDeviceSpecs().getAttributeValue("Illuminance"), 0.01);
        ctrl.setAttribute(lamp, "Illuminance", 900.0);
        assertEquals(900.0, lamp.getDeviceSpecs().getAttributeValue("Illuminance"), 0.01);
    }

    @Test
    void getDeviceAttributeTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctr = new EditDevicesCTRL();
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("Zanussi", 100);

        String result = ctr.getDeviceAttribute(d, 0);
        String expected = "Freezer Capacity";

        assertEquals(expected, result);
    }

    @Test
    void getDeviceAttributesListTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctr = new EditDevicesCTRL();
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("Zanussi", 100);

        List<String> result = ctr.getDeviceAttributesListInString(d);
        List<String> expected = new ArrayList<>();
        expected.add("Freezer Capacity");
        expected.add("Refrigerator Capacity");
        expected.add("Annual Energy Consumption");
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that device microwave is removed from room kitchen")
    void removeDeviceFromRoom() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("R02", "Living Room", 0, 3, 2, 3);
        getHouseRoomList().addRoom(kitchen);
        getHouseRoomList().addRoom(livingRoom);

        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertEquals(2, kitchenDevList.size());

        //lets remove the fridge from the kitchen device list
        Device fridge = kitchenDevList.get(0);
        assertTrue(ctrl.removeDeviceFromRoom(fridge, 1));
        assertEquals(1, kitchenDevList.size());
    }

    @Test
    @DisplayName("Ensure that device microwave is removed from Kitchen and then added to Living Room")
    void changeDeviceRoom() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("R02", "Living Room", 0, 3, 2, 3);
        getHouseRoomList().addRoom(kitchen);
        getHouseRoomList().addRoom(livingRoom);

        assertTrue(ctrl.addDevice(1, "LG Fridge", "Fridge", 200));
        assertTrue(ctrl.addDevice(1, "Philips Lamp", "Lamp", 20));

        DeviceList kitchenDevList = kitchen.getDeviceList();
        DeviceList livingRoomDeviceList = livingRoom.getDeviceList();
        assertEquals(2, kitchenDevList.size());

        //lets remove the fridge from the kitchen device list
        Device fridge = kitchenDevList.get(0);
        ctrl.removeDeviceFromRoom(fridge, 1);
        assertEquals(1, kitchenDevList.size());

        //now lets add it to the living room device list
        assertTrue(ctrl.addDeviceToRoom(fridge, 2));
        assertEquals(1, kitchenDevList.size());
        assertEquals(1, livingRoomDeviceList.size());
    }

    @Test
    @DisplayName("Ensure that device Fridge is removed from Kitchen")
    void removeDevice() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 6, 4, 2.5);
        getHouseRoomList().addRoom(kitchen);

        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertEquals(2, kitchenDevList.size());

        assertTrue(ctrl.removeDevice(1, 0));
        assertEquals(1, kitchenDevList.size());
        assertTrue(kitchenDevList.get(0) instanceof Lamp);
    }

    @Test
    void deactivateDevice() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(livingRoom);
        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        DeviceList livingRoomDeviceList = livingRoom.getDeviceList();
        assertTrue(ctrl.deactivateDevice(1, 0));
        assertFalse(livingRoomDeviceList.get(0).isActive());
        assertTrue(livingRoomDeviceList.get(1).isActive());
    }

    @Test
    void NotPossibleToDeactivateDeviceSecondTime() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(livingRoom);
        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        //Normal deactivation
        DeviceList livingRoomDeviceList = livingRoom.getDeviceList();
        assertTrue(ctrl.deactivateDevice(1, 1));
        assertTrue(livingRoomDeviceList.get(0).isActive());
        assertFalse(livingRoomDeviceList.get(1).isActive());

        //As the device is already deactivated, it is no longer possible to deactivate it once again
        assertFalse(ctrl.deactivateDevice(1, 1));
        assertTrue(livingRoomDeviceList.get(0).isActive());
        assertFalse(livingRoomDeviceList.get(1).isActive());
    }

    @Test
    void createdDeviceTest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(livingRoom);
        DeviceList livingRoomDL = livingRoom.getDeviceList();

        assertEquals(0, livingRoomDL.size());
        Device lamp = ctrl.createDevice(livingRoom, "Lamp", "Philips Lamp", 20);

        assertEquals(1, livingRoomDL.size());
        assertTrue(lamp instanceof Lamp);
    }

    @Test
    void showDeviceTypesListInStringTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctr = new EditDevicesCTRL();
        String expected = "1 - ElectricWaterHeater\n" +
                "2 - WashingMachine\n" +
                "3 - Dishwasher\n" +
                "4 - Fridge\n" +
                "5 - Freezer\n" +
                "6 - WineCooler\n" +
                "7 - Kettle\n" +
                "8 - Oven\n" +
                "9 - Stove\n" +
                "10 - MicrowaveOven\n" +
                "11 - WallElectricHeater\n" +
                "12 - PortableElectricOilHeater\n" +
                "13 - PortableElectricConvectionHeater\n" +
                "14 - WallTowelHeater\n" +
                "15 - Lamp\n" +
                "16 - Fan\n" +
                "17 - Tv\n";
        String result = ctr.showDeviceTypesListInString();
        assertEquals(expected, result);
    }

    @Test
    void getDeviceTypeFromIndexTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctr = new EditDevicesCTRL();
        String type = ctr.getDeviceTypeFromIndex(1);
        assertEquals("ElectricWaterHeater", type);
    }

    @Test
    void getDeviceFromIndex() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(livingRoom);
        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        Device device = ctrl.getDeviceFromIndex(1, 1);
        assertTrue(device instanceof Fridge);
    }

    @Test
    void getDeviceList() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        Room livingRoom = new Room("R01", "Living Room", 0, 3, 2, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(livingRoom);
        ctrl.addDevice(1, "LG Fridge", "Fridge", 200);
        ctrl.addDevice(1, "Philips Lamp", "Lamp", 20);

        DeviceList livingRoomDeviceList = ctrl.getDeviceList(livingRoom);
        assertEquals(2, livingRoomDeviceList.size());
    }

    @Test
    void showDeviceAttributesInStringTest() {
        getHouseRoomList().getRoomList().clear();

        EditDevicesCTRL ctrl = new EditDevicesCTRL();
        DeviceType dt = new FridgeType();
        Device d = dt.createDevice("Zanussi", 100);

        List<String> result = ctrl.showDeviceAttributesInString(d);

        List<String> expected = new ArrayList<>();
        expected.add("Freezer Capacity: NaN liters");
        expected.add("Refrigerator Capacity: NaN liters");
        expected.add("Annual Energy Consumption: NaN kWh");
        assertEquals(expected, result);

    }
}
