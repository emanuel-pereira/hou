package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.Address;
import smarthome.model.GeographicalArea;
import smarthome.model.House;
import smarthome.model.RoomList;

import static org.junit.jupiter.api.Assertions.*;

class US105AddNewRoomToHouseCTRLTest {

    /**
     * Create a new room in the house with an empty room list and get the size of the room list to check if it was created
     */

    @Test
    void createNewRoom() {
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45",25,34,15);
        GeographicalArea g1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);

        House house1 = new House(a1, g1);
        RoomList roomList = house1.getRoomList();

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL(house1);
        assertEquals(0, house1.getRoomList().getRoomList().size());

        ctrl1.newRoom("kitchen", 1, 3, 3.5, 2);
        assertEquals(1, roomList.getRoomList().size());
    }


    /**
     * Try to create a new room with no name in the house with an empty room list and get the size of the room list
     * to check that it wasn't created
     */

    @Test
    void cantCreateNewRoom() {
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", 22,32,8);
        GeographicalArea g1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);

        House house2 = new House(a1,g1);

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL(house2);
        assertEquals(0, house2.getRoomList().getRoomList().size());

        ctrl1.newRoom(" ", 1, 3, 3.5, 2);
        assertEquals(0, house2.getRoomList().getRoomList().size());
    }

    /**
     * Create a new correct room and checked if the room was created (true). Check if the second one, that has an empty
     * name wasn't created (false).
     */

    @Test
    void createOneRoomSuccessAnotherFail() {
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45 Porto", 41, 12.3, 110);
        GeographicalArea g1 = new GeographicalArea("POR","Porto","City",25,35,15,40,45);

        House house2 = new House(a1,g1);

        US105AddNewRoomToHouseCTRL ctrl1 = new US105AddNewRoomToHouseCTRL(house2);
        assertEquals(0, house2.getRoomList().getRoomList().size());

        boolean result = ctrl1.newRoom("kitchen", 1, 3, 3.5, 2);
        assertEquals(1, house2.getRoomList().getRoomList().size());
        assertTrue(result);

        boolean result1 = ctrl1.newRoom(" ", 1, 3, 3.5, 2);
        assertEquals(1, house2.getRoomList().getRoomList().size());
        assertFalse(result1);


    }

}
