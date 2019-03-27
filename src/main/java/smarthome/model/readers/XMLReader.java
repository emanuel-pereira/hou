package smarthome.model.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import smarthome.model.ReadingList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.w3c.dom.Node.ELEMENT_NODE;


public class XMLReader {


    private Document xmlDocument;
    private String id;
    private double value;
    private Calendar timestamp_date;
    private String unit;
    private ReadingList readingList;


    public void readXMLFile() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        xmlDocument = builder.parse("/Users/emanuel/Desktop/project_g6/resources/DataSet_sprint05_SensorData_alt01.xml");
        xmlDocument.getDocumentElement().normalize();
    }


    public List<String[]> extractData() {


        List<String[]> xmlReadings = new ArrayList<>();

        NodeList readingsList = xmlDocument.getElementsByTagName("reading");

        for (int read = 0; read < readingsList.getLength(); read++) {
            Node reading = readingsList.item(read);

            if (reading.getNodeType() == ELEMENT_NODE) {

                String[] tokens = new String[4];

                NodeList nodeListReading = xmlDocument.getElementsByTagName("reading");

                Element idElement = (Element) nodeListReading.item(0);
                NodeList readingID = idElement.getElementsByTagName("id");
                tokens[0] = readingID.toString();


                Element timeStampElement = (Element) nodeListReading.item(0);
                NodeList readingTimeStamp = timeStampElement.getElementsByTagName("timestamp_date");
                tokens[1] = readingTimeStamp.toString();

                Element valueElement = (Element) nodeListReading.item(0);
                NodeList readingValue = valueElement.getElementsByTagName("value");
                tokens[2] = readingValue.toString();


                Element unitElement = (Element) nodeListReading.item(0);
                NodeList readingUnit = unitElement.getElementsByTagName("unit");
                tokens[3] = readingUnit.toString();


                xmlReadings.add(tokens);

            }
        }
        return xmlReadings;
    }


    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    @XmlAttribute(name = "value")
    public void setValue(double value) {
        this.value = value;
    }

    public Calendar getTimestamp_date() {
        return timestamp_date;
    }

    @XmlAttribute(name = "timestamp_date")
    public void setTimestamp_date(Calendar timestamp_date) {
        this.timestamp_date = timestamp_date;
    }

    public String getUnit() {
        return unit;
    }

    @XmlAttribute(name = "unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "reading{" + "id" + id + "value" + value + "timestamp_date" + timestamp_date + "unit" + unit + '}';
    }

}



