package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.Fan;
import smarthome.model.devices.FanType;
import smarthome.model.devices.WashingMachine;
import smarthome.model.devices.WashingMachineType;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    /**
     * Checks if the room name is only spaces, if so the validation method returns False
     */
    @Test
    public void nameNotValid() {

        Room roomOne = new Room("  ", "  ", 0, 2.5, 3, 2);

        String name = "  ";
        boolean result = roomOne.validateName(name);

        assertFalse(result);
    }

    /**
     * Checks if the room name is null, if so the validation method returns False
     */
    @Test
    public void nullNameNotValid() {

        String name = " ";
        String id = " ";
        Room roomOne = new Room(id, name, 0, 2.5, 3, 2);

        boolean result = roomOne.validateName(name);

        assertFalse(result);
    }

    /**
     * Checks if the room name is correct, if so the validation method returns True
     */
    @Test
    public void nameValid() {

        Room roomOne = new Room("R01", "bedroom", 0, 2.5, 3, 4.7);

        String name = "bedroom";
        boolean result = roomOne.validateName(name);

        assertTrue(result);
    }

    /**
     * Checks if two different room objects are equals because of their content
     */
    @Test
    public void equalsIfRoomEqualsRoom() {
        Room room1 = new Room("R01","bedroom", 0, 2.5, 3, 3);
        Room room2 = new Room("R01","bedroom", 0, 2.5, 3, 3);

        boolean result = room1.equals(room2);

        assertEquals(room1.hashCode(), room2.hashCode());
        assertTrue(result);
    }

    /**
     * Checks if two different room objects are different because of their content
     */
    @Test
    public void equalsIfRoomEqualsDifferentRoom() {
        Room room1 = new Room("R01", "bedroom", 0, 2.5, 3, 3);
        Room room2 = new Room("R02", "garden", 0, 2.5, 3, 3);

        boolean result = room1.equals(room2);

        assertNotEquals(room1.hashCode(), room2.hashCode());
        assertFalse(result);
    }

    /**
     * Check if two different types objects are not equal
     */
    @Test
    public void equalsIfStringEqualsRoom() {
        String person1 = "Joana";
        Room room1 = new Room("R01","bedroom", 0, 2.5, 3, 3);
        boolean result;

        result = room1.equals(person1);

        assertFalse(result);
    }


    /**
     * Test to define/edit the name of the room.
     */
    @Test
    public void setName() {

        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2.5);
        bedroom.setName("bedroom1");

        String expectedResult = "bedroom1";
        String result = bedroom.getName();

        assertEquals(expectedResult, result);

    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    public void setFloor() {

        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);
        bedroom.setFloor(2);

        int expectedResult = 2;
        int result = bedroom.getFloor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test to define/edit the dimensions of the room.
     */
    @Test
    public void setArea() {

        OccupationArea oa = new OccupationArea(3, 2);
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);
        bedroom.setArea(oa);

        OccupationArea result = bedroom.getArea();

        assertEquals(oa, result);


    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    public void setHeight() {

        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);
        bedroom.setHeight(3);

        double expectedResult = 3;
        double result = bedroom.getHeight();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Correctly get the estimated energy consumption of a room")
    void getCorrectRoomEstimatedEnergyConsumption() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = roomList.createNewRoom("R01", "bedroom", 1, 2, 2, 2);
        roomList.addRoom(kitchen);

        DeviceType dt = new FanType();
        Device d = dt.createDevice("Fan 1",2);
        Fan fan = (Fan) d;

        DeviceType dt1= new WashingMachineType();
        Device d1 = dt1.createDevice("Washing Machine1",100);
        WashingMachine washingMachine = (WashingMachine) d1;

        DeviceList deviceList = kitchen.getDeviceList();
        deviceList.addDevice(fan);
        deviceList.addDevice(washingMachine);

        ProgramMode fast = fan.createProgram("Fast", 2);
        ProgramMode ultraFast = fan.createProgram("Ultra Fast", 4);
        fan.addProgramToList(fast);
        fan.addProgramToList(ultraFast);
        ultraFast.setTime(10);
        fan.setMeteredProgram("Ultra Fast");

        ProgramWithTimer eco = washingMachine.createProgram("Eco", 20);
        washingMachine.addProgramToList(eco);
        eco.setDuration(30);
        washingMachine.setMeteredProgram("Eco");

        double expected = 60;
        double result = kitchen.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Correctly get the estimated energy consumption of a room")
    void getRoomEstimatedEnergyConsumption() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room kitchen = roomList.createNewRoom("R01","bedroom", 1, 2, 2, 2);
        roomList.addRoom(kitchen);

        DeviceType dt = new FanType();
        Device d = dt.createDevice("Fan 1",2);
        Fan fan = (Fan) d;

        DeviceType dt1= new WashingMachineType();
        Device d1 = dt1.createDevice("Washing Machine1",100);
        WashingMachine washingMachine = (WashingMachine) d1;


        DeviceList deviceList = kitchen.getDeviceList();
        deviceList.addDevice(fan);
        deviceList.addDevice(washingMachine);

        ProgramMode fast = fan.createProgram("Fast", 2);
        ProgramMode ultraFast = fan.createProgram("Ultra Fast", 4);
        fan.addProgramToList(fast);
        fan.addProgramToList(ultraFast);
        ultraFast.setTime(10);
        fan.setMeteredProgram("Ultra Fast");

        ProgramWithTimer eco = washingMachine.createProgram("Eco", 20);
        washingMachine.addProgramToList(eco);
        eco.setDuration(30);
        washingMachine.setMeteredProgram("Eco");

        double expected = 60;
        double result = kitchen.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of two devices
     */

    /*
    @Test
    public void getCorrectNominalPowerIfTwoDevices() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device("EWH DAIKIN2", ewh, 15);
        roomList.addDeviceToRoom (dEWH1, 1);
        roomList.addDeviceToRoom (dEWH2, 1);

        double expectedResult = 30;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }
*/

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of one device
     */

  /*
    @Test
    public void getCorrectNominalPowerIfOneDevice() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        roomList.addDeviceToRoom (dEWH1, 1);

        double expectedResult = 15;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }

*/
    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of two devices with zero nominal power
     */

 /*FIXME
    @Test
    public void getCorrectNominalPowerIfZero() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        DeviceSpecs fri = new FridgeSpecs("Fridge");
        Device dEWH1 = new Device("Fridge DAIKIN1", fri, 0);
        Device dEWH2 = new Device("Fridge DAIKIN2", fri, 0);roomList.addDeviceToRoom (dEWH1, 1);
        roomList.addDeviceToRoom (dEWH2, 1);

        double expectedResult = 0;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }

    */

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of a room with no devices
     */
    @Test
    public void getCorrectNominalPowerIf() {
        House house = new House();
        RoomList roomList = house.getRoomList();
        Room bedroom = roomList.createNewRoom("R01","bedroom", 1, 2, 2, 2);
        roomList.addRoom(bedroom);

        double expectedResult = 0;
        double result = bedroom.getNominalPower();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return incorrect number
     */

   /*
    @Test
    public void getIncorrectNominalPower() {

        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH = new Device("EWH DAIKIN", ewh, 15);

        roomList.addDeviceToRoom (dEWH, 1);


        double expectedResult = 10;
        double result = bedroom.getNominalPower ();


        assertNotEquals (expectedResult, result);
    }

*/

    /**
     * Check if sum of all metered devices in the room is correct with one metered device
     */

  /*
    @Test
    void getRoomEnergyConsumptionInPeriod1() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH = new Device("EWH DAIKIN", ewh, 15);

        roomList.addDeviceToRoom (dEWH, 1);

        ReadingList activityLog = dEWH.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading r3 = new Reading (22, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading r4 = new Reading (37, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading r5 = new Reading (31, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading r6 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading r7 = new Reading (22, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading r8 = new Reading (37, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLog.addReading (r2);
        activityLog.addReading (r3);
        activityLog.addReading (r4);
        activityLog.addReading (r5);
        activityLog.addReading (r6);
        activityLog.addReading (r7);
        activityLog.addReading (r8);
        GregorianCalendar startDate = new GregorianCalendar (2018, 11, 5, 0, 30);
        GregorianCalendar endDate = new GregorianCalendar (2018, 11, 5, 1, 00);
        dEWH.setIsMetered (true);
        double expected = 71;
        double result = bedroom.getDailyEnergyConsumption (startDate, endDate);

        assertEquals (expected, result);
    }

*/

    /**
     * Check if sum of all metered devices in the room is correct with two metered devices and one not metered device
     */

   /*
    @Test
    void getRoomEnergyConsumptionInPeriod2() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH = new Device("EWH DAIKIN", ewh, 15);
        DeviceSpecs tv = new OtherDevices ();
        Device dTv = new Device("Samsung TV", tv, 15);
        DeviceSpecs frid = new Fridge();
        Device dFrid = new Device("Fridge1", frid, 100);
        Device dOtherFrid = new Device("Fridge2", frid, 80);

        roomList.addDeviceToRoom (dEWH, 1);
        roomList.addDeviceToRoom (dTv, 1);
        roomList.addDeviceToRoom (dFrid, 1);
        roomList.addDeviceToRoom (dOtherFrid, 1);

        ReadingList activityLog = dEWH.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading r3 = new Reading (22, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading r4 = new Reading (37, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading r5 = new Reading (31, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading r6 = new Reading (18, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading r7 = new Reading (22, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading r8 = new Reading (37, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLog.addReading (r2);
        activityLog.addReading (r3);
        activityLog.addReading (r4);
        activityLog.addReading (r5);
        activityLog.addReading (r6);
        activityLog.addReading (r7);
        activityLog.addReading (r8);

        ReadingList activityLogFrid = dFrid.getActivityLog ();
        Reading rA2 = new Reading (20, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rA3 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rA4 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rA5 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rA6 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rA7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rA8 = new Reading (20, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogFrid.addReading (rA2);
        activityLogFrid.addReading (rA3);
        activityLogFrid.addReading (rA4);
        activityLogFrid.addReading (rA5);
        activityLogFrid.addReading (rA6);
        activityLogFrid.addReading (rA7);
        activityLogFrid.addReading (rA8);

        ReadingList activityLogOtherFrid = dOtherFrid.getActivityLog ();
        Reading rB2 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rB3 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rB4 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rB5 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rB6 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rB7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rB8 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogOtherFrid.addReading (rB2);
        activityLogOtherFrid.addReading (rB3);
        activityLogOtherFrid.addReading (rB4);
        activityLogOtherFrid.addReading (rB5);
        activityLogOtherFrid.addReading (rB6);
        activityLogOtherFrid.addReading (rB7);
        activityLogOtherFrid.addReading (rB8);

        GregorianCalendar startDate = new GregorianCalendar (2018, 11, 5, 0, 30);
        GregorianCalendar endDate = new GregorianCalendar (2018, 11, 5, 1, 00);
        dEWH.setIsMetered (true);
        dFrid.setIsMetered (false);
        dOtherFrid.setIsMetered (true);

        double expected = 101;
        double result = bedroom.getDailyEnergyConsumption (startDate, endDate);

        assertEquals (expected, result);
    }
*/

/*
    @Test
    public void checkIfSameDeviceIsAddedTwice() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        assertTrue (dL.addDevice (dEWH1));
        assertFalse (dL.addDevice (dEWH1));
    }
*/

/*
    @Test
    public void getLastDevice() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device("EWH DAIKIN2", ewh, 15);
        dL.addDevice (dEWH1);
        dL.addDevice (dEWH2);

        Device expectedResult = dEWH2;
        Device result = dL.getLastElement ();

        assertEquals (expectedResult, result);
    }
*/

/*
    @Test
    void getSizeDeviceListInRoom() {
        House house = new House ();
        RoomList roomList = house.getRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        DeviceSpecs ewh = new ElectricWaterHeater ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device("EWH DAIKIN2", ewh, 15);
        dL.addDevice (dEWH1);
        dL.addDevice (dEWH2);

        int expected = 2;
        int result = bedroom.getSizeDeviceListInRoom ();

        assertEquals (expected, result);
    }
*/

}
