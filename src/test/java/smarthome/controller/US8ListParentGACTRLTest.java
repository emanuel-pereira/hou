package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;
import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class US8ListParentGACTRLTest {
    //return GA's List
    //user selects one from the List

    //return the parent GA if there's only one
    @DisplayName("Add two GA  with success and look for one's Parent GA, however none was found")
    @Test
    void addGACheckParent() {
        //invoke new empty list
        TypeGAList TypeGAList = new TypeGAList();
        //pass the empty list as a parameter to US1 controller
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL(TypeGAList);
        //creation of a new GA Type and it's addition to the initially empty list
        assertTrue(ctrl1.newTypeGA("village"));
        assertEquals(1, TypeGAList.getTypeGAList().size());
        //creation of a new GA Type and it's addition to the previously list already with one element
        assertTrue(ctrl1.newTypeGA("city"));
        //check the size of the list, now with both elements successfully added
        assertEquals(2, TypeGAList.getTypeGAList().size());
        assertTrue(ctrl1.newTypeGA("street"));
        //check the size of the list, now with both elements successfully added
        assertEquals(3, TypeGAList.getTypeGAList().size());

        //optional
        //pass the list again as a parameter to US2 controller in order for it to spell out the list
        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL(TypeGAList);
        //check size of the list in US2 controller
        List<TypeGA> list2 = ctrl2.getTypeGAList();
        assertEquals(3, list2.size());

        //creation of a new list of GA's, for now empty
        GAList GAList = new GAList();
        //invocation of the controller for the US3, passing both list's: GA's and GATypes's as parameters
        US3CreateGACTRL ctrl3 = new US3CreateGACTRL(GAList, TypeGAList);
        //GA's list size is initially zero
        assertEquals(0, GAList.getGAList().size());

        //String for new city GA
        String name1 = "Funchal";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList1 = 2;
        //method that invokes the GA creation by passing all the nre city GA parameters
        assertTrue(ctrl3.newGA2(name1, GATypeIndexFromList1, 20, 20, 1, 3, -10));

        //String for new city GA
        String name2 = "Rua 31 de Janeiro";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList2 = 3;
        //method that invokes the GA creation by passing all the nre city GA parameters
        assertTrue(ctrl3.newGA2(name2, GATypeIndexFromList2, 2, 2, 1, 3, -10));

        //US8
        US8ListParentGACTRL ctrl8 = new US8ListParentGACTRL(GAList);
        //check GA List size with the newly added GA
        assertEquals(2, ctrl8.getGAListSize());
        //check the new GA designation from the GA's list
        assertEquals("Funchal", GAList.getGAList().get(0).getGeographicalAreaDesignation());
        //check the new GA type from the GA's list
        assertEquals("city", GAList.getGAList().get(0).getGeographicalAreaType());
        assertEquals("1 - Funchal\n2 - Rua 31 de Janeiro\n", ctrl8.showListInString());

        assertNull(ctrl8.isParentOf(1));
    }

}
