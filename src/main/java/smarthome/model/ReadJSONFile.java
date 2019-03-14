package smarthome.model;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReadJSONFile {
    //Created an instance of JSONParser to read through JSON file
    private JSONParser parser = new JSONParser();
    private double latitude = Double.NaN;
    private double longitude = Double.NaN;
    private double altitude = Double.NaN;
    private GAList gaList;
    private String filePath;

    /**
     * public constructor of ReadJSONFile class requiring to input the JSON file path
     *
     * @param filePath directory where JSON file is located
     */
    public ReadJSONFile(String filePath, GAList gaList) {

        this.filePath = filePath;
        this.gaList= gaList;
    }

    private JSONObject readFile() throws IOException, ParseException {
        FileReader fileReader = new FileReader(filePath);
        return (JSONObject) this.parser.parse(fileReader);
    }



    public void readGAs() throws java.text.ParseException, ParseException, IOException {

        //Start reading JSON objects based on their type(JSONArray, JSONObject)
        JSONObject jsonGAs = (JSONObject) this.readFile().get("geographical_area_list");
        JSONArray jsonGAList = (JSONArray) jsonGAs.get("geographical_area");
        for (Object ga : jsonGAList) {
            JSONObject jsonGA = (JSONObject) ga;
            GeographicalArea geographicalArea = createGeographicalArea(jsonGA);
            SensorList gaSensorList = geographicalArea.getSensorListInGA();
            addGASensors(jsonGA, gaSensorList);
            this.gaList.addGA(geographicalArea);
        }
    }

    @NotNull

    /**
     * Method that returns a Geographical Area object after parsing all its attributes from a JSON file
     */
    private GeographicalArea createGeographicalArea(JSONObject jsonGA) {
        String id = (String) jsonGA.get("id");
        String description = (String) jsonGA.get("description");
        String type = (String) jsonGA.get("type");
        double width = (double) jsonGA.get("width");
        double length = (double) jsonGA.get("length");
        JSONObject location = (JSONObject) jsonGA.get("location");
        getCoordinates(location);
        OccupationArea occupationArea = new OccupationArea(length, width);
        Location gaLocation = new Location(latitude, longitude, altitude);
        return new GeographicalArea(id, description, type, occupationArea, gaLocation);
    }

    /**
     * Method that iterates all area_sensors of the JSONObject inputted as parameter
     * and adds them to the sensorListInGA.
     *
     * @param jsonGA JSONObject that contains an array of area_sensors JSONObjects
     * @param sensorListInGA JSON area_sensors will be parsed to Sensor objects and then added to the sensorListInGA
     * @throws java.text.ParseException
     */
    private void addGASensors(JSONObject jsonGA, SensorList sensorListInGA) throws java.text.ParseException {
        JSONArray jsonAreaSensorList = (JSONArray) jsonGA.get("area_sensor");
        for (Object areaSensor : jsonAreaSensorList) {
            JSONObject jsonSensor = (JSONObject) areaSensor;
            Sensor s = createSensor(jsonSensor);
            sensorListInGA.addSensor(s);
        }
    }

    private Sensor createSensor(JSONObject jsonSensor) throws java.text.ParseException {
        JSONObject sensor = (JSONObject) jsonSensor.get("sensor");
        String id = (String) sensor.get("id");
        String name = (String) sensor.get("name");
        GregorianCalendar cal = getStartDate(sensor);
        String sType = (String) sensor.get("type");
        SensorType sensorType = new SensorType(sType);
        String unit = (String) sensor.get("units");
        JSONObject location = (JSONObject) jsonSensor.get("location");
        getCoordinates(location);
        Location loc = new Location(latitude, longitude, altitude);
        return new Sensor(id,name, cal, loc, sensorType, unit, new ReadingList());
    }

    /**
     * This method is invoked to get GPS coordinates of a JSONObject containing the key location and parse them as doubles
     *
     * @param location JSONObject that contains all GPS coordinates
     */
    private void getCoordinates(JSONObject location) {
        this.latitude = (double) location.get("latitude");
        this.longitude = (double) location.get("longitude");
        this.altitude = (long) location.get("altitude");
    }

    @NotNull
    /**
     * Method to get the start date of a JSONObject containing the key sensor
     */
    private GregorianCalendar getStartDate(JSONObject sensor) throws java.text.ParseException {
        String startDate = (String) sensor.get("start_date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(startDate);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }
}


