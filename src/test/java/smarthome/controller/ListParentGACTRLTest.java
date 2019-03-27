package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.GAList;
import smarthome.model.Location;
import smarthome.model.OccupationArea;
import smarthome.model.TypeGAList;

import static org.junit.jupiter.api.Assertions.*;

public class ListParentGACTRLTest {
    //return GA's List
    //user selects one from the List

    //return the parent GA if there's only one
    @DisplayName("Add two GA  with success and look for one's Parent GA, however none was found")
    @Test
    void addGACheckParent() {
        //invoke new empty list
        TypeGAList TypeGAList = new TypeGAList();
        //pass the empty list as a parameter to US1 controller
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(TypeGAList);
        //creation of a new GA Type and it's addition to the initially empty list
        assertTrue(ctrl1.createTypeGA ("village"));
        assertEquals(1, TypeGAList.getTypeGAList().size());
        //creation of a new GA Type and it's addition to the previously list already with one element
        assertTrue(ctrl1.createTypeGA ("city"));
        //check the size of the list, now with both elements successfully added
        assertEquals(2, TypeGAList.getTypeGAList().size());
        assertTrue(ctrl1.createTypeGA ("street"));
        //check the size of the list, now with both elements successfully added
        assertEquals(3, TypeGAList.getTypeGAList().size());

        //creation of a new list of GA's, for now empty
        GAList gaList = new GAList();
        //invocation of the controller for the US3, passing both list's: GA's and GATypes's as parameters
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(gaList, TypeGAList);
        //GA's list size is initially zero
        assertEquals(0, gaList.getGAList().size());

        //String for new city GA
        String name1 = "Funchal";
        String id="Fc";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList1 = 1;
        //method that invokes the GA creation by passing all the nre city GA parameters
        OccupationArea occupationArea= new OccupationArea(20,20);
        Location location= new Location(1,3,-10);
        assertTrue(ctrl3.newGA(id,name1, GATypeIndexFromList1, occupationArea, location));

        //String for new city GA
        String name2 = "Rua 31 de Janeiro";
        String id2 = "31j";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList2 = 2;
        //method that invokes the GA creation by passing all the nre city GA parameters
        assertTrue(ctrl3.newGA(id2,name2, GATypeIndexFromList2, occupationArea,location));

        //US7
        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(gaList);
        //set Funchal as Parent GA of Rua 31 de Janeiro
        ctrl7.setParentofGA(2,1);//index 2 Rua 31 de Janeiro // index 1 Funchal

        //US8
        ListParentGACTRL ctrl8 = new ListParentGACTRL(gaList);
        //check a GA parent GA
        assertEquals(2,ctrl8.getGAListSize());
        assertEquals("Funchal", gaList.get(1).getGeographicalParentGA().getGAName());
        assertEquals("Funchal", ctrl8.isParentOf(2));
        assertEquals("1 - Funchal\n2 - Rua 31 de Janeiro\n",ctrl8.showListInString());
    }

    @DisplayName("Add four GA's with success and look for the first one's Parent GA")
    @Test
    void add4GACheckParent() {
        //invoke new empty list
        TypeGAList TypeGAList = new TypeGAList();
        //pass the empty list as a parameter to US1 controller
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(TypeGAList);
        //creation of a new GA Type and it's addition to the initially empty list
        assertTrue(ctrl1.createTypeGA ("district"));
        assertEquals(1, TypeGAList.getTypeGAList().size());
        //creation of a new GA Type and it's addition to the previously list already with one element
        assertTrue(ctrl1.createTypeGA ("city"));
        //check the size of the list, now with both elements successfully added
        assertEquals(2, TypeGAList.getTypeGAList().size());
        assertTrue(ctrl1.createTypeGA ("street"));
        //check the size of the list, now with both elements successfully added
        assertEquals(3, TypeGAList.getTypeGAList().size());

        //creation of a new list of GA's, for now empty
        GAList GAList = new GAList();
        //invocation of the controller for the US3, passing both list's: GA's and GATypes's as parameters
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(GAList, TypeGAList);
        //GA's list size is initially zero
        assertEquals(0, GAList.getGAList().size());

        //String for new city GA
        String name1 = "Funchal";
        String id="fc";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList1 = 1;
        //method that invokes the GA creation by passing all the nre city GA parameters
        OccupationArea occupationArea= new OccupationArea(20,20);
        Location location= new Location(1,3,-10);
        assertTrue(ctrl3.newGA(id,name1, GATypeIndexFromList1, occupationArea, location));

        //String for new city GA
        String name2 = "Rua 31 de Janeiro";
        String id2 = "31j";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList2 = 2;
        //method that invokes the GA creation by passing all the nre city GA parameters
        assertTrue(ctrl3.newGA(id2,name2, GATypeIndexFromList2,occupationArea, location));

        //String for new city GA
        String name3 = "Madeira";
        String id3 = "mdr";
        //Int for new city GA types, from the list previously create of GA Types
        int GATypeIndexFromList3 = 0;
        //method that invokes the GA creation by passing all the nre city GA parameters
        OccupationArea occupationArea3= new OccupationArea(20,20);
        Location location3= new Location(1,3,-10);
        assertTrue(ctrl3.newGA(id3,name3, GATypeIndexFromList3, occupationArea3,location3));

        //US7
        SetParentOfGACTRL ctrl7 = new SetParentOfGACTRL(GAList);
        //set Funchal as Parent GA of Rua 31 de Janeiro
        ctrl7.setParentofGA(2,1);//index 2 Rua 31 de Janeiro //index 1 Funchal
        //set Madeira as Parent GA of Funchal
        ctrl7.setParentofGA(1,3);//index 3 Madeira //index 1 Funchal

        //US8
        ListParentGACTRL ctrl8 = new ListParentGACTRL(GAList);
        //check a GA parent GA
        assertEquals(3,ctrl8.getGAListSize());
        assertEquals("Funchal, Madeira", ctrl8.isParentOf(2));
    }

}
