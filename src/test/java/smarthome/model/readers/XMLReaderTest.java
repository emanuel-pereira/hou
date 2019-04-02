package smarthome.model.readers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XMLReaderTest {

    private Document xmlDocument;

    @Before
    public void readXMLFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        xmlDocument = builder.parse("resources/DataSet_sprint05_SD.xml");
    }


    @Test
    public void getReadingsXML() {


        NodeList readings = xmlDocument.getChildNodes();
        Element reading = (Element) readings.item(0);
        Assert.assertEquals("readings_list", reading.getNodeName());
    }

    @Test
    public void testReadID() {

        NodeList reading = xmlDocument.getElementsByTagName("reading");
        Element idElement = (Element) reading.item(0);
        NodeList readingID = idElement.getElementsByTagName("id");
        Element id = (Element) readingID.item(0);
        Assert.assertEquals("TT12346", id.getTextContent());
    }

    @Test
    public void testReadTimeStamp() {

        NodeList reading = xmlDocument.getElementsByTagName("reading");
        Element timeStampElement = (Element) reading.item(0);
        NodeList readingTimeStamp = timeStampElement.getElementsByTagName("timestamp_date");
        Element timeStampDate = (Element) readingTimeStamp.item(0);
        Assert.assertEquals("2018-12-30T02:00:00+00:00", timeStampDate.getTextContent());
    }

    @Test
    public void testReadValue() {

        NodeList reading = xmlDocument.getElementsByTagName("reading");
        Element valueElement = (Element) reading.item(0);
        NodeList readingValue = valueElement.getElementsByTagName("value");
        Element value = (Element) readingValue.item(0);
        Assert.assertEquals("57.2", value.getTextContent());
    }

    @Test
    public void testReadUnit() {

        NodeList reading = xmlDocument.getElementsByTagName("reading");
        Element unitElement = (Element) reading.item(0);
        NodeList readingUnit = unitElement.getElementsByTagName("unit");
        Element unit = (Element) readingUnit.item(0);
        Assert.assertEquals("F", unit.getTextContent());
    }

    @Test
    public void importDataTest() throws ParserConfigurationException,IOException,SAXException {
        XMLReading xmlReading = new XMLReading();
        Path path = Paths.get("resources/DataSet_sprint05_SD.xml");
        List<String[]> resultList = xmlReading.importData(path);
        int size = resultList.size();
        Assert.assertEquals(61, size);
    }



}