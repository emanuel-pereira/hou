package smarthome.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.RoomDetailDTO;

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

}