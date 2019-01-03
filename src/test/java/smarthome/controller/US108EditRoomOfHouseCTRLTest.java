package smarthome.controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US108EditRoomOfHouseCTRLTest {

    @Test
    @DisplayName("Ensure that bedroom has been edited has Quarto da Maria")
    public void setRoomListBedroom() {


        Room bedroom = new Room("bedroom", 2, 2, 3);
        List<Room> roomList = new ArrayList<>();
        roomList.add(bedroom);

        US108EditRoomOfHouseCTRL ctrl108 = new US108EditRoomOfHouseCTRL(roomList);

        ctrl108.setRoom(0, "Quarto da Maria", 3, 3, 3);

        String expectedResultName = "Quarto da Maria";

        assertEquals(expectedResultName, bedroom.getName());

    }


    @Test
    @DisplayName("Ensure that kitchen has been edited has Cozinha")
    public void setRoomListKitchen() {

        Room kitchen = new Room("kitchen", 2, 2, 3);
        List<Room> roomList = new ArrayList<>();
        roomList.add(kitchen);

        US108EditRoomOfHouseCTRL ctrl108 = new US108EditRoomOfHouseCTRL(roomList);

        ctrl108.setRoom(0, "Cozinha", 3, 3, 3);

        String expectedResultName = "Cozinha";

        assertEquals(expectedResultName, kitchen.getName());

    }

    @Test
    @DisplayName("Ensure that bathroom has been edited has Quarto de Banho")
    public void setRoomListBathroom() {

        Room bathroom = new Room("bedroom", 2, 2, 3);
        List<Room> roomList = new ArrayList<>();
        roomList.add(bathroom);

        US108EditRoomOfHouseCTRL ctrl108 = new US108EditRoomOfHouseCTRL(roomList);

        ctrl108.setRoom(0, "Quarto de banho", 3, 3, 3);

        String expectedResultName = "Quarto de banho";

        assertEquals(expectedResultName, bathroom.getName());
    }


    @Test
    @DisplayName("Ensure that bedroom, kitchen and bathroom has been edited has indexed to a list ")
    public void showListRoomInString() {


        List<Room> roomList = new ArrayList<>();

        US108EditRoomOfHouseCTRL ctrl108 = new US108EditRoomOfHouseCTRL(roomList);

        Room bedroom = new Room("Quarto da Maria",1,2,3);
        Room kitchen = new Room("Cozinha",1,2,3);
        Room bathroom = new Room("Quarto de banho",1,2,3);

        roomList.add(0,bedroom);
        roomList.add(1,kitchen);
        roomList.add(2,bathroom);

        String expectedResult = "1 - Quarto da Maria\n2 - Cozinha\n3 - Quarto de banho\n";
        String result =  ctrl108.showListRoomInString();

        assertEquals(expectedResult, result);

    }
}

