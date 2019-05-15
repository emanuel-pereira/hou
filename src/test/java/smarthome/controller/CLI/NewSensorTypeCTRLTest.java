package smarthome.controller.CLI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.controller.CLI.NewSensorTypeCTRL;
import smarthome.model.SensorTypeList;

import static org.junit.jupiter.api.Assertions.*;

class NewSensorTypeCTRLTest {


    @Test
    @DisplayName("Tests if a new sensor type is added to an empty list")
    void newSensorTypeInEmptyList() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals(0, list.getSensorTypeList().size());
        ctrl1.newSensorType("Temperature");
        assertEquals(1, list.getSensorTypeList().size());
    }


    @Test
    @DisplayName("Tests if a new sensor type is added to a filled list")
    void newSensorTypeInFilledList() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals(0, list.getSensorTypeList().size());

        ctrl1.newSensorType("Humidity");
        assertEquals(1, list.getSensorTypeList().size());

        ctrl1.newSensorType("Temperature");
        assertEquals(2, list.getSensorTypeList().size());
    }


    @Test
    @DisplayName("Tests if two new equal sensor types are added to an empty list")
    void twoNewEqualSensorTypesInEmptyList() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals(0, list.getSensorTypeList().size());

        ctrl1.newSensorType("wind");
        assertEquals(1, list.getSensorTypeList().size());

        ctrl1.newSensorType("wind");
        assertEquals(1, list.getSensorTypeList().size());
    }

    @Test
    @DisplayName("Tests if new sensor types are added to a list with same and different types")
    void differentAndEqualSensorTypesAddedToList() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals(0, list.getSensorTypeList().size());

        ctrl1.newSensorType("temperature");
        assertEquals(1, list.getSensorTypeList().size());

        ctrl1.newSensorType("humidity");
        assertEquals(2, list.getSensorTypeList().size());

        ctrl1.newSensorType("temperature");
        assertEquals(2, list.getSensorTypeList().size());

        ctrl1.newSensorType("wind");
        assertEquals(3, list.getSensorTypeList().size());

        ctrl1.newSensorType("wind");
        assertEquals(3, list.getSensorTypeList().size());
    }



   @Test
    @DisplayName("Tests if a sensor type with only a space is not added to a list")
    void spaceOnlySensorTypeIsNotAddedToList() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals (0, list.getSensorTypeList().size());
        ctrl1.newSensorType(" ");

        assertEquals (0, list.getSensorTypeList().size());
    }


    @Test
    @DisplayName("Add a wrong type of geographical area in a list")
    void newTypeGAIfSpaceNotAdded() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals(0, list.getSensorTypeList().size());

        ctrl1.newSensorType("humidity");
        assertEquals(1, list.getSensorTypeList().size());

        ctrl1.newSensorType("            ");
        assertEquals(1, list.getSensorTypeList().size());

        ctrl1.newSensorType("temperature");
        assertEquals(2, list.getSensorTypeList().size());
    }



    @Test
    @DisplayName("Add a string with an unaccepted character 'Ç' type of GA")
    void newTypeGAIfSpaceNotAdded2() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals (0, list.getSensorTypeList().size ());

        assertTrue(ctrl1.newSensorType("temperature"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType("temperatureÇ"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType("            "));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType(null));
        assertEquals (1, list.getSensorTypeList().size ());

        assertTrue(ctrl1.newSensorType("wind"));
        assertEquals (2, list.getSensorTypeList().size ());
    }

    @Test
    @DisplayName("Add a repetitive string with lower case and a title case and a upper case type of GA")
    void newTypeGAIfSpaceNotAdded3() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals (0, list.getSensorTypeList().size ());

        assertTrue(ctrl1.newSensorType("rainfall"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType("Rainfall"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType("RAINFALL"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertFalse(ctrl1.newSensorType("RAINfall"));
        assertEquals (1, list.getSensorTypeList().size ());

        assertTrue(ctrl1.newSensorType("temperature"));
        assertEquals (2, list.getSensorTypeList().size ());

        assertTrue(ctrl1.newSensorType("wind"));
        assertEquals (3, list.getSensorTypeList().size ());
    }

    @Test
    @DisplayName("Add a null sensor type")
    void newNullSensorType() {
        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl1 = new NewSensorTypeCTRL(list);

        assertEquals (0, list.getSensorTypeList().size ());

        ctrl1.newSensorType(null);
        assertEquals (0, list.getSensorTypeList().size ());


        assertFalse(ctrl1.newSensorType("            "));
        assertEquals (0, list.getSensorTypeList().size ());

    }

    @Test
    @DisplayName("Add new sensor types to a list and return the list as a String")
    void checkListWithNewSensorTypes() {

        SensorTypeList list = new SensorTypeList();
        NewSensorTypeCTRL ctrl5 = new NewSensorTypeCTRL(list);


        assertEquals (0, list.getSensorTypeList().size ());
        assertTrue(ctrl5.newSensorType("rainfall"));
        assertEquals (1, list.getSensorTypeList().size ());
        assertTrue(ctrl5.newSensorType("wind"));
        assertEquals (2, list.getSensorTypeList().size ());

        String result = ctrl5.returnSensorTypeList();
        String expected = "rainfall\nwind\n";

        assertEquals(expected,result);
    }

}