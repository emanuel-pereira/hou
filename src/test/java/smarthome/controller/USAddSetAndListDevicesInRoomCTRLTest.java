package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class USAddSetAndListDevicesInRoomCTRLTest {

    @Test
    void showRoomListInString() {
        Room kitchen = new Room("Kitchen",0,5.5,5,3);
        Room bathroom = new Room("Bathroom",0,3,2,3);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        House house = new House();
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(bathroom);
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        String expected="1 - Kitchen\n2 - Bathroom\n";
        String result=ctrl.showRoomListInString();
        assertEquals(expected,result);
    }

    @Test
    void addDeviceWithSpecsToRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        DeviceList deviceList= new DeviceList();
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        ctrl.addDevice(1,"LG Fridge",fridge,2);
        String expected="LG Fridge";
        String result= kitchen.getDeviceList().get(0).getName();
        assertEquals(expected,result);
            }

    @Test
    void addDeviceWithoutSpecsToRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Room livingRoom= new Room("Living Room",0,5,5,2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(livingRoom);
        ctrl.addDevice(2,"Sony Tv",DeviceType.TV,0.2);
        ctrl.addDevice(2,"Bosch Washing Machine",DeviceType.WASHING_MACHINE,0.2);
        String expected="Bosch Washing Machine";
        String result= livingRoom.getDeviceList().getLastElement().getName();
        assertEquals(expected,result);
    }

    @Test
    void showDeviceListInString() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        ctrl.addDevice(1,"Samsung Microwave",DeviceType.MICROWAVE_OVEN,0.8);
        ctrl.addDevice(1,"LG Fridge",fridge,1.5);
        String expected="1 - Device: Samsung Microwave | Type: Microwave Oven\n2 - Device: LG Fridge | Type: Fridge\n";
        String result= ctrl.showDeviceListInString(1);
        assertEquals(expected,result); }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        String name ="Fridge 1 - Sony";
        String expected="Fridge 1 - Sony";
        String result= ctrl.alphanumericName(name);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not empty string")
    void emptyStringReturnsNull() {
        NameValidations n = new NameValidations();
        String name=" ";
        String expected=null;
        String result=n.alphanumericName(name);
        assertEquals(expected,result);
    }
}