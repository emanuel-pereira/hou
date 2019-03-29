package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.Reading;
import smarthome.model.Sensor;


@Component
public interface ReadingRepository extends CrudRepository<Reading, Long> {

    void deleteAllBySensor(Sensor sensor);

}
