package smarthome.services;

import org.apache.log4j.Logger;
import smarthome.dto.RoomDTO;
import smarthome.dto.RoomDetailDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.mapper.RoomMapper;
import smarthome.mapper.SensorTypeMapper;
import smarthome.model.*;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomService {

    RoomMapper mapper;


    /**
     * Constructor method that creates an instance of the RoomRepoDDD
     */
    public RoomService() {
        this.mapper = new RoomMapper();
    }

    /**
     * Method to create and add a room to the database if the room id doesn't exists.
     *
     * @param id Unique id of the room (string)
     * @param description Description of the room (string)
     * @param floor Number of the floor (integer)
     * @param length Length of the room (double) to calculate the area
     * @param width Width of the room (double) to calculate the area
     * @param height Height of the room (double)
     * @return True if created and added to the database
     */
    public RoomDetailDTO createRoom(String id, String description, int floor, double length, double width, double height) {
        NameValidations validation = new NameValidations();
        if (validation.idIsValid(id) && !this.checkIfIDExists(id)) {
               return new RoomDetailDTO (id,description,floor,length,width,height);
            }
        return null;
    }

    public boolean save (RoomDetailDTO roomDto){
        Room room = this.convertToObject(roomDto);
        if (room == null || this.checkIfIDExists(room.getId())) {
            return false;
        }
        Repositories.getRoomRepository().save(room);
        return true;

    }

    private Room convertToObject(RoomDetailDTO roomDTO) {
        return mapper.toObject(roomDTO);
    }

      /**
     * Checks if the room ID exists in the database, so the ID is not repeated
     *
     * @param id Room ID
     * @return True if the room ID exists
     */
    public boolean checkIfIDExists(String id) {
        return Repositories.getRoomRepository().existsById(id);
    }

    /**
     * @return the number of rooms persisted in the repository
     */
    public long size() {
        return Repositories.getRoomRepository().count();
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

    public List<RoomDetailDTO> findAllDetail() {
        Iterable<Room> rooms = Repositories.getRoomRepository().findAll();
        List<Room> roomList = new ArrayList<>();
        for (Room room : rooms) {
            roomList.add(room);
        }
        return Collections.unmodifiableList(mapper.toDetailDtoList(roomList));
    }

    public RoomDetailDTO findById(String id) {
        Room room = Repositories.getRoomRepository().findById(id).get();
        return mapper.toDetailDto(room);
    }

}
