package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.Room;

@Component
public interface RoomRepository extends CrudRepository<Room, String> {
}
