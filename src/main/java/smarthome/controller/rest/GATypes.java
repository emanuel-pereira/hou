package smarthome.controller.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import smarthome.dto.TypeGADTO;
import smarthome.model.TypeGA;
import smarthome.repository.TypeGARepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GATypes {

    @Autowired
    public TypeGARepository repository;

    @GetMapping("/gatypes")
    @ResponseStatus(HttpStatus.OK)
    public List<TypeGADTO> all() {
        List<TypeGADTO> types = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        repository.findAll().forEach((TypeGA typeGA) ->
            types.add(modelMapper.map(typeGA, TypeGADTO.class)));
        return types;
    }
}
