package smarthome.model.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class XMLReading implements FileReaderReadings {


    public XMLReading() {

        //This comment is used to avoid code smell
    }

    @Override
    public List<String[]> importData(Path filePath) throws ParserConfigurationException, IOException, SAXException {

        String maliciousSample = "xxe.xml";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
        List<String[]> readingList = new ArrayList<>();


        /*try(FileInputStream fileInputStream = new FileInputStream(maliciousSample)){

        }*/

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File file = filePath.toFile();

            Document xmlDoc = dBuilder.parse(file);


            NodeList readingNodeList = xmlDoc.getElementsByTagName("reading");

            for (int i = 0; i < readingNodeList.getLength(); i++) {
                readingList.add(importReading(readingNodeList.item(i)));
            }




        return readingList;

    }


    private static String[] importReading(Node readingNode){

        String[] data = new String[4];

        if (readingNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) readingNode;

            String id = getTagValue("id", element);
            data[0] = id;

            String timeStampDate = getTagValue("timestamp_date", element);
            data[1] = timeStampDate;

            String value = getTagValue("value", element);
            data[2] = value;

            String unit = getTagValue("unit", element);
            data[3] = unit;
        }
        return data;

    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


}



