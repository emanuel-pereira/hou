package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.dto.TypeGADTO;
import smarthome.model.TypeGA;
import smarthome.repository.Repositories;
import smarthome.repository.TypeGARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GaTypesService {

    @Autowired
    public TypeGARepository repository;


    private ModelMapper modelMapper;

    public GaTypesService(TypeGARepository typeGARepository) {
        this.repository = typeGARepository;
        modelMapper= new ModelMapper();
    }

    private void setRepositories() {
        if (this.repository == null)
            this.repository = Repositories.getTypeGARepository();
    }

    public boolean existsByType(String type) {
        setRepositories();
        return repository.existsByType(type);
    }

    public TypeGA findByType(String type){
        setRepositories();
        return repository.findByType(type);
    }
}
