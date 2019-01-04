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

        Room roomOne = new Room("  ", 0, 2.5, 3);

        String name = "  ";
        boolean result = roomOne.validateName(name);

        assertFalse(result);
    }

    /**
     * Checks if the room name is correct, if so the validation method returns True
     */
    @Test
    public void nameValid() {

        Room roomOne = new Room("bedroom", 0, 2.5, 3);

        String name = "bedroom";
        boolean result = roomOne.validateName(name);

        assertTrue(result);
    }

    /**
     * Checks if two different room objects are equals because of their content
     */
    @Test
    public void equalsIfTypeGAEqualsTypeGA() {
        Room room1 = new Room("bedroom", 0, 2.5, 3);
        Room room2 = new Room("bedroom", 0, 2.5, 3);

        boolean result = room1.equals(room2);

        assertEquals(room1.hashCode(), room2.hashCode());
        assertTrue(result);
    }

    /**
     * Checks if two different room objects are different because of their content
     */
    @Test
    public void equalsIfTypeGAEqualsDifferentTypeGA() {
        Room room1 = new Room("bedroom", 0, 2.5, 3);
        Room room2 = new Room("garden", 0, 2.5, 3);

        boolean result = room1.equals(room2);

        assertNotEquals(room1.hashCode(), room2.hashCode());
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if house grid with nominal power of 1500 is assigned to living room")
    void setHouseGrid() {
        HouseGrid grid = new HouseGrid(1500);
        Room room1 = new Room("Living Room", 0, 5, 6);
        room1.setmHouseGrid(grid);
        double expectedResult = 1500;
        double result = room1.getmHouseGrid().getContractedMaximumPower();
        assertEquals(expectedResult, result);
    }
    /**
     * Test to define/edit the name of the room.
     */
    @Test
    public void setName() {

        Room bedroom = new Room("bedroom",1,2,3);
        bedroom.setName ("bedroom1");

        String expectedResult = "bedroom1";
        String result = bedroom.getName();

        assertEquals(expectedResult, result);

    }

    /**
     * Test to define/edit the floor of the room.
     */
    @Test
    public void setFloor() {

        Room bedroom = new Room ("bedroom",1,2,3);
        bedroom.setFloor (2);

        int expectedResult = 2;
        int result = bedroom.getFloor();

        assertEquals(expectedResult, result);
    }

    /**
     * Test to define/edit the dimensions of the room.
     */
    @Test
    public void setArea() {

        OccupationArea oa = new OccupationArea(3,2);
        Room bedroom = new Room ("bedroom",1,2,3);
        bedroom.setArea (oa);

        OccupationArea expectedResult = oa;
        OccupationArea result = bedroom.getArea();

        assertEquals(expectedResult,result);


    }
}
