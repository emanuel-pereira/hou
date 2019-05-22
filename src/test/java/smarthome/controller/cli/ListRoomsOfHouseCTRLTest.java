package smarthome.controller.cli;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.RoomDTO;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.House.getHouseRoomList;

public class ListRoomsOfHouseCTRLTest {

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
    public void roomListSize() {

        RoomList roomList = getHouseRoomList();

        ListRoomsOfHouseCTRL ctrl108 = new ListRoomsOfHouseCTRL();

        Room bedroom = new Room("R01", "Quarto da Maria", 1, 2, 3, 2);
        Room kitchen = new Room("R02", "Cozinha", 1, 2, 3, 2);

        roomList.addRoom(bedroom);
        roomList.addRoom(kitchen);

        long expected = 2;
        long result = ctrl108.roomListSize();

        assertEquals(expected, result);


    }
}

