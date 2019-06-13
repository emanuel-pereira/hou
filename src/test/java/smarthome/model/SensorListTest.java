/*package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.validations.Name;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.TypeGAList.getTypeGAListInstance;

class SensorListTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance1 = House.class.getDeclaredField("theHouse");
        instance1.setAccessible(true);
        instance1.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    @DisplayName("Tests if a new sensor is created")
    void createNewSensorObject() {
        //Arrange
        SensorList list1 = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        //Act

        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location location = new Location(25, 32, 2);
        Sensor sensor1 = list1.newSensor("R0001", "Sensor1", startDate, location, sT1, "Celsius", rL);


        String result = sensor1.getSensorBehavior().getDesignation();

        //Assert
        assertEquals("Sensor1", result);
    }

    @Test
    @DisplayName("Tests if a new sensor is created and is added to a sensor list")
    void createAndAddSensorToList() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate1 = new GregorianCalendar(2018, 12, 15);
        Location sensor1Loc = new Location(25, 32, 25);

        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate1, sensor1Loc, sT1, "C", rL);

        //Act
        assertTrue(list.addSensor(sensor1));
        int expectedResult = 1;
        int result = list.getSensorList().size();

        //Assert
        assertEquals(expectedResult, result);
    }

    @DisplayName("Tests if a Sensors is not added to the list if the list already contains that sensor")
    @Test
    public void notAddRepeatedSensorType() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location sensor1Loc = new Location(25, 25, 3);

        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate, sensor1Loc, sT1, "C", rL);
        list.addSensor(sensor1);
        list.addSensor(sensor1);
        int expectedResult = 1;
        int result = list.getSensorList().size();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Tests if a Sensors is not added to the list if the list already contains that sensor")
    @Test
    public void failAddSensor() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location sensor1Loc = new Location(25, 25, 32);

        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate, sensor1Loc, sT1, "C", rL);

        list.addSensor(sensor1);
        boolean result = list.addSensor(sensor1);
        assertFalse(result);
    }

    @DisplayName("Tests if a Sensors is not added to the list if the list already contains a sensor with the same name")
    @Test
    public void failAddSensorSameName() {
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location location = new Location(25, 25, 32);

        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate, location, sT1, "C", rL);
        Sensor sensor2 = list.newSensor("R0001", "Sensor1", startDate, location, sT1, "C", rL);

        list.addSensor(sensor1);
        boolean result = list.addSensor(sensor2);
        assertFalse(result);
    }

    @DisplayName("Tests if a Sensors is normally added to the list")
    @Test
    public void addSensorSuccess() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location loc = new Location(25, 25, 32);

        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate, loc, sT1, "C", rL);

        boolean expected = true;
        boolean result = list.addSensor(sensor1);
        assertEquals(expected, result);


    }

    @DisplayName("Tests if a Sensor is normally removed from the list")
    @Test
    public void removeSensorSuccess() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location loc = new Location(25, 25, 32);
        Sensor sensor1 = list.newSensor("R0001", "Sensor1", startDate, loc, sT1, "C", rL);
        Sensor sensor2 = list.newSensor("R0002", "Sensor2", startDate, loc, sT1, "C", rL);

        list.addSensor(sensor1);
        list.addSensor(sensor2);

        assertEquals(2, list.size());

        list.removeSensor(sensor1);

        assertEquals(1,list.size());
    }

    @Test
    public void checkIfSensorTypeDoesntExist() {

        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("Temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location sensor1Loc = new Location(24, 34, 25);

        Sensor sensor1 = list.newSensor("T0001", "SensorTemperature", startDate, sensor1Loc, sT1, "C", rL);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL2 = new ReadingList();
        rL2.addReading(r3);
        rL2.addReading(r4);
        SensorType sH1 = new SensorType("Humidity");
        Location sensor2Loc = new Location(25, 32, 25);

        Sensor sensor2 = list.newSensor("H0001", "SensorHum", startDate, sensor2Loc, sH1, "Percentage", rL2);
        list.addSensor(sensor1);
        list.addSensor(sensor2);

        boolean result = list.checkIfRequiredSensorTypeExists("rainfall");

        assertFalse(result);
    }


    public void checkIfSensorTypeExists() {

        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL = new ReadingList();
        rL.addReading(r1);
        rL.addReading(r2);
        SensorType sT1 = new SensorType("temperature");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 15);
        Location sensor1Loc = new Location(24, 34, 25);

        Sensor sensor1 = list.newSensor("T0001", "SensorTemp", startDate, sensor1Loc, sT1, "C", rL);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList rL2 = new ReadingList();
        rL2.addReading(r3);
        rL2.addReading(r4);
        SensorType sH1 = new SensorType("light");
        Location sensor2Loc = new Location(25, 32, 25);

        Sensor sensor2 = list.newSensor("H0001", "SensorTemp", startDate, sensor2Loc, sH1, "Percentage", rL2);
        list.addSensor(sensor1);
        list.addSensor(sensor2);

        boolean result = list.checkIfRequiredSensorTypeExists("temperature");

        assertTrue(result);
    }

    @DisplayName("Test if Sensors List is showed as a string to the user")
    @Test
    void showSensorListInString() {

        SensorType type1 = new SensorType("rainfall");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();

        Sensor s1 = new ExternalSensor("AB", "RainfallSensorOfPorto", startDate, loc, type1, "Celsius", readingList);
        SensorType temperature = new SensorType("temperature");
        GregorianCalendar sDate2 = new GregorianCalendar(2018, 9, 1, 15, 0);
        Location loc2 = new Location(42, 20, 10);
        ReadingList readings = new ReadingList();

        Sensor s2 = new ExternalSensor("BC", "TemperatureSensor", sDate2, loc2, temperature, "Celsius", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);

        String expected = "1 - RainfallSensorOfPorto\n" +
                "2 - TemperatureSensor\n";
        String result = sensorList.showSensorListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Room")
    @Test
    void addNewSensorToRoom() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        RoomList roomList = new RoomList();

        Room r1 = new Room("R01", "room1", 1, 1, 1, 1);
        Room r2 = new Room("R02", "room2", 1, 1, 1, 1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2Porto = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readingsPt = new ReadingList();
        readingsPt.addReading(r1Porto);
        readingsPt.addReading(r2Porto);

        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 0));
        Reading r2Lis = new Reading(21, new GregorianCalendar(2018, 12, 26, 13, 0));
        ReadingList readingsLis = new ReadingList();
        readingsLis.addReading(r1Lis);
        readingsLis.addReading(r2Lis);


        Sensor s1 = r1.getSensorListInRoom().createNewInternalSensor("S01", "Sensor1", new GregorianCalendar(2018, 12, 26, 12, 0), type1, "c", readingsLis);
        Sensor s2 = r1.getSensorListInRoom().createNewInternalSensor("S02", "Sensor2", new GregorianCalendar(2018, 12, 26, 12, 0), type1, "c", readingsLis);
        r1.getSensorListInRoom().addSensor(s1);
        r1.getSensorListInRoom().addSensor(s2);
        assertEquals(2, r1.getSensorListInRoom().getSensorList().size());
    }

    @Test
    void createNewInternalSensor() {
        SensorType type1 = new SensorType("Temperature");
        ReadingList readingsLis = new ReadingList();
        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 0));
        readingsLis.addReading(r1Lis);
        SensorList sensorList = new SensorList();

        Sensor s1 = sensorList.createNewInternalSensor("S01", "Sensor1", new GregorianCalendar(2018, 12, 26, 12, 0), type1, "c", readingsLis);

        String result = s1.getId();
        assertEquals("S01", result);
    }

    @Test
    void getListOfSensorsByType() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();

        Sensor s1 = new InternalSensor("S01", "Sensor1", startDate, temp, "c", readings);
        Sensor s2 = new InternalSensor("S02", "Sensor2", startDate, temp, "c", readings);
        Sensor s3 = new InternalSensor("S03", "Sensor3", startDate, wind, "c", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);

        SensorList sListByType = sensorList.getListOfSensorsByType(temp);

        assertEquals(2, sListByType.size());

    }

    @Test
    void getRequiredSensorPerType() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(temp);
        sensorTypeList.addSensorType(wind);
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();


        Sensor s1 = new InternalSensor("S01", "Sensor1", startDate, temp, "c", readings);
        Sensor s2 = new InternalSensor("S02", "Sensor2", startDate, wind, "c", readings);
        Sensor s3 = new InternalSensor("S03", "Sensor3", startDate, wind, "c", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);

        Sensor result = sensorList.getRequiredSensorPerType("temperature");

        assertEquals(s1, result);
    }

    @Test
    void getRequiredSensorPerTypeDontExists() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();


        Sensor s1 = new InternalSensor("S01", "Sensor1", startDate, temp, "c", readings);
        Sensor s2 = new InternalSensor("S02", "Sensor2", startDate, wind, "c", readings);
        Sensor s3 = new InternalSensor("S03", "Sensor3", startDate, wind, "c", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);

        Sensor result = sensorList.getRequiredSensorPerType("rain");

        assertNull(result);
    }

    @Test
    void getLastSensor() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();


        Sensor s1 = new InternalSensor("S01", "Sensor1", startDate, temp, "c", readings);
        Sensor s2 = new InternalSensor("S02", "Sensor2", startDate, temp, "c", readings);
        Sensor s3 = new InternalSensor("S03", "Sensor3", startDate, wind, "c", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);
        Sensor result = sensorList.getLastSensor();
        assertEquals(s3, result);
    }

    @Test
    @DisplayName("Add three sensors, deactivate one and return the correct size of the list")
    void getActiveSensors() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();


        Sensor s1 = new InternalSensor("S01", "Sensor1", startDate, temp, "c", readings);
        Sensor s2 = new InternalSensor("S02", "Sensor2", startDate, temp, "c", readings);
        Sensor s3 = new InternalSensor("S03", "Sensor3", startDate, wind, "c", readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);

        assertEquals(3, sensorList.getActiveSensors().size());

        GregorianCalendar pauseDate = new GregorianCalendar(2019, 01, 26, 12, 0);
        s2.getSensorBehavior().deactivate(pauseDate);

        assertEquals(2, sensorList.getActiveSensors().size());
    }

    @Test
    @DisplayName("Check if the is not possible to deactivate a sensor with a invalid sensor id")
    void deactivateSensorForInvalidSensorId() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");

        Sensor sensor = new ExternalSensor("TL1023", "Sensor1", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        GregorianCalendar pDate = new GregorianCalendar(2019, 2, 2);

        lisbonSensorList.deactivateSensor("TL", pDate);

        int expected = 1;
        int result = lisbonSensorList.getActiveSensors().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add two sensors, try to deactivate one twice")
    void tryDeactivateTwice() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, 2, 2);
        SensorType sensorType = new SensorType("Temperature");

        ExternalSensor sensor1 = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        ExternalSensor sensor2 = new ExternalSensor("TL1024", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());

        lisbonSensorList.addSensor(sensor1);
        lisbonSensorList.addSensor(sensor2);

        GregorianCalendar pDate = new GregorianCalendar(2019, 2, 3);

        lisbonSensorList.deactivateSensor("TL1023", pDate);

        int expected = 1;
        int result = lisbonSensorList.getActiveSensors().size();
        assertEquals(expected, result);

        lisbonSensorList.deactivateSensor("TL1023", pDate);

        int expected1 = 1;
        int result1 = lisbonSensorList.getActiveSensors().size();
        assertEquals(expected1, result1);
    }

    @Test
    @DisplayName("Add no sensors, try to deactivate")
    void tryDeactivateWhenNoSensors() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGa();
        GregorianCalendar pDate = new GregorianCalendar(2019, 2, 2);

        lisbonSensorList.deactivateSensor("TL1023", pDate);

        int expected = 0;
        int result = lisbonSensorList.getActiveSensors().size();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if any sensor has same ID and get false because it isn't added")
    void checkIfAnySensorHasSameIDGetFalse() {
        SensorType type1 = new SensorType("rainfall");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor s1 = new ExternalSensor("AB", "RainfallSensorOfPorto", startDate, loc, type1, "mm", readingList);
        Sensor s2 = new ExternalSensor("AC", "TemperatureSensorOfPorto", startDate, loc, type1, "Celsius", readingList);

        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        assertFalse(sensorList.checkIfAnySensorHasSameID(s2));
    }

    @Test
    @DisplayName("Check if any sensor has same ID and get true because there's already that id")
    void checkIfAnySensorHasSameIDGetTrue() {
        SensorType type1 = new SensorType("rainfall");
        GregorianCalendar startDate = new GregorianCalendar(2018, 8, 1, 9, 0);
        Location loc = new Location(40, 20, 10);
        ReadingList readingList = new ReadingList();
        Sensor s1 = new ExternalSensor("AB", "RainfallSensorOfPorto", startDate, loc, type1, "Celsius", readingList);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        assertTrue(sensorList.checkIfAnySensorHasSameID(s1));
    }

    @Test
    void getSensorByTypeWithLatestReadings(){
        SensorList sList = new SensorList();

        SensorType temp = new SensorType("temperature");
        ReadingList l1 = new ReadingList();
        Reading r1 = new Reading(0,new GregorianCalendar(2019, Calendar.MAY,31),"C");
        l1.addReading(r1);
        Sensor s1 = new InternalSensor("s001","one",new GregorianCalendar(2018,Calendar.JANUARY,1),temp,"C",l1);

        ReadingList l2 = new ReadingList();
        Reading r2 = new Reading(0,new GregorianCalendar(2019,Calendar.MAY,30),"C");
        l2.addReading(r2);
        Sensor s2 = new InternalSensor("s002","two",new GregorianCalendar(2018,Calendar.JANUARY,1),temp,"C",l2);

        ReadingList l3 = new ReadingList();
        Reading r3 = new Reading(0,new GregorianCalendar(2019,Calendar.MAY,29),"C");
        l3.addReading(r3);
        Sensor s3 = new InternalSensor("s003","three",new GregorianCalendar(2018,Calendar.JANUARY,1),temp,"C",l3);

        sList.addSensor(s1);
        sList.addSensor(s2);
        sList.addSensor(s3);

        Sensor result = sList.getInternalSensorByTypeWithLatestReadings(temp);

        assertEquals(s1,result);

    }



    @Test
    @DisplayName("save sensor to repo")
    void saveSensorToRepositoryNullPointer(){
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        SensorType temperature = new SensorType("temperature");
        SensorList sList = bedroom.getSensorListInRoom();
        Sensor sensor1 = new InternalSensor("S1", "Sensor1", new GregorianCalendar(2019, 2, 2), temperature, "C", new ReadingList());
        Sensor sensor2 = new InternalSensor("S2", "Sensor2", new GregorianCalendar(2019, 3, 4), temperature, "C", new ReadingList());

        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        getHouseRoomList().addRoom(bedroom);

        boolean thrown = false;
         try{
             Repositories.getInternalSensorRepository().count();
         }
         catch (NullPointerException e){
             thrown = true;
         }

         assertTrue(thrown);
    }

    @Test
    @DisplayName("deactivate sensor in repo")
    void deactivateSensorToRepositoryNullPointer(){
        Room bedroom = new Room("R1", "Bedroom 1", 2, 2, 2, 2);
        SensorType temperature = new SensorType("temperature");
        SensorList sList = bedroom.getSensorListInRoom();
        Sensor sensor1 = new InternalSensor("S1", "Sensor1", new GregorianCalendar(2019, 2, 2), temperature, "C", new ReadingList());
        Sensor sensor2 = new InternalSensor("S2", "Sensor2", new GregorianCalendar(2019, 3, 4), temperature, "C", new ReadingList());

        sList.addSensor(sensor1);
        sList.addSensor(sensor2);

        getHouseRoomList().addRoom(bedroom);

        GregorianCalendar pauseDate = new GregorianCalendar(2019,05,03);

        sList.deactivateSensor(sensor1.getIdentification(),pauseDate);

        boolean thrown = false;
        try{
            Repositories.getInternalSensorRepository().count();
        }
        catch (Exception e){
            thrown = true;
        }

        assertTrue(thrown);
    }


}
*/