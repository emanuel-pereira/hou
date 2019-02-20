package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class GetEnergyConsumptionInPeriodCTRLTest {

    @Test

    void showMeteredDevicesInStr() {
        House house= new House();
        GetEnergyConsumptionInPeriodCTRL ctrl= new GetEnergyConsumptionInPeriodCTRL(house);
        RoomList roomList= house.getRoomList();

        Room kitchen1= new Room("Kitchen1",0,5,5,3);
        Room kitchen2= new Room("Kitchen2",0,6,4,3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);

        DeviceList k1DeviceList= kitchen1.getDeviceList();
        DeviceList k2DeviceList= kitchen2.getDeviceList();



        DeviceSpecs fridgeSpecs= new Fridge(25,50,25);
        Device fridge= new Device("LG Fridge1",fridgeSpecs,15);
        fridge.setIsMetered(true);
        DeviceSpecs stoveSpecs= new OtherDevices();
        Device stove= new Device("XStove",stoveSpecs,15);
        stove.setIsMetered(false);

        DeviceSpecs fridgeSpecs2= new Fridge(25,50,25);
        Device fridge2= new Device("LG Fridge2",fridgeSpecs2,15);

        DeviceSpecs stoveSpecs2= new OtherDevices();
        Device stove2= new Device("XStove",stoveSpecs2,15);
        stove2.setIsMetered(false);

        k1DeviceList.addDevice(fridge);
        k1DeviceList.addDevice(stove);

        k2DeviceList.addDevice(fridge2);
        k2DeviceList.addDevice(stove2);


        String expected="1 - LG Fridge1\n" +
                "2 - LG Fridge2\n";
        String result= ctrl.showMeteredDevicesInStr();
        assertEquals(expected,result);
    }

    @Test
    void getMeteredDevicesInHouseSize() {
        House house= new House();
        GetEnergyConsumptionInPeriodCTRL ctrl= new GetEnergyConsumptionInPeriodCTRL(house);

        RoomList roomList= house.getRoomList();
        Room r1= new Room("Kitchen1",0,5,5,3);
        Room r2= new Room("Kitchen2",0,6,4,3);

        DeviceList r1DevLst= r1.getDeviceList();
        DeviceList r2DevLst= r2.getDeviceList();

        DeviceSpecs fridge= new Fridge(25,50,25);
        Device dFridge= new Device("LG Fridge1",fridge,15);

        DeviceSpecs stove= new OtherDevices();
        Device dStove= new Device("XStove",stove,15);

        DeviceSpecs fridge2= new Fridge(25,50,25);
        Device dFridge2= new Device("LG Fridge2",fridge2,15);

        DeviceSpecs stove2= new OtherDevices();
        Device dStove2= new Device("XStove",stove2,15);

        dStove.setIsMetered(false);
        dStove2.setIsMetered(false);

        r1DevLst.addDevice(dFridge);
        r1DevLst.addDevice(dStove);
        r2DevLst.addDevice(dFridge2);
        r2DevLst.addDevice(dStove2);
        roomList.addRoom(r1);
        roomList.addRoom(r2);

        int expected=2;
        int result= ctrl.getMeteredDevicesInHouseSize();

        assertEquals(expected,result);

    }

    @Test
    void getEnergyConsumptionInPeriod() {
        House house= new House();
        GetEnergyConsumptionInPeriodCTRL ctrl= new GetEnergyConsumptionInPeriodCTRL(house);

        RoomList roomList= house.getRoomList();
        Room kitchen1= new Room("Kitchen1",0,5,5,3);
        roomList.addRoom(kitchen1);


        DeviceList k1DeviceList= kitchen1.getDeviceList();

        DeviceSpecs ewhSpecs = new ElectricWaterHeater( 25, 50, 2);
        Device ewh= new Device("EWH DAIKIN",ewhSpecs,15);
        k1DeviceList.addDevice(ewh);

        DeviceSpecs fridgeSpecs= new Fridge(25,50,25);
        Device fridge= new Device("LG Fridge1",fridgeSpecs,15);
        k1DeviceList.addDevice(fridge);


        DeviceSpecs stoveSpecs= new OtherDevices();
        Device stove= new Device("XStove",stoveSpecs,15);
        stove.setIsMetered(false);
        k1DeviceList.addDevice(stove);

        ReadingList ewhLog = ewh.getActivityLog();
        ReadingList fridgeLog = fridge.getActivityLog();
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 15, 5, 0, 00));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 20));
        Reading r4 = new Reading(37, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 0, 40));
        Reading r6 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 50));
        Reading r7 = new Reading(22, new GregorianCalendar(2018, 11, 5, 1, 00));
        Reading r8 = new Reading(37, new GregorianCalendar(2018, 11, 5, 1, 10));
        ewhLog.addReading(r2);
        ewhLog.addReading(r3);
        ewhLog.addReading(r4);
        ewhLog.addReading(r5);
        ewhLog.addReading(r6);
        ewhLog.addReading(r7);
        ewhLog.addReading(r8);

        fridgeLog.addReading(r2);
        fridgeLog.addReading(r3);
        fridgeLog.addReading(r4);
        fridgeLog.addReading(r5);
        fridgeLog.addReading(r6);
        fridgeLog.addReading(r7);
        fridgeLog.addReading(r8);

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 5, 0, 10);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 5, 1, 00);

        double expected = 130;
        double result = ctrl.getEnergyConsumptionInPeriod(1,startDate, endDate);

        assertEquals(expected,result);

        String expected1="LG Fridge1";
        String result1=ctrl.getDeviceName(1);
        assertEquals(expected1,result1);
    }

    @Test
    @DisplayName("Ensure that the houseGridName ")
    void getHGName() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        String expected = "Grid 1";
        String result = ctrl.getHGName(0);
        assertEquals(expected, result);
    }

    @Test
    void showHouseGridListInString() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        String expected = "1 - Grid 1\n" +
                "2 - Grid 2\n";
        String result = ctrl.showHouseGridListInString();
        assertEquals(expected, result);
    }

    @Test
    void getHouseGridEnergyConsumptionInPeriod() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGridList houseGridList=house.getHGListInHouse();
        HouseGrid grid= new HouseGrid("MainGrid");
        houseGridList.addHouseGrid(grid);


        RoomList roomList=grid.getRoomListInAGrid();
        Room kitchen= new Room("Kitchen",0,8,8,3);
        Room garage= new Room("Living Room",0,5,4,3);
        roomList.addRoom(kitchen);
        roomList.addRoom(garage);

        DeviceList kitDeviceList= kitchen.getDeviceList();
        DeviceList grDeviceList= garage.getDeviceList();

        DeviceSpecs fridgeSpecs= new Fridge();
        Device fridge= new Device("LG Fridge",fridgeSpecs,2);

        DeviceSpecs ewhSpecs= new ElectricWaterHeater();
        Device ewh1= new Device("Daikin EWH1",ewhSpecs,2);
        Device ewh2= new Device("Daikin EWH1",ewhSpecs,2);

        kitDeviceList.addDevice(fridge);
        kitDeviceList.addDevice(ewh1);
        grDeviceList.addDevice(ewh2);

        ReadingList fridgeActivityLog= fridge.getActivityLog();
        Reading r1= new Reading(20,new GregorianCalendar(2018,2,1,9,10));
        Reading r2= new Reading(20,new GregorianCalendar(2018,2,1,12,10));
        Reading r3= new Reading(20,new GregorianCalendar(2018,2,1,12,20));
        Reading r4= new Reading(20,new GregorianCalendar(2018,2,1,12,30));
        Reading r5= new Reading(20,new GregorianCalendar(2018,2,1,14,40));
        Reading r6= new Reading(20,new GregorianCalendar(2018,2,1,17,50));
        fridgeActivityLog.addReading(r1);
        fridgeActivityLog.addReading(r2);
        fridgeActivityLog.addReading(r3);
        fridgeActivityLog.addReading(r4);
        fridgeActivityLog.addReading(r5);
        fridgeActivityLog.addReading(r6);

        ReadingList ewh2ActivityLog= ewh2.getActivityLog();
        ewh2ActivityLog.addReading(r1);
        ewh2ActivityLog.addReading(r2);
        ewh2ActivityLog.addReading(r3);
        ewh2ActivityLog.addReading(r4);
        ewh2ActivityLog.addReading(r5);
        ewh2ActivityLog.addReading(r6);

        Calendar startTime= new GregorianCalendar(2018,2,1,12,20);
        Calendar endTime= new GregorianCalendar(2018,2,1,15,20);

        double expected=80;
        double result=ctrl.getHouseGridEnergyConsumptionInPeriod(0,startTime,endTime);
        assertEquals(expected,result);
    }

    @Test
    void getHouseGridListSize() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getHouseGridListSize();
        assertEquals(expected, result);
    }

    @Test
    void showListRoomInString() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room r1 = new Room ("cozinha", 1, 10, 20, 3);
        Room r2 = new Room ("sala", 1, 10, 20, 3);
        house.getRoomList ().addRoom (r1);
        house.getRoomList ().addRoom (r2);

        String expectedResult = "1 - cozinha\n2 - sala\n";
        String result = ctrl.showRoomListInStr ();

        assertEquals (expectedResult, result);
    }

    @Test
    void getRoomListSize() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room r1 = new Room ("cozinha", 1, 10, 20, 3);
        Room r2 = new Room ("sala", 1, 10, 20, 3);
        house.getRoomList ().addRoom (r1);
        house.getRoomList ().addRoom (r2);

        int expectedResult = 2;
        int result = ctrl.getRoomListSize ();

        assertEquals (expectedResult, result);
    }

    /**
     * Get the total energy consumption of a room with three devices, two of them area metered from a a list off
     * two rooms and return the correct sum
     */
    @Test
    void getRoomEnergyConsumptionInPeriod() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);

        RoomList roomList = house.getRoomList ();
        Room kitchen1 = new Room ("Kitchen1", 0, 5, 5, 3);
        Room bedroom = new Room ("Bedroom1", 1, 2, 2, 2);
        roomList.addRoom (kitchen1);
        roomList.addRoom (bedroom);

        DeviceList k1DeviceList = kitchen1.getDeviceList ();
        DeviceList roDeviceList = bedroom.getDeviceList ();

        DeviceSpecs ewhSpecs = new ElectricWaterHeater ();
        Device ewh = new Device ("EWH DAIKIN", ewhSpecs, 15);
        k1DeviceList.addDevice (ewh);

        DeviceSpecs fridgeSpecs = new Fridge ();
        Device fridge = new Device ("LG Fridge1", fridgeSpecs, 15);
        Device fridge2 = new Device ("LG Fridge2", fridgeSpecs, 14);
        k1DeviceList.addDevice (fridge);
        k1DeviceList.addDevice (fridge2);

        DeviceSpecs stoveSpecs = new OtherDevices ();
        Device stove = new Device ("XStove", stoveSpecs, 15);
        stove.setIsMetered (false);
        roDeviceList.addDevice (stove);

        ReadingList ewhLog = ewh.getActivityLog ();
        ReadingList fridgeLog = fridge.getActivityLog ();
        ReadingList fridge2Log = fridge2.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 15, 5, 0, 00));
        Reading r3 = new Reading (22, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading r4 = new Reading (37, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading r5 = new Reading (31, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading r6 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading r7 = new Reading (22, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading r8 = new Reading (37, new GregorianCalendar (2018, 11, 5, 1, 10));
        ewhLog.addReading (r2);
        ewhLog.addReading (r3);
        ewhLog.addReading (r4);
        ewhLog.addReading (r5);
        ewhLog.addReading (r6);
        ewhLog.addReading (r7);
        ewhLog.addReading (r8);

        fridgeLog.addReading (r2);
        fridgeLog.addReading (r3);
        fridgeLog.addReading (r4);
        fridgeLog.addReading (r5);
        fridgeLog.addReading (r6);
        fridgeLog.addReading (r7);
        fridgeLog.addReading (r8);

        fridge2Log.addReading (r2);
        fridge2Log.addReading (r3);
        fridge2Log.addReading (r4);
        fridge2Log.addReading (r5);
        fridge2Log.addReading (r6);
        fridge2Log.addReading (r7);
        fridge2Log.addReading (r8);

        GregorianCalendar startDate = new GregorianCalendar (2018, 11, 5, 0, 10);
        GregorianCalendar endDate = new GregorianCalendar (2018, 11, 5, 1, 00);
        ewh.setIsMetered (true);
        fridge.setIsMetered (true);
        fridge2.setIsMetered (false);

        double expected = 260;
        double result = ctrl.getRoomEnergyConsumptionInPeriod (0, startDate, endDate);

        assertEquals (expected, result);
    }


    /**
     * Get the total energy consumption of a room with a non metered device from a list of two rooms and return zero
     */
    @Test
    void getRoomEnergyConsumptionInPeriodIfZero() {
        House house = new House ();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);

        RoomList roomList = house.getRoomList ();
        Room kitchen1 = new Room ("Kitchen1", 0, 5, 5, 3);
        Room bedroom = new Room ("Bedroom1", 1, 2, 2, 2);
        roomList.addRoom (kitchen1);
        roomList.addRoom (bedroom);

        DeviceList k1DeviceList = kitchen1.getDeviceList ();
        DeviceList roDeviceList = bedroom.getDeviceList ();

        DeviceSpecs ewhSpecs = new ElectricWaterHeater ();
        Device ewh = new Device ("EWH DAIKIN", ewhSpecs, 15);
        roDeviceList.addDevice (ewh);

        DeviceSpecs fridgeSpecs = new Fridge ();
        Device fridge = new Device ("LG Fridge1", fridgeSpecs, 15);
        k1DeviceList.addDevice (fridge);

        ReadingList ewhLog = ewh.getActivityLog ();
        ReadingList fridgeLog = fridge.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 15, 5, 0, 00));
        Reading r3 = new Reading (22, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading r4 = new Reading (37, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading r5 = new Reading (31, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading r6 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading r7 = new Reading (22, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading r8 = new Reading (37, new GregorianCalendar (2018, 11, 5, 1, 10));
        ewhLog.addReading (r2);
        ewhLog.addReading (r3);
        ewhLog.addReading (r4);
        ewhLog.addReading (r5);
        ewhLog.addReading (r6);
        ewhLog.addReading (r7);
        ewhLog.addReading (r8);

        fridgeLog.addReading (r2);
        fridgeLog.addReading (r3);
        fridgeLog.addReading (r4);
        fridgeLog.addReading (r5);
        fridgeLog.addReading (r6);
        fridgeLog.addReading (r7);
        fridgeLog.addReading (r8);

        GregorianCalendar startDate = new GregorianCalendar (2018, 11, 5, 0, 10);
        GregorianCalendar endDate = new GregorianCalendar (2018, 11, 5, 1, 00);
        ewh.setIsMetered (false);
        fridge.setIsMetered (true);

        double expected = 0;
        double result = ctrl.getRoomEnergyConsumptionInPeriod (1, startDate, endDate);

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Ensure that the room name is shown")
    void getRoomName() {
        House house = new House();
        GetEnergyConsumptionInPeriodCTRL ctrl = new GetEnergyConsumptionInPeriodCTRL(house);
        Room bedroom = new Room("Bedroom",1,2,2,2);
        Room office = new Room("Office",0,2,1,1);
        RoomList rList = house.getRoomList ();
        rList.addRoom (bedroom);
        rList.addRoom (office);
        String expected = "Bedroom";
        String result = ctrl.getRoomName (0);
        assertEquals(expected, result);
    }

}