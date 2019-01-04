package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class US149DettachRoomFromHouseGridCTRLTest {

    @Test
    void showHouseGridListInString() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345","3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US149DettachRoomFromHouseGridCTRL ctrl = new US149DettachRoomFromHouseGridCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addHouseGrid(grid1);
        house.addHouseGrid(grid2);
        String expected = "1 - Nominal Power: 45.0\n2 - Nominal Power: 50.0\n";
        String result =  ctrl.showHouseGridListInString();
        assertEquals(expected,result);
    }

    @Test
    void getHouseGridList() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA type1 = new TypeGA("cidade");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US149DettachRoomFromHouseGridCTRL ctrl = new US149DettachRoomFromHouseGridCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addHouseGrid(grid1);
        house.addHouseGrid(grid2);
        int expectedResult=2;
        int result=ctrl.getHouseGridList().size();
        assertEquals(expectedResult,result);
    }
    @Test
    void getListOfRoomsWithHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua dos Heróis e Mártires de Angola", "25","4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US149DettachRoomFromHouseGridCTRL ctrl = new US149DettachRoomFromHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4,2);
        Room wc= new Room("Bathroom",0,4,3, 1.5);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 3);
        Room bedroom2 = new Room("Bedroom2",1,4,5,3);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);
        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);
        int expectedResult=2;
        int result=ctrl.getListOfRoomsWithHouseGrid(1).size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that grid2 is detached from kitchen")
    void detachRoomFromHouseGrid() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25","4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US149DettachRoomFromHouseGridCTRL ctrl = new US149DettachRoomFromHouseGridCTRL(house);

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
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);

        boolean expectedResult = true;
        boolean result= ctrl.detachRoomFromHouseGrid(2,1);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that method detachRoomFromHouseGrid returns false as grid2 does not have any rooms attached.")
    void detachRoomFromHouseGridReturnsFale() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US149DettachRoomFromHouseGridCTRL ctrl = new US149DettachRoomFromHouseGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room bedroom1 = new Room("Bedroom1",1,4,5, 2.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 2.5);

        house.addRoom(bedroom1);
        house.addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);


        boolean expectedResult = false;
        boolean result= ctrl.detachRoomFromHouseGrid(2,1);

        assertEquals(expectedResult,result);
    }
}