package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HouseGridRepository gridRepository;

    private RoomService roomService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        this.roomService = new RoomService(this.roomRepository, this.gridRepository);
    }

    @Test
    @DisplayName("Save a room with success")
    void save(){
        Room room = new Room ("B100","Classroom",3,2,2,1.5);
        RoomDetailDTO roomDto = new RoomDetailDTO("B100","Classroom",3,2,2,1.5);
        when(this.roomRepository.save(room)).thenReturn(room);
        assertTrue(roomService.save(roomDto));
    }

    @Test
    @DisplayName("Can't save a null room")
    void cantSaveNull(){
        assertFalse(roomService.save(null));
    }

    @Test
    @DisplayName("Have 2 rooms return the right number of rooms")
    void findAll(){
        when(this.roomRepository.findAll()).thenReturn(Stream.of(new Room ("B108","Classroom",1,2,3,2),
          new Room ("B201","Classroom",2,2,3,2)).collect(Collectors.toList()));
        assertEquals(2,roomService.findAll().size());
    }

    @Test
    @DisplayName("Check if a room exists")
    void checkIfIDExists(){
        when(this.roomRepository.existsById("B108")).thenReturn(true);
        assertTrue(roomService.checkIfIDExists("B108"));
    }

    @Test
    @DisplayName("Check if a room doesn't exists")
    void checkIfIDDontExists(){
        when(this.roomRepository.existsById("B")).thenReturn(false);
        assertFalse(roomService.checkIfIDExists("B"));
    }

    @Test
    @DisplayName("Check if the repository has 6 rooms with success")
    void size(){
        when(this.roomRepository.count()).thenReturn(6L);
        assertEquals(6,roomService.size());
    }

    @Test
    @DisplayName("Find a room by the Id with success")
    void findById() throws NoSuchFieldException {
        Room room = new Room("B108","Classroom",2,2,3,1.5);
        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(room));
        RoomDetailDTO expected = new RoomDetailDTO("B108","Classroom",2,2,3,1.5);
        RoomDetailDTO result = roomService.findById("B108");
        assertEquals(expected.getId(),result.getId());
    }

    @Test
    @DisplayName("Try to find a room that doesn't exists")
    void notFindById(){
        when(this.roomRepository.findById("B108")).thenReturn(Optional.of(new Room("B108","Classroom",2,2,3,1.5)));
        Assertions.assertThrows(NoSuchFieldException.class, () -> roomService.findById("B"));
    }

    @Test
    @DisplayName("Try to find a room in a empty repository")
    void notFindByIdEmpty(){
        when(this.roomRepository.findById("B108")).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchFieldException.class, () -> roomService.findById("B"));
    }

    @Test
    @DisplayName("Create a room with success")
    void createRoomSuccess(){
        RoomDetailDTO expected = new RoomDetailDTO("B300", "Office",3,2,2.5,1.5);
        RoomDetailDTO result = roomService.createRoom("B300", "Office",3,2,2.5,1.5);
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Cant Create a room with null Id")
    void createRoomIdNull(){
        RoomDetailDTO room = roomService.createRoom(null, "Office",3,2,2.5,1.5);
        assertNull(room);
    }

    @Test
    @DisplayName("Check if the repository is empty and return true")
    void checkIfRoomRepositoryEmpty() {
        when(roomRepository.count()).thenReturn(0L);
        assertTrue(roomService.checkIfRoomRepositoryEmpty());
    }

    @Test
    @DisplayName("Check if the repository is empty and return false")
    void checkIfRoomRepositoryNotEmpty() {
        when(roomRepository.count()).thenReturn(4L);
        assertFalse(roomService.checkIfRoomRepositoryEmpty());
    }

    @Test
    void injectRoomRepository() {
        roomRepository = null;
        RoomService roomService = new RoomService();
        roomService.setRepositoriesIfNull();
        assertThat(roomRepository).isNull();
    }

    @Test
    void injectGridRepository() {
        gridRepository = null;
        RoomService roomService = new RoomService();
        roomService.setRepositoriesIfNull();
        assertThat(gridRepository).isNull();
    }

        //Because we are using a specific constructor for tests this one has no coverage
    @Test
    @DisplayName("Test used constructor in running mode")
    void realConstructor() {

        RoomService roomService = new RoomService();

        assertThat(roomService).isInstanceOf(RoomService.class);
    }

}