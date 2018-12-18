package pt.ipp.isep.dei.smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.smarthome.model.DataTypeList;

import static org.junit.jupiter.api.Assertions.*;

class US5DefineSensorDataTypeCTRLTest {


    @Test
    @DisplayName("Tests if a new data type is added to an empty list")
    void newDataTypeInEmptyList() {
        DataTypeList list = new DataTypeList();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals(0, list.getDataTypeList().size());
        ctrl1.newDataType("Temperature");
        assertEquals(1, list.getDataTypeList().size());
    }


    @Test
    @DisplayName("Tests if a new data type is added to a filled list")
    void newDataTypeInFilledList() {
        DataTypeList list = new DataTypeList();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals(0, list.getDataTypeList().size());

        ctrl1.newDataType("Humidity");
        assertEquals(1, list.getDataTypeList().size());

        ctrl1.newDataType("Temperature");
        assertEquals(2, list.getDataTypeList().size());
    }


    @Test
    @DisplayName("Tests if two new equal data types are added to an empty list")
    void twoNewEqualDataTypesInEmptyList() {
        DataTypeList list = new DataTypeList();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals(0, list.getDataTypeList().size());

        ctrl1.newDataType("wind");
        assertEquals(1, list.getDataTypeList().size());

        ctrl1.newDataType("wind");
        assertEquals(1, list.getDataTypeList().size());
    }

    @Test
    @DisplayName("Tests if new data types are added to a list with same and different types")
    void differentAndEqualDataTypesAddedToList() {
        DataTypeList list = new DataTypeList();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals(0, list.getDataTypeList().size());

        ctrl1.newDataType("temperature");
        assertEquals(1, list.getDataTypeList().size());

        ctrl1.newDataType("humidity");
        assertEquals(2, list.getDataTypeList().size());

        ctrl1.newDataType("temperature");
        assertEquals(2, list.getDataTypeList().size());

        ctrl1.newDataType("wind");
        assertEquals(3, list.getDataTypeList().size());

        ctrl1.newDataType("wind");
        assertEquals(3, list.getDataTypeList().size());
    }



   @Test
    @DisplayName("Tests if a data type with only a space is not added to a list")
    void spaceOnlyDataTypeIsNotAddedToList() {
        DataTypeList list = new DataTypeList ();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals (0, list.getDataTypeList().size());
        ctrl1.newDataType (" ");

        assertEquals (0, list.getDataTypeList().size());
    }


    @Test
    @DisplayName("Add a wrong type of geographical area in a list")
    void newTypeGAIfSpaceNotAdded() {
        DataTypeList list = new DataTypeList();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL(list);

        assertEquals(0, list.getDataTypeList().size());

        ctrl1.newDataType("humidity");
        assertEquals(1, list.getDataTypeList().size());

        ctrl1.newDataType("            ");
        assertEquals(1, list.getDataTypeList().size());

        ctrl1.newDataType("temperature");
        assertEquals(2, list.getDataTypeList().size());
    }



    @Test
    @DisplayName("Add a string with an unaccepted character 'Ç' type of GA")
    void newTypeGAIfSpaceNotAdded2() {
        DataTypeList list = new DataTypeList ();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL (list);

        assertEquals (0, list.getDataTypeList ().size ());

        assertTrue(ctrl1.newDataType ("temperature"));
        assertEquals (1, list.getDataTypeList ().size ());

        assertFalse(ctrl1.newDataType ("temperatureÇ"));
        assertEquals (1, list.getDataTypeList().size ());

        assertFalse(ctrl1.newDataType("            "));
        assertEquals (1, list.getDataTypeList ().size ());

        assertFalse(ctrl1.newDataType(null));
        assertEquals (1, list.getDataTypeList ().size ());

        assertTrue(ctrl1.newDataType ("wind"));
        assertEquals (2, list.getDataTypeList ().size ());
    }

    @Test
    @DisplayName("Add a repetitive string with lower case and a title case and a upper case type of GA")
    void newTypeGAIfSpaceNotAdded3() {
        DataTypeList list = new DataTypeList ();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL (list);

        assertEquals (0, list.getDataTypeList ().size ());

        assertTrue(ctrl1.newDataType ("rainfall"));
        assertEquals (1, list.getDataTypeList ().size ());

        assertFalse(ctrl1.newDataType ("Rainfall"));
        assertEquals (1, list.getDataTypeList ().size ());

        assertFalse(ctrl1.newDataType ("RAINFALL"));
        assertEquals (1, list.getDataTypeList ().size ());

        assertFalse(ctrl1.newDataType ("RAINfall"));
        assertEquals (1, list.getDataTypeList().size ());

        assertTrue(ctrl1.newDataType ("temperature"));
        assertEquals (2, list.getDataTypeList ().size ());

        assertTrue(ctrl1.newDataType ("wind"));
        assertEquals (3, list.getDataTypeList ().size ());
    }

    @Test
    @DisplayName("Add a null data type")
    void newNullDataType() {
        DataTypeList list = new DataTypeList ();
        US5DefineSensorDataTypeCTRL ctrl1 = new US5DefineSensorDataTypeCTRL (list);

        assertEquals (0, list.getDataTypeList ().size ());

        ctrl1.newDataType(null);
        assertEquals (0, list.getDataTypeList().size ());


        assertFalse(ctrl1.newDataType("            "));
        assertEquals (0, list.getDataTypeList ().size ());

    }

    @Test
    @DisplayName("Add new sensor data types to a list and return the list as a String")
    void checkListWithNewSensorDataTypes() {

        DataTypeList list = new DataTypeList ();
        US5DefineSensorDataTypeCTRL ctrl5 = new US5DefineSensorDataTypeCTRL (list);


        assertEquals (0, list.getDataTypeList ().size ());
        assertTrue(ctrl5.newDataType ("rainfall"));
        assertEquals (1, list.getDataTypeList ().size ());
        assertTrue(ctrl5.newDataType ("wind"));
        assertEquals (2, list.getDataTypeList ().size ());

        String result = ctrl5.returnDataTypeList();
        String expected = "rainfall\nwind\n";

        assertEquals(expected,result);
    }

}