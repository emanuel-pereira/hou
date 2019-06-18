package smarthome.controller.rest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.services.RoomService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002","http://localhost:8080", "http://localhost:8082", "https://localhost:8443"}, maxAge = 3600)
@RestController
public class RoomCTRL {

    private final RoomService roomService;

    RoomCTRL(RoomService rService) {
        roomService = rService;
    }

    /**
     * Receives HTTP Get request and shows all rooms
     *
     * @return ResponseEntity that represents the whole HTTP response with a List<RoomDTO> (the basic information of the Room)
     */
    @GetMapping("/rooms")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(this.roomService.findAll(), HttpStatus.OK);
    }

    /*@GetMapping("/rooms")
    public ResponseEntity<Resources<Resource<RoomDTO>>> alternative() throws NoSuchFieldException {
        List<Resource<RoomDTO>> rooms = new ArrayList<>();
        for (RoomDTO room : this.roomService.findAll()) {
            Resource<RoomDTO> roomDTOResource = new Resource<>(room,
                    linkTo(methodOn(RoomCTRL.class).findOne(room.getID())).withSelfRel(),
                    linkTo(methodOn(RoomCTRL.class).findAll()).withRel("rooms"));
            rooms.add(roomDTOResource);
        }
        Resources<Resource<RoomDTO>> resource = new Resources <>(rooms,
                linkTo(methodOn(RoomCTRL.class).findAll()).withSelfRel());

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }*/

    /**
     * Receives HTTP Post request and creates and saves a room in the repository
     *
     * @param room RoomDetailDTO with the elements necessary to create a Room
     * @return ResponseEntity that represents the whole HTTP response
     */
    @PostMapping("/rooms")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDetailDTO room) throws NoSuchFieldException {
        if (this.roomService.checkIfIDExists(room.getId())) {
            return new ResponseEntity<>("There is already a room with this id.", HttpStatus.CONFLICT);
        }
        if (this.roomService.save(room)) {
            return new ResponseEntity<>(this.roomService.findById(room.getId()), HttpStatus.CREATED);
        } else return new ResponseEntity<>("Could not create the room", HttpStatus.BAD_REQUEST);
    }

    /**
     * Receives HTTP Get request and shows the specific information of a room
     *
     * @param id The Id of the Room
     * @return ResponseEntity that represents the whole HTTP response with a RoomDetailDTO (more information of the Room) if exists
     */
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) throws NoSuchFieldException {
        if (roomService.checkIfIDExists(id)) {
            RoomDetailDTO room = roomService.findById(id);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        return new ResponseEntity<>("Room not found.", HttpStatus.NOT_FOUND);
    }

    /**
     * Receives HTTP Put request and shows the edited and unedited information of a room. It isn't possible to edit the Id.
     *
     * @param id   Id of the room
     * @param room RoomDetailDTO with the elements necessary to edit a room
     * @return ResponseEntity that represents the whole HTTP response with a RoomDetailDTO in case of success
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    @PutMapping("/rooms/{id}")
    public ResponseEntity<Object> editRoom(@PathVariable String id, @RequestBody RoomDetailDTO room) throws NoSuchFieldException {
        if (this.roomService.checkIfIDExists(id)) {
            if (this.roomService.editRoom(id, room)) {
                RoomDetailDTO editRoom = this.roomService.findById(id);
                return new ResponseEntity<>(editRoom, HttpStatus.OK);
            }
            return new ResponseEntity<>("Could not update the room", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Room not found.", HttpStatus.NOT_FOUND);
    }

}