package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US147AttachRoomToHouseGridCTRLTest {

    @Test
    @DisplayName("Ensure HouseGrid List is displayed as String")
    void showHouseGridListInString() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addHouseGrid(grid1);
        house.addHouseGrid(grid2);
        String expected = "1 - Nominal Power: 45.0\n2 - Nominal Power: 50.0\n";
        String result =  ctrl.showHouseGridListInString();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure HouseGrid List size is 2")
    void getHouseGridList() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addHouseGrid(grid1);
        house.addHouseGrid(grid2);
        int expectedResult=2;
        int result=ctrl.getHouseGridList().size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that list of Rooms without HouseGrid returns 2")
    void getListOfRoomsWithoutHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3,2 );
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        int expectedResult=2;
        int result=ctrl.getListOfRoomsWithoutHouseGrid().size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that list of Rooms without HouseGrid is displayed in string")
    void listOfRoomsWithoutHouseGridInString() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2.5);
        Room wc= new Room("Bathroom",0,4,3, 2.5);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2.5);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        String expected = "1 - Kitchen\n2 - Bathroom\n";
        String result =  ctrl.listOfRoomsWithoutHouseGridInString();
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that grid2 is set as kitchen HouseGrid")
    void attachRoomToHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 2);
        Room wc= new Room("Bathroom",0,4,3, 2);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        boolean expected = true;
        boolean result =  ctrl.attachRoomToHouseGrid(2,1);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure that grid2 is not set to any room as the list of rooms without HouseGrid is empty.")
    void attachRoomToHouseGridReturnsFalseAsAllRoomsAlreadyHaveGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25","4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US147AttachRoomToHouseGridCTRL ctrl = new US147AttachRoomToHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 2);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        boolean expected = false;
        boolean result =  ctrl.attachRoomToHouseGrid(2,1);
        assertEquals(expected,result);
    }
}