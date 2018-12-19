package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.TypeGAList;

import static org.junit.jupiter.api.Assertions.*;

class US1CreateTypeGACTRLTest {

    /**
     * Add a new type of geographical area in an empty list and get the size of the list that is 1.
     */
    @Test
    @DisplayName("Add a new type of geographical area in an empty list")
    void newTypeGAIfSuccessVillage() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());
        ctrl1.newTypeGA ("village");

        assertEquals (1, list.getTypeGAList ().size ());
    }

    /**
     * Add a new type of geographical area in a list with other different types and get the size of the list that is 2.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with other types")
    void newTypeGAIfSuccessCityAfterVillage() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getTypeGAList ().size ());
    }


    /**
     * Add a new type of geographical area in a list with the same type and get the size of the list that remains as 1.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with same type")
    void newTypeGAIfCityAfterCity() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getTypeGAList ().size ());
    }

    /**
     * Add new types of geographical area in a list with same and different types and get the size of the list.
     * The repetitions are not considered.
     */
    @Test
    @DisplayName("Add  new types of geographical area in a list with same and different types")
    void newTypeGAIfRepeatAndDifferentTypes() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (2, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("country");
        assertEquals (3, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("country");
        assertEquals (3, list.getTypeGAList ().size ());
    }

    /**
     * Add spaces has type of geographical area in an empty list and confirm that is not possible to add that spaces
     * into the list
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in an empty list")
    void newTypeGAIfSpaceNotAddedEmptyList() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());
        ctrl1.newTypeGA (" ");

        assertEquals (0, list.getTypeGAList ().size ());
    }

    /**
     * Add spaces has type of geographical area in an list with one TypeGA and confirm that is not possible to add that
     * space into the list but is possible to add a correct one after
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in a list")
    void newTypeGAIfSpaceNotAdded() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("            ");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getTypeGAList ().size ());
    }

    @Test
    @DisplayName("Add a string with an unaccepted character 'Ç' type of GA")
    void newTypeGAIfSpaceNotAdded2() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("VillageÇ");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("            ");
        assertEquals (1, list.getTypeGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getTypeGAList ().size ());
    }

    @Test
    @DisplayName("Add a repetitive string with lower case and a title case and a upper case type of GA")
    void newTypeGAIfSpaceNotAdded3() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        assertTrue(ctrl1.newTypeGA ("village"));
        assertEquals (1, list.getTypeGAList ().size ());

        assertFalse(ctrl1.newTypeGA ("Village"));
        assertEquals (1, list.getTypeGAList ().size ());

        assertFalse(ctrl1.newTypeGA ("VILLAGE"));
        assertEquals (1, list.getTypeGAList ().size ());

        assertFalse(ctrl1.newTypeGA ("VILlage"));
        assertEquals (1, list.getTypeGAList ().size ());

        assertFalse(ctrl1.newTypeGA ("village"));
        assertEquals (1, list.getTypeGAList ().size ());

        assertTrue(ctrl1.newTypeGA ("city"));
        assertEquals (2, list.getTypeGAList ().size ());
    }

    @Test
    @DisplayName("Add a null type of GA")
    void newTypeGAIfSpaceNotAdded4() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getTypeGAList ().size ());

        /*ctrl1.newTypeGA (null);
        assertEquals (0, list.getTypeGAList ().size ());*/

        assertFalse(ctrl1.newTypeGA ("            "));
        assertEquals (0, list.getTypeGAList ().size ());



    }
}