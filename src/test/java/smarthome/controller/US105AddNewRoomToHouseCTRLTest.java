package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US105AddNewRoomToHouseCTRLTest {

    /**
     * Create a new room in the house with an empty room list and get the size of the room list to check if it was created
     */
    @Test
    void createNewRoom() {

        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", null, "3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house1 = new House(a1, ga1);

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL (house1);
        assertEquals (0,house1.getRoomList ().size () );

        ctrl1.newRoom ("kitchen", 1, 3,3.5);
        assertEquals ( 1, house1.getRoomList ().size () );
    }

    /**
     * Try to create a new room with no name in the house with an empty room list and get the size of the room list
     * to check that it wasn't created
     */
    @Test
    void cantCreateNewRoom() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", null, "3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house2 = new House(a1, ga1);

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL (house2);
        assertEquals (0,house2.getRoomList ().size () );

        ctrl1.newRoom (" ", 1, 3,3.5);
        assertEquals ( 0, house2.getRoomList ().size () );
    }

    /**
     * Create a new correct room and checked if the room was created (true). Check if the second one, that has an empty
     * name wasn't created (false).
     */
    @Test
    void createOneRoomSuccessAnotherFail() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3", "3380-45 Porto", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);

        House house2 = new House(a1, ga1);

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL (house2);
        assertEquals (0,house2.getRoomList ().size () );

        boolean result = ctrl1.newRoom ("kitchen", 1, 3,3.5);
        assertEquals ( 1, house2.getRoomList ().size () );
        assertTrue(result);

        boolean result1 = ctrl1.newRoom (" ", 1, 3,3.5);
        assertEquals ( 1, house2.getRoomList ().size () );
        assertFalse(result1);


    }

}