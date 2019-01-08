package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        //Act
        Sensor sensor1 = list1.newSensor("Sensor1", new GregorianCalendar(2018, 12, 15), 25, 32, 2, new SensorType("Temperature"));

        //Assert
        assertEquals("Sensor1", sensor1.getDesignation());
    }

    @Test
    @DisplayName("Tests if a new sensor is added to the Sensor list")
    void addSensorToList() {
        //Arrange
        SensorList list = new SensorList();
        Sensor sensor1 = list.newSensor("TempSensor", new GregorianCalendar(2018, 12, 15), 25, 32, 2, new SensorType("Temperature"));

        //Act
        assertTrue(list.addSensor(sensor1));
        List<Sensor> expectedResult = Arrays.asList(sensor1);
        List<Sensor> result = list.getSensorList();

        //Assert
        assertEquals(expectedResult, result);
    }

    @DisplayName("Tests if a Sensor is not added to the list if it is repeated")
    @Test
    public void notAddRepeatedSensorType() {
        //Arrange
        SensorList list = new SensorList();
        Sensor sensor1 = list.newSensor("TempSensor", new GregorianCalendar(2018, 12, 15), 25, 32, 2, new SensorType("Temperature"));
        Sensor sensor2 = list.newSensor("WindSensor", new GregorianCalendar(2018, 12, 15), 25, 32, 2, new SensorType("Wind"));

        //Act
        assertEquals(0, list.getSensorList().size());
        assertTrue(list.addSensor(sensor1));
        assertEquals(1, list.getSensorList().size());
        assertFalse(list.addSensor(sensor1));

        List<Sensor> expectedResult = Arrays.asList(sensor1);
        List<Sensor> result = list.getSensorList();

        //Assert
        assertEquals(expectedResult, result);
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
