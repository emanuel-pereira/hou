package sprintzero.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GAListTest {

    @Test
    void newGA() {

        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        String expectedResult = "Porto";
        String result = area1.getGeographicalAreaDesignation();
        assertEquals(expectedResult, result);
    }

    @Test
    void addGA() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        List<GeographicalArea> expectedResult = Arrays.asList(area1);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Test addition of multiple equal areas")
    @Test
    void addGA2() {
        GAList ga = new GAList();

        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        assertEquals(Arrays.asList(area1), ga.getGAList());

        GeographicalArea area2  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area2);
        assertEquals(Arrays.asList(area1), ga.getGAList());
    }

    @Test
    void getGAList() {
        GAList ga = new GAList();
        GeographicalArea area1 = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        GeographicalArea area2 = ga.newGA("Braga", "district", 20, 20, 1, 3, -10);

        ga.addGA(area1);
        ga.addGA(area2);

        List<GeographicalArea> expectedResult = Arrays.asList(area1, area2);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Ensure GAList doesn't allow to add the same GA twice.")
    void addGAReturnsFalse() {
        GAList ga = new GAList();
        GeographicalArea area1  = ga.newGA("Porto", "district", 20, 20, 1, 3, -10);
        ga.addGA(area1);
        boolean expectedResult = false;
        boolean result = ga.addGA(area1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test if listOfGAsContainingLocation method returns list of GAs with only one GA")
    void checkIfLocationIsInGAList() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(2.7,5.2,0);
        GAList listOfGAsContainingLocation=gaList.listOfGAsContainingLocation(loc.getLatitude(),loc.getLongitude());
        int expectedResult=1;
        int result=listOfGAsContainingLocation.size();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that Location loc is not within any geographical area in gaList")
    void checkIfLocationIsNotInGAList() {
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        gaList.addGA(ga1);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga2);
        Location loc = new Location(22.7,5.2,0);
        GAList listOfGAsContainingLocation=gaList.listOfGAsContainingLocation(loc.getLatitude(),loc.getLongitude());
        int expectedResult=0;
        int result=listOfGAsContainingLocation.size();
        assertEquals(expectedResult,result);
    }


    @Test
    @DisplayName("Ensure sensor is added to the geographical area in the index 0 of a list of Geographical Areas that contain the sensor location")
    void addSensorToGAInListOfGAs() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),new Location(2.7,5.2,0),new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = true;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,0);
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure sensor is not added to the geographical area in the index 0 of a list of Geographical Areas that contain the sensor location, when the sensor is already in that Geographical Area")
    void addSensorToGAInListOfGAsWhichAlreadyContainsThatSensor() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),new Location(2.7,5.2,0),new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        ga2.addSensor(sensor);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = false;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,0);
        assertEquals(expectedResult,result);
    }
    @Test
    @DisplayName("Ensure that when the user selects an index of GA greater than the size of the GAList containing the sensor location returns false")
    void checkIfReturnsFalseWhenIndexOfGAGreaterThanSizeOfGAListContainingSensorLocation() {
        Sensor sensor = new Sensor("GaiaTemperatureSensor", new GregorianCalendar(2018, 8, 4, 11, 0),new Location(2.7,5.2,0),new DataType ("Temperature"));
        GAList gaList= new GAList();
        GeographicalArea ga1= new GeographicalArea("Gaia","City",20,20,2,2,5);
        GeographicalArea ga2= new GeographicalArea("Matosinhos","City",4,5,2,2,5);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        boolean expectedResult = false;
        boolean result= gaList.addSensorToGAInListOfGAs(sensor,2);
        assertEquals(expectedResult,result);
    }


}