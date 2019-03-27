package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GAListTest {

    @Test
    void newGA() {

        GAList ga = new GAList();
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        TypeGA typeGA= new TypeGA("city");
        GeographicalArea area1 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        String expectedResult = "Porto";
        String result = area1.getGAName();
        assertEquals(expectedResult, result);
    }

    @Test
    void addGA() {
        GAList ga = new GAList();
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        TypeGA typeGA= new TypeGA("city");
        GeographicalArea area1 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        ga.addGA(area1);
        List<GeographicalArea> expectedResult = Arrays.asList(area1);
        List<GeographicalArea> result = ga.getGAList();
        assertEquals(expectedResult, result);
    }

    @DisplayName("Test addition of multiple equal areas")
    @Test
    void addGA2() {
        GAList ga = new GAList();
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        TypeGA typeGA= new TypeGA("city");
        GeographicalArea area1 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        ga.addGA(area1);
        assertEquals(Arrays.asList(area1), ga.getGAList());

        GeographicalArea area2 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        ga.addGA(area2);
        assertEquals(Arrays.asList(area1), ga.getGAList());
    }

    @Test
    void getGAList() {
        GAList ga = new GAList();
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        TypeGA typeGA= new TypeGA("city");
        GeographicalArea area1 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        GeographicalArea area2 = ga.newGA("Pt", "Braga", typeGA, occupationArea, location);

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
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        TypeGA typeGA= new TypeGA("city");
        GeographicalArea area1 = ga.newGA("Pt", "Porto", typeGA, occupationArea, location);
        ga.addGA(area1);
        boolean expectedResult = false;
        boolean result = ga.addGA(area1);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is only 1 result")
    void GAFromTypeTestOneElement() {
        GAList gaList = new GAList();

        OccupationArea occupationArea1 = new OccupationArea(2, 5);
        Location location1 = new Location(20, 20, 2);

        OccupationArea occupationArea2 = new OccupationArea(2, 5);
        Location location2 = new Location(4, 5, 2);

        OccupationArea occupationArea3 = new OccupationArea(2, 5);
        Location location3 = new Location(41, -8, 83);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Gaia", "City", occupationArea1, location1);
        gaList.addGA(ga1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Matosinhos", "City", occupationArea2, location2);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt", "Cedofeita", "street", occupationArea3, location3);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga3)); //Usar Arrays.asList dentro de um a nova array list caso dê erro null point exception
        List<GeographicalArea> result = gaList.gAFromThisType("street");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns a List of GA from the type chosen by the user, when there is 2 or more results")
    void GAFromTypeTestTwoOrMoreElements() {
        GAList gaList = new GAList();

        OccupationArea occupationArea1 = new OccupationArea(2, 5);
        Location location1 = new Location(20, 20, 2);

        OccupationArea occupationArea2 = new OccupationArea(2, 5);
        Location location2 = new Location(4, 5, 2);

        OccupationArea occupationArea3 = new OccupationArea(2, 5);
        Location location3 = new Location(41, -8, 83);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Gaia", "city", occupationArea1, location1);
        gaList.addGA(ga1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Matosinhos", "city", occupationArea2, location2);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt", "Cedofeita", "Street", occupationArea3, location3);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = new ArrayList<>(Arrays.asList(ga1, ga2));
        List<GeographicalArea> result = gaList.gAFromThisType("city");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no matches")
    void GAFromTypeTestEmptyNoMatch() {
        GAList gaList = new GAList();


        OccupationArea occupationArea1 = new OccupationArea(2, 5);
        Location location1 = new Location(20, 20, 2);

        OccupationArea occupationArea2 = new OccupationArea(2, 5);
        Location location2 = new Location(4, 5, 2);

        OccupationArea occupationArea3 = new OccupationArea(2, 5);
        Location location3 = new Location(41, -8, 83);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Gaia", "City", occupationArea1, location1);
        gaList.addGA(ga1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Matosinhos", "City", occupationArea2, location2);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt", "Cedofeita", "Street", occupationArea3, location3);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result = gaList.gAFromThisType("Country");
        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("Check if method returns an empty List of GA, when there is no input/empty")
    void GAFromTypeTestEmptyNoInput() {
        GAList gaList = new GAList();

        OccupationArea occupationArea1 = new OccupationArea(2, 5);
        Location location1 = new Location(20, 20, 2);

        OccupationArea occupationArea2 = new OccupationArea(2, 5);
        Location location2 = new Location(4, 5, 2);

        OccupationArea occupationArea3 = new OccupationArea(2, 5);
        Location location3 = new Location(41, -8, 83);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Gaia", "City", occupationArea1, location2);
        gaList.addGA(ga1);
        GeographicalArea ga2 = new GeographicalArea("Pt", "Matosinhos", "City", occupationArea2, location2);
        gaList.addGA(ga2);
        GeographicalArea ga3 = new GeographicalArea("Pt", "Cedofeita", "Street", occupationArea3, location3);
        gaList.addGA(ga3);
        List<GeographicalArea> expectedresult = Arrays.asList();
        List<GeographicalArea> result = gaList.gAFromThisType("");
        assertEquals(expectedresult, result);
    }

    @DisplayName("Test if Geographical Area List is showed as a string to the user")
    @Test
    void showGAListInString() {
        GAList gaList = new GAList();

        OccupationArea occupationArea1 = new OccupationArea(32, 41);
        Location location1 = new Location(25, 15, 12);

        OccupationArea occupationArea2 = new OccupationArea(42, 41);
        Location location2 = new Location(45, 25, 32);

        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", occupationArea1, location2);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", occupationArea2, location2);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        String expected = "1 - Porto\n2 - Lisboa\n";
        String result = gaList.showGAListInString();
        assertEquals(expected, result);
    }

    @Test
    void size() {
        GAList gaList = new GAList();

        OccupationArea occupationArea1 = new OccupationArea(32, 41);
        Location location1 = new Location(45, 25, 32);

        OccupationArea occupationArea2 = new OccupationArea(42, 41);
        Location location2 = new Location(45, 25, 32);


        GeographicalArea ga1 = new GeographicalArea("Pt", "Porto", "city", occupationArea1, location1);
        GeographicalArea ga2 = new GeographicalArea("Lis", "Lisboa", "city", occupationArea2, location2);
        gaList.addGA(ga1);
        gaList.addGA(ga2);
        int expected = 2;
        int result = gaList.size();
        assertEquals(expected, result);
    }

    @Test
    void importDataFromCSVFileForEachGATest() throws IOException {
        GAList gaList = new GAList();
        GeographicalArea porto = new GeographicalArea("001", "Porto", "city", new OccupationArea(3,2),new Location(3, 30, 20));
        GeographicalArea lisboa = new GeographicalArea("002", "Lisboa", "city", new OccupationArea(3,2),new Location(3, 30, 20));
        gaList.addGA(porto);
        gaList.addGA(lisboa);
        GregorianCalendar startDate = new GregorianCalendar(2018,2,3);
        GregorianCalendar startDate2 = new GregorianCalendar(2018,1,1);
        Location location = new Location(3,2,1);
        SensorType temp = new SensorType("Temperature");

        Sensor sensorISEP = new Sensor("TT12346","SensorISEP",startDate,location,temp,"C",new ReadingList());
        Sensor sensorPorto = new Sensor("TT1236A","SensorPorto",startDate,location,temp,"C",new ReadingList());

        porto.getSensorListInGA().addSensor(sensorPorto);
        porto.getSensorListInGA().addSensor(sensorISEP);
        //lisboa.getSensorListInGA().addSensor(sensorLisboa);

        gaList.importDataFromCSVFileForEachGA("resources/ReadingsRegistry");
        int result = gaList.getGAList().get(0).getSensorListInGA().getSensorList().get(0).getReadingList().getReadingsList().size();
        assertEquals(24,result);
        int result2 = gaList.getGAList().get(0).getSensorListInGA().getSensorList().get(1).getReadingList().getReadingsList().size();
        assertEquals(20,result2);


        //int result3 = gaList.getGAList().get(1).getSensorListInGA().getSensorList().get(0).getReadingsList().getReadingsList().size();
        //assertEquals(24,result3);


    }

}
