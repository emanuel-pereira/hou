package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US4ReturnGAsWithThisTypeCTRLTest {


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

        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga2 = new GeographicalArea("Gaia", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita", "street", 10, 10, 10, 10, 10);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList(ga3);

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("With US3 + US1 Constructor - Show list of one GA from type street, or Type List index position 1 ")
    void getGAListFromTypeTestOneGAFromUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL(listType);
        ctrl1.newTypeGA("street");
        ctrl1.newTypeGA("city");
        ctrl1.newTypeGA("country");

        int typeIndex = 1;

        US3CreateGACTRL ctrl3 = new US3CreateGACTRL(listGa, listType);
        ctrl3.newGA("Porto", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Gaia", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Cedofeita", "street", 10, 10, 10, 40, 8);

        List<GeographicalArea> expected = Arrays.asList(listGa.get(2));

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
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

        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga2 = new GeographicalArea("Gaia", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita", "street", 10, 10, 10, 10, 10);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList(ga1,ga2);

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("With US3 + US1 Constructor - Show list of two GAs from type city, or Type List index position 2 ")
    void getGAListFromTypeTestTwoGAWithUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL(listType);
        ctrl1.newTypeGA("street");
        ctrl1.newTypeGA("city");
        ctrl1.newTypeGA("country");

        int typeIndex = 2;

        US3CreateGACTRL ctrl3 = new US3CreateGACTRL(listGa, listType);
        ctrl3.newGA("Porto", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Gaia", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Cedofeita", "street", 10, 10, 10, 40, 8);

        List<GeographicalArea> expected = Arrays.asList(listGa.get(0),listGa.get(1));

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
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


        GeographicalArea ga1 = new GeographicalArea("Porto", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga2 = new GeographicalArea("Gaia", "city", 10, 10, 10, 10, 10);
        GeographicalArea ga3 = new GeographicalArea("Cedofeita", "street", 10, 10, 10, 10, 10);

        listGa.addGA(ga1);
        listGa.addGA(ga2);
        listGa.addGA(ga3);

        List<GeographicalArea> expected = Arrays.asList();

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("With US3 + US1 Constructor - Get empty list of GA from type country, or Type List index position 3")
    void getGAListFromTypeTestEMPTYWithUS3AndUS1() {
        GAList listGa = new GAList();
        TypeGAList listType = new TypeGAList();

        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL(listType);
        ctrl1.newTypeGA("street");
        ctrl1.newTypeGA("city");
        ctrl1.newTypeGA("country");

        int typeIndex = 3;

        US3CreateGACTRL ctrl3 = new US3CreateGACTRL(listGa, listType);
        ctrl3.newGA("Porto", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Gaia", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Cedofeita", "street", 10, 10, 10, 40, 8);

        List<GeographicalArea> expected = Arrays.asList();

        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
        List<GeographicalArea> result = ctrl4.getGAListFromType(typeIndex);
        assertEquals(expected, result);
    }

    @Test
    void showListInString() {
        TypeGAList listType = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (listType);
        ctrl1.newTypeGA ("village");
        ctrl1.newTypeGA ("city");

        int typeIndex = 2;

        GAList listGa = new GAList();
        US3CreateGACTRL ctrl3 = new US3CreateGACTRL(listGa);
        ctrl3.newGA("Porto", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Gaia", "city", 10, 10, 10, 40, 8);
        ctrl3.newGA("Cedofeita", "street", 10, 10, 10, 40, 8);

        String expected = "1 - Porto city\n2 - Gaia city\n";
        US4ReturnGAsWithThisTypeCTRL ctrl4 = new US4ReturnGAsWithThisTypeCTRL(listGa,listType);
        String result = ctrl4.showListInString (typeIndex);

        assertEquals (expected, result);
    }
}