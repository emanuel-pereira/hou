package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    /**
     * Checks if the room name is only spaces, if so the validation method returns False
     */
    @Test
    void nameNotValid() {

        Room roomOne = new Room("  ", "  ", 0, 2.5, 3, 2);

        String name = "  ";
        boolean result = roomOne.validateDescription(name);

        assertFalse(result);
    }

    /**
     * Checks if the room name is null, if so the validation method returns False
     */
    @Test
    void nullNameNotValid() {

        String name = " ";
        String id = " ";
        Room roomOne = new Room(id, name, 0, 2.5, 3, 2);

        boolean result = roomOne.validateDescription(name);

        assertFalse(result);
    }

    /**
     * Checks if the room name is correct, if so the validation method returns True
     */
    @Test
    void nameValid() {

        Room roomOne = new Room("R01", "bedroom", 0, 2.5, 3, 4.7);

        String name = "bedroom";
        boolean result = roomOne.validateDescription(name);

        assertTrue(result);
    }

    /**
     * Checks if two different room objects are equals because of their content
     */
    @Test
    void equalsIfRoomEqualsRoomSameValues() {
        Room room1 = new Room("R01","bedroom", 0, 2.5, 3, 3);
        Room room2 = new Room("R01","bedroom", 0, 2.5, 3, 3);

        boolean result = room1.equals(room2);

        assertEquals(room1.hashCode(), room2.hashCode());
        assertTrue(result);
    }

    /**
     * Checks if one room object is equals to itself
     */
    @Test
    void equalsIfRoomEqualsRoom() {
        Room room1 = new Room("R01","bedroom", 0, 2.5, 3, 3);

        boolean result = room1.equals(room1);

        assertEquals(room1.hashCode(), room1.hashCode());
        assertTrue(result);
    }

    /**
     * Checks if two different room objects are different because of their content
     */
    @Test
    void equalsIfRoomEqualsDifferentRoom() {
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
    void equalsIfStringEqualsRoom() {
        String person1 = "Joana";
        Room room1 = new Room("R01","bedroom", 0, 2.5, 3, 3);
        boolean result;

        result = room1.equals(person1);

        assertFalse(result);
    }

    /**
     * Test to define/edit the id of the room.
     */
    @Test
    void setIdCorrectly() {
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2.5);
        bedroom.setId("R1");

        String expectedResult = "R1";
        String result = bedroom.getId();

        assertEquals(expectedResult, result);

    }

    /**
     * Test to define/edit the id of the room.
     */
    @Test
    void setIdIncorrectly() {
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2.5);
        bedroom.setId(" ");

        String expectedResult = "R01";
        String result = bedroom.getId();

        assertEquals(expectedResult, result);
    }

    @Test
    void setIdValidateFalse() {
        Room bedroom = new Room();

        boolean result = bedroom.setId(" ");

        assertFalse(result);
    }

    @Test
    void setIdValidateTrue() {
        Room bedroom = new Room();

        boolean result = bedroom.setId("r01");

        assertTrue(result);
    }


    /**
     * Test to define/edit the name of the room.
     */
    @Test
    void setName() {

        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2.5);
        bedroom.setDescription("bedroom1");

        String expectedResult = "bedroom1";
        String result = bedroom.getDesignation();

        assertEquals(expectedResult, result);

    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    void setFloor() {

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
    void setArea() {

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
    void setHeight() {

        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);
        bedroom.setHeight(3);

        double expectedResult = 3;
        double result = bedroom.getHeight();

        assertEquals(expectedResult, result);
    }

    //@Test
    void getSensorListTest(){
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);

        GregorianCalendar sDate = new GregorianCalendar(2019,05,03);
        SensorType type1 = new SensorType("temperature");
        ReadingList readingList = new ReadingList();

        InternalSensor s1 = new InternalSensor("s1","meteo1",sDate,type1,"C",readingList);
        InternalSensor s2 = new InternalSensor("s2","meteo2",sDate,type1,"C",readingList);

        bedroom.getSensorListInRoom().addSensor(s1);
        bedroom.getSensorListInRoom().addSensor(s2);

        List<Sensor> expected = Arrays.asList(s1,s2);
        List<Sensor> result = bedroom.getSensorListInRoom().getSensorList();

        assertEquals(expected,result);
    }

    //@Test
    void checkIfSensorTypeInRoomTestTrue(){
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);

        GregorianCalendar sDate = new GregorianCalendar(2019,05,03);
        SensorType type1 = new SensorType("temperature");
        ReadingList readingList = new ReadingList();

        InternalSensor s1 = new InternalSensor("s1","meteo1",sDate,type1,"C",readingList);
        InternalSensor s2 = new InternalSensor("s2","meteo2",sDate,type1,"C",readingList);

        bedroom.getSensorListInRoom().addSensor(s1);
        bedroom.getSensorListInRoom().addSensor(s2);

        boolean result = bedroom.checkIfSensorTypeExistsInRoom("temperature");

        assertTrue(result);
    }

    //@Test
    void checkIfSensorTypeInRoomTestFalse(){
        Room bedroom = new Room("R01","bedroom", 1, 2, 3, 2);

        GregorianCalendar sDate = new GregorianCalendar(2019,05,03);
        SensorType type1 = new SensorType("temperature");
        ReadingList readingList = new ReadingList();

        InternalSensor s1 = new InternalSensor("s1","meteo1",sDate,type1,"C",readingList);
        InternalSensor s2 = new InternalSensor("s2","meteo2",sDate,type1,"C",readingList);

        bedroom.getSensorListInRoom().addSensor(s1);
        bedroom.getSensorListInRoom().addSensor(s2);

        boolean result = bedroom.checkIfSensorTypeExistsInRoom("rainfall");

        assertFalse(result);
    }

    @Test
    @DisplayName("Correctly get the estimated energy consumption of a room")
    void getCorrectRoomEstimatedEnergyConsumption() {
        RoomList roomList = new RoomList();
        Room kitchen = roomList.createNewRoom("R01", "bedroom", 1, 2, 2, 2);
        roomList.addRoom(kitchen);

        // EXPERIMENTAL
        kitchen.setTime(1);

        DeviceType dt = new FanType();
        Device d = dt.createDevice("Fan 1", 2);
        Fan fan = (Fan) d;

        DeviceType dt1 = new WashingMachineType();
        Device d1 = dt1.createDevice("Washing Machine1", 100);
        WashingMachine washingMachine = (WashingMachine) d1;

        DeviceList deviceList = kitchen.getDeviceList();
        deviceList.add(fan);
        deviceList.add(washingMachine);

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


        double expected = 102;
        double result = kitchen.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }



    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of two devices
     */

    @Test
    void getCorrectNominalPowerIfTwoDevices() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH1 = ewh.createDevice("EWH DAIKIN1", 15);
        Device dEWH2 = ewh.createDevice("EWH DAIKIN2", 15);
        roomList.addDeviceToRoom (dEWH1, 1);
        roomList.addDeviceToRoom (dEWH2, 1);

        double expectedResult = 30;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of one device
     */

    @Test
    void getCorrectNominalPowerIfOneDevice() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH1 = ewh.createDevice("EWH DAIKIN1", 15);
        roomList.addDeviceToRoom (dEWH1, 1);

        double expectedResult = 15;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of two devices with zero nominal power
     */

    @Test
    void getCorrectNominalPowerIfZero() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH1 = ewh.createDevice("EWH DAIKIN1", 0);
        Device dEWH2 = ewh.createDevice("EWH DAIKIN2", 0);
        roomList.addDeviceToRoom (dEWH1, 1);
        roomList.addDeviceToRoom (dEWH2, 1);

        double expectedResult = 0;
        double result = bedroom.getNominalPower ();

        assertEquals (expectedResult, result);
    }



    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     * The sum of the nominal power of a room with no devices
     */
    @Test
    void getCorrectNominalPowerIf() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom("R01","bedroom", 1, 2, 2, 2);
        roomList.addRoom(bedroom);

        double expectedResult = 0;
        double result = bedroom.getNominalPower();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return incorrect number
     */

    @Test
    void getIncorrectNominalPower() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH = ewh.createDevice("EWH DAIKIN1", 15);

        roomList.addDeviceToRoom (dEWH, 1);


        double expectedResult = 10;
        double result = bedroom.getNominalPower ();


        assertNotEquals (expectedResult, result);
    }


    /**
     * Check if sum of all metered devices in the room is correct with one metered device
     */

    @Test
    void getRoomEnergyConsumptionInPeriod1() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH = ewh.createDevice("EWH DAIKIN1", 15);

        roomList.addDeviceToRoom (dEWH, 1);

        ReadingList activityLog = dEWH.getActivityLog ();
        Reading r2 = new Reading (18, new GregorianCalendar(2018, 11, 5, 0, 10));
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
        double expected = 71;
        double result = bedroom.getEnergyConsumption (startDate, endDate);

        assertEquals (expected, result);
    }

    /**
     * Check if sum of all metered devices in the room is correct with two metered devices and one not metered device
     */

    @Test
    void getRoomEnergyConsumptionInPeriod2() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);

        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH = ewh.createDevice("EWH DAIKIN1", 15);
        TvType tv = new TvType ();
        Device dTv = tv.createDevice("Samsung TV", 15);
        FridgeType fridge = new FridgeType();
        Device dFridge = fridge.createDevice("Fridge1", 100);
        Device dOtherFridge = fridge.createDevice("Fridge2", 80);

        roomList.addDeviceToRoom (dEWH, 1);
        roomList.addDeviceToRoom (dTv, 1);
        roomList.addDeviceToRoom (dFridge, 1);
        roomList.addDeviceToRoom (dOtherFridge, 1);

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

        ReadingList activityLogFridge = dFridge.getActivityLog ();
        Reading rA2 = new Reading (20, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rA3 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rA4 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rA5 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rA6 = new Reading (12, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rA7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rA8 = new Reading (20, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogFridge.addReading (rA2);
        activityLogFridge.addReading (rA3);
        activityLogFridge.addReading (rA4);
        activityLogFridge.addReading (rA5);
        activityLogFridge.addReading (rA6);
        activityLogFridge.addReading (rA7);
        activityLogFridge.addReading (rA8);

        ReadingList activityLogOtherFridge = dOtherFridge.getActivityLog ();
        Reading rB2 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 10));
        Reading rB3 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 20));
        Reading rB4 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 30));
        Reading rB5 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 40));
        Reading rB6 = new Reading (10, new GregorianCalendar (2018, 11, 5, 0, 50));
        Reading rB7 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 00));
        Reading rB8 = new Reading (10, new GregorianCalendar (2018, 11, 5, 1, 10));
        activityLogOtherFridge.addReading (rB2);
        activityLogOtherFridge.addReading (rB3);
        activityLogOtherFridge.addReading (rB4);
        activityLogOtherFridge.addReading (rB5);
        activityLogOtherFridge.addReading (rB6);
        activityLogOtherFridge.addReading (rB7);
        activityLogOtherFridge.addReading (rB8);

        GregorianCalendar startDate = new GregorianCalendar (2018, 11, 5, 0, 30);
        GregorianCalendar endDate = new GregorianCalendar (2018, 11, 5, 1, 00);

        double expected = 135;
        double result = bedroom.getEnergyConsumption (startDate, endDate);

        assertEquals (expected, result);
    }

    /*
    @Test
    public void checkIfSameDeviceIsAddedTwice() {
        House house = new House ();
        RoomList roomList = house.getHouseRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        DeviceSpecs ewh = new Fridge ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        assertTrue (dL.add (dEWH1));
        assertFalse (dL.add (dEWH1));
    }
*/

/*
    @Test
    public void getLastDevice() {
        House house = new House ();
        RoomList roomList = house.getHouseRoomList ();
        Room bedroom = roomList.createNewRoom ("bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        DeviceSpecs ewh = new Fridge ();
        Device dEWH1 = new Device("EWH DAIKIN1", ewh, 15);
        Device dEWH2 = new Device("EWH DAIKIN2", ewh, 15);
        dL.add (dEWH1);
        dL.add (dEWH2);

        Device expectedResult = dEWH2;
        Device result = dL.getLastElement ();

        assertEquals (expectedResult, result);
    }
*/

    @Test
    void getSizeDeviceListInRoom() {
        RoomList roomList = new RoomList();
        Room bedroom = roomList.createNewRoom ("r1","bedroom", 1, 2, 2, 2);
        roomList.addRoom (bedroom);
        DeviceList dL = bedroom.getDeviceList ();
        ElectricWaterHeaterType ewh = new ElectricWaterHeaterType ();
        Device dEWH1 = ewh.createDevice("EWH DAIKIN1",  15);
        Device dEWH2 = ewh.createDevice("EWH DAIKIN2", 15);
        dL.add (dEWH1);
        dL.add (dEWH2);

        int expected = 2;
        int result = bedroom.getSizeDeviceListInRoom ();

        assertEquals (expected, result);
    }


}
