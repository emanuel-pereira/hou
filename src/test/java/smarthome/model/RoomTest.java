package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    /**
     * Checks if the room name is only spaces, if so the validation method returns False
     */
    @Test
    public void nameNotValid() {

        Room roomOne = new Room ("  ", 0, 2.5, 3, 2);

        String name = "  ";
        boolean result = roomOne.validateName (name);

        assertFalse (result);
    }

    /**
     * Checks if the room name is null, if so the validation method returns False
     */
    @Test
    public void nullNameNotValid() {

        String name = " ";
        Room roomOne = new Room (name, 0, 2.5, 3, 2);

        boolean result = roomOne.validateName (name);

        assertFalse (result);
    }

    /**
     * Checks if the room name is correct, if so the validation method returns True
     */
    @Test
    public void nameValid() {

        Room roomOne = new Room ("bedroom", 0, 2.5, 3, 4.7);

        String name = "bedroom";
        boolean result = roomOne.validateName (name);

        assertTrue (result);
    }

    /**
     * Checks if two different room objects are equals because of their content
     */
    @Test
    public void equalsIfTypeGAEqualsTypeGA() {
        Room room1 = new Room ("bedroom", 0, 2.5, 3, 3);
        Room room2 = new Room ("bedroom", 0, 2.5, 3, 3);

        boolean result = room1.equals (room2);

        assertEquals (room1.hashCode (), room2.hashCode ());
        assertTrue (result);
    }

    /**
     * Checks if two different room objects are different because of their content
     */
    @Test
    public void equalsIfTypeGAEqualsDifferentTypeGA() {
        Room room1 = new Room ("bedroom", 0, 2.5, 3, 3);
        Room room2 = new Room ("garden", 0, 2.5, 3, 3);

        boolean result = room1.equals (room2);

        assertNotEquals (room1.hashCode (), room2.hashCode ());
        assertFalse (result);
    }

    /**
     * Check if two different types objects are not equal
     */
    @Test
    public void equalsIfStringEqualsRoom() {
        String person1 = "Joana";
        Room room1 = new Room ("bedroom", 0, 2.5, 3, 3);
        boolean result;

        result = room1.equals (person1);

        assertFalse (result);
    }


    @Test
    @DisplayName("Check if house grid with nominal power of 1500 is assigned to living room")
    void setHouseGrid() {
        HouseGrid grid = new HouseGrid ("grid01", 1500);
        Room room1 = new Room ("Living Room", 0, 5, 6, 4);
        room1.setmHouseGrid (grid);
        double expectedResult = 1500;
        double result = room1.getmHouseGrid ().getContractedMaximumPower ();
        assertEquals (expectedResult, result);
    }

    /**
     * Test to define/edit the name of the room.
     */
    @Test
    public void setName() {

        Room bedroom = new Room ("bedroom", 1, 2, 3, 2.5);
        bedroom.setName ("bedroom1");

        String expectedResult = "bedroom1";
        String result = bedroom.getName ();

        assertEquals (expectedResult, result);

    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    public void setFloor() {

        Room bedroom = new Room ("bedroom", 1, 2, 3, 2);
        bedroom.setFloor (2);

        int expectedResult = 2;
        int result = bedroom.getFloor ();

        assertEquals (expectedResult, result);
    }

    /**
     * Test to define/edit the dimensions of the room.
     */
    @Test
    public void setArea() {

        OccupationArea oa = new OccupationArea (3, 2);
        Room bedroom = new Room ("bedroom", 1, 2, 3, 2);
        bedroom.setArea (oa);

        OccupationArea expectedResult = oa;
        OccupationArea result = bedroom.getArea ();

        assertEquals (expectedResult, result);


    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    public void setHeight() {

        Room bedroom = new Room ("bedroom", 1, 2, 3, 2);
        bedroom.setHeight (3);

        double expectedResult = 3;
        double result = bedroom.getHeight ();

        assertEquals (expectedResult, result);
    }

    /**
     * Check if sum of nominal power of devices in room is correct and return correct number
     *//*
    @Test
    public void getCorrectNominalPower() {

        Room bedroom = new Room ("bedroom", 1, 2, 3, 2);
        DeviceList dL = bedroom.getDeviceList ();

        Device d1 = dL.newDevice ("fridge", bedroom, "fridge", 100);
        Device d2 = dL.newDevice("lamp", bedroom, "lamp", 50);

        dL.addDevice (d1);
        dL.addDevice (d2);


        double expectedResult = 150;
        double result = bedroom.getNominalPower ();


        assertEquals (expectedResult, result);
    }

    *//**
     * Check if sum of nominal power of devices in room is correct and return incorrect number
     *//*
    @Test
    public void getIncorrectNominalPower() {

        Room bedroom = new Room ("bedroom", 1, 2, 3, 2);
        DeviceList dL = bedroom.getDeviceList ();

        Device d1 = dL.newDeviceWithoutSpecs ("fridge", bedroom, "fridge", 200);
        Device d2 = dL.newDeviceWithoutSpecs("lamp", bedroom, "lamp", 200);

        dL.addDevice (d1);
        dL.addDevice (d2);


        double expectedResult = 410;
        double result = bedroom.getNominalPower ();


        assertNotEquals (expectedResult, result);
    }*/

}
