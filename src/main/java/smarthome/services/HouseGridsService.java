package smarthome.services;

import org.springframework.stereotype.Service;
import smarthome.model.HouseGrid;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseGridsService {


    public List<HouseGrid> findAll() {
        List<HouseGrid> grids = new ArrayList<>();
        Repositories.getGridsRepository().findAll().forEach(housegrid -> grids.add(housegrid));
        return grids;
    }

    public HouseGrid findbyId(Long id) {
        return Repositories.getGridsRepository().findById(id).get();
    }

    public boolean gridExists(Long id) {
        return Repositories.getGridsRepository().existsById(id);
    }
}
