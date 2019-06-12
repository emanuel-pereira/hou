package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import smarthome.model.TypeGA;

@Repository
public interface TypeGARepository extends CrudRepository<TypeGA, Long> {

    TypeGA findByType(String type);


    //-------------------//--------------------------------------//
    //The methods of type delete, put and post have been overridden so they don't are
    // exposed by Spring Data REST which will throw HTTP.Status 405 method not allowed whenever called

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(TypeGA entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TypeGA> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    <S extends TypeGA> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends TypeGA> Iterable<S> saveAll(Iterable<S> entities);
    

}
