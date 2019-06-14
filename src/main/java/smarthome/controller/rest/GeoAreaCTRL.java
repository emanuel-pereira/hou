package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.GeographicalAreaDTO;

import smarthome.model.GeographicalArea;
import smarthome.repository.Repositories;
import smarthome.services.GeoAreaService;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
public class GeoAreaCTRL {


    private final GeoAreaService geoService;

    ModelMapper modelMapper = new ModelMapper();


    @GetMapping("/geoareas")
    List<GeographicalAreaDTO> all() {
        List<GeographicalAreaDTO> areas = new ArrayList<>();

        Repositories.getGeoRepository().findAll()
                .forEach(geographicalArea ->
                        areas.add(modelMapper.map(geographicalArea, GeographicalAreaDTO.class)));
        return areas;
    }


    @GetMapping("/geoareas/{id}")
    GeographicalAreaDTO one(@PathVariable String id) {

        GeographicalArea area = Repositories.getGeoRepository().findById(id).get();

        return modelMapper.map(area, GeographicalAreaDTO.class);
    }

    @PostMapping("/geoareas")
    GeographicalArea newGeoArea(@RequestBody GeographicalAreaDTO geoAreaDto) {

       GeographicalArea area = modelMapper.map(geoAreaDto,GeographicalArea.class);

       return Repositories.getGeoRepository().save(area);
    }


    GeoAreaCTRL() {
        geoService = new GeoAreaService();
    }


    @GetMapping("/geoareas/getParent")
    public List<GeographicalAreaDTO> showListOfGAS() {
        return this.geoService.findAll();
    }

    @GetMapping("/geoareas/{findParentById}")
    public GeographicalArea findByID(@PathVariable String findParentById) {
        return geoService.findByIdGa(findParentById);
    }

    @PostMapping("/geoareas/setParent")
    public ResponseEntity<Object> setParentOfGAWebCTRL(@RequestParam String id, String idParent) {

        if (this.geoService.setParentGaWebCTRL(id, idParent)) {
            return new ResponseEntity<>("The Geographical area was set to another", HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>("The Geographical area wasn't set to another", HttpStatus.BAD_REQUEST);
    }

}
