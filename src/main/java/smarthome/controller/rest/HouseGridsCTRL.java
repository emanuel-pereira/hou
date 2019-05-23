package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.GridDTO;
import smarthome.dto.GridDTOsimple;
import smarthome.model.HouseGrid;
import smarthome.repository.RoomRepository;
import smarthome.services.HouseGridsService;
import smarthome.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/housegrids")
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

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDTO byId(@PathVariable Long id) {
        System.out.println("Listing grid " + id);
        HouseGrid houseGrid = gridService.findbyId(id);
        return modelMapper.map(houseGrid, GridDTO.class);
    }

    @GetMapping(value = "/{id}/rooms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findBy(@PathVariable Long id) {
        System.out.println("Listing rooms attached to house grid " + id);
        if (gridService.gridExists(id)) {
            GridDTOsimple grid = roomService.findRoomsByHouseGrid(id);
            HouseGrid houseGrid = gridService.findbyId(id);
            grid.setName(houseGrid.getMeteredDesignation());
            return new ResponseEntity<>("List of rooms attached to house grid " + houseGrid.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The requested house grid does not exist", HttpStatus.NOT_FOUND);
    }

}
