package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.dto.*;
import smarthome.model.*;
import smarthome.services.DailySensorDataService;
import smarthome.services.ExternalSensorService;
import smarthome.services.GeoAreaService;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping(path = "/house")

public class DailySensorDataCTRL {

    private DailySensorDataService sensorDataService;
    private GeoAreaService geoAreaService;
    private ExternalSensorService externalSensorService;
    private String temp = "temperature";
    private String rain = "rainfall";
    private String teapotMsg = "Pink Fluffy Unicorns Dancing On Rainbows";

    DailySensorDataCTRL() {
        sensorDataService = new DailySensorDataService();
        geoAreaService = new GeoAreaService();
        externalSensorService = new ExternalSensorService();
    }

    boolean checkHouseGA() {
        return House.getHouseGA() != null;
    }

    boolean checkHouseLocation() {
        return House.getAddress().getGPSLocation() != null;
    }

    boolean checkGeoAreaTempSensors() {
        return House.getHouseGA().getSensorListInGa().checkIfRequiredSensorTypeExists(temp);
    }

    boolean checkGeoAreaRainSensors() {
        return House.getHouseGA().getSensorListInGa().checkIfRequiredSensorTypeExists(rain);
    }


    boolean checkIfTempSensorHasReadings(String startDate, String endDate) throws ParseException {
        return sensorDataService.getBestSensorReadings(startDate, endDate,temp).size() != 0;
    }

    boolean checkIfRainSensorHasReadings(){
        return sensorDataService.checkIfRainSensorHasReadings();
    }

    boolean checkIfRainSensorHasReadingsInDay(String startDate) throws ParseException {
        return sensorDataService.checkIfRainSensorHasReadingsInPeriod(startDate);
    }


    ResponseEntity<Object> checkPreConditions() {
//WORKS ON POSTMAN!
        if (this.geoAreaService.size() == 0 || this.externalSensorService.findAll().isEmpty()) {
            return new ResponseEntity<>("Please add Geographical Areas and/or Sensors first!", HttpStatus.PRECONDITION_FAILED);
        }

//WORKS ON POSTMAN!
        if (!checkHouseGA() || !checkHouseLocation()) {
            return new ResponseEntity<>("Please configure the House's Geographical Area/Location first", HttpStatus.PRECONDITION_FAILED);
        }

        else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }

    }

    ResponseEntity<Object> checkTemperaturePreConditions(){
//WORKS ON POSTMAN!
        if (checkHouseGA() && checkHouseLocation() && !checkGeoAreaTempSensors()) {
            return new ResponseEntity<>("Please add temperature sensors to the House's Geographical Area!", HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkRainfallPreConditions(){
//WORKS ON POSTMAN!
        if (checkHouseGA() && checkHouseLocation() && !checkGeoAreaRainSensors()) {
            return new ResponseEntity<>("Please add rainfall sensors to the House's Geographical Area!", HttpStatus.PRECONDITION_FAILED);
        }
//NOT WORKING
        if(checkGeoAreaRainSensors() && !checkIfRainSensorHasReadings()){
            return new ResponseEntity<>("Please add readings to the sensor!",HttpStatus.PRECONDITION_FAILED);
        }

        else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDatePreConditions(String startDate, String endDate) throws ParseException {
        GregorianCalendar falseDate = new GregorianCalendar(1000, Calendar.JANUARY, 1);
//WORKS ON POSTMAN!
        if (startDate == null || endDate == null){
            return new ResponseEntity<>("Please add date parameters!",HttpStatus.PRECONDITION_FAILED);
        }
//WORKS ON POSTMAN!
        if (this.sensorDataService.convertStringToCalendar(startDate).equals(falseDate) || this.sensorDataService.convertStringToCalendar(endDate).equals(falseDate)) {
            return new ResponseEntity<>("Please insert valid dates!(yyyyMMdd)", HttpStatus.PRECONDITION_FAILED);
        }

        else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDateTempPreConditions(String startDate,String endDate) throws ParseException {

        if(!checkDatePreConditions(startDate,endDate).equals(HttpStatus.I_AM_A_TEAPOT)){
         return checkDatePreConditions(startDate,endDate);
        }
        if (checkGeoAreaTempSensors() && !checkIfTempSensorHasReadings(startDate, endDate)) {
            return new ResponseEntity<>("The Sensor has no available Readings in the selected time interval!", HttpStatus.PRECONDITION_FAILED);
        }
        else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDateRainPreConditions(String startDate,String endDate) throws ParseException {

        if(!checkDatePreConditions(startDate,endDate).equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkDatePreConditions(startDate,endDate);
        }
        if (checkGeoAreaRainSensors() && checkIfRainSensorHasReadings() && !this.checkIfRainSensorHasReadingsInDay(startDate)){
            return new ResponseEntity<>("The Sensor has no available Readings in the selected time interval!", HttpStatus.PRECONDITION_FAILED);
        }
        else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/dailyMaxAmplitude")
    ResponseEntity<Object> getMaxDailyAmplitude(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if (!checkDateTempPreConditions(startDate, endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateTempPreConditions(startDate, endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if(!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkTemperaturePreConditions();
        }
        else {
            ExternalReadingDTO result = this.sensorDataService.displayAmplitude(startDate, endDate);
            result.getDateAndTime().add(Calendar.HOUR_OF_DAY,1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }

    @GetMapping("/dailyMaximum")
    ResponseEntity<Object> getDailyMaximum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {

        if (!checkDateTempPreConditions(startDate, endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateTempPreConditions(startDate, endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if(!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkTemperaturePreConditions();
        }

        else {
            ExternalReadingDTO result = this.sensorDataService.displayMaximum(startDate, endDate);
            result.getDateAndTime().add(Calendar.HOUR_OF_DAY,1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/dailyMinimum")
    ResponseEntity<Object> getDailyMinimum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if (!checkDateTempPreConditions(startDate,endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkDateTempPreConditions(startDate,endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if(!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkTemperaturePreConditions();
        }

        else {
            ExternalReadingDTO result = this.sensorDataService.displayMinimum(startDate, endDate);
            result.getDateAndTime().add(Calendar.HOUR_OF_DAY,1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/currentTemperature")
    ResponseEntity<Object> getCurrentTemperature(){
        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if(!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkTemperaturePreConditions();
        }

        else {
            ExternalReadingDTO result = this.sensorDataService.getCurrentTemperature();
            result.getDateAndTime().add(Calendar.HOUR_OF_DAY,1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/totalRainfall")
    ResponseEntity<Object> getTotalRainfall(@PathParam("day") String day) throws java.text.ParseException {
        if (!checkDateRainPreConditions(day,day).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkDateRainPreConditions(day,day);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if(!checkRainfallPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkRainfallPreConditions();
        }

        else {
            ExternalReadingDTO result = this.sensorDataService.getTotalRainfall(day);
            result.getDateAndTime().add(Calendar.HOUR_OF_DAY,1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
