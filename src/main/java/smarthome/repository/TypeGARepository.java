package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.TypeGA;

@Component
public interface TypeGARepository extends CrudRepository<TypeGA, String> {

    TypeGA findByTypeGeographicalArea(String type);

}
