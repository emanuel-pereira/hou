package smarthome.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RoomDetailDTOTest {

    @Test
    void getLength() {
        RoomDetailDTO room = new RoomDetailDTO();

        room.setLength(2);

        double expected = 2;
        double result = room.getLength();

        assertEquals(expected,result);

    }

    @Test
    void getWidth() {
        RoomDetailDTO room = new RoomDetailDTO("R01","bedroom", 0, 2.5, 3, 3);

        room.setWidth(3);

        double expected = 3;
        double result = room.getWidth();

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Different roomDetailDTO with the same values is the same roomDetailDTO")
    void equalsIfRoomDetailDTOEqualsRoomSameValues() {
        RoomDetailDTO room1 = new RoomDetailDTO("R01","bedroom", 0, 2.5, 3, 3);
        RoomDetailDTO room2 = new RoomDetailDTO("R01","bedroom", 0, 2.5, 3, 3);

        boolean result = room1.equals(room2);

        assertEquals(room1.hashCode(), room2.hashCode());
        assertTrue(result);
    }

    @Test
    @DisplayName("Checks if one roomDetailDTO object is equal to itself")
    void equalsIfRoomDetailDTOEqualsRoomDetailDTO() {
        RoomDetailDTO room = new RoomDetailDTO ("R01","bedroom", 0, 2.5, 3, 3);

        boolean result = room.equals(room);

        assertEquals(room.hashCode(), room.hashCode());
        assertTrue(result);
    }


    @Test
    @DisplayName("Checks if two different roomDetailDTO objects are different because of their content")
    void equalsIfRoomDetailDTOEqualsDifferentRoomDetailDTO() {
        RoomDetailDTO room1 = new RoomDetailDTO("R01", "bedroom", 0, 2.5, 3, 3);
        RoomDetailDTO room2 = new RoomDetailDTO("R02", "garden", 0, 2.5, 3, 3);

        boolean result = room1.equals(room2);

        assertNotEquals(room1.hashCode(), room2.hashCode());
        assertFalse(result);
    }


    @Test
    @DisplayName("Check if two different types objects are not equal")
    void equalsIfStringEqualsRoomDetailDTO() {
        String string = "Joana";
        RoomDetailDTO room1 = new RoomDetailDTO("R01","bedroom", 0, 2.5, 3, 3);
        boolean result;

        result = room1.equals(string);

        assertFalse(result);
    }


}
