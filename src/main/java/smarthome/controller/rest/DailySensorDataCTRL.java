package smarthome.controller.REST;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@RestController
@RequestMapping(path = "/house")

public class DailySensorDataCTRL {

    private DailySensorDataService sensorDataService;
    private GeoAreaService geoAreaService;
    private ExternalSensorService externalSensorService;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private String valueOf = " with a value of ";

    DailySensorDataCTRL() {
        sensorDataService = new DailySensorDataService();
        geoAreaService = new GeoAreaService();
        externalSensorService = new ExternalSensorService();
    }

    boolean checkHouseGA(){
        return House.getHouseGA()!=null;
    }

    boolean checkHouseLocation(){
        return House.getAddress().getGPSLocation()!=null;
    }

    boolean checkGeoAreaSensors(){
       return House.getHouseGA().getSensorListInGa().checkIfRequiredSensorTypeExists("temperature");
    }

    boolean checkIfSensorHasReadings(String startDate,String endDate) throws ParseException {
        return sensorDataService.getBestSensorReadings(startDate,endDate).size()!=0;
    }


    ResponseEntity<Object> checkPreConditions (String startDate, String endDate) throws ParseException{

        GregorianCalendar falseDate = new GregorianCalendar(0000,00,00);
        if(this.sensorDataService.convertStringToCalendar(startDate).equals(falseDate)|| this.sensorDataService.convertStringToCalendar(endDate).equals(falseDate)){
            return new ResponseEntity<>("Please insert valid dates!(yyyyMMdd)", HttpStatus.PRECONDITION_FAILED);
        }

        if(this.geoAreaService.size()==0 || this.externalSensorService.findAll().isEmpty() ){
            return new ResponseEntity<>("Please add Geographical Areas and/or Sensors first!", HttpStatus.PRECONDITION_FAILED);
        }

        if(!checkHouseGA() || !checkHouseLocation()){
            return new ResponseEntity<>("Please configure the House's Geographical Area/Location first",HttpStatus.PRECONDITION_FAILED);
        }


        if(checkHouseGA() & checkHouseLocation() & !checkGeoAreaSensors()){
            return new ResponseEntity<>("Please add temperature sensors to the House's Geographical Area!",HttpStatus.PRECONDITION_FAILED);
        }

        if (checkGeoAreaSensors() & !checkIfSensorHasReadings(startDate,endDate)){
            return new ResponseEntity<>("The Sensor has no available Readings in the selected time interval!",HttpStatus.PRECONDITION_FAILED);
        }

        else {
            return new ResponseEntity<>("Pink Fluffy Unicorns Dancing On Rainbows",HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @GetMapping("/dailyMaxAmplitude")
    ResponseEntity<Object> getMaxDailyAmplitude(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if(!checkPreConditions(startDate,endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkPreConditions(startDate,endDate);
        }

        else{
            ExternalReadingDTO result = this.sensorDataService.displayAmplitude(startDate,endDate);
            return new ResponseEntity<>("The highest daily amplitude happened in " +this.df.format(result.getDateAndTime().getTime())+
                    this.valueOf +result.getValue()+"ºC",HttpStatus.OK);
        }
    }

    @GetMapping("/dailyMaximum")
    ResponseEntity<Object> getDailyMaximum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
            if(!checkPreConditions(startDate,endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
                return checkPreConditions(startDate,endDate);
            }

            else{
                ExternalReadingDTO result = this.sensorDataService.displayMaximum(startDate,endDate);
                return new ResponseEntity<>("The first hottest day was " +this.df.format(result.getDateAndTime().getTime())+
                        this.valueOf+result.getValue() +"°C",HttpStatus.OK);
            }
    }

    @GetMapping("/dailyMinimum")
    ResponseEntity<Object> getDailyMinimum(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws java.text.ParseException {
        if(!checkPreConditions(startDate,endDate).getStatusCode().equals(HttpStatus.I_AM_A_TEAPOT)){
            return checkPreConditions(startDate,endDate);
        }

        else{
            ExternalReadingDTO result = this.sensorDataService.displayMinimum(startDate,endDate);
            return new ResponseEntity<>("The last coldest day was " +this.df.format(result.getDateAndTime().getTime())+
                    this.valueOf+result.getValue() +"°C",HttpStatus.OK);
        }
    }
}
