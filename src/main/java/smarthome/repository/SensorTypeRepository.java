package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;
@Repository
public interface SensorTypeRepository extends CrudRepository<SensorType, Long> {
    boolean existsByType(Name type);
    SensorType findByType(Name type);

    //-------------------//--------------------------------------//
    /*The methods of type delete, put and post have been overridden so they don't are
    exposed by Spring Data REST which will throw HTTP.Status 405 method not allowed
    whenever called on HTTP requests */


    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(SensorType entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends SensorType> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    <S extends SensorType> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends SensorType> Iterable<S> saveAll(Iterable<S> entities);
}
