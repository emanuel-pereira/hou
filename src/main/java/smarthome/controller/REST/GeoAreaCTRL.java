package smarthome.controller.REST;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.GeographicalAreaDTO;

import smarthome.model.GeographicalArea;
import smarthome.repository.Repositories;


import java.util.ArrayList;
import java.util.List;



@RestController


public class GeoAreaCTRL {

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/geoareas")
    GeographicalArea newGeoArea(@RequestBody GeographicalArea geoArea) {

        return Repositories.getGeoRepository().save(geoArea);
    }

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


}
