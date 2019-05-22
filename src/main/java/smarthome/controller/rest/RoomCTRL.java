package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.services.RoomService;

import java.text.ParseException;
import java.util.List;

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
    public ResponseEntity<Object> createRoom(@RequestBody RoomDetailDTO room ) {

        if (this.roomRepoDDD.save(room)) {
            return new ResponseEntity<>("Room was successfully created", HttpStatus.CREATED);
        } else return new ResponseEntity<>("Room wasn't created", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Receives HTTP Get request and shows the specific information of a room
     * @param id The Id of the Room
     * @return ResponseEntity that represents the whole HTTP response with a RoomDetailDTO (more information of the Room)
     */
    @GetMapping("rooms/{id}")
    public RoomDetailDTO findOne(@PathVariable String id) {
        return this.roomRepoDDD.findById(id);
    }

}
