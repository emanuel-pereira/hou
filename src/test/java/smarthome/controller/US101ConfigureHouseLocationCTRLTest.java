package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;


import static org.junit.jupiter.api.Assertions.*;

class US101ConfigureHouseLocationCTRLTest {

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        DataTypeList dataTypeList = new DataTypeList();
        GAList gaList = new GAList ();
        US6CreateSensorCTRL ctrl6 = new US6CreateSensorCTRL(dataTypeList,gaList);
        GeographicalArea ga1= new GeographicalArea("Porto","city",25,15,12,32,41);
        GeographicalArea ga2= new GeographicalArea("Lisboa","city",45,25,32,42,41);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result =  ctrl6.showGAListInString();
        assertEquals(expected,result);
    }


}
