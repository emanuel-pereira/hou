package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
     * Check if required SensorType doesn't exists in the SensorTypeList and return false
     */
    @Test
    public void checkIfSensorDoesntTypeExists() {

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
}
