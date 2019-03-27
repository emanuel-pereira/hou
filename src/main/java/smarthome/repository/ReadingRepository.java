package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.Reading;


@Component
public interface ReadingRepository extends CrudRepository<Reading, Long> {

}
