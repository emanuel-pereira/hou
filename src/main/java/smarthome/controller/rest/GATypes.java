package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.TypeGADTO;
import smarthome.services.GaTypesService;

import java.util.List;

@RestController
@RequestMapping("/gatypes")
public class GATypes {

    @Autowired
    public GaTypesService service;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/{object}")
    @ResponseStatus(HttpStatus.OK)
    public TypeGADTO findBy(@PathVariable Object object) {
        return service.findByObject(object);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeGADTO> all() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //TODO implement method return ResponseEntity<TypeGADTO>
    public TypeGADTO add(@RequestBody TypeGADTO newType) {
        return service.newType(newType);
    }

}
