package smarthome.model.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Calendar;

import static org.w3c.dom.Node.ELEMENT_NODE;


public class XMLReader {


    private Document xmlDocument;
    private String id;
    private double value;
    private Calendar timestamp_date;
    private String unit;


    public void readXMLFile() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        xmlDocument = builder.parse("/Users/emanuel/Desktop/project_g6/resources/DataSet_sprint05_SensorData_alt01.xml");
        xmlDocument.getDocumentElement().normalize();
        NodeList readingsList = xmlDocument.getElementsByTagName("reading");

        for (int read = 0; read < readingsList.getLength(); read++) {
            Node reading = readingsList.item(read);

            if (reading.getNodeType() == ELEMENT_NODE) {


            }
        }
        NodeList reading = xmlDocument.getElementsByTagName("reading");

        Element idElement = (Element) reading.item(0);
        NodeList readingID = idElement.getElementsByTagName("id");
        Element id = (Element) readingID.item(0);

        String idString = id.getTextContent();

        Element timeStampElement = (Element) reading.item(0);
        NodeList readingTimeStamp = timeStampElement.getElementsByTagName("timestamp_date");
        Element timeStampDate = (Element) readingTimeStamp.item(0);

        String timeStampDateString = timeStampDate.getTextContent();

        Element valueElement = (Element) reading.item(0);
        NodeList readingValue = valueElement.getElementsByTagName("value");

        Element value = (Element) readingValue.item(0);

        String valueString = value.getTextContent();

        Element unitElement = (Element) reading.item(0);
        NodeList readingUnit = unitElement.getElementsByTagName("unit");
        Element unit = (Element) readingUnit.item(0);

        String unitString = unit.getTextContent();


        //return new Reading(idString, valueString, timeStampDateString, unitString);
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



