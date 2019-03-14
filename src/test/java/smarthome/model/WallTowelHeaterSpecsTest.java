package smarthome.model;

import org.junit.jupiter.api.Test;

import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterSpecsTest {

    @Test
    void getDeviceType() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        String expected = "WallTowelHeater";
        String result = specs.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void getAttributesNames() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributesNames();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValues() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        List<Double> expected = new ArrayList<>();
        List<Double> result = specs.getAttributeValues();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnits() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        specs.setAttributeUnit(null,null);

        List<String> expected = new ArrayList<>();
        List<String> result = specs.getAttributeUnits();

        assertEquals(expected,result);
    }

    @Test
    void getAttributeValue() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        specs.setAttributeValue(null,Double.NaN);

        double expected = Double.NaN;
        double result = specs.getAttributeValue(null);

        assertEquals(expected,result);
    }

    @Test
    void getAttributeUnit() {
        WallTowelHeaterType type = new WallTowelHeaterType();
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs(type.getDeviceType());

        specs.setAttributeUnit(null,null);

        String result = specs.getAttributeUnit(null);

        assertNull(result);
    }
}