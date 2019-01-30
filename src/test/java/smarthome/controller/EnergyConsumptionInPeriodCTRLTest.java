package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class EnergyConsumptionInPeriodCTRLTest {

    @Test

    void showMeteredDevicesInStr() {
        House house= new House();
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);
        RoomList roomList= house.getRoomList();

        Room kitchen1= new Room("Kitchen1",0,5,5,3);
        Room kitchen2= new Room("Kitchen2",0,6,4,3);
        roomList.addRoom(kitchen1);
        roomList.addRoom(kitchen2);

        DeviceList k1DeviceList= kitchen1.getDeviceList();
        DeviceList k2DeviceList= kitchen2.getDeviceList();

        DeviceSpecs fridgeSpecs= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device fridge= new Device("LG Fridge1",fridgeSpecs,15);
        fridge.setIsMetered(true);
        DeviceSpecs stoveSpecs= new OtherDevices(DeviceType.STOVE);
        Device stove= new Device("XStove",stoveSpecs,15);
        stove.setIsMetered(false);

        DeviceSpecs fridgeSpecs2= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device fridge2= new Device("LG Fridge2",fridgeSpecs2,15);

        DeviceSpecs stoveSpecs2= new OtherDevices(DeviceType.STOVE);
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
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);

        RoomList roomList= house.getRoomList();
        Room r1= new Room("Kitchen1",0,5,5,3);
        Room r2= new Room("Kitchen2",0,6,4,3);

        DeviceList r1DevLst= r1.getDeviceList();
        DeviceList r2DevLst= r2.getDeviceList();

        DeviceSpecs fridge= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device dFridge= new Device("LG Fridge1",fridge,15);

        DeviceSpecs stove= new OtherDevices(DeviceType.STOVE);
        Device dStove= new Device("XStove",stove,15);

        DeviceSpecs fridge2= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device dFridge2= new Device("LG Fridge2",fridge2,15);

        DeviceSpecs stove2= new OtherDevices(DeviceType.STOVE);
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
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);

        RoomList roomList= house.getRoomList();
        Room kitchen1= new Room("Kitchen1",0,5,5,3);
        roomList.addRoom(kitchen1);


        DeviceList k1DeviceList= kitchen1.getDeviceList();


        DeviceSpecs ewhSpecs = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 25, 50, 2);
        Device ewh= new Device("EWH DAIKIN",ewhSpecs,15);
        k1DeviceList.addDevice(ewh);

        DeviceSpecs fridgeSpecs= new Fridge(DeviceType.FRIDGE,25,50,25);
        Device fridge= new Device("LG Fridge1",fridgeSpecs,15);
        k1DeviceList.addDevice(fridge);


        DeviceSpecs stoveSpecs= new OtherDevices(DeviceType.STOVE);
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
        ewh.setIsMetered(true);

        double expected = 130;
        double result = ctrl.getEnergyConsumptionInPeriod(2,startDate, endDate);

        assertEquals(expected,result);
    }

    @Test
    void dateIsValid() {
        House house= new House();
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);
        String year="2018";
        boolean result= ctrl.yearIsValid(year);
        assertTrue(result);

        String month="11";
        boolean result1= ctrl.monthIsValid(month);
        assertTrue(result1);

        int yearAsInteger=Integer.parseInt(year);
        int monthAsInteger=Integer.parseInt(month);
        String day="30";
        boolean result2= ctrl.dayIsValid(day,monthAsInteger,yearAsInteger);
        assertTrue(result2);
    }

    @Test
    void monthAndDayAreInvalid() {
        House house= new House();
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);
        String year="2018";
        boolean result= ctrl.yearIsValid(year);
        assertTrue(result);

        String month="15";
        boolean result1= ctrl.monthIsValid(month);
        assertFalse(result1);

        int yearAsInteger=Integer.parseInt(year);
        int monthAsInteger=Integer.parseInt(month);
        String day="35";
        boolean result2= ctrl.dayIsValid(day,monthAsInteger,yearAsInteger);
        assertFalse(result2);
    }

    @Test
    void hourIsValid() {
        House house= new House();
        EnergyConsumptionInPeriodCTRL ctrl= new EnergyConsumptionInPeriodCTRL(house);
        String hour="15";
        boolean result= ctrl.hourIsValid(hour);
        assertTrue(result);

        String minute="15";
        boolean result1= ctrl.minuteIsValid(minute);
        assertTrue(result1);
    }

}