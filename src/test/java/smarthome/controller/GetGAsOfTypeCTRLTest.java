package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetGAsOfTypeCTRLTest {

    @Test
    @DisplayName("Show list of one GA from type street, or Type List index position 1 ")
    void getGAListFromTypeTestOneGA() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();
        TypeGA inputType1 = new TypeGA("street");
        TypeGA inputType2 = new TypeGA("city");
        TypeGA inputType3 = new TypeGA("country");

        int typeIndex = 1;
        listType.addTypeGA(inputType1);
        listType.addTypeGA(inputType2);
        listType.addTypeGA(inputType3);

        OccupationArea oc1 = new OccupationArea(10, 10);
        Location loc1 = new Location(10,10,10);
        OccupationArea oc2 = new OccupationArea(10,10);
        Location loc2 = new Location(10,10,10);
        OccupationArea oc3 = new OccupationArea(10, 10);
        Location loc3 = new Location(10,10,10);

        GeographicalArea ga1 = new GeographicalArea("opo","Porto", "city", oc1,loc1);
        GeographicalArea ga2 = new GeographicalArea("vng","Gaia", "city", oc2,loc2);
        GeographicalArea ga3 = new GeographicalArea("cdf","Cedofeita", "street", oc3,loc3);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList(ga3);

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("With US3 + US1 Constructor - Show list of one GA from type street, or Type List index position 1 ")
    void getGAListFromTypeTestOneGAFromUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(listType);
        ctrl1.createTypeGA ("street");
        ctrl1.createTypeGA ("city");
        ctrl1.createTypeGA ("country");

        int typeIndex = 1;

        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(listGa, listType);
        OccupationArea occupationArea= new OccupationArea(10,10);
        Location location= new Location(10,40,8);
        ctrl3.newGA("opo","Porto", 1, occupationArea, location);
        ctrl3.newGA("vng","Gaia", 1, occupationArea, location);
        ctrl3.newGA("cdf","Cedofeita", 0, occupationArea, location);

        List<GeographicalArea> expected = Arrays.asList(listGa.get(2));

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Show list of two GAs from type city, or Type List index position 2 ")
    void getGAListFromTypeTestTwoGA() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        TypeGA inputType1 = new TypeGA("street");
        TypeGA inputType2 = new TypeGA("city");
        TypeGA inputType3 = new TypeGA("country");

        int typeIndex = 2;
        listType.addTypeGA(inputType1);
        listType.addTypeGA(inputType2);
        listType.addTypeGA(inputType3);

        OccupationArea oc1 = new OccupationArea(10, 10);
        Location loc1 = new Location(10,10,10);
        OccupationArea oc2 = new OccupationArea(10,10);
        Location loc2 = new Location(10,10,10);
        OccupationArea oc3 = new OccupationArea(10, 10);
        Location loc3 = new Location(10,10,10);

        GeographicalArea ga1 = new GeographicalArea("opo","Porto", "city", oc1,loc1);
        GeographicalArea ga2 = new GeographicalArea("vng","Gaia", "city", oc2,loc2);
        GeographicalArea ga3 = new GeographicalArea("cdf","Cedofeita", "street", oc3,loc3);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList(ga1,ga2);

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("With US3 + US1 Constructor - Show list of two GAs from type city, or Type List index position 2 ")
    void getGAListFromTypeTestTwoGAWithUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(listType);
        ctrl1.createTypeGA ("street");
        ctrl1.createTypeGA ("city");
        ctrl1.createTypeGA ("country");

        int typeIndex = 2;

        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(listGa, listType);
        OccupationArea occupationArea= new OccupationArea(10,10);
        Location location= new Location(10,40,8);
        ctrl3.newGA("opo","Porto", 1, occupationArea, location);
        ctrl3.newGA("vng","Gaia", 1, occupationArea, location);
        ctrl3.newGA("cdf","Cedofeita", 0, occupationArea, location);

        List<GeographicalArea> expected = Arrays.asList(listGa.get(0),listGa.get(1));

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get empty list of GA from type country, or Type List index position 3 ")
    void getGAListFromTypeTestEMPTY() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();
        TypeGA inputType1 = new TypeGA("street");
        TypeGA inputType2 = new TypeGA("city");
        TypeGA inputType3 = new TypeGA("country");

        int typeIndex = 3;
        listType.addTypeGA(inputType1);
        listType.addTypeGA(inputType2);
        listType.addTypeGA(inputType3);

        OccupationArea occupationArea= new OccupationArea(10,10);
        Location location= new Location(10,40,8);

        GeographicalArea ga1 = new GeographicalArea("opo","Porto", "city",occupationArea,location);
        GeographicalArea ga2 = new GeographicalArea("vng","Gaia", "city", occupationArea,location);
        GeographicalArea ga3 = new GeographicalArea("cdf","Cedofeita", "street", occupationArea,location);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList();

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("With US3 + US1 Constructor - Get empty list of GA from type country, or Type List index position 3")
    void getGAListFromTypeTestEMPTYWithUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(listType);
        ctrl1.createTypeGA ("street");
        ctrl1.createTypeGA ("city");
        ctrl1.createTypeGA ("country");

        int typeIndex = 3;

        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(listGa, listType);
        OccupationArea occupationArea= new OccupationArea(10,10);
        Location location= new Location(10,40,8);
        ctrl3.newGA("opo","Porto", 1, occupationArea,location);
        ctrl3.newGA("vng","Gaia", 1, occupationArea,location);
        ctrl3.newGA("cdf","Cedofeita", 1, occupationArea,location);

        List<GeographicalArea> expected = Arrays.asList();

        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    void showListInString() {
        TypeGAList listType = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(listType);
        ctrl1.createTypeGA ("village");
        ctrl1.createTypeGA ("city");

        int typeIndex = 1;

        GAList listGa = new GAList();
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(listGa,listType);
        OccupationArea occupationArea= new OccupationArea(10,10);
        Location location= new Location(10,40,8);
        ctrl3.newGA("opo","Porto", 0, occupationArea,location);
        ctrl3.newGA("vng","Gaia", 0, occupationArea,location);
        ctrl3.newGA("cdf","Cedofeita", 1, occupationArea,location);

        String expected = "1 - Porto\n2 - Gaia\n";
        GetGAsOfTypeCTRL ctrl4 = new GetGAsOfTypeCTRL(listGa,listType);
        String result = ctrl4.showListGAFromType(typeIndex);

        assertEquals (expected, result);

    }
}