package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.GeographicalArea;

import java.util.List;

@Repository
public interface GeoRepository extends CrudRepository<GeographicalArea, String> {

    List<GeographicalArea> findByDesignation(String designation);
}
