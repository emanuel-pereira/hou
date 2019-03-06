/*package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.validations.NameValidations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditDevicesCTRLTest {

    @Test
    @DisplayName("Ensure showRoomListInString method returns a list of two rooms: Kitchen and Bathroom")
    void showRoomListInString() {
        House house = new House();
        EditDevicesCTRL ctrl = new EditDevicesCTRL(house);
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
    void getRoomListTest() {
        House house = new House();
        EditDevicesCTRL ctrl = new EditDevicesCTRL(house);
        RoomList roomList = house.getRoomList();
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getRoomListSize();
        assertEquals(expected, result);

        int result2 = ctrl.getRoomList().getRoomListSize();
        int expected2= 2;
        assertEquals(expected,result);
    }

    @Test
    void addDeviceToRoom() {
        House house = new House();
        EditDevicesCTRL ctrl = new EditDevicesCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomList().addRoom(kitchen);
        Fridge fridge = new Fridge( 50, 350, 50);
        ctrl.addDevice(1, "LG Fridge", "Fridge", 2);
        String expected = "LG Fridge";
        String result = kitchen.getDeviceList().get(0).getDeviceName();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceListInString() throws ClassNotFoundException, IllegalAccessException, InstantiationException  {
        House house = new House();
        EditDevicesCTRL ctrl = new EditDevicesCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomList().addRoom(kitchen);
        DeviceType fridge = new DeviceType("Fridge");
        DeviceType microwave = new DeviceType("MicrowaveOven");
        Device fr = kitchen.getDeviceList().newDeviceV2(fridge);
        kitchen.getDeviceList().addDevice(fr);
        fr.setAttributeValue("Device Name","LG Fridge");
        Device mic = kitchen.getDeviceList().newDeviceV2(microwave);
        kitchen.getDeviceList().addDevice(mic);
        mic.setAttributeValue("Device Name","Samsung Microwave");
        String expected = "1 - Device: LG Fridge | Type: Fridge | Active: true\n2 - Device: Samsung Microwave | Type: MicrowaveOven | Active: true\n";
        String result = ctrl.showDeviceListInString(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        House house = new House();
        EditDevicesCTRL ctrl = new EditDevicesCTRL(house);
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
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Room r2 = new Room("A", 1, 1, 1, 1);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(r1);
        roomList.addRoom(r2);
        OtherDevices stove = new OtherDevices();
        Device d1 = newDevice("A", stove, 150.1);
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
    void getDeviceAttributesListInStringTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        Room r = new Room("BedRoom", 1, 1, 1, 1);
        h.getRoomList().addRoom(r);
        Room r1 = ctr.getRoomFromListIndex(1);
        DeviceList deviceList = ctr.getDeviceList(r);
        DeviceType deviceType = new DeviceType("WashingMachine");
        Device d1 = deviceList.newDeviceV2(deviceType);
        ctr.setAttribute(d1, "Device Name", "A");
        ctr.setAttribute(d1, "Device Nominal Power", "150.1");
        String result = ctr.showDeviceAttributesInString(d1);
        String expected = "" + d1.getDeviceSpecs().getNewDeviceType().getDeviceTypeName() + "\n1 - Device Name : A\n" +
                "2 - Device Nominal Power : 150.1\n3 - Washing Machine Capacity : 0\n";
        assertEquals(expected, result);
    }

    @Test
    void getDeviceAttribute() {
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        WashingMachine wm = new WashingMachine( 20);
        Device d1 = new Device("A", wm, 150.1);
        String result = ctr.getDeviceAttribute(d1, 0);
        String expected = "Device Name";
        assertEquals(expected, result);
    }

    @Test
    void setAttributeTest()throws IllegalAccessException {
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        WashingMachine wm = new WashingMachine( 20);
        Device d1 = new Device("A", wm, 150.1);
        ctr.setAttribute(d1, "3 - Device Nominal Power : " + d1.getNominalPower(), "155");
        assertEquals(155.0, d1.getNominalPower());
    }


    @Test
    void getDeviceAttributesListInStringTest2() {
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        WashingMachine wm = new WashingMachine( 20);
        Device d1 = new Device("A", wm, 150.1);
        String deviceName = "Device Name";
        String deviceNominalPower = "Device Nominal Power";
        String capacity = "Washing Machine Capacity";
        ctr.getDeviceAttributesListInString(d1);
        List<String> result = d1.getDeviceAttributesInString();
        List<String> expected = Arrays.asList(deviceName, deviceNominalPower, capacity);
        assertEquals(expected, result);


    }

    @Test
    @DisplayName("Ensure that device microwave is removed from room kitchen")
    void removeDeviceFromRoom() {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(bathroom);
        roomList.addRoom(kitchen);
        OtherDevices micro = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", micro, 0.8);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);

        boolean result = ctr.removeDeviceFromRoom(microwave, 2);

        assertTrue(result);

    }

    @Test
    @DisplayName("Ensure that device microwave is removed from Kitchen and then added to Living Room")
    void changeDeviceRoom() {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(livingRoom);
        roomList.addRoom(kitchen);
        OtherDevices tv = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", tv, 0.8);
        Fridge fridge = new Fridge( 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        kitchenDevList.addDevice(microwave);
        kitchenDevList.addDevice(dFridge);

        ctr.removeDeviceFromRoom(microwave, 2);
        boolean result = ctr.addDeviceToRoom(microwave, 1);
        assertTrue(result);
    }


    @Test
    @DisplayName("Ensure that device microwave is removed from Kitchen")
    void removeDevice() {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        assertTrue(roomList.addRoom(livingRoom));
        assertTrue(roomList.addRoom(kitchen));
        OtherDevices tv = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", tv, 0.8);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertTrue(kitchenDevList.addDevice(microwave));
        assertTrue(kitchenDevList.addDevice(dFridge));

        assertTrue(ctr.removeDevice(2, 0));
    }

    @Test
    void deactivateDevice() {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        assertTrue(roomList.addRoom(livingRoom));
        assertTrue(roomList.addRoom(kitchen));
        OtherDevices tv = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", tv, 0.8);
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertTrue(kitchenDevList.addDevice(microwave));
        assertTrue(kitchenDevList.addDevice(dFridge));

        assertTrue(ctr.deactivateDevice(2, 0));
    }

    @Test
    void NotPossibleToDeactivateDevice() {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        RoomList roomList = house.getRoomList();
        assertTrue(roomList.addRoom(livingRoom));
        assertTrue(roomList.addRoom(kitchen));
        OtherDevices tv = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", tv, 0.8);
        Fridge fridge = new Fridge( 50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        assertTrue(kitchenDevList.addDevice(microwave));
        assertTrue(kitchenDevList.addDevice(dFridge));

        boolean thrown = false;
        try {
            ctr.deactivateDevice(2, 3);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void createdDeviceTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        Room livingRoom = new Room("Living Room", 0, 3, 2, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(livingRoom);
        Room r = ctr.getRoomFromListIndex(1);
        DeviceType deviceType = new DeviceType("Lamp");
        Device d = ctr.createDevice(r, deviceType);
        List<String> result = ctr.getDeviceAttributesListInString(d);
        List<String> expected = Arrays.asList("Device Name", "Device Nominal Power", "Luminous Flux");
        assertEquals(expected, result);
    }

    @Test
    void showDeviceTypesListInStringTest(){
        House house = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(house);
        String expected = "1 - ElectricWaterHeater\n" +
                "2 - WashingMachine\n" +
                "3 - Dishwasher\n" +
                "4 - Fridge\n" +
                "5 - Kettle\n" +
                "6 - Oven\n" +
                "7 - Stove\n" +
                "8 - MicrowaveOven\n" +
                "9 - WallElectricHeater\n" +
                "10 - PortableElectricOilHeater\n" +
                "11 - PortableElectricConvectionHeater\n" +
                "12 - WallTowelHeater\n" +
                "13 - Lamp\n" +
                "14 - Television\n";
        String result = ctr.showDeviceTypesListInString();
        assertEquals(expected,result);
    }

    @Test
    void getDeviceTypeFromIndexTest(){
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        DeviceType d = ctr.getDeviceTypeFromIndex(1);
        assertEquals("ElectricWaterHeater",d.getDeviceTypeName());
    }
    @Test
    void getDeviceFromIndex() throws ClassNotFoundException,IllegalAccessException,InstantiationException{
        House h = new House();
        EditDevicesCTRL ctr = new EditDevicesCTRL(h);
        DeviceType deviceType = new DeviceType("Television");
        Room r = new Room("A",1,1,1,1);
        h.getRoomList().addRoom(r);
        Device d = r.getDeviceList().newDeviceV2(deviceType);
        r.getDeviceList().addDevice(d);
        Device device = ctr.getDeviceFromIndex(1,1);
        assertEquals(device,d);
    }
    @Test
    void showDeviceListInStringTest() {

    }

}
*/