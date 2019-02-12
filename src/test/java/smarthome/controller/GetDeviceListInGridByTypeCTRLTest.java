/*package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetDeviceListInGridByTypeCTRLTest {

    @Test
    void getHouseGridListTest () {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        List<HouseGrid> expectedResult = Arrays.asList(hg1,hg2);
        List<HouseGrid> result = ctrl160.getHouseGridListCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    void showHouseGridListInStringTest () {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        String expectedResult = "1 - grid1\n"+
                                "2 - grid2\n";
        String result = ctrl160.showHouseGridListInStringCtrl();

        assertEquals(expectedResult,result);
    }

    @Test
    void deviceListInGridCtrlTestSize() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListSize().addRoom(r1);
        house.getRoomListSize().addRoom(r2);
        house.getRoomListSize().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,r1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,r1,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,r2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,r2,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,r3,10);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d1);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d2);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d3);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d4);
        house.getRoomListSize().get(2).deviceListSizeInGridIsNotEmpty().addDevice(d5);

        r1.setmHouseGrid(hg1);
        r2.setmHouseGrid(hg2);
        r3.setmHouseGrid(hg1);

        int expectedResult = 3;
        int result = ctrl160.getDeviceListInGridCtrl(1).size();

        assertEquals(expectedResult,result);
    }

    @Test
    void deviceListInGridCtrlTest() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,r1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,r1,50);
      Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,r2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,r2,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,r3,10);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d1);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d2);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d3);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d4);
        house.getRoomListSize().get(2).deviceListSizeInGridIsNotEmpty().addDevice(d5);

        r1.setmHouseGrid(hg1);
        r2.setmHouseGrid(hg2);
        r3.setmHouseGrid(hg1);

        List<Device> expectedResult = Arrays.asList(d1,d2,d5);
        List<Device> result = ctrl160.getDeviceListInGridCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    void deviceListInGridCtrlTestGROUPBY() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListSize().addRoom(r1);
        house.getRoomListSize().addRoom(r2);
        house.getRoomListSize().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,r1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,r1,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,r2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,r2,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,r3,10);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d1);
        house.getRoomListSize().get(0).deviceListSizeInGridIsNotEmpty().addDevice(d2);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d3);
        house.getRoomListSize().get(1).deviceListSizeInGridIsNotEmpty().addDevice(d4);
        house.getRoomListSize().get(2).deviceListSizeInGridIsNotEmpty().addDevice(d5);

        r1.setmHouseGrid(hg1);
        r2.setmHouseGrid(hg2);
        r3.setmHouseGrid(hg1);

        List<Device> expectedResult = Arrays.asList(d2,d1,d5);//d1,d2,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1);

        assertEquals(expectedResult,result);
    }

    @Test
    void deviceListInGridCtrlTestGROUPBYnotEquals() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListSize().addRoom(r1);
        house.getRoomListSize().addRoom(r2);
        house.getRoomListSize().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.attachRoomToGrid(r1);
        hg1.attachRoomToGrid(r3);
        hg2.attachRoomToGrid(r2);

        List<Device> expectedResult = Arrays.asList(d1,d2,d5);//d1,d2,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1);

        assertNotEquals(expectedResult,result);
    }

    @Test
    void showGroupedByDeviceListInGridStringTest() {
        House house = new House();
        GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1");
        HouseGrid hg2 = new HouseGrid("grid2");
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomList().addRoom(r1);
        house.getRoomList().addRoom(r2);
        house.getRoomList().addRoom(r3);

        Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",fridge1,50);
        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",dishwasher,50);
        Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",fridge2,50);
        Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",lamp1,10);
        Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",lamp2,10);
        house.getRoomList().get(0).getDeviceList().addDevice(d1);
        house.getRoomList().get(0).getDeviceList().addDevice(d2);
        house.getRoomList().get(1).getDeviceList().addDevice(d3);
        house.getRoomList().get(1).getDeviceList().addDevice(d4);
        house.getRoomList().get(2).getDeviceList().addDevice(d5);

        hg1.attachRoomToGrid(r1);
        hg1.attachRoomToGrid(r3);
        hg2.attachRoomToGrid(r2);

        String expectedResult = "1 - maq loica, Nominal Power: 50.0, Type: Dishwasher,Location: cozinha.\n2 - frigo, Nominal Power: 50.0, Type: Fridge,Location: cozinha.\n3 - candeeiro2, Nominal Power: 10.0, Type: Lamp,Location: quarto.\n";
        String result = ctrl160.showGroupedDeviceListInGridString(1);

        assertEquals(expectedResult,result);
    }
}*/
