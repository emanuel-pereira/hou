package smarthome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.dto.HouseGridDTOsimple;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.mapper.RoomMapper;
import smarthome.model.HouseGrid;
import smarthome.model.Room;
import smarthome.model.SensorList;
import smarthome.model.validations.NameValidations;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.Repositories;
import smarthome.repository.RoomRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomMapper mapper;

    @Autowired
    private HouseGridRepository houseGridRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Constructor method that creates an instance of the RoomRepoDDD
     */
    public RoomService() {
        this.mapper = new RoomMapper();
    }

    /**
     * Method to create a room using a RoomDTO.
     *
     * @param id          Unique id of the room (string)
     * @param description Description of the room (string)
     * @param floor       Number of the floor (integer)
     * @param length      Length of the room (double) to calculate the area
     * @param width       Width of the room (double) to calculate the area
     * @param height      Height of the room (double)
     * @return True if created and added to the database
     */
    public RoomDetailDTO createRoom(String id, String description, int floor, double length, double width, double height) {
        NameValidations validation = new NameValidations();
        if (validation.idIsValid(id)) {
            return new RoomDetailDTO(id, description, floor, length, width, height);
        }
        return null;
    }

    /**
     * Method that receives a RoomDTO, transform it in a Room, checks if the room id exists and then saves the room in the repository
     *
     * @param roomDto Detailed Room DTO (id, description, floor, length, width, height)
     * @return True if save
     */
    public boolean save(RoomDetailDTO roomDto) {
        Room room = this.mapper.toObject(roomDto);
        if (room == null /*|| this.checkIfIDExists(room.getId())*/) {
            return false;
        }
        Repositories.getRoomRepository().save(room);
        return true;

    }

    /**
     * Checks if the room ID exists in the database
     *
     * @param id Room ID
     * @return True if the room ID exists
     */
    public boolean checkIfIDExists(String id) {
        return Repositories.getRoomRepository().existsById(id);
    }

    /**
     * Checks the size of rooms in the Room Repository
     * @return the number of rooms persisted in the repository
     */
    public long size() {
        return Repositories.getRoomRepository().count();
    }

    /**
     * Checks if the Room Repository is empty
     * @return true if empty
     */
    public boolean checkIfRoomRepositoryEmpty() {
        return this.size() == 0;
    }

    /**
     * Method to return all rooms included in the repository as dto objects
     *
     * @return list of rooms as DTO with id and designation
     */
    public List<RoomDTO> findAll() {
        Iterable<Room> rooms = Repositories.getRoomRepository().findAll();
        List<Room> roomList = new ArrayList<>();
        for (Room room : rooms) {
            roomList.add(room);
        }
        return Collections.unmodifiableList(mapper.toDtoList(roomList));
    }

    /**
     * This method will look for a Room with a specific id in the Rooms repository, in the persistence layer.
     *
     * @param id Room Id (String)
     * @return RoomDetailDTO with more information of the Room
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public RoomDetailDTO findById(String id) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room temp = optional.get();
        return this.mapper.toDetailDto(temp);
    }

    /**
     * Checks if a room with the specific id exists
     *
     * @param id Room id
     * @return True if exists. False if not.
     */
    public boolean roomExists(String id) {
        return Repositories.getRoomRepository().existsById(id);
    }

    /**
     * Changes the floor of a room by getting that room from the repository. Because the return findById(id) is a Optional<>
     * there's the need to validate if is present. Then we get the specific room from the optional, set the floor and then we save the room
     *
     * @param id    Room id
     * @param floor New floor value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setFloor(String id, Integer floor) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room room = optional.get();
        room.setFloor(floor);
        Repositories.getRoomRepository().save(room);
    }

    /**
     * Changes the length of a room by getting that room from the repository. Because the return findById(id) is a Optional<>
     * there's the need to validate if is present. Then we get the specific room from the optional, set the length and then we save the room
     *
     * @param id     Room id
     * @param length New length value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setLength(String id, double length) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room room = optional.get();
        room.getArea().setLength(length);
        Repositories.getRoomRepository().save(room);
    }

    /**
     * Changes the width of a room by getting that room from the repository. Because the return findById(id) is a Optional<>
     * there's the need to validate if is present. Then we get the specific room from the optional, set the width and then we save the room
     *
     * @param id    Room id
     * @param width New width value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setWidth(String id, double width) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room room = optional.get();
        room.getArea().setWidth(width);
        Repositories.getRoomRepository().save(room);
    }

    /**
     * Using the Command-line interface to edit individually the length and the width the occupation area is not updated.
     * This method fixes it by doing a set to the Occupation Area
     *
     * @param id Room id
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void updateArea(String id) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room room = optional.get();
        double length = room.getArea().getLength();
        double width = room.getArea().getWidth();
        room.getArea().setOccupation(length * width);
        Repositories.getRoomRepository().save(room);
    }

    /**
     * Changes the height of a room by getting that room from the repository. Because the return findById(id) is a Optional<>
     * there's the need to validate if is present. Then we get the specific room from the optional, set the height and then we save the room
     *
     * @param id     Room id
     * @param height New height value
     * @throws NoSuchFieldException Signals that the class doesn't have a field of a specified name (because of the Optional<> return of the findById(id) method.
     */
    public void setHeight(String id, double height) throws NoSuchFieldException {
        Optional<Room> optional = Repositories.getRoomRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        Room room = optional.get();
        room.setHeight(height);
        Repositories.getRoomRepository().save(room);
    }

    /**
     * This method will ask findAllByHouseGridId() in the Rooms repository to look for all the persisted Rooms
     * matching with the attribute housegrid specified in the query.
     *
     * @param id HouseGrid Id
     * @return HouseGrid DTO object (a response specific DTO) containing it's name and a list of Room Id's
     */
    public HouseGridDTOsimple findRoomsByHouseGrid(Long id) throws NoSuchFieldException {
        Optional<HouseGrid> optional = this.houseGridRepository.findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        else {
            HouseGrid houseGrid = optional.get();

            HouseGridDTOsimple dto = new HouseGridDTOsimple();
            dto.setName(houseGrid.getDesignation());

            List<Room> rooms = this.roomRepository.findAllByHouseGridId(id);
            for (Room temp : rooms) {
                dto.addRoomId(temp.getId());
            }
            return dto;
        }
    }

    /**
     * This method will access the HouseGrid repository and retrieve the object will the Id matches the queried
     * and then will update the Room objectwith this new HouseGrid attribute.
     *
     * @param roomId Room Id
     * @param hgId   Housegrid Id
     * @return Room DTO object containing all it's info
     */
    public RoomDetailDTO attachHouseGrid(String roomId, Long hgId) throws
            NoSuchFieldException, IllegalAccessException {
        //check if a Room with that Id exists
        Optional<Room> roomOptional = this.roomRepository.findById(roomId);
        if (!roomOptional.isPresent())
            throw new NoSuchFieldException();//if the room with the ID does not exist
        Room room = roomOptional.get();

        //check if a HouseGrid with that Id exists
        Optional<HouseGrid> gridOptional = this.houseGridRepository.findById(hgId);
        if (!gridOptional.isPresent())
            throw new NoSuchFieldException();//if the housegrid with the ID does not exist
        HouseGrid houseGrid = gridOptional.get();

        //check if the room already is already attached to a HouseGrid
        if (room.getHouseGrid() != null)
            throw new IllegalAccessException();//room cant be attached without previously being detach

            //in case the room is not attached to any HouseGrid this will be set
        else {
            room.setHouseGrid(houseGrid);
            Room save = this.roomRepository.save(room);
            //TODO verify dto mapping
            return this.mapper.toDetailDto(save);
        }
    }


    public RoomDetailDTO detachHouseGrid(String attachId) throws NoSuchFieldException, IllegalAccessException {
        //check if a Room with that Id exists
        Optional<Room> roomOptional = this.roomRepository.findById(attachId);
        if (!roomOptional.isPresent())
            throw new NoSuchFieldException();
        Room room = roomOptional.get();

        //check if the room already is already attached to a HouseGrid
        if (room.getHouseGrid() == null)
            throw new IllegalAccessException();//room cant be detached without previously being attached to a HouseGrid

            //detach the Room from it's current attached HouseGrid
        else {
            room.detachHouseGrid();
            Room save = this.roomRepository.save(room);
            return this.mapper.toDetailDto(save);
        }
    }

    public void setHouseGrid(String roomId, Long hgId) {
        Room room = Repositories.getRoomRepository().findById(roomId).get();
        HouseGrid houseGrid = Repositories.getGridsRepository().findById(hgId).get();
        room.setHouseGrid(houseGrid);
        Repositories.getRoomRepository().save(room);
    }
}
