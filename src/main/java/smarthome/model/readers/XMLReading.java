package smarthome.model.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class XMLReading implements FileReaderReadings {


    public XMLReading() {
    }

    @Override
    public List<String[]> importData(Path filePath) {


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        List<String[]> readingList = new ArrayList<>();

        try {

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File file = filePath.toFile();

            Document xmlDoc = dBuilder.parse(file);

            xmlDoc.getDocumentElement().normalize();

            NodeList readingNodeList = xmlDoc.getElementsByTagName("reading");

            for (int i = 0; i < readingNodeList.getLength(); i++) {
                readingList.add(importReading(readingNodeList.item(i)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return readingList;

    }


    private String[] importReading(Node readingNode) throws ParseException {

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

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


}



