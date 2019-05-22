package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.dto.TypeGADTO;
import smarthome.model.TypeGA;
import smarthome.repository.TypeGARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GaTypesService {

    @Autowired
    public TypeGARepository repository;

    ModelMapper modelMapper = new ModelMapper();

    public TypeGADTO findByObject(Object object) {
        TypeGA newtype = null;
        String pathvariable = object.toString();
        try {
            Optional<TypeGA> type = repository.findById(Long.parseLong(pathvariable));
            if (type.isPresent())
                newtype = type.get();
        } catch (Exception e) {
            newtype = repository.findByType(pathvariable);
        }
        return modelMapper.map(newtype, TypeGADTO.class);
    }


    public List<TypeGADTO> findAll() {
        List<TypeGADTO> types = new ArrayList<>();
        repository.findAll().forEach((TypeGA typeGA) ->
                types.add(modelMapper.map(typeGA, TypeGADTO.class)));
        return types;
    }

    public TypeGADTO newType(TypeGADTO newType) {
        TypeGA type = modelMapper.map(newType, TypeGA.class);
        TypeGA saved = repository.save(type);
        //TODO implement service that looks for equals, requests objects, and saves objects
        //TODO implement ResponseEntity<> for multiple return messages
        return modelMapper.map(saved, TypeGADTO.class);
    }
}
