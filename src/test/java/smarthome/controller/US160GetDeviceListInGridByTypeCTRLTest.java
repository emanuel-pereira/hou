package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US160GetDeviceListInGridByTypeCTRLTest {

    @Test
    void getHouseGridListTest () {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
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
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
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
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);
        house.getRoomListFromHouse().addRoom(r3);

        //Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",DeviceType.FRIDGE,r1,50);
        //Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",DeviceType.DISHWASHER,r1,50);
        //Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",DeviceType.FRIDGE,r2,50);
        //Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",DeviceType.LAMP,r2,10);
        //Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",DeviceType.LAMP,r3,10);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);
        house.getRoomListFromHouse().get(2).getDeviceList().addDevice(d5);

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
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);
        house.getRoomListFromHouse().addRoom(r3);

        //Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",DeviceType.FRIDGE,r1,50);
        //Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",DeviceType.DISHWASHER,r1,50);
        //Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",DeviceType.FRIDGE,r2,50);
        //Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",DeviceType.LAMP,r2,10);
        //Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",DeviceType.LAMP,r3,10);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);
        house.getRoomListFromHouse().get(2).getDeviceList().addDevice(d5);

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
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);
        house.getRoomListFromHouse().addRoom(r3);

        //Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",DeviceType.FRIDGE,r1,50);
        //Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",DeviceType.DISHWASHER,r1,50);
        //Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",DeviceType.FRIDGE,r2,50);
        //Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",DeviceType.LAMP,r2,10);
        //Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",DeviceType.LAMP,r3,10);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);
        house.getRoomListFromHouse().get(2).getDeviceList().addDevice(d5);

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
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);
        house.getRoomListFromHouse().addRoom(r3);

        //Device d1 = new Device("frigo",r1,"fridge",50);
        //Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",DeviceType.FRIDGE,r1,50);
        //Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",DeviceType.DISHWASHER,r1,50);
        //Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",DeviceType.FRIDGE,r2,50);
        //Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",DeviceType.LAMP,r2,10);
        //Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",DeviceType.LAMP,r3,10);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);
        house.getRoomListFromHouse().get(2).getDeviceList().addDevice(d5);

        r1.setmHouseGrid(hg1);
        r2.setmHouseGrid(hg2);
        r3.setmHouseGrid(hg1);

        List<Device> expectedResult = Arrays.asList(d1,d2,d5);//d1,d2,d5);
        List<Device> result = ctrl160.deviceListGroupByTypeCtrl(1);

        assertNotEquals(expectedResult,result);
    }

    @Test
    void showGroupedByDeviceListInGridStringTest() {
        House house = new House();
        US160GetDeviceListInGridByTypeCTRL ctrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
        HouseGrid hg1 = new HouseGrid("grid1",100);
        HouseGrid hg2 = new HouseGrid("grid2",200);
        house.getHGListInHouse().addHouseGrid(hg1);
        house.getHGListInHouse().addHouseGrid(hg2);

        Room r1 = new Room("cozinha",1,2,2,2);
        Room r2 = new Room("sala",1,2,2,2);
        Room r3 = new Room("quarto",2,2,2,2);
        house.getRoomListFromHouse().addRoom(r1);
        house.getRoomListFromHouse().addRoom(r2);
        house.getRoomListFromHouse().addRoom(r3);

        //Fridge fridge1 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d1 = new Device("frigo",DeviceType.FRIDGE,r1,50);
        //Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER,20);
        Device d2 = new Device("maq loica",DeviceType.DISHWASHER,r1,50);
        //Fridge fridge2 = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device d3 = new Device("mini",DeviceType.FRIDGE,r2,50);
        //Lamp lamp1 = new Lamp(DeviceType.LAMP,10);
        Device d4 = new Device("candeeiro",DeviceType.LAMP,r2,10);
        //Lamp lamp2 = new Lamp(DeviceType.LAMP,10);
        Device d5 = new Device("candeeiro2",DeviceType.LAMP,r3,10);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d1);
        house.getRoomListFromHouse().get(0).getDeviceList().addDevice(d2);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d3);
        house.getRoomListFromHouse().get(1).getDeviceList().addDevice(d4);
        house.getRoomListFromHouse().get(2).getDeviceList().addDevice(d5);

        r1.setmHouseGrid(hg1);
        r2.setmHouseGrid(hg2);
        r3.setmHouseGrid(hg1);

        String expectedResult = "1 - maq loica, Nominal Power: 50.0, Type: Dishwasher,Location: cozinha.\n2 - frigo, Nominal Power: 50.0, Type: Fridge,Location: cozinha.\n3 - candeeiro2, Nominal Power: 10.0, Type: Lamp,Location: quarto.\n";
        String result = ctrl160.showGroupedDeviceListInGridString(1);

        assertEquals(expectedResult,result);
    }
}