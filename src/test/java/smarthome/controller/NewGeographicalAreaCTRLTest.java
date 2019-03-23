package smarthome.controller;

public class NewGeographicalAreaCTRLTest {

   /* @Test
    void newGA() {
        GAList list = new GAList();
        TypeGAList typeGAList = new TypeGAList();
        TypeGA district = new TypeGA("district");
        typeGAList.addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list, typeGAList);

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
        TypeGAList typeGAList = new TypeGAList();
        TypeGA district = new TypeGA("district");
        typeGAList.addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list, typeGAList);

        assertEquals(0, list.getGAList().size());
        OccupationArea occupationArea = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);

        assertTrue(ctrl1.newGA("Pt", "Porto", 0, occupationArea, location));
        assertEquals(1, list.getGAList().size());

        OccupationArea occupationArea2 = new OccupationArea(60, 20);
        Location location2 = new Location(10, 20, -11);
        assertTrue(ctrl1.newGA("Pt", "Lisboa", 0, occupationArea2, location2));
        assertEquals(2, list.getGAList().size());
    }


    *//*Add a new geographical area in a list with the same GA and get the size of the list that remains as 1.*//*


    @Test
    @DisplayName("Add a new geographical area in a list with the same GA")
    void checkifGAalreadyExistsAndisNotAdded() {
        GAList list = new GAList();
        TypeGAList typeGAList = new TypeGAList();
        TypeGA district = new TypeGA("district");
        typeGAList.addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list, typeGAList);

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
    void newGAIfRepeatAndDifferentGAs() {
        GAList list = new GAList();
        TypeGAList typeGAList = new TypeGAList();
        TypeGA district = new TypeGA("district");
        typeGAList.addTypeGA(district);
        NewGeographicalAreaCTRL ctrl1 = new NewGeographicalAreaCTRL(list, typeGAList);

        assertEquals(0, list.getGAList().size());

        OccupationArea occupation = new OccupationArea(20, 20);
        Location location = new Location(1, 3, -10);
        assertTrue(ctrl1.newGA("Pt", "Porto", 0, occupation, location));
        assertEquals(1, list.getGAList().size());


        OccupationArea occupation2 = new OccupationArea(60, 20);
        Location location2 = new Location(60, 20, -11);
        assertTrue(ctrl1.newGA("Pt", "Lisboa", 0, occupation2, location2));
        assertEquals(2, list.getGAList().size());

        assertFalse(ctrl1.newGA("Pt", "Porto", 0, occupation, location));
        assertEquals(2, list.getGAList().size());

        OccupationArea occupation3 = new OccupationArea(10, 10);
        Location location3 = new Location(50, 30, 1);
        assertTrue(ctrl1.newGA("Pt", "Braga", 0, occupation3, location3));
        assertEquals(3, list.getGAList().size());

        assertFalse(ctrl1.newGA("Pt", "Braga", 0, occupation3, location3));
        assertEquals(3, list.getGAList().size());
    }

    @DisplayName("Add GA with GA type from GA type's list with usage of US1 and US3 Controller methods")
    @Test
    void addGATypeFromList() {
        TypeGAList typeGAList = new TypeGAList();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(typeGAList);
        assertTrue(ctrl1.createTypeGA("village"));

        assertEquals(1, typeGAList.getTypeGAList().size());

        assertTrue(ctrl1.createTypeGA("city"));

        assertEquals(2, typeGAList.getTypeGAList().size());

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(typeGAList);
        List<TypeGA> list2 = ctrl2.getTypeGAList();
        assertEquals(2, list2.size());

        GAList GAList = new GAList();
        NewGeographicalAreaCTRL ctrl3 = new NewGeographicalAreaCTRL(GAList, typeGAList);
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
        assertEquals("Funchal", GAList.getGAList().get(0).getGAName());
        //check the new GA type from the GA's list
        assertEquals("city", GAList.getGAList().get(0).getType());
    }

    @Test
    void size() {
        TypeGAList typeGAList = new TypeGAList();
        TypeGA city = new TypeGA("city");
        TypeGA country = new TypeGA("country");
        typeGAList.addTypeGA(city);
        typeGAList.addTypeGA(country);
        GAList gaList = new GAList();
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList, typeGAList);
        int expected = 2;
        int result = ctrl.typeGAListSize();
        assertEquals(expected, result);

    }

    @Test
    void showTypeGAListInStr() {
        TypeGAList typeGAList = new TypeGAList();
        TypeGA city = new TypeGA("city");
        TypeGA country = new TypeGA("country");
        typeGAList.addTypeGA(city);
        typeGAList.addTypeGA(country);
        GAList gaList = new GAList();
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList, typeGAList);
        String expected = "1 - city\n" +
                "2 - country\n";
        String result = ctrl.showTypeGAListInStr();
        assertEquals(expected, result);
    }

    @Test
    void getGAName() {
        TypeGAList typeGAList = new TypeGAList();
        TypeGA city = new TypeGA("city");
        typeGAList.addTypeGA(city);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(15,22);
        Location location= new Location(25,32,15);
        GeographicalArea porto = new GeographicalArea("POR","Porto",typeGAList.toString(),occupationArea,location);
        gaList.addGA(porto);
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList, typeGAList);
        String expected="Porto";
        String result= ctrl.getGAName();
        assertEquals(expected,result);
    }

    @Test
    void getGAType() {
        TypeGAList typeGAList = new TypeGAList();
        TypeGA city = new TypeGA("city");
        typeGAList.addTypeGA(city);
        GAList gaList = new GAList();
        OccupationArea occupationArea = new OccupationArea(15,22);
        Location location= new Location(25,32,15);
        GeographicalArea porto = new GeographicalArea("POR","Porto","city",occupationArea,location);
        gaList.addGA(porto);
        NewGeographicalAreaCTRL ctrl = new NewGeographicalAreaCTRL(gaList, typeGAList);
        String expected="city";
        String result= ctrl.getGAType();
        assertEquals(expected,result);

    }*/
}
