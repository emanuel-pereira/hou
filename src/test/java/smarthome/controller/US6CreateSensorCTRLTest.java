package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class US6CreateSensorCTRLTest {


    @DisplayName("Test if DataType List is showed as a string to the user")
    @Test
    void showDataTypeListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        SensorList sensorList = new SensorList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList, sensorList);
        DataType type1= new DataType("Temperature");
        DataType type2= new DataType("Wind");
        dataTypeList.addDataType(type1);
        dataTypeList.addDataType(type2);
        String expected = "1 - Temperature\n2 - Wind\n";
        String result =  ctrl6.showDataTypeListInString();
        assertEquals(expected,result);

    }

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        SensorList sensorList = new SensorList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList, sensorList);
        GeographicalArea ga1= new GeographicalArea("Porto","city",25,15,12,32,41);
        GeographicalArea ga2= new GeographicalArea("Lisboa","city",45,25,32,42,41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result =  ctrl6.showGAListInString();
        assertEquals(expected,result);
    }

    @DisplayName("Ensure that two different sensors are added to the respective Geographical Area")
    @Test
    void addNewSensorToGA() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        SensorList sensorList = new SensorList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList, sensorList);
        GeographicalArea ga1= new GeographicalArea("Porto","city",25,15,12,32,41);
        GeographicalArea ga2= new GeographicalArea("Lisboa","city",45,25,32,42,41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        DataType type1= new DataType("Temperature");
        DataType type2= new DataType("Wind");
        dataTypeList.addDataType(type1);
        dataTypeList.addDataType(type2);

        Sensor sensor1= new Sensor("TemperatureSensor", new GregorianCalendar(2018,11,12),type1.getDataTypeDesignation());
        sensorList.addSensor(sensor1);
        ctrl6.addNewSensorToGA(2);

        Sensor sensor2= new Sensor("WindSensor", new GregorianCalendar(2018,11,12),type2.getDataTypeDesignation());
        sensorList.addSensor(sensor2);
        boolean expected = true;
        boolean result= ctrl6.addNewSensorToGA(2);

        assertEquals(expected,result);

    }

    @DisplayName("Test if size of sensor List is updated when a new Sensor is added to a Sensor List")
    @Test
    void newSensor() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        SensorList sensorList = new SensorList();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList, sensorList);
        assertEquals(0,sensorList.getSensorList().size());
        DataType dataType = new DataType("Temperature");
        dataTypeList.addDataType(dataType);
        assertTrue(ctrl6.newSensor("TempSensor", new GregorianCalendar(2018,12,25),1));
        assertEquals(1,sensorList.getSensorList().size());
    }
}