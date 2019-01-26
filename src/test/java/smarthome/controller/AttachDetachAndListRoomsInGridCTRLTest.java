package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class AttachDetachAndListRoomsInGridCTRLTest {

    @Test
    void getRoomOfHGName() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        String expected = "Kitchen";
        String result = ctrl.getRoomOfHGName(1, 1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the houseGridName ")
    void getHGName() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        String expected = "Grid 1";
        String result = ctrl.getHGName(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the size of the roomList is 2 ")
    void getListOfRoomsSize() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList roomList = house.getRoomList();
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getListOfRoomsSize();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the size of the roomList in the selected houseGrid is 2 ")
    void getRoomListOfHGSize() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getRoomListOfHGSize(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that the name of the room in the selected houseGrid is Bathroom")
    void getNameOfLastRoomInHG() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        String expected = "Bathroom";
        String result = ctrl.getNameOfLastRoomInHG(1);
        assertEquals(expected, result);
    }

    @Test
    void showHouseGridListInString() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
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
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        int expected = 2;
        int result = ctrl.getHouseGridListSize();
        assertEquals(expected, result);
    }

    @Test
    void getRoomsWithoutGridSize() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        RoomList houseRoomList = house.getRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        int expected = 1;
        int result = ctrl.getRoomsWithoutGridSize(1);
        assertEquals(expected, result);
    }

    @Test
    void showRoomsWithoutHouseGrid() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        RoomList houseRoomList = house.getRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        String expected = "1 - Bathroom\n";
        String result = ctrl.showRoomsWithoutHouseGrid(1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that room Bathroom is attached to grid1")
    void attachRoomToHouseGrid() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        RoomList houseRoomList = house.getRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        boolean result = ctrl.attachRoomToHouseGrid(1,1);
        assertTrue(result);
        int expected=2;
        int result1 = ctrl.getRoomListOfHGSize(1);
        assertEquals(expected,result1);
    }

    @Test
    @DisplayName("Ensure that showRoomsInHouseGrid() returns the room Bathroom")
    void showRoomsInHouseGrid() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        RoomList houseRoomList = house.getRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(bathroom);
        String expected= "1 - Bathroom\n";
        String result = ctrl.showRoomsInHouseGrid(1);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that room Kitchen is detached from the houseGrid")
    void detachRoomFromGrid() {
        House house = new House();
        AttachDetachAndListRoomsInGridCTRL ctrl = new AttachDetachAndListRoomsInGridCTRL(house);
        RoomList houseRoomList = house.getRoomList();
        HouseGrid grid1 = new HouseGrid("Grid 1");
        HouseGrid grid2 = new HouseGrid("Grid 2");
        HouseGridList hgList = house.getHGListInHouse();
        hgList.addHouseGrid(grid1);
        hgList.addHouseGrid(grid2);
        Room kitchen = new Room("Kitchen", 0, 4, 3, 3);
        Room bathroom = new Room("Bathroom", 0, 2, 3, 3);
        houseRoomList.addRoom(kitchen);
        houseRoomList.addRoom(bathroom);
        RoomList grid1RoomList = grid1.getRoomListInAGrid();
        grid1RoomList.addRoom(kitchen);
        grid1RoomList.addRoom(bathroom);
        boolean result = ctrl.detachRoomFromGrid(1,1);
        assertTrue(result);
        int expected=1;
        int result1 = ctrl.getRoomListOfHGSize(1);
        assertEquals(expected,result1);
    }

}