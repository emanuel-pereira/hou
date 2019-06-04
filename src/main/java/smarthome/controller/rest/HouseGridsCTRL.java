package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.HouseGridDTO;
import smarthome.dto.HouseGridDTOsimple;
import smarthome.dto.RoomDetailDTO;
import smarthome.services.HouseGridService;
import smarthome.services.RoomService;

import java.util.List;

/**
 * This class is a web controller conceived to intercept HTTP REST requests.
 * <p>
 * {@link RestController} annotation is a combination of Spring’s @Controller and @ResponseBody annotations.
 * <p>
 * The @Controller annotation is used to define a controller and the @ResponseBody annotation is used to indicate that
 * the return value of a method should be used as the response body of the request.
 * <p>
 * {@link RequestMapping}("/housegrids") declares that the base url for all the possible API requests in this controller
 * will start with /housegrids.
 */
@RestController
@RequestMapping("/housegrids")
public class HouseGridsCTRL {

    private HouseGridService houseGridService;

    private RoomService roomService;

    public HouseGridsCTRL(HouseGridService houseGridService, RoomService roomService) {
        this.houseGridService = houseGridService;
        this.roomService = roomService;
    }

    /**
     * Mapping the POST request operation receiving a new HouseGrid object (as a simplified DTO object) to be
     * inserted in the system.
     * The operation receives an object and forwards it to the recquired Service class to process the new request
     * which in turn sends back an already converted HouseGrid DTO object.
     *
     * @param gridDTO simplefied HouseGrid DTO object
     * @return The operation returns a HTTP Status message and an object. A housegrid DTO if successfull, or
     * a blank object if unsuccessful.
     */
    @PostMapping
    public ResponseEntity<Object> addGrid(@RequestBody HouseGridDTO gridDTO) {
        HouseGridDTO dto;
        try {
            dto = houseGridService.addNewGrid(gridDTO);
        } catch (InstantiationException e) {
            return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * GET request Mapping operation to find all persisted Housegrids and retrieve them as a DTO list of objects;
     *
     * @return HTTP Status and a list of housegrid DTO's
     */
    @GetMapping
    public ResponseEntity<Object> all() {
        List<HouseGridDTO> dto = houseGridService.findAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * HTTP GET request mapping to retrieve an object with a specific id;
     *
     * @param id objects id
     * @return HTTP Status and dto object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findGrid(@PathVariable Long id) {
        HouseGridDTO dto;
        try {
            dto = houseGridService.findbyId(id);
        } catch (NoSuchFieldException e) {
            return new ResponseEntity<>("[]", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * HTTP POST request mapping to update a Room attaching/detaching a specified HouseGrid
     *
     * @param attachId    Room Id (String)
     * @param housegridID HouseGrid ID (Long)
     * @return HTTP Status and updated Room DTO object
     */
    @PostMapping(value = "/{housegridID}")
    public ResponseEntity<Object> updateRoom(@RequestParam(required = false) String attachId,
                                             @RequestParam(required = false) String detachId,
                                             @PathVariable Long housegridID) {
        // Check if the selected Grid exists
        if (!this.houseGridService.gridExists(housegridID)) {
            return new ResponseEntity<>("Grid not found.", HttpStatus.NOT_FOUND);
        }

        if (attachId != null) {
            // Check if the selected room exists
            if(!this.roomService.roomExists(attachId)){
                return new ResponseEntity<>("Room not found.", HttpStatus.NOT_FOUND);
            }
            RoomDetailDTO roomDTO;
            try {
                roomDTO = roomService.attachHouseGrid(attachId, housegridID);
            } catch (NoSuchFieldException e) {
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            } catch (IllegalAccessException e) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(roomDTO, HttpStatus.OK);
        }

        if (detachId != null) {
            // Check if the selected room exists
            if(!this.roomService.roomExists(detachId)){
                return new ResponseEntity<>("Room not found.", HttpStatus.NOT_FOUND);
            }
            try {
                roomService.detachHouseGrid(detachId);
            } catch (NoSuchFieldException e) {
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            } catch (IllegalAccessException e) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("Room detached", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }


    /**
     * HTTP GET request mapping to present the list of Rooms currently attached to a HouseGrid
     *
     * @param id HouseGrid Id (Long)
     * @return HTTP Status and HouseGrid DTO object
     */
    @GetMapping("/{id}/rooms")
    public ResponseEntity<Object> findRooms(@PathVariable Long id) {
        HouseGridDTOsimple grid;
        try {
            grid = roomService.findRoomsByHouseGrid(id);
        } catch (NoSuchFieldException e) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(grid, HttpStatus.OK);
    }
}
