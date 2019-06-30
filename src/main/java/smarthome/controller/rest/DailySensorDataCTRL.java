package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.dto.ReadingDTO;
import smarthome.model.House;
import smarthome.services.DailySensorDataService;
import smarthome.services.ExternalSensorService;
import smarthome.services.GeoAreaService;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping(path = "/house")
public class DailySensorDataCTRL {

    private DailySensorDataService sensorDataService;
    private GeoAreaService geoAreaService;
    private ExternalSensorService externalSensorService;
    private String temp = "temperature";
    private String rain = "rainfall";
    private String teapotMsg = "Pink Fluffy Unicorns Dancing On Rainbows";
    private Calendar errorC = new GregorianCalendar(1143,Calendar.JANUARY,1);
    private ReadingDTO errorReading = new ReadingDTO(0,errorC);

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
        return sensorDataService.getBestSensorReadings(startDate, endDate, temp).size() != 0;
    }

    boolean checkIfRainSensorHasReadings() {
        return sensorDataService.checkIfRainSensorHasReadings();
    }

    boolean checkIfRainSensorHasReadingsInDay(String startDate) throws ParseException {
        return sensorDataService.checkIfRainSensorHasReadingsInPeriod(startDate);
    }


    ResponseEntity<Object> checkPreConditions() {
//WORKS ON POSTMAN!
        if (this.geoAreaService.size() == 0 || this.externalSensorService.findAll().isEmpty()) {
            //no ga's or sensors
            errorReading.setReadingValue(1);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        }

//WORKS ON POSTMAN!
        if (!checkHouseGA() || !checkHouseLocation()) {
            //house ga and location undefined
            errorReading.setReadingValue(2);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }

    }

    ResponseEntity<Object> checkTemperaturePreConditions() {
//WORKS ON POSTMAN!
        if (checkHouseGA() && checkHouseLocation() && !checkGeoAreaTempSensors()) {
            //no temperature sensors in house ga
            errorReading.setReadingValue(3);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkRainfallPreConditions() {
//WORKS ON POSTMAN!
        if (checkHouseGA() && checkHouseLocation() && !checkGeoAreaRainSensors()) {
            //no rainfall sensors in house ga
            errorReading.setReadingValue(4);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        }
//NOT WORKING
        if (checkGeoAreaRainSensors() && !checkIfRainSensorHasReadings()) {
            //sensor has no readings
            errorReading.setReadingValue(5);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDatePreConditions(String startDate, String endDate) throws ParseException {
        GregorianCalendar falseDate = new GregorianCalendar(1000, Calendar.JANUARY, 1);
//WORKS ON POSTMAN!
        if (startDate == null || endDate == null) {
            //no date selected
            errorReading.setReadingValue(6);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        }
//WORKS ON POSTMAN!
        if (this.sensorDataService.convertStringToCalendar(startDate).equals(falseDate) || this.sensorDataService.convertStringToCalendar(endDate).equals(falseDate)) {
            //invalidDates
            errorReading.setReadingValue(7);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDateTempPreConditions(String startDate, String endDate) throws ParseException {

        if (!checkDatePreConditions(startDate, endDate).equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDatePreConditions(startDate, endDate);
        }
        if (checkGeoAreaTempSensors() && checkIfTempSensorHasReadings(startDate, endDate)) {
            //no available readings in period!
            ReadingDTO trial = new ReadingDTO(8,errorC);
            return new ResponseEntity<>("oops ", HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    ResponseEntity<Object> checkDateRainPreConditions(String startDate, String endDate) throws ParseException {

        if (!checkDatePreConditions(startDate, endDate).equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDatePreConditions(startDate, endDate);
        }
        if (checkGeoAreaRainSensors() && checkIfRainSensorHasReadings() && !this.checkIfRainSensorHasReadingsInDay(startDate)) {
            //no available readings in period
            errorReading.setReadingValue(8);
            return new ResponseEntity<>(errorReading, HttpStatus.PRECONDITION_FAILED);
        } else {
            return new ResponseEntity<>(teapotMsg, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/dailyMaxAmplitude")
    public ResponseEntity<Object> getMaxDailyAmplitude(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if (!checkDateTempPreConditions(startDate, endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateTempPreConditions(startDate, endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if (!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkTemperaturePreConditions();
        } else {
            ReadingDTO result = null;
            try {
                result = this.sensorDataService.displayAmplitude(startDate, endDate);
            } catch (IllegalAccessException e) {
                //selected interval does not have readings
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
            result.getReadingDateAndTime().add(Calendar.HOUR_OF_DAY, 1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }

    @GetMapping("/dailyMaximum")
    public ResponseEntity<Object> getDailyMaximum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {

        if (!checkDateTempPreConditions(startDate, endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateTempPreConditions(startDate, endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if (!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkTemperaturePreConditions();
        } else {
            ReadingDTO result = null;
            try {
                result = this.sensorDataService.displayMaximum(startDate, endDate);
            } catch (IllegalAccessException e) {
                //selected interval does not have readings
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
            result.getReadingDateAndTime().add(Calendar.HOUR_OF_DAY, 1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/dailyMinimum")
    public ResponseEntity<Object> getDailyMinimum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if (!checkDateTempPreConditions(startDate, endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateTempPreConditions(startDate, endDate);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if (!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkTemperaturePreConditions();
        } else {
            ReadingDTO result = null;
            try {
                result = this.sensorDataService.displayMinimum(startDate, endDate);
            } catch (IllegalAccessException e) {
                //selected interval does not have readings
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
            result.getReadingDateAndTime().add(Calendar.HOUR_OF_DAY, 1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/currentTemperature")
    public ResponseEntity<Object> getCurrentTemperature() {
        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if (!checkTemperaturePreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkTemperaturePreConditions();
        } else {
            ReadingDTO result = this.sensorDataService.getCurrentTemperature();
            result.getReadingDateAndTime().add(Calendar.HOUR_OF_DAY, 1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/totalRainfall")
    public ResponseEntity<Object> getTotalRainfall(@PathParam("day") String day) throws java.text.ParseException, IllegalAccessException{
        if (!checkDateRainPreConditions(day, day).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkDateRainPreConditions(day, day);
        }

        if (!checkPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkPreConditions();
        }

        if (!checkRainfallPreConditions().getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)) {
            return checkRainfallPreConditions();
        } else {
            ReadingDTO result = null;
            try {
                result = this.sensorDataService.getTotalRainfall(day);
            } catch (IllegalAccessException e) {
                //selected interval does not have readings
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
            result.getReadingDateAndTime().add(Calendar.HOUR_OF_DAY, 1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
