package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public List<RoomDetailDTO> listOfRooms() {
        return this.roomRepoDDD.findAllDetail();
    }

    @PostMapping("/rooms")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDetailDTO room ) {

        if (this.roomRepoDDD.save(room)) {
            return new ResponseEntity<>("Room was successfully created", HttpStatus.CREATED);
        } else return new ResponseEntity<>("Room wasn't created", HttpStatus.UNAUTHORIZED);
    }

}
