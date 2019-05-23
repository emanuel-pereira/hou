package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.dto.GridDTO;
import smarthome.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {

    List<Room> findAllByHouseGrid(Long id);
}
