package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.FanType;
import smarthome.model.devices.OvenType;
import smarthome.model.devices.TvType;
import smarthome.model.devices.WallTowelHeaterType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.House.*;

class HouseTest {


    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {

        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
        //@DisplayName("House instance not null")
    void getHouseInstance(){
        Location loc = new Location(20, 20, 2);
        Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
        OccupationArea oc = new OccupationArea(2, 5);
        GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
        House house = House.getHouseInstance(a1, g1);


        assertEquals(house,House.getHouseInstance(a1,g1));
    }

    @Test
        //@DisplayName("Check if address/location exists")
    void checkIfLocationExistsTest(){
        Location location = new Location(12,34,56);
        Address address = new Address("Rua Blah","123","1234-567","Porto","Portugal",location);

        House.setHouseAddress(address);

        assertTrue(checkIfLocationExists());
    }

    @Test
        //@DisplayName("Check if address/location exists")
    void checkIfLocationExistsTestFalse(){
        Address address = null;

        House.setHouseAddress(address);

        assertFalse(checkIfLocationExists());

    }


    //RoomList

    /**
     * Check if a new room is created and confirm that the get methods are working
     */

    @Test
    void newRoom() {

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        assertEquals("bedroom", room.getMeteredDesignation());
        assertEquals(1, room.getFloor());
        assertEquals(5, room.getArea().getOccupationArea());
    }


    /**
     * Check if a room is create and added to the RoomList
     */

    @Test
    void addOneRoom() {


        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);

        getHouseRoomList().addRoom(room);
        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = getHouseRoomList().getRoomList();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if two rooms are create and added to the RoomList
     */

    @Test
    void addTwoRooms() {


        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room1 = getHouseRoomList().createNewRoom("R02", "garden", 0, 2.5, 3, 2);
        assertEquals(0, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room);
        assertEquals(1, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room1);
        assertEquals(2, getHouseRoomList().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room, room1);
        List<Room> result = getHouseRoomList().getRoomList();
        assertEquals(expectedResult, result);
    }

    /**
     * Try to add two rooms but one is empty so only the correct one is added
     */

    @Test
    void addOneRoomEmptyName() {


        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room1 = getHouseRoomList().createNewRoom(" ", "  ", 0, 2.5, 3, 2);

        assertEquals(0, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room);
        assertEquals(1, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room1);
        assertEquals(1, getHouseRoomList().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = getHouseRoomList().getRoomList();
        assertEquals(expectedResult, result);
    }

    /**
     * Don't validate room name if empty and return false
     */

    @Test
    void nameNotValid() {


        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room1 = getHouseRoomList().createNewRoom("  ", "  ", 0, 2.5, 3, 2);

        assertEquals(0, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room);
        assertEquals(1, getHouseRoomList().getRoomList().size());

        getHouseRoomList().addRoom(room1);
        assertEquals(1, getHouseRoomList().getRoomList().size());

        boolean expectedResult = false;
        String name = " ";

        boolean result = room.validateDescription(name);
        assertEquals(expectedResult, result);
    }

    /**
     * Validate correct room name and return true and also validate empty room name and return false
     */

    @Test
    void addOneGetTrueAddAnotherGetFalse() {


        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 1.7);
        Room room1 = getHouseRoomList().createNewRoom(" ", "  ", 0, 2.5, 3, 2);

        assertEquals(0, getHouseRoomList().getRoomList().size());

        boolean result = getHouseRoomList().addRoom(room);
        assertEquals(1, getHouseRoomList().getRoomList().size());
        assertTrue(result);

        boolean result1 = getHouseRoomList().addRoom(room1);
        assertEquals(1, getHouseRoomList().getRoomList().size());
        assertFalse(result1);

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result2 = getHouseRoomList().getRoomList();
        assertEquals(expectedResult, result2);
    }


    @Test
        //@DisplayName("Ensure a room is removed from the list of rooms of a house ")
    void removeRoom() {


        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room2 = getHouseRoomList().createNewRoom("R02", "kitchen", 1, 4, 5, 2);

        getHouseRoomList().addRoom(room1);
        getHouseRoomList().addRoom(room2);
        assertEquals(2, getHouseRoomList().getRoomList().size());
        assertTrue(getHouseRoomList().removeRoom(room2));
        assertEquals(1, getHouseRoomList().getRoomList().size());
    }

    @Test
        //@DisplayName("Ensure a room not contained in the list of rooms of a house" + "cannot be removed")
    void removeRoomReturnsFalse() {



        Room room1 = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        getHouseRoomList().addRoom(room1);
        Room room2 = getHouseRoomList().createNewRoom("R02", "kitchen", 1, 4, 5, 2);

        assertFalse(getHouseRoomList().removeRoom(room2));
    }

    @Test
        //@DisplayName("Get House Grid List from the House")
    void getHouseGridListFromHouseTest() {

        HouseGrid hg01 = new HouseGrid("grid01");
        HouseGrid hg02 = new HouseGrid("grid02");

        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        List<HouseGrid> expectedResult = Arrays.asList(hg01, hg02);
        List<HouseGrid> result = getGridListInHouse().getHouseGridList();

        assertEquals(expectedResult, result);

    }

    @Test
    void showRoomsWithoutGrid() {

        HouseGrid hg1 = new HouseGrid("grid01");
        HouseGrid hg2 = new HouseGrid("grid02");
        HouseGridList hgLst = getGridListInHouse();
        hgLst.addHouseGrid(hg1);
        hgLst.addHouseGrid(hg2);
        RoomList roomLstOfHouse = getHouseRoomList();
        Room kitchen = new Room("R01", "kitchen", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(kitchen);
        hg1.attachRoomToGrid(kitchen);
        Room bathroom = new Room("R02", "bathroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bathroom);
        Room bedroom = new Room("R03", "bedroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bedroom);
        hg1.attachRoomToGrid(kitchen);
        hg1.attachRoomToGrid(bathroom);
        int expected = 1;
        int result = getHouseRoomsWithoutGrid(hg1).getRoomListSize();
        assertEquals(expected, result);
    }

    @Test
    void showRoomsWithoutHouseGrid() {

        HouseGrid hg1 = new HouseGrid("grid01");
        HouseGrid hg2 = new HouseGrid("grid02");
        HouseGridList hgLst = getGridListInHouse();
        hgLst.addHouseGrid(hg1);
        hgLst.addHouseGrid(hg2);
        RoomList roomLstOfHouse = getHouseRoomList();
        Room kitchen = new Room("R01", "Kitchen", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(kitchen);
        Room bathroom = new Room("R02", "bathroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bathroom);
        Room bedroom = new Room("R03", "Bedroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bedroom);

        hg1.attachRoomToGrid(bathroom);
        String expected = "1 - Kitchen\n" +
                "2 - Bedroom\n";
        String result = showHouseRoomsWithoutHouseGrid(hg1);
        assertEquals(expected, result);
    }

    @Test
    void getListOfDeviceTypesTest() {

        List<String> result = getListOfDeviceTypes();
        assertEquals(17, result.size());
    }

    @Test
    void showDeviceTypesListTest() {

        String expected = "1 - ElectricWaterHeater\n" +
                "2 - WashingMachine\n" +
                "3 - Dishwasher\n" +
                "4 - Fridge\n" +
                "5 - Freezer\n" +
                "6 - WineCooler\n" +
                "7 - Kettle\n" +
                "8 - Oven\n" +
                "9 - Stove\n" +
                "10 - MicrowaveOven\n" +
                "11 - WallElectricHeater\n" +
                "12 - PortableElectricOilHeater\n" +
                "13 - PortableElectricConvectionHeater\n" +
                "14 - WallTowelHeater\n" +
                "15 - Lamp\n" +
                "16 - Fan\n" +
                "17 - Tv\n";
        String result = showDeviceTypesList();
        assertEquals(expected, result);

    }

    @Test
        //@DisplayName("Filter Sensor List by type and proximity")
    void filterByTypeAndProximityTest(){
        SensorType temperature = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 25);
        Location loc3 = new Location(0, 15, 12);
        Location loc4 = new Location(0, 22, 12);
        Location loc5 = new Location(0, 24, 12);
        ReadingList readings = new ReadingList();
        ExternalSensor s1 = new ExternalSensor("T001", "Temperature Sensor 1", startDate, loc3, temperature, "C", readings);
        ExternalSensor s2 = new ExternalSensor("T002", "Temperature Sensor 2", startDate, loc4, temperature, "C", readings);
        ExternalSensor s3 = new ExternalSensor("T003", "Temperature Sensor 3", startDate, loc5, temperature, "C", readings);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        List<Sensor> expected = Arrays.asList(s2);

        List<Sensor> result = House.filterListByTypeAndProximity(temperature).getSensorList();

        assertEquals(expected,result);
    }

    @Test
        //@DisplayName("Filter Sensor List by type and proximity and test the null pointer exception")
    void filterByTypeAndProximityTestNullPointer(){
        SensorType temperature = new SensorType("temperature");

        GeographicalArea houseGa = null;

        House.setHouseGA(houseGa);

        GeographicalArea geographicalArea = new GeographicalArea("Opo","Porto","city",oc,loc);

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 25);
        Location loc3 = new Location(0, 15, 12);
        Location loc4 = new Location(0, 22, 12);
        Location loc5 = new Location(0, 24, 12);
        ReadingList readings = new ReadingList();
        ExternalSensor s1 = new ExternalSensor("T001", "Temperature Sensor 1", startDate, loc3, temperature, "C", readings);
        ExternalSensor s2 = new ExternalSensor("T002", "Temperature Sensor 2", startDate, loc4, temperature, "C", readings);
        ExternalSensor s3 = new ExternalSensor("T003", "Temperature Sensor 3", startDate, loc5, temperature, "C", readings);

        geographicalArea.getSensorListInGA().addSensor(s1);
        geographicalArea.getSensorListInGA().addSensor(s2);
        geographicalArea.getSensorListInGA().addSensor(s3);

        assertEquals(0,House.filterListByTypeAndProximity(temperature).size());
    }

    @Test
        //@DisplayName("Filter Sensor List by type, distance and interval and test when sensor list of the house ga is empty/doesnt exist")
    void filterByTypeIntervalAndDistance(){
        SensorType temperature = new SensorType("temperature");
        SensorType rainfall = new SensorType("rainfall");

        GeographicalArea houseGa = null;

        House.setHouseGA(houseGa);

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 25);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 25);
        Location loc3 = new Location(0, 15, 12);
        Location loc4 = new Location(0, 22, 12);
        Location loc5 = new Location(0, 24, 12);
        ReadingList readings = new ReadingList();
        ExternalSensor s1 = new ExternalSensor("T001", "Temperature Sensor 1", startDate, loc3, temperature, "C", readings);
        ExternalSensor s2 = new ExternalSensor("T002", "Temperature Sensor 2", startDate, loc4, temperature, "C", readings);
        ExternalSensor s3 = new ExternalSensor("T003", "Temperature Sensor 3", startDate, loc5, temperature, "C", readings);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        int result = House.filterListByTypeByIntervalAndDistance(rainfall,startDate,endDate).size();

        assertEquals(0,result);

    }


    @Test
        //@DisplayName("Ensure that sensor with the latest reading in the specified date is sensor s3.")
    void getSensorOfTypeWithLatestReadingsInDateTest() {

        SensorType temperature = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 25);
        Location loc3 = new Location(0, 15, 12);
        Location loc4 = new Location(26, 26, 12);
        Location loc5 = new Location(24, 24, 12);
        ReadingList readings = new ReadingList();
        Sensor s1 = new ExternalSensor("T001", "Temperature Sensors 1", startDate, loc3, temperature, "C", readings);
        Sensor s2 = new ExternalSensor("T001", "Temperature Sensors 1", startDate, loc4, temperature, "C", readings);
        Sensor s3 = new ExternalSensor("T001", "Temperature Sensors 1", startDate, loc5, temperature, "C", readings);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        Reading r1TempSensor1 = new Reading(25, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor1 = new Reading(20, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 30), "C");
        Reading r3TempSensor1 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 15), "C");
        s1.getSensorBehavior().getReadingList().addReading(r1TempSensor1);
        s1.getSensorBehavior().getReadingList().addReading(r2TempSensor1);
        s1.getSensorBehavior().getReadingList().addReading(r3TempSensor1);

        Reading r1TempSensor2 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor2 = new Reading(10, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 30), "C");
        Reading r3TempSensor2 = new Reading(5, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 15), "C");
        s2.getSensorBehavior().getReadingList().addReading(r1TempSensor2);
        s2.getSensorBehavior().getReadingList().addReading(r2TempSensor2);
        s2.getSensorBehavior().getReadingList().addReading(r3TempSensor2);

        Reading r1TempSensor3 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor3 = new Reading(10, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 45), "C");
        Reading r3TempSensor3 = new Reading(5, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 15), "C");
        s3.getSensorBehavior().getReadingList().addReading(r1TempSensor3);
        s3.getSensorBehavior().getReadingList().addReading(r2TempSensor3);
        s3.getSensorBehavior().getReadingList().addReading(r3TempSensor3);

        Sensor result = getSensorOfTypeWithLatestReadingsInDate(new GregorianCalendar(2018, Calendar.DECEMBER, 26), temperature);

        assertEquals(s3, result);
    }

    @Test
        //@DisplayName("Ensure that sensor with the latest reading in the specified date has the latest reading")
    void getSensorOfTypeWithLatestReadingsInDateTestLastAfter() {

        SensorType temperature = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 25);
        Location loc3 = new Location(0, 15, 12);
        Location loc4 = new Location(26, 26, 12);
        Location loc5 = new Location(26, 26, 12);
        ReadingList readings1 = new ReadingList();
        ReadingList readings2 = new ReadingList();
        ReadingList readings3 = new ReadingList();
        ExternalSensor s1 = new ExternalSensor("T001", "Temperature Sensor 1", startDate, loc3, temperature, "C", readings1);
        ExternalSensor s2 = new ExternalSensor("T002", "Temperature Sensor 2", startDate, loc4, temperature, "C", readings2);
        ExternalSensor s3 = new ExternalSensor("T003", "Temperature Sensor 3", startDate, loc5, temperature, "C", readings3);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        Reading r1TempSensor1 = new Reading(25, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor1 = new Reading(20, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 30), "C");
        Reading r3TempSensor1 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 15), "C");
        readings1.addReading(r1TempSensor1);
        readings1.addReading(r2TempSensor1);
        readings1.addReading(r3TempSensor1);

        Reading r1TempSensor2 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor2 = new Reading(10, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 30), "C");
        Reading r3TempSensor2 = new Reading(5, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 50), "C");
        readings2.addReading(r1TempSensor2);
        readings2.addReading(r2TempSensor2);
        readings2.addReading(r3TempSensor2);

        Reading r1TempSensor3 = new Reading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 26, 9, 15), "C");
        Reading r2TempSensor3 = new Reading(10, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 9, 45), "C");
        Reading r3TempSensor3 = new Reading(5, new GregorianCalendar(2018, Calendar.DECEMBER, 27, 10, 0), "C");
        readings3.addReading(r1TempSensor3);
        readings3.addReading(r2TempSensor3);
        readings3.addReading(r3TempSensor3);

        String result = getSensorOfTypeWithLatestReadingsInDate(new GregorianCalendar(2018, Calendar.DECEMBER, 27), temperature).getId();

        assertEquals(s3.getId(), result);
    }

    @Test
        //@DisplayName("Tests if the second sensor has the most recent readings")
    void getSecondSensorWithLatestReadingsNotInPeriod() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);


        Location l1 = new Location(47, -12, 200);

        Sensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        Sensor result = filterByTypeByIntervalAndDistance(sT, startDate, endDate);

        assertEquals(s2, result);
    }


    @Test
    @DisplayName("Tests if a sensor with latest readings is selected")
    void getClosestSensorWithLatestReadingsTest() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JULY, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.JULY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 8);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 7);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        ExternalSensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        ExternalSensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        Sensor result = getClosestSensorWithLatestReading(sT);

        assertEquals(s1, result);
    }

    @Test
        //@DisplayName("Tests if a sensor with readings in date is selected  ")
    void getClosestSensorWithReadingsInDateTrueTest() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JULY, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.JULY, 27);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JULY, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 8);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 7);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        ExternalSensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        ExternalSensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        boolean result = closestSensorsWithReadingsInDate(endDate,sT);

        assertTrue(result);
    }

    @Test
        //@DisplayName("Tests if a sensor with readings in period is selected  ")
    void checkIfClosestSensorsHasReadingsInTimePeriodTest() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JULY, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.JULY, 27);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JULY, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 8);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 7);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        ExternalSensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        ExternalSensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        boolean result = checkIfClosestSensorsHasReadingsInTimePeriod(sT,startDate,endDate);

        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if a sensor with no readings in period is not selected")
    void getClosestSensorWithLatestReadingsNotInPeriod() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 8);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 7);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        Sensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        Sensor result = filterByTypeByIntervalAndDistance(sT, startDate, endDate);

        assertNotEquals(s1, result);
    }


    @Test
        //@DisplayName("Tests if readings with a date equal to start date are included in the average")
    void averageOfReadingsWithStartDate() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JULY, 2);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 4);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 2);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 5);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        Sensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        double result = averageOfReadingsInPeriod(sT, startDate, endDate);

        assertEquals(34.4, result, 0.1);


    }

    @Test
        //@DisplayName("Tests if readings with a date equal to end date are included in the average")
    void averageOfReadingsWithEndDate() {

        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date3);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 2);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Location l1 = new Location(47, -12, 200);

        Sensor s1 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);

        double result = averageOfReadingsInPeriod(sT, startDate, endDate);

        assertEquals(27.1, result);

    }

    @Test
        //@DisplayName("set and get House GeoArea")
    void getHouseGATest(){
        Location location = new Location(12,34,56);
        //Address address = new Address("Rua","123","1234-567","Porto","Portugal",location);
        OccupationArea occupationArea = new OccupationArea(11,22);
        GeographicalArea ga = new GeographicalArea("area01","porto","city",occupationArea,location);

        House.setHouseGA(ga);

        GeographicalArea result = House.getHouseGA();

        assertEquals(ga,result);
    }

    @Test
        //@DisplayName("set and get House Address")
    void getHouseAddressTest(){
        Location location = new Location(12,34,56);
        Address address = new Address("Rua","123","1234-567","Porto","Portugal",location);
        //OccupationArea occupationArea = new OccupationArea(11,22);
        //GeographicalArea ga = new GeographicalArea("area01","porto","city",occupationArea,location);

        House.setHouseAddress(address);

        Address result = House.getAddress();

        assertEquals(address,result);
    }

    @Test
        //@DisplayName("get metered objects list")
    void getMeteredTest(){

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room1 = getHouseRoomList().createNewRoom("R02", "garden", 0, 2.5, 3, 2);
        Room room2 = getHouseRoomList().createNewRoom("R03", "kitchen", 0, 2.5, 3, 2);

        getHouseRoomList().addRoom(room);
        getHouseRoomList().addRoom(room1);
        getHouseRoomList().addRoom(room2);

        HouseGrid hg01 = new HouseGrid("grid01");
        HouseGrid hg02 = new HouseGrid("grid02");

        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        hg01.attachRoomToGrid(room);
        hg01.attachRoomToGrid(room1);
        hg02.attachRoomToGrid(room2);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        room.getDeviceList().add(d5);
        room.getDeviceList().add(d4);

        room1.getDeviceList().add(d2);

        room2.getDeviceList().add(d1);
        room2.getDeviceList().add(d3);

        int result = House.getMetered().size();

        assertEquals(10,result);
    }

    @Test
        //@DisplayName("show metered objects list")
    void showMeteredTest(){

        Room room = getHouseRoomList().createNewRoom("R01", "bedroom", 1, 2, 2.5, 2);
        Room room1 = getHouseRoomList().createNewRoom("R02", "garden", 0, 2.5, 3, 2);
        Room room2 = getHouseRoomList().createNewRoom("R03", "kitchen", 0, 2.5, 3, 2);

        getHouseRoomList().addRoom(room);
        getHouseRoomList().addRoom(room1);
        getHouseRoomList().addRoom(room2);

        HouseGrid hg01 = new HouseGrid("grid01");
        HouseGrid hg02 = new HouseGrid("grid02");

        getGridListInHouse().addHouseGrid(hg01);
        getGridListInHouse().addHouseGrid(hg02);

        hg01.attachRoomToGrid(room);
        hg01.attachRoomToGrid(room1);
        hg02.attachRoomToGrid(room2);

        OvenType typeOven = new OvenType();
        WallTowelHeaterType typeWth = new WallTowelHeaterType();
        TvType typeTv = new TvType();
        FanType typeFan = new FanType();

        Device d1 = typeOven.createDevice("baker", 420);
        Device d2 = typeTv.createDevice("Silver", 200);
        Device d3 = typeWth.createDevice("Textile Dryer", 300);
        Device d4 = typeFan.createDevice("Micro Fan", 250);
        Device d5 = typeTv.createDevice("Smart Tv", 200);

        room.getDeviceList().add(d5);
        room.getDeviceList().add(d4);

        room1.getDeviceList().add(d2);

        room2.getDeviceList().add(d1);
        room2.getDeviceList().add(d3);

        String expected = "1 - grid01\n" +
                "2 - bedroom\n" +
                "3 - garden\n" +
                "4 - Smart Tv\n" +
                "5 - Micro Fan\n" +
                "6 - Silver\n" +
                "7 - grid02\n" +
                "8 - kitchen\n" +
                "9 - baker\n" +
                "10 - Textile Dryer\n";
        String result = House.showMetered();

        assertEquals(expected,result);
    }

}
