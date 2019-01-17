package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTypeTest {

    @Test
    @DisplayName("Test if display device types lists all device types in string")
    void displayDeviceTypes() {

        String expectedResult=("1 - Electric Water Heater;\n2 - Washing Machine;\n3 - Dishwasher;\n4 - Fridge;\n5 - Kettler;\n6 - Oven;\n7 - Stove;\n8 - Microwave Oven;\n9 - Wall Electric Heater;\n10 - Portable Electric Oil Heater;\n11 - Portable Electric Convection Heater;\n12 - Wall Towel Heater;\n13 - Lamp;\n14 - Television;\n");
        String result= DeviceType.displayDeviceTypes();

        assertEquals(expectedResult,result);
    }


}