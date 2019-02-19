package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;
import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewGeographicalAreaCTRLTest {

    @Test
    void newGA() {
        GAList list = new GAList ();
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals(0, list.getGAList ().size ());
        assertTrue(ctrl1.newGA ("Pt","Porto", "district", 20, 20, 1, 3, -10));

        assertEquals(1, list.getGAList ().size ());
    }


    @Test
    @DisplayName("Add a new geographical area on a list with other GA's")
    void newGAonListwithOthersGAs() {
        GAList list = new GAList ();
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA("Pt","Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (1, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Pt","Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (2, list.getGAList ().size ());
    }


     /*Add a new geographical area in a list with the same GA and get the size of the list that remains as 1.*/


    @Test
    @DisplayName("Add a new geographical area in a list with the same GA")
    void checkifGAalreadyExistsAndisNotAdded() {
        GAList list = new GAList ();
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Pt","Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (1, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Pt","Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (1, list.getGAList ().size ());
    }

    @Test
    @DisplayName("Add new geographical area in a list with the same and different GA's")
    void newGAIfRepeatAndDifferentGAs() {
        GAList list = new GAList ();
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Pt","Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (1, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Pt","Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (2, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Pt","Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (2, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Pt","Braga", "district", 10, 10, 50, 30, 1));
        assertEquals (3, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Pt","Braga", "district", 10, 10, 50, 30, 1));
        assertEquals (3, list.getGAList ().size ());
    }

    @DisplayName("Add GA with GA type from GA type's list with usage of US1 and US3 Controller methods")
    @Test
    void addGATypeFromList() {
        //invoke new empty list
        TypeGAList TypeGAList = new TypeGAList ();
        //pass the empty list as a parameter to US1 controller
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(TypeGAList);
        //creation of a new GA Type and it's addition to the initially empty list
        assertTrue(ctrl1.createTypeGA ("village"));
        assertEquals (1, TypeGAList.getTypeGAList().size ());
        //creation of a new GA Type and it's addition to the previously list already with one element
        assertTrue(ctrl1.createTypeGA ("city"));
        //check the size of the list, now with both elements successfully added
        assertEquals (2, TypeGAList.getTypeGAList().size ());

        //optional
        //pass the list again as a parameter to US2 controller in order for it to spell out the list
        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(TypeGAList);
        //check size of the list in US2 controller
        List<TypeGA> list2 = ctrl2.getTypeGAList ();
        assertEquals (2, list2.size ());

        //creation of a new list of GA's, for now empty
        GAList GAList = new GAList ();
        //invocation of the controller for the US3, passing both list's: GA's and GATypes's as parameters
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(GAList, TypeGAList);
        //GA's list size is initially zero
        assertEquals (0, GAList.getGAList ().size ());
        //String for new city GA
        String name = "Funchal";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList = 2;
        String id= "Pt";
        //method that invokes the GA creation by passing all the nre city GA parameters
        assertTrue(ctrl3.newGA2(id, name, GATypeIndexFromList, 20, 20, 1, 3, -10));
        //check GA List size with the newly added GA
        assertEquals (1, GAList.getGAList ().size ());
        //check the new GA designation from the GA's list
        assertEquals("Funchal",GAList.getGAList().get(0).getGAName());
        //check the new GA type from the GA's list
        assertEquals("city",GAList.getGAList().get(0).getGeographicalAreaType());
    }
}
