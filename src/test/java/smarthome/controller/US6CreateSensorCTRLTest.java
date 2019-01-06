
package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US6CreateSensorCTRLTest {


    @DisplayName("Test if DataType List is showed as a string to the user")
    @Test
    void showDataTypeListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList);
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
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList);
        GeographicalArea ga1= new GeographicalArea("Pt","Porto","city",25,15,12,32,41);
        GeographicalArea ga2= new GeographicalArea("Lis","Lisboa","city",45,25,32,42,41);
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
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList);

        GeographicalArea ga1= new GeographicalArea("Pt","Porto","city",25,15,12,32,41);
        GeographicalArea ga2= new GeographicalArea("Lis","Lisboa","city",45,25,32,42,41);

        gaList.addGA(ga1);
        gaList.addGA(ga2);

        DataType type1= new DataType("Temperature");
        DataType type2= new DataType("Wind");
        dataTypeList.addDataType(type1);
        dataTypeList.addDataType(type2);

        Reading r1Porto= new Reading(15,new GregorianCalendar(2018,12,26,12,00));
        Reading r2Porto= new Reading(18,new GregorianCalendar(2018,12,26,13,00));
        List<Reading> readingsPt= new ArrayList<>();
        readingsPt.add(r1Porto);
        readingsPt.add(r2Porto);

        Reading r1Lis= new Reading(27,new GregorianCalendar(2018,12,26,12,00));
        Reading r2Lis= new Reading(21,new GregorianCalendar(2018,12,26,13,00));
        List<Reading> readingsLis= new ArrayList<>();
        readingsLis.add(r1Lis);
        readingsLis.add(r2Lis);

        boolean result= ctrl6.addNewSensorToGA("LisboaTempSensor",new GregorianCalendar(2018,12,26),1,"C",55,40,15,1,readingsPt);
        assertTrue(result);

        boolean result1= ctrl6.addNewSensorToGA("PortoWindSensor",new GregorianCalendar(2018,12,26),2,"km/h",55,40,15,2,readingsLis);
        assertTrue(result1);

    }
}
