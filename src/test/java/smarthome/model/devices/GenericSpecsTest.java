package smarthome.model.devices;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericSpecsTest {

    @Test
    void getDeviceType() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};

        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);
        String expected = "A device";
        String result = specs.getDeviceType();
        assertEquals(expected, result);
    }

    @Test
    void getAttributeValue() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        specs.setAttributeValue("A", 1);
        specs.setAttributeValue("B", 2);
        specs.setAttributeValue("C", 3);

        double expected = 1;
        double result = specs.getAttributeValue("A");
        assertEquals(expected, result);

        expected = 2;
        result = specs.getAttributeValue("B");
        assertEquals(expected, result);

        expected = 3;
        result = specs.getAttributeValue("C");
        assertEquals(expected, result);

    }

    @Test
    void getAttributeUnit() {

        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);


        String expected = "aUnit";
        String result = specs.getAttributeUnit("A");
        assertEquals(expected, result);

        expected = "bUnit";
        result = specs.getAttributeUnit("B");
        assertEquals(expected, result);

        expected = "cUnit";
        result = specs.getAttributeUnit("C");
        assertEquals(expected, result);
    }

    @Test
    void getAttributesNames() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        List<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        List<String> result = specs.getAttributesNames();

        assertEquals(expected, result);
    }

    @Test
    void getAttributeValues() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        List<Double> expected = new ArrayList<>();
        expected.add(1.1);
        expected.add(2.2);
        expected.add(3.3);

        specs.setAttributeValue("A",1.1);
        specs.setAttributeValue("B",2.2);
        specs.setAttributeValue("C",3.3);
        List<Double> result = specs.getAttributeValues();

        assertEquals(expected, result);
    }

    @Test
    void getAttributeUnits() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        List<String> expected = new ArrayList<String>();
        expected.add("aUnit");
        expected.add("bUnit");
        expected.add("cUnit");
        List<String> result = specs.getAttributeUnits();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeUnit() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        specs.setAttributeUnit("A", "New unit");
        String expected = "New unit";
        String result = specs.getAttributeUnit("A");
        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        String[] attributeNames = {"A", "B", "C"};
        String[] attributeUnits = {"aUnit", "bUnit", "cUnit"};
        GenericSpecs specs = new GenericSpecs("A device", attributeNames, attributeUnits);

        specs.setAttributeValue("A", 3.1415926);
        double expected = 3.1415926;
        double result = specs.getAttributeValue("A");
        assertEquals(expected, result);

    }
}