package smarthome.controller.rest;

import javafx.beans.binding.ObjectExpression;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.TypeGADTO;
import smarthome.model.TypeGA;
import smarthome.repository.TypeGARepository;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gatypes")
public class GATypes {

    @Autowired
    public TypeGARepository repository;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/{object}")
    @ResponseStatus(HttpStatus.OK)
    public TypeGADTO byType(@PathVariable Object object) {
        TypeGA newtype;
        String pathvariable = object.toString();
        try {
            //TODO implement service call
            newtype = repository.findById(Long.parseLong(pathvariable)).get();
        } catch (Exception e) {
            e.printStackTrace();
            newtype = repository.findByType(pathvariable);
        }
        return modelMapper.map(newtype, TypeGADTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeGADTO> all() {
        List<TypeGADTO> types = new ArrayList<>();

        repository.findAll().forEach((TypeGA typeGA) ->
                types.add(modelMapper.map(typeGA, TypeGADTO.class)));
        return types;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //TODO implement method return ResponseEntity<TypeGADTO>
    public TypeGADTO add(@RequestBody TypeGADTO newType){
        System.out.println(newType.getType() + "POST");
        TypeGA type = modelMapper.map(newType, TypeGA.class);
        System.out.println(type.getType() + "MAPPED");
        TypeGA saved = repository.save(type);
        //TODO implement service that looks for equals, requests objects, and saves objects
        System.out.println(saved.getType() + "PERSISTED");
        //TODO implement ResponseEntity<> for multiple return messages
        return modelMapper.map(saved, TypeGADTO.class);
    }

}
