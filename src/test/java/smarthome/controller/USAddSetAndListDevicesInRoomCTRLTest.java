package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class USAddSetAndListDevicesInRoomCTRLTest {

    @Test
    void showRoomListInString() {
        Room kitchen = new Room("Kitchen", 0, 5.5, 5, 3);
        Room bathroom = new Room("Bathroom", 0, 3, 2, 3);
        RoomList roomList = new RoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        House house = new House();
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(bathroom);
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result = ctrl.showRoomListInString();
        assertEquals(expected, result);
    }

    @Test
    void addDeviceWithSpecsToRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        DeviceList deviceList = new DeviceList();
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        ctrl.addDevice(1, "LG Fridge", fridge, 2);
        String expected = "LG Fridge";
        String result = kitchen.getDeviceList().get(0).getName();
        assertEquals(expected, result);
    }

    @Test
    void addDeviceWithoutSpecsToRoom() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Room livingRoom = new Room("Living Room", 0, 5, 5, 2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(livingRoom);
        ctrl.addDevice(2, "Sony Tv", DeviceType.TV, 0.2);
        ctrl.addDevice(2, "Bosch Washing Machine", DeviceType.WASHING_MACHINE, 0.2);
        String expected = "Bosch Washing Machine";
        String result = livingRoom.getDeviceList().getLastElement().getName();
        assertEquals(expected, result);
    }

    @Test
    void showDeviceListInString() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        house.getRoomListFromHouse().addRoom(kitchen);
        Fridge fridge = new Fridge(DeviceType.FRIDGE, 50, 350, 50);
        ctrl.addDevice(1, "Samsung Microwave", DeviceType.MICROWAVE_OVEN, 0.8);
        ctrl.addDevice(1, "LG Fridge", fridge, 1.5);
        String expected = "1 - Device: Samsung Microwave | Type: Microwave Oven\n2 - Device: LG Fridge | Type: Fridge\n";
        String result = ctrl.showDeviceListInString(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that an alphanumeric name with spaces and hyphens is valid")
    void alphanumericName() {
        House house = new House();
        USAddSetAndListDevicesInRoomCTRL ctrl = new USAddSetAndListDevicesInRoomCTRL(house);
        String name = "Fridge 1 - Sony";
        String expected = "Fridge 1 - Sony";
        String result = ctrl.alphanumericName(name);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that alphanumericName() method does not empty string")
    void emptyStringReturnsNull() {
        NameValidations n = new NameValidations();
        String name = " ";
        String expected = null;
        String result = n.alphanumericName(name);
        assertEquals(expected, result);
    }

    @Test
    void getRoomListFromHouseTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);

        Room r1 = new Room("B", 1, 1, 1, 1);
        Room r2 = new Room("A", 1, 1, 1, 1);

        h.getRoomListFromHouse().addRoom(r1);
        h.getRoomListFromHouse().addRoom(r2);

        RoomList result = ctr.getRoomList();
        RoomList expected = h.getRoomListFromHouse();

        assertEquals(expected, result);
    }

    @Test
    void getDeviceListTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Room r2 = new Room("A", 1, 1, 1, 1);
        h.getRoomListFromHouse().addRoom(r1);
        h.getRoomListFromHouse().addRoom(r2);
        Device d1 = new Device("A", r1, "type1", 150.1);
        Device d2 = new Device("B", r1, "type2", 53.1);
        Device d3 = new Device("C", r1, "type3", 5.5);
        d1.setRoom(r1);
        d2.setRoom(r2);
        d3.setRoom(r1);
        DeviceList result = ctr.getDeviceList(r1);
        DeviceList expected = h.getRoomListFromHouse().getRoomList().get(0).getDeviceList();
        assertEquals(expected, result);
    }

    @Test
    void setDeviceNameTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Room r1 = new Room("B", 1, 1, 1, 1);
        h.getRoomListFromHouse().addRoom(r1);
        Device d1 = new Device("A", r1, "type1", 150.1);
        d1.setRoom(r1);
        ctr.setDeviceName(d1, "B");
        assertEquals(d1.getName(), "B");
    }

    @Test
    void setDeviceRoomTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Room r2 = new Room("A", 1, 1, 1, 1);
        h.getRoomListFromHouse().addRoom(r1);
        Device d1 = new Device("A", r1, "type1", 150.1);
        ctr.setDeviceRoom(d1, r2);
        assertEquals(d1.getRoom(), r2);
    }

    @Test
    void setDeviceNominalPowerTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Room r1 = new Room("B", 1, 1, 1, 1);
        h.getRoomListFromHouse().addRoom(r1);
        Device d1 = new Device("A", r1, "type1", 150.1);
        ctr.setNominalPower(d1, 68.9);
        assertEquals(d1.getNominalPower(), 68.9);
    }

    @Test
    void setFridgeAttributesTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Fridge fr = new Fridge(DeviceType.FRIDGE, 2, 3, 6);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", fr, r1, 150.1);

        ctr.setFridgeFreezerCapacity(d1, 7);
        assertEquals(fr.getFreezerCapacity(), 7);
        ctr.setFridgeRefrigeratorCapacity(d1, 1);
        assertEquals(fr.getRefrigeratorCapacity(), 1);
    }

    @Test
    void setDishWaterAttributesTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Dishwasher dw = new Dishwasher(DeviceType.DISHWASHER, 1);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", dw, r1, 150.1);

        ctr.setDWCapacity(d1, 7);
        assertEquals(dw.getCapacity(), 7);
    }

    @Test
    void setEWaterHeaterAttributesTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        ElectricWaterHeater ewh = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 100, 34, 12, 1.5);
        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", ewh, r1, 150.1);

        ctr.setEWHVolumeOfWater(d1, 120);
        assertEquals(ewh.getVolumeOfWater(), 120);
        ctr.setEWHHotWaterTemperature(d1, 36);
        assertEquals(ewh.getHotWaterTemperature(), 36);
        ctr.setEWHColdWaterTemperature(d1, 10);
        assertEquals(ewh.getColdWaterTemperature(), 10);
        ctr.setEWHPerformanceRatio(d1, 1.1);
        assertEquals(ewh.getPerformanceRatio(),1.1);
    }
    @Test
    void setLampAttributesTest(){
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        Lamp l = new Lamp(DeviceType.LAMP,10);

        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", l, r1, 150.1);

        ctr.setLampLuminousFlux(d1, 12);
        assertEquals(l.getLuminousFlux(), 12);
    }
    @Test
    void setWashingMachineAttributes() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE,20);

        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", wm, r1, 150.1);

        ctr.setWashingMachineCapacity(d1, 15);
        assertEquals(wm.getCapacity(), 15);
    }
    @Test
    void getDeviceAttributesListInStringTest() {
        House h = new House();
        USAddSetAndListDevicesInRoomCTRL ctr = new USAddSetAndListDevicesInRoomCTRL(h);
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE,20);

        Room r1 = new Room("B", 1, 1, 1, 1);
        Device d1 = new Device("A", wm, r1, 150.1);
        String result = ctr.getDeviceAttributesListInString(d1);
        String expected = "1 - Device name : "+d1.getName()+"\n2 - Device room : "+d1.getRoom().getName()+"\n3 - Nominal Power : "+d1.getNominalPower()+ " kW\n4 - Capacity : 20" ;
        assertEquals(expected,result);
    }
}