package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class EditRoomCTRLTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private EditRoomCTRL editRoomCTRL;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.editRoomCTRL = new EditRoomCTRL(roomRepository, gridRepository);
    }


    @Test
    @DisplayName("Find a room by the Id with success")
    void findById() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        RoomDetailDTO expected = new RoomDetailDTO("B108", "Classroom", 2, 2, 3, 1.5);
        RoomDetailDTO result = editRoomCTRL.findById("B108");
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set the description of a room")
    void setDescription() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        String description = "Garden";
        editRoomCTRL.setDescription("B108", "Garden");
        assertEquals(description, room.getDesignation());
    }

    @Test
    @DisplayName("Set the floor of a room")
    void setFloor() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        int floor = 4;
        editRoomCTRL.setFloor("B108",4);
        assertEquals(floor,room.getFloor());
    }

    @Test
    @DisplayName("Set the length of a room")
    void setLength() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        double length = 2.5;
        editRoomCTRL.setLength("B108",2.5);
        assertEquals(length,room.getArea().getLength());
    }

    @Test
    @DisplayName("Set the width of a room")
    void setWidth() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        double width = 3;
        editRoomCTRL.setWidth("B108",3);
        assertEquals(width,room.getArea().getWidth());
    }

    @Test
    @DisplayName("Set the height of a room")
    void setHeight() throws NoSuchFieldException {
        Room room = new Room("B108", "Classroom", 2, 2, 3, 1.5);

        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));

        double height = 1;
        editRoomCTRL.setHeight("B108",1);
        assertEquals(height,room.getHeight());
    }




}
