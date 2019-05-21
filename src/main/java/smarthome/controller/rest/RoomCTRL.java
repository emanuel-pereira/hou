package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.model.Room;
import smarthome.services.RoomService;

import java.text.ParseException;
import java.util.List;

@RestController
public class RoomCTRL {

    private final RoomService roomRepoDDD;

    RoomCTRL() {
        roomRepoDDD = new RoomService();

    }

    @GetMapping("/rooms")
    public List<RoomDTO> findAll() {
        return this.roomRepoDDD.findAll();
    }

    @PostMapping("/rooms")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDetailDTO room ) {

        if (this.roomRepoDDD.save(room)) {
            return new ResponseEntity<>("Room was successfully created", HttpStatus.CREATED);
        } else return new ResponseEntity<>("Room wasn't created", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("rooms/{id}")
    public RoomDetailDTO findOne(@PathVariable String id) {
        return this.roomRepoDDD.findById(id);
    }

}
