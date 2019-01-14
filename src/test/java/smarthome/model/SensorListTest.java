package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorListTest {

    @Test
    @DisplayName("Tests if a new sensor is created")
    void createNewSensorObject() {
        //Arrange
        SensorList list1 = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL = new ReadingList ();
        rL.addReading (r1);
        rL.addReading (r2);
        //Act

        SensorType sT1 = new SensorType ("Temperature");
        Sensor sensor1 = list1.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), 25, 32, 2, sT1, "Celsius", rL);

        //Assert
        assertEquals("Sensor1", sensor1.getDesignation());
    }

    @Test
    @DisplayName("Tests if a new sensor is created and is added to a sensor list")
    void createAndAddSensorToList() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL = new ReadingList();
        rL.addReading (r1);
        rL.addReading (r2);
        SensorType sT1 = new SensorType ("Temperature");
        Sensor sensor1 = list.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), 25, 32, 25, sT1, "C", rL);

        //Act
        assertTrue(list.addSensor(sensor1));
        int expectedResult = 1;
        int result = list.getSensorList().size();

        //Assert
        assertEquals(expectedResult, result);
    }

    @DisplayName("Tests if a Ssensor is not added to the lit if the list already contains that sensor")
    @Test
    public void notAddRepeatedSensorType() {
        //Arrange
        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL = new ReadingList();
        rL.addReading (r1);
        rL.addReading (r2);
        SensorType sT1 = new SensorType ("Temperature");

        Sensor sensor1 = list.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), 25, 25, 32, sT1, "C", rL);

        list.addSensor(sensor1);
        list.addSensor(sensor1);
        int expectedResult=1;
        int result=list.getSensorList().size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Tests if two sensor designations are different")
    public void checkIfSensorDesignationAreDifferent() {
        Sensor sensor1 = new Sensor("Sensor1");
        Sensor sensor2 = new Sensor("Sensor2");

        boolean result;

        result = sensor1.equals(sensor2);

        assertNotEquals(sensor1.hashCode(), sensor2.hashCode());
        assertEquals(false, result);
    }

    /**
     * Check if required SensorType doesn't exist in the SensorTypeList and return false
     */
    @Test
    public void checkIfSensorTypeDoesntExist() {

        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL = new ReadingList();
        rL.addReading (r1);
        rL.addReading (r2);
        SensorType sT1 = new SensorType ("Temperature");
        Sensor sensor1 = list.newSensor("SensorLight", new GregorianCalendar(2018, 12, 15), 24, 34, 25, sT1, "C", rL);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL2 = new ReadingList();
        rL2.addReading (r3);
        rL2.addReading (r4);
        SensorType sH1 = new SensorType ("Humidity");
        Sensor sensor2 = list.newSensor("SensorHum", new GregorianCalendar(2018, 12, 15), 25, 32, 25, sH1,"Percentage", rL2);
        list.addSensor(sensor1);
        list.addSensor(sensor2);

        boolean result = list.checkIfRequiredSensorTypeExists ("rainfall");

        assertFalse (result);
    }

    /**
     * Check if required SensorType exists in the SensorTypeList and return true
     */
    @Test
    public void checkIfSensorTypeExists() {

        SensorList list = new SensorList();
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL = new ReadingList();
        rL.addReading (r1);
        rL.addReading (r2);
        SensorType sT1 = new SensorType ("temperature");
        Sensor sensor1 = list.newSensor("SensorTemp", new GregorianCalendar(2018, 12, 15), 24, 34, 25, sT1, "C", rL);
        Reading r3 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r4 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rL2 = new ReadingList();
        rL2.addReading (r3);
        rL2.addReading (r4);
        SensorType sH1 = new SensorType ("light");
        Sensor sensor2 = list.newSensor("SensorHum", new GregorianCalendar(2018, 12, 15), 25, 32, 25, sH1,"Percentage", rL2);
        list.addSensor(sensor1);
        list.addSensor(sensor2);

        boolean result = list.checkIfRequiredSensorTypeExists ("temperature");

        assertTrue (result);
    }
    @DisplayName("Test if Sensors List is showed as a string to the user")
    @Test
    void showSensorListInString() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        House h1 = new House();
        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);
        Sensor s1 = new Sensor("sensor1");
        Sensor s2 = new Sensor("sensor2");
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);

        String expected = "1 - sensor1\n2 - sensor2\n";
        String result = sensorList.showSensorListInString();
        assertEquals(expected, result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Room")
    @Test
    void addNewSensorToRoom() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        RoomList roomList = new RoomList();

        Room r1 = new Room("room1",1,1,1,1);
        Room r2 = new Room("room2",1,1,1,1);

        roomList.addRoom(r1);
        roomList.addRoom(r2);

        SensorType type1 = new SensorType("Temperature");
        SensorType type2 = new SensorType("Wind");
        sensorTypeList.addSensorType(type1);
        sensorTypeList.addSensorType(type2);

        Reading r1Porto = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Porto = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsPt = new ReadingList();
        readingsPt.addReading (r1Porto);
        readingsPt.addReading (r2Porto);

        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2Lis = new Reading(21, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList readingsLis = new ReadingList();
        readingsLis.addReading (r1Lis);
        readingsLis.addReading (r2Lis);

        Sensor s1 = r1.getSensorListInRoom().createNewInternalSensor("sensor1", new GregorianCalendar(2018, 12, 26, 12, 00),type1,"c",readingsLis);
        Sensor s2 = r1.getSensorListInRoom().createNewInternalSensor("sensor2", new GregorianCalendar(2018, 12, 26, 12, 00),type1,"c",readingsLis);

        r1.getSensorListInRoom().addSensor(s1);
        r1.getSensorListInRoom().addSensor(s2);
        assertEquals(2,r1.getSensorListInRoom().getSensorList().size());
    }
    @Test
    void createNewInternalSensor() {
        SensorType type1 = new SensorType("Temperature");
        ReadingList readingsLis = new ReadingList();
        Reading r1Lis = new Reading(27, new GregorianCalendar(2018, 12, 26, 12, 00));
        readingsLis.addReading (r1Lis);
        SensorList sensorList = new SensorList();

        Sensor s1 = sensorList.createNewInternalSensor("sensor1", new GregorianCalendar(2018, 12, 26, 12, 00),type1,"c",readingsLis);

        assertEquals("sensor1",s1.getDesignation());
    }

    @Test
    void getListOfSensorsByType() {
        SensorType temp = new SensorType("temperature");
        SensorType wind = new SensorType("wind");
        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 00);
        ReadingList readings = new ReadingList();
        Sensor s1 = new Sensor("sensor1", startDate,temp,"c",readings);
        Sensor s2 = new Sensor("sensor2",startDate,temp,"c",readings);
        Sensor s3 = new Sensor("sensor3",startDate,wind,"c",readings);
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        sensorList.addSensor(s3);

        List<Sensor> sListByType = sensorList.getListOfSensorsByType(temp);

        assertEquals(2,sListByType.size());

    }



}
