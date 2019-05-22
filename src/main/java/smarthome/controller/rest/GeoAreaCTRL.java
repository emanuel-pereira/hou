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



@RestController


public class GeoAreaCTRL {


    private final GeoAreaService geoRepoDDD;

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

        GeographicalArea geoArea = geoAreaDto.fromDTO();

       return Repositories.getGeoRepository().save(geoArea);
    }


    GeoAreaCTRL() {
        geoRepoDDD = new GeoAreaService();
    }


    @GetMapping("/GeographicalAreaParent")
    public List<GeographicalAreaDTO> listOfGAS() {
        return this.geoRepoDDD.findAll();
    }

    @PostMapping("/GeographicalAreaParent")
    public ResponseEntity<Object> setParentOfGAWebCTRL(@RequestParam String id, String designation) {

        if (this.geoRepoDDD.setParentGaDTO(id, designation)) {
            return new ResponseEntity<>("The Geographical area was set to another", HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>("The Geographical area wasn't set to another", HttpStatus.UNAUTHORIZED);
    }

}
