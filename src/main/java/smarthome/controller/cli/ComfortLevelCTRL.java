package smarthome.controller.cli;

import smarthome.dto.RoomDTO;
import smarthome.services.ComfortLevelService;

import java.util.GregorianCalendar;
import java.util.List;

public class ComfortLevelCTRL {

    private ComfortLevelService comfortLevelService = new ComfortLevelService();

    // Basic validations for running US

    public boolean validateGeoAreaHasTemperatureSensorWithReadings() {
        return comfortLevelService.checkIfGeoAreaHasTemperatureSensor();
    }

    public boolean validateHouseHasRooms() {
        return comfortLevelService.checkIfHouseHasRooms();
    }

    public boolean validateRoomsHaveTemperatureSensors() {
        return comfortLevelService.validateRoomsHaveTemperatureSensors();
    }

    public boolean validateTemperatureSensorsHaveReadings() {
        return comfortLevelService.validateRoomsHaveTemperatureSensors();
    }

    // DTO Handling UI -> CTRL -> SRVC and vice versa

    // List of rooms to show to the user
    public List<RoomDTO> getRoomListDTO() {
        return comfortLevelService.getRoomListDTO();
    }

    // Room selected by the user is sent via DTO to the Service
    public void setRoomByDTO(RoomDTO roomDTO){
        comfortLevelService.setRoomByDTO(roomDTO);
    }

    public String calculateThermalComfort(RoomDTO selectedRoom, boolean maxOrMin, int category, GregorianCalendar startDate, GregorianCalendar endDate) {
        return comfortLevelService.calculateThermalComfort(selectedRoom, maxOrMin, category, startDate, endDate); // The result is a DTO List
    }


}
