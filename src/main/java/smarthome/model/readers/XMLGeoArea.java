package smarthome.model.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import smarthome.model.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;


public class XMLGeoArea implements FileReaderGeoArea {


    public XMLGeoArea() {
    }


    public List<GeographicalArea> loadData(Path filePath) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        List<GeographicalArea> gaList = new ArrayList<>();

        try {

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File file = filePath.toFile();

            Document xmlDoc = dBuilder.parse(file);

            xmlDoc.getDocumentElement().normalize();

            NodeList gaNodeList = xmlDoc.getElementsByTagName("geographical_area");

            for (int i = 0; i < gaNodeList.getLength(); i++) {
                gaList.add(importGeographicalArea(gaNodeList.item(i)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return gaList;
    }


    private GeographicalArea importGeographicalArea(Node gaNode) throws ParseException {

        GeographicalArea geographicalArea = null;

        if (gaNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) gaNode;

            String description = getTagValue("description", element);
            String id = getTagValue("id", element);
            String type = getTagValue("type", element);

            Double width = Double.parseDouble(getTagValue("width", element));
            Double length = Double.parseDouble(getTagValue("length", element));
            OccupationArea occupationArea = new OccupationArea(length, width);

            Location location = importLocation(element.getElementsByTagName("location").item(0));

            geographicalArea = new GeographicalArea(id, description, type, occupationArea, location);
            addSensorListToGA(geographicalArea, element.getElementsByTagName("area_sensors").item(0));

        }
        return geographicalArea;

    }


    private Location importLocation(Node node) {

        Element element = (Element) node;
        Double latitude = Double.parseDouble(getTagValue("latitude", element));
        Double longitude = Double.parseDouble(getTagValue("longitude", element));
        Double altitude = Double.parseDouble(getTagValue("altitude", element));

        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setAltitude(altitude);
        return location;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


    private void addSensorListToGA(GeographicalArea geographicalArea, Node node) throws ParseException {

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

                String sensorType = getTagValue("type", sensor);
                SensorType type = new SensorType(sensorType);

                String unit = getTagValue("units", sensor);
                Location location = importLocation(sensor.getElementsByTagName("location").item(0));

                ReadingList readingList = new ReadingList();
                Sensor newSensor = new Sensor(id, name, calendar, location, type, unit, readingList);


                geographicalArea.getSensorListInGA().addSensor(newSensor);

            }
        }
    }

}
