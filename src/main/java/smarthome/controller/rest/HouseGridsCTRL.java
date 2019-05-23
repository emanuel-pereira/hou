package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.GridDTOsimple;
import smarthome.model.HouseGrid;
import smarthome.services.HouseGridsService;
import smarthome.services.RoomService;

import java.util.List;

@RestController
//@RequestMapping("/housegrids")
public class HouseGridsCTRL {

    @Autowired
    public HouseGridsService gridService;

    @Autowired
    public RoomService roomService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<HouseGrid> all() {
        System.out.println("Listing grids");
        return gridService.findAll();
    }

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<Object> byId(@PathVariable Long id) {
        if (gridService.gridExists(id)) {
            System.out.println("Listing grid " + id);
            HouseGrid houseGrid = gridService.findbyId(id);
            return new ResponseEntity<>(modelMapper.map(houseGrid, GridDTO.class), HttpStatus.OK);
        }
        return new ResponseEntity<>("The requested house grid does not exist", HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/housegrids/{id}/rooms")
    public ResponseEntity<Object> findRooms(@PathVariable Long id) {
        if (gridService.gridExists(id)) {
            GridDTOsimple grid = roomService.findRoomsByHouseGrid(id);
            HouseGrid houseGrid = gridService.findbyId(id);
            grid.setName(houseGrid.getDesignation());
            if (!grid.getRooms().isEmpty())
                return new ResponseEntity<>(grid, HttpStatus.OK);
            return new ResponseEntity<>("The requested house grid does not have any rooms attached", HttpStatus.OK);
        }
        return new ResponseEntity<>("The requested house grid does not exist", HttpStatus.NOT_FOUND);
    }

}
