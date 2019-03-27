package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.SensorType;
import smarthome.model.ReadingList;
import smarthome.model.Location;
import smarthome.model.OccupationArea;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JSONGeoArea implements FileReaderGeoArea{
    private Path filePath;
    private JSONParser parser = new JSONParser();

    public JSONGeoArea (){
    }



    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public List<Sensor> getAllSensors() throws org.json.simple.parser.ParseException, java.text.ParseException, IOException  {
        List<Sensor> allSensors = new ArrayList<>();
        for(GeographicalArea ga: importData(filePath)){
            List<Sensor> sensorsInGa = ga.getSensorListInGA().getSensorList();
            allSensors.addAll(sensorsInGa);
        }
        return allSensors;
    }

    public List<GeographicalArea> importData(Path filePath) throws org.json.simple.parser.ParseException, java.text.ParseException, IOException {
        this.filePath = filePath;
        List<GeographicalArea> gaList = new ArrayList<>();
        JSONObject jsonGAs = (JSONObject) this.readFile().get("geographical_area_list");
        JSONArray jsonGAList = (JSONArray) jsonGAs.get("geographical_area");

        for(Object ga : jsonGAList) {
            JSONObject jsonGA = (JSONObject) ga;
            GeographicalArea geoArea = importGA(jsonGA);
            List<Sensor> gaSensorList = geoArea.getSensorListInGA().getSensorList();
            importSensorList(jsonGA,gaSensorList);
            gaList.add(geoArea);
        }
        return gaList;
    }

    private GeographicalArea importGA (JSONObject jsonGA) {
        String id = (String) jsonGA.get("id");
        String description = (String) jsonGA.get("description");
        String type = (String) jsonGA.get("type");
        Location location = importLocation(jsonGA);
        OccupationArea occupationArea = importOccupationArea(jsonGA);
        return new GeographicalArea(id,description,type,occupationArea,location);
    }

    private OccupationArea importOccupationArea (JSONObject jsonObject){
        double width = (double) jsonObject.get("width");
        double length = (double) jsonObject.get("length");

        return new OccupationArea(length,width);

    }

    private void importSensorList (JSONObject jsonGA,List<Sensor> sensorList) throws java.text.ParseException {
        JSONArray jsonSensorList = (JSONArray) jsonGA.get("area_sensor");
        for(Object areaSensor : jsonSensorList){
            JSONObject jsonSensor = (JSONObject) areaSensor;
            Sensor sensor = importSensor(jsonSensor);
            sensorList.add(sensor);
        }
    }

    private Sensor importSensor (JSONObject jsonSensor) throws java.text.ParseException{
        JSONObject sensor = (JSONObject) jsonSensor.get("sensor");
        String id = (String) sensor.get("id");
        String name = (String) sensor.get("name");

        String startDate = (String) sensor.get("start_date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(startDate);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String sensorType = (String) sensor.get("type");
        SensorType type = new SensorType(sensorType);

        String unit = (String) sensor.get("units");
        Location location = importLocation(jsonSensor);
        ReadingList readings = new ReadingList();
        return new Sensor(id,name,calendar,location,type,unit,readings);

    }

    private Location importLocation(JSONObject jsonObject){
        JSONObject jsonLocation = (JSONObject) jsonObject.get("location");
        double latitude = (double) jsonLocation.get("latitude");
        double longitude = (double) jsonLocation.get("longitude");
        double altitude = (long) jsonLocation.get("altitude");

        return new Location(latitude,longitude,altitude);
    }
}
