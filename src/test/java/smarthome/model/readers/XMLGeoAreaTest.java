package smarthome.model.readers;

import org.junit.jupiter.api.Test;
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.OccupationArea;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLGeoAreaTest {

    @Test
    void importData() {

        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

       int result = xmlReader.importData(path).size();

        assertEquals(2,result);

    }

    @Test
    void checkGAImport () {
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaList = xmlReader.importData(path);

        GeographicalArea ga = gaList.get(0);

        String expected = "urban area";
        String result = ga.getType();

        assertEquals(expected, result);
    }

    @Test
    void checkOccupationAreaImport (){
        Path path = Paths.get("resources/DataSet_sprint05_GA.xml");
        XMLGeoArea xmlReader = new XMLGeoArea();

        List<GeographicalArea> gaListInFile = xmlReader.importData(path);

        GeographicalArea porto = gaListInFile.get(0);
        double expected = 0.261;
        double result = porto.getOccupation().getWidth();
        assertEquals(expected, result);
    }
}