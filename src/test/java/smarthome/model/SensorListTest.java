package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Reading> readings = new ArrayList<>();
        readings.add(r1);
        readings.add(r2);
        //Act


        Sensor sensor1 = list1.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), "Temperature", "Celsius", 25, 32, 2, readings);

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
        List<Reading> readings = new ArrayList<>();
        readings.add(r1);
        readings.add(r2);
        Sensor sensor1 = list.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), "Temperature", "Celsius", 25, 32, 2, readings);

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
        List<Reading> readings = new ArrayList<>();
        readings.add(r1);
        readings.add(r2);
        Sensor sensor1 = list.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), "Temperature", "Celsius", 25, 32, 2, readings);

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
}
