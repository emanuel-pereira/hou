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

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002", "http://localhost:8080", "http://localhost:8082", "https://localhost:8443"}, maxAge = 3600)
@RestController
public class RoomCTRL {

    private final RoomService roomService;

    RoomCTRL(RoomService rService) {
        roomService = rService;
    }

    /**
     * Receives HTTP Get request and shows all rooms
     *
     * @return ResponseEntity that represents the whole HTTP response with a Resource (the basic information of the Room) with links and HttpStatus
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    @GetMapping("/rooms")
    public ResponseEntity<Resources<Resource<RoomDTO>>> findAll() throws NoSuchFieldException {
        List<Resource<RoomDTO>> rooms = new ArrayList<>();
        for (RoomDTO room : this.roomService.findAll()) {
            Resource<RoomDTO> roomDTOResource = new Resource<>(room,
                    linkTo(methodOn(RoomCTRL.class).findOne(room.getID())).withSelfRel());
            rooms.add(roomDTOResource);
        }
        Resources<Resource<RoomDTO>> resource = new Resources<>(rooms,
                linkTo(methodOn(RoomCTRL.class).findAll()).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    /**
     * Receives HTTP Post request and creates and saves that room in the repository
     *
     * @param room RoomDetailDTO with the elements necessary to create a Room
     * @return ResponseEntity that represents the whole HTTP response with a Resource (attributes of the Room) with the link to that resource and HttpStatus
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    @PostMapping("/rooms")
    public ResponseEntity<Resource<Object>> createRoom(@RequestBody RoomDetailDTO room) throws NoSuchFieldException {
        Resource<Object> resource;
        if (this.roomService.checkIfIDExists(room.getId())) {
            resource = new Resource<>("There is already a room with this id.");
            return new ResponseEntity<>(resource, HttpStatus.CONFLICT);
        }
        if (this.roomService.save(room)) {
            resource = new Resource<>(room,
                    linkTo(methodOn(RoomCTRL.class).findOne(room.getId())).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } else
            resource = new Resource<>("Could not create the room");
        return new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
    }

    /**
     * Receives HTTP Get request and shows the specific information of a room
     *
     * @param id The Id of the Room
     * @return ResponseEntity that represents the whole HTTP response with a Resource (attributes of the Room) with the link to that resource and HttpStatus
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Resource<Object>> findOne(@PathVariable String id) throws NoSuchFieldException {
        Resource<Object> resource;
        if (roomService.checkIfIDExists(id)) {
            RoomDetailDTO room = roomService.findById(id);
            resource = new Resource<>(room,
                    linkTo(methodOn(RoomCTRL.class).findOne(room.getId())).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        resource = new Resource<>("Room not found.");
        return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Resource<Object>> editRoom(@PathVariable String id, @RequestBody RoomDetailDTO room) throws NoSuchFieldException {
        Resource<Object> resource;
        if (this.roomService.checkIfIDExists(id)) {
            if (this.roomService.editRoom(id, room)) {
                RoomDetailDTO editRoom = this.roomService.findById(id);
                resource = new Resource<>(editRoom,
                        linkTo(methodOn(RoomCTRL.class).findOne(room.getId())).withSelfRel(),
                        linkTo(methodOn(RoomCTRL.class).findAll()).withRel("rooms"));
                return new ResponseEntity<>(resource, HttpStatus.OK);
            }
            resource = new Resource<>("Could not update the room.");
            return new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
        }
        resource = new Resource<>("Room not found.");
        return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
    }

}