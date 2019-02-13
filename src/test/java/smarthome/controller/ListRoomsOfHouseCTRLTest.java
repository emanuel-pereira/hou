package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListRoomsOfHouseCTRLTest {


    @Test
    @DisplayName("Ensure that bedroom, kitchen and bathroom has been edited has indexed to a list ")
    public void showListRoomInString() {


        List<Room> roomList = new ArrayList<>();

        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL(roomList);

        Room bedroom = new Room("Quarto da Maria", 1, 2, 3, 2);
        Room kitchen = new Room("Cozinha", 1, 2, 3, 2);
        Room bathroom = new Room("Quarto de banho", 1, 2, 3, 2);

        roomList.add(0, bedroom);
        roomList.add(1, kitchen);
        roomList.add(2, bathroom);

        String expectedResult = "1 - Quarto da Maria\n2 - Cozinha\n3 - Quarto de banho\n";
        String result = ctrl108.showListRoomInString();

        assertEquals(expectedResult, result);

    }
}

