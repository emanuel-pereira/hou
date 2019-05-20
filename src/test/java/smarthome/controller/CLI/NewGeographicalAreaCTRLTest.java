package smarthome.controller.CLI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.TypeGAList.addTypeGA;
import static smarthome.model.TypeGAList.getTypeGAListInstance;

class NewGeographicalAreaCTRLTest {

    TypeGAList typeGAList = getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = TypeGAList.class.getDeclaredField("typeGaList");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void newGA() {
        GAList list = new GAList();
        TypeGA district = new TypeGA("district");
        addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals(0, list.getGAList().size());
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        assertTrue(ctrl1.newGA("Pt", "Porto", 0, occupationArea, location));

        assertEquals(1, list.getGAList().size());
    }

    @Test
    @DisplayName("Add a new geographical area on a list with other GA's")
    void newGAonListWithOthersGAs() {
        GAList list = new GAList();

        TypeGA district = new TypeGA("district");
        addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals(0, list.getGAList().size());
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);

        assertTrue(ctrl1.newGA("opo", "Porto", 0, occupationArea, location));
        assertEquals(1, list.getGAList().size());

        OccupationArea occupationArea2 = new OccupationArea(60, 20);
        Location location2 = new Location(10, 20, -11);
        assertTrue(ctrl1.newGA("lx", "Lisboa", 0, occupationArea2, location2));
        assertEquals(2, list.getGAList().size());
    }

    //Add a new geographical area in a list with the same GA and get the size of the list that remains as 1.

    @Test
    @DisplayName("Add a new geographical area in a list with the same GA")
    void checkifGAalreadyExistsAndisNotAdded() {
        GAList list = new GAList();

        TypeGA district = new TypeGA("district");
        addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals(0, list.getGAList().size());
        OccupationArea occupationArea = new OccupationArea(60, 20);
        Location location = new Location(10, 20, -11);

        assertTrue(ctrl1.newGA("Pt", "Lisboa", 0, occupationArea, location));
        assertEquals(1, list.getGAList().size());

        assertFalse(ctrl1.newGA("Pt", "Lisboa", 0, occupationArea, location));
        assertEquals(1, list.getGAList().size());
    }

    @Test
    @DisplayName("Add new geographical area in a list with the same and different GA's")
    public void newGAIfRepeatAndDifferentGAs() {
        GAList list = new GAList();

        TypeGA district = new TypeGA("district");
        addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals(0, list.getGAList().size());

        OccupationArea occupation = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        assertTrue(ctrl1.newGA("opo", "Porto", 0, occupation, location));
        assertEquals(1, list.getGAList().size());


        OccupationArea occupation2 = new OccupationArea(60, 20);
        Location location2 = new Location(60, 20, -11);
        assertTrue(ctrl1.newGA("lx", "Lisboa", 0, occupation2, location2));
        assertEquals(2, list.getGAList().size());

        assertFalse(ctrl1.newGA("opo", "Porto", 0, occupation, location));
        assertEquals(2, list.getGAList().size());

        OccupationArea occupation3 = new OccupationArea(10, 10);
        Location location3 = new Location(50, 30, 1);
        assertTrue(ctrl1.newGA("brg", "Braga", 0, occupation3, location3));
        assertEquals(3, list.getGAList().size());

        assertFalse(ctrl1.newGA("brg", "Braga", 0, occupation3, location3));
        assertEquals(3, list.getGAList().size());
    }

    @DisplayName("Add GA with GA type from GA type's list with usage of US1 and US3 Controller methods")
    @Test
    public void addGATypeFromList() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();
        assertTrue(ctrl1.createTypeGA("village"));

        assertEquals(1, TypeGAList.size());

        assertTrue(ctrl1.createTypeGA("city"));

        assertEquals(2, TypeGAList.size());

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        List<TypeGA> list2 = ctrl2.getTypeGAListCTRL();
        assertEquals(2, list2.size());

        GAList GAList = new GAList();
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(GAList);
        assertEquals(0, GAList.getGAList().size());
        String name = "Funchal";
        int GATypeIndexFromList = 1;
        String id = "Pt";
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        assertTrue(ctrl3.newGA(id, name, GATypeIndexFromList, occupationArea, location));
        //check GA List size with the newly added GA
        assertEquals(1, GAList.getGAList().size());
        //check the new GA designation from the GA's list
        assertEquals("Funchal", GAList.getGAList().get(0).getDesignation());
        //check the new GA type from the GA's list
        assertEquals("city", GAList.getGAList().get(0).getTypeName());
    }

    @Test
    void size() {

        TypeGA city = new TypeGA("city");
        TypeGA country = new TypeGA("country");
        addTypeGA(city);
        addTypeGA(country);
        GAList gaList = new GAList();
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList);
        int expected = 2;
        int result = ctrl.typeGAListSize();
        assertEquals(expected, result);
    }

    @Test
    void showTypeGAListInStr() {

        TypeGA city = new TypeGA("city");
        TypeGA country = new TypeGA("country");
        addTypeGA(city);
        addTypeGA(country);
        GAList gaList = new GAList();
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList);
        String expected = "1 - city\n" +
                "2 - country\n";
        String result = ctrl.showTypeGAListInStr();
        assertEquals(expected, result);
    }

    @Test
    void getGAName() {

        TypeGA city = new TypeGA("city");
        addTypeGA(city);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(15, 22);
        Location location = new Location(25, 32, 15);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", city, occupationArea, location);
        gaList.addGA(porto);
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList);
        String expected = "Porto";
        String result = ctrl.getGAName();
        assertEquals(expected, result);
    }

    @Test
    void getGAType() {

        TypeGA city = new TypeGA("city");
        addTypeGA(city);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(15, 22);
        Location location = new Location(25, 32, 15);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "city", occupationArea, location);
        gaList.addGA(porto);
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList);
        String expected = "city";
        String result = ctrl.getGAType();
        assertEquals(expected, result);

    }
}