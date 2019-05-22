package smarthome.controller.cli;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.*;
import smarthome.services.RoomService;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ListRoomsOfHouseCTRLTest {


/*
    @Test
    @DisplayName("Ensure that bedroom, kitchen and bathroom has been edited has indexed to a list ")
    @Before
    public void showListRoomInString() {

        RoomList roomList = getHouseRoomList();


        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL();

        Room bedroom = new Room("R01", "Quarto da Maria", 1, 2, 3, 2);
        Room kitchen = new Room("R02", "Cozinha", 1, 2, 3, 2);
        Room bathroom = new Room("R03", "Quarto de banho", 1, 2, 3, 2);

        roomList.addRoom(bedroom);
        roomList.addRoom(kitchen);
        roomList.addRoom(bathroom);

        String expectedResult = "1 - R01, Quarto da Maria\n" +
                                "2 - R02, Cozinha\n" +
                                "3 - R03, Quarto de banho\n";
        String result = ctrl108.showListRoomInString();

        assertEquals(expectedResult, result);
    }
*/


    @Test
    void roomListSize() {

        RoomService roomService = new RoomService();

        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL();

        RoomDetailDTO bedroom = roomService.createRoom("R01", "Bedroom", 1, 2, 3, 2);
        RoomDetailDTO kitchen = roomService.createRoom("R02", "Kitchen", 1, 2, 3, 2);

        roomService.save(bedroom);
        roomService.save(kitchen);

        long expected = 2;
        long result = ctrl108.roomListSize();

        assertEquals(expected, result);


    }
}

