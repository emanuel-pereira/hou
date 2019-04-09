package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import smarthome.model.Reading;
import smarthome.model.Sensor;

import javax.transaction.Transactional;

@Transactional
public interface ReadingRepository extends CrudRepository<Reading, Long> {

    void deleteReadingsBySensor(Sensor sensor);

}
