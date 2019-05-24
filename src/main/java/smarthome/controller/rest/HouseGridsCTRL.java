package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.HouseGridDTO;
import smarthome.dto.HouseGridDTOsimple;
import smarthome.model.HouseGrid;
import smarthome.services.HouseGridService;
import smarthome.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/housegrids")
public class HouseGridsCTRL {

    @Autowired
    public HouseGridService houseGridService;

    @Autowired
    public RoomService roomService;

    ModelMapper modelMapper = new ModelMapper();

    //TODO add POST mapping US130 to add new house grids


    @GetMapping
    public ResponseEntity<Object> all() {
        List<HouseGridDTO> dto = houseGridService.findAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findGrid(@PathVariable Long id) {
        if (houseGridService.gridExists(id)) {
            System.out.println("Listing grid " + id);
            HouseGrid houseGrid = houseGridService.findbyId(id);
            HouseGridDTO temp = modelMapper.map(houseGrid, HouseGridDTO.class);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<>("The requested house grid does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<Object> findRooms(@PathVariable Long id) {
        if (houseGridService.gridExists(id)) {
            HouseGridDTOsimple grid = roomService.findRoomsByHouseGrid(id);
            HouseGrid houseGrid = houseGridService.findbyId(id);
            grid.setName(houseGrid.getDesignation());
            if (!grid.getRooms().isEmpty())
                return new ResponseEntity<>(grid, HttpStatus.OK);
            return new ResponseEntity<>("The requested house grid does not have any rooms attached", HttpStatus.OK);
        }
        return new ResponseEntity<>("The requested house grid does not exist", HttpStatus.NOT_FOUND);
    }

}
