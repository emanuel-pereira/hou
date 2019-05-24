package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import smarthome.dto.HouseGridDTO;
import smarthome.model.HouseGrid;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseGridService {

    ModelMapper mapper = new ModelMapper();

    public List<HouseGridDTO> findAll() {
        List<HouseGridDTO> grids = new ArrayList<>();
        Repositories.getGridsRepository().findAll().forEach(housegrid -> {
            HouseGridDTO temp = mapper.map(housegrid, HouseGridDTO.class);
            grids.add(temp);
        });
        return grids;
    }

    public HouseGrid findbyId(Long id) {
        return Repositories.getGridsRepository().findById(id).get();
    }

    public boolean gridExists(Long id) {
        return Repositories.getGridsRepository().existsById(id);
    }
}
