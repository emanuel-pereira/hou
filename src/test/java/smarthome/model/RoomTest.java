package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void nameNotValid() {

        Room roomOne = new Room("  ", 0, 2.5,3);

        String name = "  ";
        boolean result = roomOne.validateName (name);

        assertFalse(result);
    }

    @Test
    public void nameValid() {

        Room roomOne = new Room("bedroom", 0, 2.5,3);

        String name = "bedroom";
        boolean result = roomOne.validateName (name);

        assertTrue(result);
    }

    @Test
    public void equalsIfTypeGAEqualsTypeGA() {
        Room room1 = new Room("bedroom", 0, 2.5,3);
        Room room2 = new Room("bedroom", 0, 2.5,3);

        boolean result = room1.equals (room2);

        assertEquals (room1.hashCode (), room2.hashCode ());
        assertTrue(result);
    }

    @Test
    public void equalsIfTypeGAEqualsDifferentTypeGA() {
        Room room1 = new Room("bedroom", 0, 2.5,3);
        Room room2 = new Room("garden", 0, 2.5,3);

        boolean result = room1.equals (room2);

        assertNotEquals (room1.hashCode (), room2.hashCode ());
        assertFalse(result);
    }

}
