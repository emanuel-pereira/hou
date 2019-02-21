
package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {


    @Test
    @DisplayName("Tests if two different instances of house are the same")
    void differentHouseInstancesAreEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("VNG", "Gaia", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio1", a1, g1);

        House house2 = new House("Prédio1", a1, g1);

        //Assert
        assertEquals(house1.hashCode(), house2.hashCode());
        assertEquals(house1, house2);
    }

    @Test
    @DisplayName("Check if two objects are the same")
    void checkIfSameHouse() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio1", a1, g1);


        //Assert
        assertEquals(house1.hashCode(), house1.hashCode());
        assertEquals(house1, house1);
    }

    @Test
    @DisplayName("Tests if two different instances of house are different")
    void differentHouseInstancesAreNotEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 34", "3380-45", "Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);


        Address a2 = new Address("Rua Júlio Dinis, 32", "3380-45", "Porto", 41, 12.3, 110);

        House house1 = new House("Prédio1", a1, g1);
        House house2 = new House("Prédio2", a2, g1);

        //Assert
        assertNotEquals(house1.hashCode(), house2.hashCode());
        assertNotEquals(house1, house2);
    }

    @Test
    @DisplayName("Tests if two different objects are different")
    void differentObjectsAreNotEqual() {

        //Arrange
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Beja", 41, 12.3, 110);
        TypeGA t1 = new TypeGA("cidade");
        GeographicalArea g1 = new GeographicalArea("POR", "Porto", "City", 20, 20, 2, 2, 5);

        House house1 = new House("Prédio", a1, g1);

        //Assert
        assertNotEquals(house1, t1);
    }


    //RoomList

    /**
     * Check if a new room is created and confirm that the get methods are working
     */

    @Test
    void newRoom() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);


        House h = new House("Prédio", a1, g1);
        Room room = h.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);

        assertEquals("bedroom", room.getName());
        assertEquals(1, room.getFloor());
        assertEquals(5, room.getArea().getOccupationArea());
    }


    /**
     * Check if a room is create and added to the RoomList
     */

    @Test
    void addOneRoom() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h1 = new House("Prédio", a1, g1);
        Room room = h1.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);

        h1.getRoomList().addRoom(room);
        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = h1.getRoomList().getRoomList();

        assertEquals(expectedResult, result);
    }

    /**
     * Check if two rooms are create and added to the RoomList
     */

    @Test
    void addTwoRooms() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Prédio", a1, g1);
        Room room = h.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomList().createNewRoom("garden", 0, 2.5, 3, 2);
        assertEquals(0, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room);
        assertEquals(1, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room1);
        assertEquals(2, h.getRoomList().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room, room1);
        List<Room> result = h.getRoomList().getRoomList();
        assertEquals(expectedResult, result);
    }

    /**
     * Try to add two rooms but one is empty so only the correct one is added
     */

    @Test
    void addOneRoomEmptyName() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomList().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room);
        assertEquals(1, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room1);
        assertEquals(1, h.getRoomList().getRoomList().size());

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result = h.getRoomList().getRoomList();
        assertEquals(expectedResult, result);
    }


    /**
     * Don't validate room name if empty and return false
     */

    @Test
    void nameNotValid() {
        Address a1 = new Address("Av. da Liberdade, 34", "2000-123", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room1 = h.getRoomList().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room);
        assertEquals(1, h.getRoomList().getRoomList().size());

        h.getRoomList().addRoom(room1);
        assertEquals(1, h.getRoomList().getRoomList().size());

        boolean expectedResult = false;
        String name = " ";

        boolean result = room.validateName(name);
        assertEquals(expectedResult, result);
    }

    /**
     * Validate correct room name and return true and also validate empty room name and return false
     */

    @Test
    void addOneGetTrueAddAnotherGetFalse() {
        Address a1 = new Address("Rua Júlio Dinis", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House h = new House("Casa", a1, g1);
        Room room = h.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 1.7);
        Room room1 = h.getRoomList().createNewRoom("  ", 0, 2.5, 3, 2);

        assertEquals(0, h.getRoomList().getRoomList().size());

        boolean result = h.getRoomList().addRoom(room);
        assertEquals(1, h.getRoomList().getRoomList().size());
        assertTrue(result);

        boolean result1 = h.getRoomList().addRoom(room1);
        assertEquals(1, h.getRoomList().getRoomList().size());
        assertFalse(result1);

        List<Room> expectedResult = Arrays.asList(room);
        List<Room> result2 = h.getRoomList().getRoomList();
        assertEquals(expectedResult, result2);
    }


    @Test
    @DisplayName("Ensure a room is removed from the list of rooms of a house ")
    void removeRoom() {
        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);

        House house = new House("Casa", a1, g1);
        Room room1 = house.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);
        Room room2 = house.getRoomList().createNewRoom("kitchen", 1, 4, 5, 2);

        house.getRoomList().addRoom(room1);
        house.getRoomList().addRoom(room2);
        assertEquals(2, house.getRoomList().getRoomList().size());
        assertTrue(house.getRoomList().removeRoom(room2));
        assertEquals(1, house.getRoomList().getRoomList().size());
    }

    @Test
    @DisplayName("Ensure a room not contained in the list of rooms of a house" +
            "cannot be removed")
    void removeRoomReturnsFalse() {

        Address a1 = new Address("Rua Júlio Dinis, 345", "3380-45", "Lisboa", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("LIS", "Lisboa", "City", 20, 20, 2, 2, 5);


        House house = new House("Casa", a1, g1);
        Room room1 = house.getRoomList().createNewRoom("bedroom", 1, 2, 2.5, 2);
        house.getRoomList().addRoom(room1);
        Room room2 = house.getRoomList().createNewRoom("kitchen", 1, 4, 5, 2);

        assertFalse(house.getRoomList().removeRoom(room2));
    }

    @Test
    @DisplayName("Get House Grid List from the House")
    void getHouseGridListFromHouseTest() {
        House house = new House();
        HouseGrid hg01 = new HouseGrid("grid01");
        HouseGrid hg02 = new HouseGrid("grid02");

        house.getHGListInHouse().addHouseGrid(hg01);
        house.getHGListInHouse().addHouseGrid(hg02);

        List<HouseGrid> expectedResult = Arrays.asList(hg01, hg02);
        List<HouseGrid> result = house.getHGListInHouse().getHouseGridList();

        assertEquals(expectedResult, result);

    }

    @Test
    void showRoomsWithoutGrid() {
        House house = new House();
        HouseGrid hg1 = new HouseGrid("grid01");
        HouseGrid hg2 = new HouseGrid("grid02");
        HouseGridList hgLst = house.getHGListInHouse();
        hgLst.addHouseGrid(hg1);
        hgLst.addHouseGrid(hg2);
        RoomList roomLstOfHouse = house.getRoomList();
        Room kitchen = new Room("kitchen", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(kitchen);
        hg1.attachRoomToGrid(kitchen);
        Room bathroom = new Room("bathroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bathroom);
        Room bedroom = new Room("bedroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bedroom);
        hg1.attachRoomToGrid(kitchen);
        hg1.attachRoomToGrid(bathroom);
        int expected = 1;
        int result = house.getRoomsWithoutGrid(hg1).getRoomListSize();
        assertEquals(expected, result);
    }

    @Test
    void showRoomsWithoutHouseGrid() {
        House house = new House();
        HouseGrid hg1 = new HouseGrid("grid01");
        HouseGrid hg2 = new HouseGrid("grid02");
        HouseGridList hgLst = house.getHGListInHouse();
        hgLst.addHouseGrid(hg1);
        hgLst.addHouseGrid(hg2);
        RoomList roomLstOfHouse = house.getRoomList();
        Room kitchen = new Room("Kitchen", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(kitchen);
        Room bathroom = new Room("bathroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bathroom);
        Room bedroom = new Room("Bedroom", 1, 2, 2.5, 2);
        roomLstOfHouse.addRoom(bedroom);

        hg1.attachRoomToGrid(bathroom);
        String expected = "1 - Kitchen\n" +
                "2 - Bedroom\n";
        String result = house.showRoomsWithoutHouseGrid(hg1);
        assertEquals(expected, result);
    }


    @Test
    void getListOfDeviceTypes() {
        House h = new House();
        List<DeviceType> result = h.getListOfDeviceTypes();
        assertEquals(result.size(),14);
    }

    @Test
    void showDeviceTypesList() {
        House h = new House();
        String expected="1 - ElectricWaterHeater\n" +
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
        String result = h.showDeviceTypesList();
        assertEquals(expected,result);

    }

    @Test
    @DisplayName("Ensure that sensor with the latest reading in the specified date is sensor s3.")
    void getSensorOfTypeWithLatestReadingsInDate() {
        House house = new House();
        house.setHouseAddress("Avenida Central","11","4425-255",25,25,12);
        GeographicalArea braga = new GeographicalArea("BR","Braga","City",25,25,25,2,5);
        house.setHouseGA(braga);
        SensorType temperature = new SensorType("temperature");
        SensorList bragaSensorList= braga.getSensorListInGA();
        GregorianCalendar startDate= new GregorianCalendar(2018,11,25);
        Location loc1= new Location(0,15,12);
        Location loc2= new Location(26,26,12);
        Location loc3= new Location(24,24,12);

        Sensor s1= new Sensor("Temperature Sensor 3",startDate,loc1,temperature);
        Sensor s2= new Sensor("Temperature Sensor 1",startDate,loc2,temperature);
        Sensor s3= new Sensor("Temperature Sensor 2",startDate,loc3,temperature);

        bragaSensorList.addSensor(s1);
        bragaSensorList.addSensor(s2);
        bragaSensorList.addSensor(s3);

        Reading r1TempSensor1 = new Reading(25,new GregorianCalendar(2018,11,26,9,15));
        Reading r2TempSensor1 = new Reading(20,new GregorianCalendar(2018,11,26,9,30));
        Reading r3TempSensor1 = new Reading(15,new GregorianCalendar(2018,11,27,9,15));
        s1.getReadingList().addReading(r1TempSensor1);
        s1.getReadingList().addReading(r2TempSensor1);
        s1.getReadingList().addReading(r3TempSensor1);

        Reading r1TempSensor2 = new Reading(15,new GregorianCalendar(2018,11,26,9,15));
        Reading r2TempSensor2 = new Reading(10,new GregorianCalendar(2018,11,26,9,30));
        Reading r3TempSensor2 = new Reading(5,new GregorianCalendar(2018,11,27,9,15));
        s2.getReadingList().addReading(r1TempSensor2);
        s2.getReadingList().addReading(r2TempSensor2);
        s2.getReadingList().addReading(r3TempSensor2);

        Reading r1TempSensor3 = new Reading(15,new GregorianCalendar(2018,11,26,9,15));
        Reading r2TempSensor3 = new Reading(10,new GregorianCalendar(2018,11,26,9,45));
        Reading r3TempSensor3 = new Reading(5,new GregorianCalendar(2018,11,27,9,15));
        s3.getReadingList().addReading(r1TempSensor3);
        s3.getReadingList().addReading(r2TempSensor3);
        s3.getReadingList().addReading(r3TempSensor3);

        Sensor result = house.getSensorOfTypeWithLatestReadingsInDate(new GregorianCalendar(2018,11,26),temperature);

        assertEquals(s3,result);
    }

    @Test
    @DisplayName("Tests if the second sensor has the most recent readings")
    void getSecondSensorWithLatestReadingsNotInPeriod(){
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);
        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Sensor s1 = new Sensor("RainSensor", sDate1, 47, -12, 200, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, 47, -12, 200, sT, "l/m2", rL2);

        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);

        Sensor result = house.getClosestSensorWithLatestReadingsInPeriod(sT, startDate, endDate);

        assertEquals(s2, result);
    }

    @Test
    @DisplayName("Tests if a sensor with no readings in period is not selected  ")
    void getClosestSensorWithLatestReadingsNotInPeriod(){
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);
        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 8);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 7);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Sensor s1 = new Sensor("RainSensor", sDate1, 47, -12, 200, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, 47, -12, 200, sT, "l/m2", rL2);

        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);

        Sensor result = house.getClosestSensorWithLatestReadingsInPeriod(sT, startDate, endDate);

        assertNotEquals(s1, result);
    }


    @Test
    @DisplayName("Tests if readings with a date equal to start date are included in the average")
    void averageOfReadingsWithStartDate() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);
        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 5);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Sensor s1 = new Sensor("RainSensor", sDate1, 47, -12, 200, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, 47, -12, 200, sT, "l/m2", rL2);

        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);

        double result = house.averageOfReadingsInPeriod(sT, startDate, endDate);

        assertEquals(20.46, result,0.1);


    }

    @Test
    @DisplayName("Tests if readings with a date equal to end date are included in the average")
    void averageOfReadingsWithEndDate() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);
        SensorType sT = new SensorType("rainfall");

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 3);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 6);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date3);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 3);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 1);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        Sensor s1 = new Sensor("RainSensor", sDate1, 47, -12, 200, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, 47, -12, 200, sT, "l/m2", rL2);

        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);

        double result = house.averageOfReadingsInPeriod(sT, startDate, endDate);

        assertEquals(27.6, result);

    }


}
