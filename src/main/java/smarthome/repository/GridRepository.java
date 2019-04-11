package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.HouseGrid;

@Repository
public interface GridRepository extends CrudRepository<HouseGrid, Long> {
}
