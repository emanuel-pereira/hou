package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static smarthome.model.House.getHGListInHouse;
import static smarthome.model.House.getHouseRoomList;


class AttachDetachAndListRoomsInGridCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getRoomOfHGName() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");

        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);

        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);

        grid1.getRoomListInAGrid().addRoom(kitchen);

        String expected = "Kitchen";
        String result = ctrl.getRoomOfHGName(0, 0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the houseGridName ")
    void getHGName() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        grid1RoomList.addRoom(kitchen);
        String expected = "Grid 1";
        String result = ctrl.getHGName(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the size of the roomList is 2 ")
    void getListOfRoomsSize() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        RoomList roomList = getHouseRoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getListOfRoomsSize();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the size of the roomList in the selected houseGrid is 2 ")
    void getRoomListOfHGSize() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        grid1.getRoomListInAGrid().addRoom(kitchen);
        grid1.getRoomListInAGrid().addRoom(bathroom);

        int expected = 2;
        int result = ctrl.getRoomListOfHGSize(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the name of the room in the selected houseGrid is Bathroom")
    void getNameOfLastRoomInHG() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);

        grid1.getRoomListInAGrid().addRoom(kitchen);
        grid1.getRoomListInAGrid().addRoom(bathroom);

        String expected = "Bathroom";
        String result = ctrl.getNameOfLastRoomInHG(0);
        assertEquals(expected, result);
    }

    @Test
    void showHouseGridListInString() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        String expected = "1 - Grid 1\n" +
                "2 - Grid 2\n";
        String result = ctrl.showHouseGridListInString();
        assertEquals(expected, result);
    }

    @Test
    void getHouseGridListSize() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getHouseGridListSize();
        assertEquals(expected, result);
    }

    @Test
    void getRoomsWithoutGridSize() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        RoomList houseRoomList = getHouseRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();

        grid1RoomList.addRoom(kitchen);
        int expected = 1;
        int result = ctrl.getRoomsWithoutGridSize(0);
        assertEquals(expected, result);
    }

    @Test
    void showRoomsWithoutHouseGrid() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        RoomList houseRoomList = getHouseRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();


        grid1RoomList.addRoom(kitchen);
        String expected = "1 - Bathroom\n";
        String result = ctrl.showRoomsWithoutHouseGrid(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that room Bathroom is attached to grid1")
    void attachRoomToHouseGrid() {

        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        RoomList houseRoomList = getHouseRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);


        grid1.getRoomListInAGrid().addRoom(kitchen);


        boolean result = ctrl.attachRoomToHouseGrid(1, 1);
        assertTrue(result);
        int expected = 1;
        int result1 = ctrl.getRoomListOfHGSize(0);

        assertEquals(expected, result1);
    }

    @Test
    @DisplayName("Ensure that showRoomsInHouseGrid() returns the room Bathroom")
    void showRoomsInHouseGrid() {


        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();
        RoomList houseRoomList = getHouseRoomList();
        HouseGridList hgList = getHGListInHouse();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");

        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);

        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);

        grid1.getRoomListInAGrid().addRoom(bathroom);

        String expected = "1 - Bathroom\n";
        String result = ctrl.showRoomsInHouseGrid(0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that room Kitchen is detached from the houseGrid")
    void detachRoomFromGrid() {


        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL();

        RoomList houseRoomList = getHouseRoomList();
        HouseGridList hgList = getHGListInHouse();

        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");

        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);

        Room kitchen = new Room("R01", "Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("R02", "Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);


        grid1.getRoomListInAGrid().addRoom(kitchen);
        grid1.getRoomListInAGrid().addRoom(bathroom);

        boolean result = ctrl.detachRoomFromGrid(0, 0);
        assertTrue(result);
        int expected = 1;
        int result1 = ctrl.getRoomListOfHGSize(0);
        assertEquals(expected, result1);
    }

}