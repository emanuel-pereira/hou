
package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US145ListRoomsAttachedToGridCTRLTest {

    @Test
    void showHouseGridListInString() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address("Rua Júlio Dinis", "345", "3380-45", l1);
        TypeGA t1 = new TypeGA("city");
        GeographicalArea g1 = new GeographicalArea("Porto", t1,l1);

        House house = new House(a1,g1);
        US145ListRoomsAttachedToGridCTRL ctrl = new US145ListRoomsAttachedToGridCTRL(house);

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
        US145ListRoomsAttachedToGridCTRL ctrl = new US145ListRoomsAttachedToGridCTRL(house);
        HouseGrid grid1 = new HouseGrid(45);
        HouseGrid grid2 = new HouseGrid(50);
        house.addHouseGrid(grid1);
        house.addHouseGrid(grid2);
        int expectedResult=2;
        int result=ctrl.getHouseGridList().size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that list of Rooms with HouseGrid is displayed in string")
    void showListOfRoomsWithHouseGridInString() {
        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA t1 = new TypeGA("district");
        GeographicalArea g1 = new GeographicalArea("Gondomar", t1,l1);

        House house = new House(a1,g1);
        US145ListRoomsAttachedToGridCTRL ctrl = new US145ListRoomsAttachedToGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 3);
        Room wc= new Room("Bathroom",0,4,3, 3);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 3);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 3);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);
        String expected = "1 - Bedroom1\n2 - Bedroom2\n";
        String result =  ctrl.showListOfRoomsWithHouseGridInString(1);
        assertEquals(expected,result);
    }


    @DisplayName("Get list of rooms connected to grid2")
    @Test
    void getListRoomsWithHouseGrid() {

        Location l1 = new Location(41, 12.3, 110);
        Address a1 = new Address(" Rua dos Heróis e Mártires de Angola", "25", "4000-285", l1);
        TypeGA type1 = new TypeGA("City");
        GeographicalArea ga1 = new GeographicalArea("Porto", type1);
        House house = new House(a1, ga1);
        US145ListRoomsAttachedToGridCTRL ctrl = new US145ListRoomsAttachedToGridCTRL(house);

        HouseGrid grid= new HouseGrid(1500);
        HouseGrid grid2= new HouseGrid(2000);
        house.addHouseGrid(grid);
        house.addHouseGrid(grid2);

        Room kitchen= new Room("Kitchen",0,5,4, 1.5);
        Room wc= new Room("Bathroom",0,4,3, 1.5);
        Room bedroom1 = new Room("Bedroom1",1,4,5, 1.5);
        Room bedroom2 = new Room("Bedroom2",1,4,5, 1.5);
        house.addRoom(kitchen);
        house.addRoom(wc);
        house.addRoom(bedroom1);
        house.addRoom(bedroom2);

        bedroom1.setmHouseGrid(grid);
        bedroom2.setmHouseGrid(grid);
        kitchen.setmHouseGrid(grid2);
        wc.setmHouseGrid(grid2);

        List<Room> listExpected = new ArrayList<>();
        listExpected.add(kitchen);
        listExpected.add(wc);
        List<Room> result= ctrl.getListOfRoomsWithHouseGrid(2);

        assertEquals(listExpected,result);
    }
}
