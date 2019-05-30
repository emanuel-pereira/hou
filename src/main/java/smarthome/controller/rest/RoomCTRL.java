package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.RoomDetailDTO;
import smarthome.services.RoomService;

@RestController
public class RoomCTRL {

    private final RoomService roomRepoDDD;

    RoomCTRL() {
        roomRepoDDD = new RoomService();

    }

    /**
     * Receives HTTP Get request and shows all rooms
     * @return ResponseEntity that represents the whole HTTP response with a List<RoomDTO> (the basic information of the Room)
     */
    @GetMapping("/rooms")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(this.roomRepoDDD.findAll(),HttpStatus.OK);
    }

    /**
     * Receives HTTP Post request and creates and saves a room in the repository
     * @param room RoomDetailDTO with the elements necessary to create a Room
     * @return ResponseEntity that represents the whole HTTP response
     */
    @PostMapping("/rooms")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDetailDTO room ) throws NoSuchFieldException {

        if (this.roomRepoDDD.save(room)) {
            return new ResponseEntity<>(this.roomRepoDDD.findById(room.getId()), HttpStatus.CREATED);
        } else return new ResponseEntity<>("Could not create the room",HttpStatus.UNAUTHORIZED);
    }

    /**
     * Receives HTTP Get request and shows the specific information of a room
     * @param id The Id of the Room
     * @return ResponseEntity that represents the whole HTTP response with a RoomDetailDTO (more information of the Room) if exists
     */
    @GetMapping("rooms/{id}")
    public ResponseEntity<Object>  findOne(@PathVariable String id) throws NoSuchFieldException {
        if (roomRepoDDD.roomExists(id)){
            RoomDetailDTO room = roomRepoDDD.findById(id);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id not found.", HttpStatus.NOT_FOUND);
    }

}

