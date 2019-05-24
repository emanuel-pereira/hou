package smarthome.model.readers;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import smarthome.model.*;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class XMLGeoArea implements FileReaderGeoArea {

    static final Logger log = Logger.getLogger(XMLGeoArea.class);


    public XMLGeoArea() {
        //this constructor is empty so we can use reflection to choose the correct reader
    }


    private static GeographicalArea importGeographicalArea(Node gaNode) throws ParseException {

        GeographicalArea geographicalArea = null;

        if (gaNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) gaNode;

            String description = getTagValue("description", element);
            String id = getTagValue("id", element);
            String type = getTagValue("type", element);

            String widthString = getTagValue("width", element);
            double width = Double.parseDouble(widthString);
            String lengthString = getTagValue("length", element);
            double length = Double.parseDouble(lengthString);
            OccupationArea occupationArea = new OccupationArea(length, width);

            Location location = importLocation(element.getElementsByTagName("location").item(0));

            geographicalArea = new GeographicalArea(id, description, type, occupationArea, location);
            addSensorListToGA(geographicalArea, element.getElementsByTagName("area_sensors").item(0));

        }
        return geographicalArea;

    }

    private static Location importLocation(Node node) {

        Element element = (Element) node;
        String latitudeString = getTagValue("latitude", element);
        double latitude = Double.parseDouble(latitudeString);
        String longitudeString = getTagValue("longitude", element);
        double longitude = Double.parseDouble(longitudeString);
        String altitudeString = getTagValue("altitude", element);
        double altitude = Double.parseDouble(altitudeString);

        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(altitude);
        return location;
    }

    public List<GeographicalArea> loadData(Path filePath) throws ParserConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
        //XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE
        //
        List<GeographicalArea> gaList = new ArrayList<>();

        try {

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File file = filePath.toFile();

            Document xmlDoc = dBuilder.parse(file);

            NodeList gaNodeList = xmlDoc.getElementsByTagName("geographical_area");

            for (int i = 0; i < gaNodeList.getLength(); i++) {
                gaList.add(importGeographicalArea(gaNodeList.item(i)));
            }

        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return gaList;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


    private static void addSensorListToGA(GeographicalArea geographicalArea, Node node) throws ParseException {

        Element areaSensors = (Element) node;
        NodeList sensors = areaSensors.getChildNodes();
        for (int i = 0; i < sensors.getLength(); i++) {
            if (sensors.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element sensor = (Element) sensors.item(i);
                String id = getTagValue("id", sensor);
                String name = getTagValue("name", sensor);

                String startDate = getTagValue("start_date", sensor);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(startDate);
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(date);

                String type = getTagValue("type", sensor);
                Name nameType = new Name(type);
                SensorType sensorType;

                //Repository call
                try {
                    sensorType= Repositories.getSensorTypeRepository().findByType(nameType);
                } catch (NullPointerException e) {
                    log.warn("Repository unreachable.");
                    sensorType= new SensorType(type);
                }

                String unit = getTagValue("units", sensor);
                Location location = importLocation(sensor.getElementsByTagName("location").item(0));

                ReadingList readingList = new ReadingList();
                ExternalSensor newSensor = new ExternalSensor(id, name, calendar, location, sensorType, unit, readingList);

                geographicalArea.getSensorListInGa().addSensor(newSensor);
            }
        }
    }

}
