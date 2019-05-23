package smarthome.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.GridDTOsimple;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.repository.Repositories;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest

class RoomServiceTest {

    @Test
    @DisplayName("Tests if a room is created and save with success")
    void saveNewRoomSuccess() {
        //Arrange
        RoomService roomService = new RoomService();

        //Act
        RoomDetailDTO r1 = roomService.createRoom("R01", "BedRoom", 2, 3, 4, 1);
        roomService.save(r1);

        //Assert
        boolean result = roomService.checkIfIDExists("R01");
        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if a room is created and save twice")
    void saveRoomTwice() {
        //Arrange
        RoomService roomService = new RoomService();

        //Act
        RoomDetailDTO r1 = roomService.createRoom("R01", "BedRoom", 2, 3, 4, 1);
        roomService.save(r1);

        //Assert
        boolean result = roomService.save(r1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if a null room is created")
    void createNullRoom() {
        //Arrange
        RoomService roomService = new RoomService();

        //Act
        RoomDetailDTO r1 = roomService.createRoom(" ", "BedRoom", 2, 3, 4, 1);

        //Assert
        assertNull(r1);
    }

    @Test
    void findRoomsByHouseGrid() {
        //Arrange
        RoomService roomService = new RoomService();
        HouseGrid houseGrid = new HouseGrid("main grid");
        HouseGrid grid = Repositories.getGridsRepository().save(houseGrid);

        //Act
        Room newroom = new Room("B107", "Classroom", 2, 3, 4, 1);
        newroom.setHouseGrid(grid);
        Repositories.getRoomRepository().save(newroom);
        Room secondroom = new Room("B109", "Classroom", 2, 3, 4, 1);
        secondroom.setHouseGrid(grid);
        Repositories.getRoomRepository().save(secondroom);

        GridDTOsimple roomsByHouseGrid = roomService.findRoomsByHouseGrid(1L);
        assertEquals(2,roomsByHouseGrid.getRooms().size());

    }
}