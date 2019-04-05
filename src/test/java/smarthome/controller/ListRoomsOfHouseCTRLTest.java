package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Room;
import smarthome.model.RoomList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListRoomsOfHouseCTRLTest {


    @Test
    @DisplayName("Ensure that bedroom, kitchen and bathroom has been edited has indexed to a list ")
    public void showListRoomInString() {


        RoomList roomList = new RoomList();

        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL(roomList);

        Room bedroom = new Room("R01", "Quarto da Maria", 1, 2, 3, 2);
        Room kitchen = new Room("R02","Cozinha", 1, 2, 3, 2);
        Room bathroom = new Room("R03","Quarto de banho", 1, 2, 3, 2);

        roomList.addRoom (bedroom);
        roomList.addRoom ( kitchen);
        roomList.addRoom ( bathroom);

        String expectedResult = "1 - Quarto da Maria\n2 - Cozinha\n3 - Quarto de banho\n";
        String result = ctrl108.showListRoomInString();

        assertEquals(expectedResult, result);
    }

    @Test
    public void roomListSize(){
        RoomList roomList = new RoomList();

        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL(roomList);

        Room bedroom = new Room("R01","Quarto da Maria", 1, 2, 3, 2);
        Room kitchen = new Room("R02","Cozinha", 1, 2, 3, 2);

        roomList.addRoom (bedroom);
        roomList.addRoom (kitchen);

        int expected = 2;
        int result = ctrl108.roomListSize ();

        assertEquals (expected,result);


    }
}

