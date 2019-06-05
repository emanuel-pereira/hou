package smarthome.services;

import java.util.List;

/**
 * Generic Web Service interface used to ensure some consistency between services.
 * @param <T> the type of object returned by the web service to the controller, such as SensorDTO, RoomDTO, etc
 * @param <V> the type of id used, such as String or long
 */
public interface WebService<T,V> {

    List<T> findAllBy(V id);

    List<T> findAllBy();

    T findById(V id);

    T create(T object);

    void deleteById(V id);

    T replace(T newObject, V replacedObjectId);

}
