package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import smarthome.dto.HouseGridDTO;
import smarthome.model.HouseGrid;
import smarthome.repository.HouseGridRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class which process requests from Controllers to access Model objects and Repositories in order to Add or
 * modify data in the application;
 * This HouseGrid Service class will receive data from HouseGrid controller and others and access the HouseGrids
 * repository appliying changes to objects;
 * <p>
 * Using the Spring's @Service annotation, marks the class as a SpringBoot Component allowing for Spring Manager
 * ("Entity and Beans Manager") to know and easily instantiate classes such as this, using simple annotations such
 * as @Autowired.
 */
@Service
public class HouseGridService {

    private ModelMapper mapper = new ModelMapper();
    private HouseGridRepository houseGridRepository;


    public HouseGridService(HouseGridRepository houseGridRepository) {
        this.houseGridRepository = houseGridRepository;
    }

    /**
     * This method will access the Respective repository and look for all housegrids persisted the the database layer,
     * retrieving a list of simple DTOs to be passed on to the cli or WEB client for easy and safe manipulation;
     *
     * @return a list of housegrid dto objects
     */
    public List<HouseGridDTO> findAll() {
        List<HouseGridDTO> grids = new ArrayList<>();
        houseGridRepository.findAll().forEach(housegrid -> {
            HouseGridDTO temp = this.mapper.map(housegrid, HouseGridDTO.class);
            grids.add(temp);
        });
        return grids;
    }

    /**
     * This method will look for a HouseGrid with a specific id in the housegrids repository, in the persistence layer.
     *
     * @param id Housegrid id (Long)
     * @return HouseGrid DTO object
     * @throws NoSuchFieldException throws NoSuchFieldException
     */
    public HouseGridDTO findById(Long id) throws NoSuchFieldException {
        Optional<HouseGrid> optional = houseGridRepository.findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        HouseGrid temp = optional.get();
        return this.mapper.map(temp, HouseGridDTO.class);
    }

    /**
     * This method is similar to the findById() from the JPA repositories classes, the diference is that with this one
     * there is no need to verify if the return object is valid and instead we already receive an bolean affirmation
     * if the object with the recquired Id is persisted or not;
     *
     * @param id Housegrid id (Long)
     * @return bolean object
     */
    public boolean gridExists(Long id) {
        return houseGridRepository.existsById(id);
    }

    /**
     * This method receives a HouseGrid DTO object, which wil be first mapped into a model object validating it's
     * attributes prior to the model object (HouseGrid) is persisted.
     *
     * @param gridDTO HouseGrid DTO object created in the controller with user interaction
     * @return same HouseGrid DTO object
     * @throws InstantiationException throws InstantiationException
     */
    public HouseGridDTO addNewGrid(HouseGridDTO gridDTO) throws InstantiationException {
        HouseGrid grid = mapper.map(gridDTO, HouseGrid.class);
        if (grid.getDesignation() == null)
            throw new InstantiationException();
        HouseGrid houseGrid = houseGridRepository.save(grid);
        gridDTO.setId(houseGrid.getId());
        return gridDTO;
    }

    public long getSize() {
        return this.houseGridRepository.count();
    }
}
