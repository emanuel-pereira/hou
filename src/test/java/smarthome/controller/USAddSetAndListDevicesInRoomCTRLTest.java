package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.Validations.NameValidations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class USAddSetAndListDevicesInRoomCTRLTest {

    @Test
    @DisplayName("Ensure showRoomListInString method returns a list of two rooms: Kitchen and Bathroom")
    void showRoomListInString() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that RoomList has a size of 2 even though trying to add the same two rooms twice to the RoomList")
    void getRoomList() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getRoomList().getRoomList().size();
        assertEquals(expected, result);
    }

    @Test
    void addDeviceToRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomList().addRoom(kitchen);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        ctrl.addDevice(1, "LG Fridge", fridge, 2);
        String expected = "LG Fridge";
        String result = kitchen.getDeviceList().get(0).getName();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceListInString() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomList().addRoom(kitchen);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        ctrl.addDevice(1, "Samsung Microwave", micro, 0.8);
        ctrl.addDevice(1, "LG Fridge", fridge, 1.5);
        String expected = "1 - Device: Samsung Microwave | Type: MICROWAVE_OVEN\n2 - Device: LG Fridge | Type: FRIDGE\n";
        String result = ctrl.showDeviceListInString(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        String name = "Fridge 1 - Sony";
        boolean result = ctrl.alphanumericName(name);
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method returns false to empty string")
    void emptyStringReturnsFalse() {
        NameValidations n = new NameValidations();
        String name = " ";
        boolean result = n.alphanumericName(name);
        assertFalse(result);
    }

    @Test
    void getDeviceListTest() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(house);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Room r2 = new Room("A", 1, 1, 1, 1);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        OtherDevices stove = new OtherDevices(DeviceType.STOVE);
        Device d1 = new Device("A", stove, 150.1);
        Device d2 = new Device("B", stove, 53.1);
        Device d3 = new Device("C", stove, 5.5);
        r1.getDeviceList().addDevice(d1);
        r1.getDeviceList().addDevice(d2);
        r2.getDeviceList().addDevice(d3);
        DeviceList result = ctr.getDeviceList(r1);
        DeviceList expected = house.getRoomList().get(0).getDeviceList();
        assertEquals(expected, result);
    }


    @Test
    void getDeviceAttributesListInStringTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE, 20);
        Device d1 = new Device("A", wm, 150.1);
        String result = ctr.showDeviceAttributesInString(d1);
        String expected = "1 - Device Name : A\n" +
                "2 - Device Nominal Power : 150.1\n" +
                "3 - Device Type : Washing Machine\n" +
                "4 - Washing Machine Capacity : 20\n";
        assertEquals(expected, result);
    }

    @Test
    void getDeviceAttribute() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE, 20);
        Device d1 = new Device("A", wm, 150.1);
        String result = ctr.getDeviceAttribute(d1, 0);
        String expected = "1 - Device Name : A";
        assertEquals(expected, result);
    }

    @Test
    void setAttributeTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE, 20);
        Device d1 = new Device("A", wm, 150.1);
        ctr.setAttribute(d1, "3 - Device Nominal Power : " + d1.getNominalPower(), "155");
        assertEquals(155.0, d1.getNominalPower());
    }


    @Test
    void getDeviceAttributesListInStringTest2() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE, 20);
        Device d1 = new Device("A", wm, 150.1);
        String deviceName = "1 - Device Name : " + d1.getName();
        String deviceNominalPower = "2 - Device Nominal Power : " + d1.getNominalPower();
        String deviceType = "3 - Device Type : " + d1.getDeviceSpecs().getType().getTypeString();
        String capacity = "4 - Washing Machine Capacity : " + wm.getCapacity();
        ctr.getDeviceAttributesListInString(d1);
        List<String> result = d1.getDeviceAttributesInString();
        List<String> expected = Arrays.asList(deviceName, deviceNominalPower,deviceType, capacity);
        assertEquals(expected, result);


    }

    @Test
    @DisplayName("Ensure that device microwave is removed from room kitchen")
    void removeDeviceFromRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(house);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(bathroom);
        roomList.addRoom(kitchen);
        OtherDevices micro = new OtherDevices(DeviceType.MICROWAVE_OVEN);
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);

        boolean result=ctr.removeDeviceFromRoom(microwave,2);

        assertTrue(result);

    }

    @Test
    @DisplayName("Ensure that device microwave is removed from Kitchen and then added to Living Room")
    void changeDeviceRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(livingRoom);
        roomList.addRoom(kitchen);
        OtherDevices tv = new OtherDevices(DeviceType.TV);
        Device microwave = new Device("Samsung Microwave", tv, 0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);

        ctr.removeDeviceFromRoom(microwave,2);
        boolean result=ctr.addDeviceToRoom(microwave,1);
        assertTrue(result);
    }


}