package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.TypeGA;

@Repository
public interface TypeGARepository extends CrudRepository<TypeGA, Long> {

}
