package smarthome.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.repository.LocationRepository;
import smarthome.repository.OccupationAreaRepository;
import smarthome.repository.SensorRepository;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ReadJSONFile {
    //Created an instance of JSONParser to read through JSON file
    private JSONParser parser = new JSONParser();
    private GAList gaList;
    private Path filePath;

    /**
     * public constructor of ReadJSONFile class requiring to input the JSON file path
     *
     * @param filePath directory where JSON file is located
     */
    public ReadJSONFile(Path filePath, GAList gaList) {

        this.filePath = filePath;
        this.gaList = gaList;
    }

    /**
     * FileReader to read JSON file and pass it to parser.
     *
     * @return JSONObject
     * @throws IOException
     * @throws ParseException
     */
    private JSONObject readFile() throws IOException, ParseException {
        FileReader fileReader = new FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    /**
     * Start reading the JSON objects one by one, based on their type i.e. JSONArray and JSONObject
     *
     * @throws java.text.ParseException
     * @throws ParseException
     * @throws IOException
     */
    public List<GeographicalAreaDTO> importGAs() throws java.text.ParseException, ParseException, IOException {
        List<GeographicalAreaDTO> gaListDTO = new ArrayList<>();
        //Start reading JSON objects based on their type(JSONArray, JSONObject)
        JSONObject jsonGAs = (JSONObject) this.readFile().get("geographical_area_list");
        JSONArray jsonGAList = (JSONArray) jsonGAs.get("geographical_area");
        for (Object ga : jsonGAList) {
            JSONObject jsonGA = (JSONObject) ga;
            GeographicalArea geographicalArea = createGeographicalArea(jsonGA);
            SensorList gaSensorList = geographicalArea.getSensorListInGA();
            addGASensors(jsonGA, gaSensorList);
            this.gaList.addGA(geographicalArea);
            //todo repository.save(geographicalArea);
            GeographicalAreaDTO gaDTO = geographicalArea.toDTO();
            gaListDTO.add(gaDTO);
        }
        return gaListDTO;
    }

    public List<GeographicalAreaDTO> importGAs(OccupationAreaRepository occupationRep, LocationRepository locationRep, SensorRepository sensorRep) throws java.text.ParseException, ParseException, IOException {
        List<GeographicalAreaDTO> gaListDTO = new ArrayList<>();
        //Start reading JSON objects based on their type(JSONArray, JSONObject)
        JSONObject jsonGAs = (JSONObject) this.readFile().get("geographical_area_list");
        JSONArray jsonGAList = (JSONArray) jsonGAs.get("geographical_area");
        for (Object ga : jsonGAList) {
            JSONObject jsonGA = (JSONObject) ga;
            GeographicalArea geographicalArea = createGeographicalArea(jsonGA);
            SensorList gaSensorList = geographicalArea.getSensorListInGA();
            addGASensors(jsonGA, gaSensorList, locationRep);
            for (Sensor sensor : gaSensorList.getSensorList()) {
                sensorRep.save(sensor);
            }
            this.gaList.addGA(geographicalArea);
            occupationRep.save(geographicalArea.getOccupation());
            locationRep.save(geographicalArea.getLocation());
            //todo repository.save(geographicalArea);
            GeographicalAreaDTO gaDTO = geographicalArea.toDTO();
            gaListDTO.add(gaDTO);
        }
        return gaListDTO;
    }

    /**
     * Method that returns a Geographical Area object after parsing all its attributes from a JSON file
     *
     * @param jsonGA JSONObject with key "geographical_area"
     * @return an instance of a Geographical Area
     */
    private GeographicalArea createGeographicalArea(JSONObject jsonGA) {
        String id = (String) jsonGA.get("id");
        String description = (String) jsonGA.get("description");
        String type = (String) jsonGA.get("type");
        double width = (double) jsonGA.get("width");
        double length = (double) jsonGA.get("length");
        Location location = getLocation(jsonGA);
        OccupationArea occupationArea = new OccupationArea(length, width);
        return new GeographicalArea(id, description, type, occupationArea, location);
    }

    /**
     * Method that iterates all area_sensors of the JSONObject inputted as parameter
     * and adds them to the sensorListInGA.
     *
     * @param jsonGA         JSONObject that contains an array of area_sensors JSONObjects
     * @param sensorListInGA JSON area_sensors will be parsed to Sensor objects and then added to the sensorListInGA
     * @throws java.text.ParseException
     */
    private void addGASensors(JSONObject jsonGA, SensorList sensorListInGA, LocationRepository locationRepository) throws java.text.ParseException {
        JSONArray jsonAreaSensorList = (JSONArray) jsonGA.get("area_sensor");
        for (Object areaSensor : jsonAreaSensorList) {
            JSONObject jsonSensor = (JSONObject) areaSensor;
            Sensor s = createSensor(jsonSensor, locationRepository);
            sensorListInGA.addSensor(s);
        }
    }

    private void addGASensors(JSONObject jsonGA, SensorList sensorListInGA) throws java.text.ParseException {
        JSONArray jsonAreaSensorList = (JSONArray) jsonGA.get("area_sensor");
        for (Object areaSensor : jsonAreaSensorList) {
            JSONObject jsonSensor = (JSONObject) areaSensor;
            Sensor s = createSensor(jsonSensor);
            sensorListInGA.addSensor(s);
        }
    }


    /**
     * Method that reads a JSONObject with key "sensor" and parses it to a Sensor object
     *
     * @param jsonSensor JSONObject parameter with key "sensor"
     * @return a Sensor object
     * @throws java.text.ParseException
     */
    private Sensor createSensor(JSONObject jsonSensor, LocationRepository locationRepository) throws java.text.ParseException {
        JSONObject sensor = (JSONObject) jsonSensor.get("sensor");
        String id = (String) sensor.get("id");
        String name = (String) sensor.get("name");
        String startDate = (String) sensor.get("start_date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(startDate);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String sType = (String) sensor.get("type");
        SensorType sensorType = new SensorType(sType);
        String unit = (String) sensor.get("units");
        Location location = getLocation(jsonSensor);
        locationRepository.save(location);
        return new Sensor(id, name, cal, location, sensorType, unit, new ReadingList());
    }

    private Sensor createSensor(JSONObject jsonSensor) throws java.text.ParseException {
        JSONObject sensor = (JSONObject) jsonSensor.get("sensor");
        String id = (String) sensor.get("id");
        String name = (String) sensor.get("name");
        String startDate = (String) sensor.get("start_date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(startDate);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String sType = (String) sensor.get("type");
        SensorType sensorType = new SensorType(sType);
        String unit = (String) sensor.get("units");
        Location location = getLocation(jsonSensor);
        return new Sensor(id, name, cal, location, sensorType, unit, new ReadingList());
    }

    /**
     * This method is invoked to get GPS coordinates of a JSONObject containing the key location and parse them as doubles
     *
     * @param jsonObject JSONObject that contains another JSONObject with the key "Location", which includes an array with GPS coordinates.
     */
    private Location getLocation(JSONObject jsonObject) {
        JSONObject jsonLocation = (JSONObject) jsonObject.get("location");
        double latitude = (double) jsonLocation.get("latitude");
        double longitude = (double) jsonLocation.get("longitude");
        double altitude = (long) jsonLocation.get("altitude");
        return new Location(latitude, longitude, altitude);
    }
}


