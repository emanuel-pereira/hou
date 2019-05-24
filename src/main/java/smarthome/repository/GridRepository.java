package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.HouseGrid;

@Repository
public interface GridRepository extends CrudRepository<HouseGrid, Long> {

    /**
     * This method retrieves a house grid based on its name
     * @param name designation to compare
     * @return housegrid object that matches the queried designation
     */
    HouseGrid findHouseGridByDesignation(String name);
}
