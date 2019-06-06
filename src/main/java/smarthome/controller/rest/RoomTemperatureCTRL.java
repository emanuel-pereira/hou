package smarthome.controller.REST;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.InternalReadingDTO;
import smarthome.services.RoomService;
import smarthome.services.RoomTemperatureService;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping(path = "/rooms")

public class RoomTemperatureCTRL {

    private RoomTemperatureService roomTemperatureService;
    private RoomService roomService;
    private String temp = "temperature";

    RoomTemperatureCTRL() {
        roomTemperatureService = new RoomTemperatureService();
        roomService = new RoomService();
    }

    boolean checkIfRoomsExist() {
        return roomService.size() != 0;
    }

    boolean checkIfThisRoomExists(String roomId) {
        return roomService.checkIfIDExists(roomId);
    }

    boolean checkIfRoomHasSensors(String roomId) {
        return roomTemperatureService.getRoomSensors(roomId).size() != 0;
    }

    boolean checkIfRoomHasTemperatureSensors(String roomId) {
        return roomTemperatureService.getRoomSensors(roomId).checkIfRequiredSensorTypeExists(temp);
    }

    boolean checkIfSensorHasAnyReadings(String roomId){
       return roomTemperatureService.checkIfSensorHasReadings(roomId);
    }

    boolean checkIfSensorHasReadingsInDay(String roomId, String day) throws ParseException {
        return roomTemperatureService.checkIfSensorHasReadingsInDay(roomId, day);
    }

    ResponseEntity<Object> checkRoomPreConditions(String roomId) {
//WORKS ON POSTMAN!
        if (!checkIfRoomsExist()) {
            return new ResponseEntity<>("Please add rooms first!", HttpStatus.PRECONDITION_FAILED);
        }
//WORKS ON POSTMAN!
        if (checkIfRoomsExist() && !checkIfThisRoomExists(roomId)) {
            return new ResponseEntity<>("The room with the id " + roomId + " doesn't exist!", HttpStatus.PRECONDITION_FAILED);
        }
//WORKS ON POSTMAN!
        if (checkIfThisRoomExists(roomId) && !checkIfRoomHasSensors(roomId)) {
            return new ResponseEntity<>("Please add sensors to the room!", HttpStatus.PRECONDITION_FAILED);
        }

        if (checkIfRoomHasSensors(roomId) && !checkIfRoomHasTemperatureSensors(roomId)) {
            return new ResponseEntity<>("Please add temperature sensors to the room!", HttpStatus.PRECONDITION_FAILED);
        }
//WORKS ON POSTMAN!
        if(checkIfRoomHasTemperatureSensors(roomId) && !checkIfSensorHasAnyReadings(roomId)){
            return new ResponseEntity<>("Please add readings to the sensor!",HttpStatus.PRECONDITION_FAILED);
        }

        else {
            return new ResponseEntity<>("Pink Fluffy Unicorns Dancing On Rainbows",HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDayPreConditions(String id,String day) throws ParseException {
        GregorianCalendar falseDate = new GregorianCalendar(1000, Calendar.JANUARY, 1);

//WORKS ON POSTMAN!
        if(day == null){
            return new ResponseEntity<>("Please add date parameters!",HttpStatus.PRECONDITION_FAILED);
        }

//WORKS ON POSTMAN!
        if (this.roomTemperatureService.convertStringToCalendar(day).equals(falseDate)) {
            return new ResponseEntity<>("Please insert valid dates!(yyyyMMdd)", HttpStatus.PRECONDITION_FAILED);
        }
        if(checkRoomPreConditions(id).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT) && !checkIfSensorHasReadingsInDay(id,day)){
            return new ResponseEntity<>("Please add readings to the sensor on the given date!", HttpStatus.PRECONDITION_FAILED);
        }
        else {
            return new ResponseEntity<>("Pink Fluffy Unicorns Dancing On Rainbows",HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("{id}/maxTemperature")
    ResponseEntity<Object> getMaxTemperatureInDay(@PathVariable String id, @PathParam("day")String day)throws ParseException{
        if(!checkDayPreConditions(id,day).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkDayPreConditions(id,day);
        }

        if(!checkRoomPreConditions(id).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkRoomPreConditions(id);
        }

        else{
            InternalReadingDTO result = roomTemperatureService.getMaxTempInRoom(id,day);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
    }

    @GetMapping("{id}/currentTemperature")
    ResponseEntity<Object> geCurrentTemperature(@PathVariable String id){
        if(!checkRoomPreConditions(id).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkRoomPreConditions(id);
        }

        else{
            InternalReadingDTO result = roomTemperatureService.getCurrentTempInRoom(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
    }
}
