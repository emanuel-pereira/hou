package smarthome.controller.CLI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.controller.CLI.NewTypeGACTRL;
import smarthome.model.TypeGAList;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.TypeGAList.getTypeGAListInstance;
import static smarthome.model.TypeGAList.size;

class NewTypeGACTRLTest {

    TypeGAList typeGAList = getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = TypeGAList.class.getDeclaredField("typeGaList");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    /*
     * Add a new type of geographical area in an empty list and get the size of the list that is 1.*/
    @Test
    @DisplayName("Add a new type of geographical area in an empty list")
    void newTypeGAIfSuccessVillage() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());
        ctrl1.createTypeGA("village");

        assertEquals(1, size());
    }

    /*
     * Add a new type of geographical area in a list with other different types and get the size of the list that is 2.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with other types")
    void newTypeGAIfSuccessCityAfterVillage() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA("village");
        assertEquals(1, size());

        ctrl1.createTypeGA("city");
        assertEquals(2, size());
    }


    /*
     * Add a new type of geographical area in a list with the same type and get the size of the list that remains as 1.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with same type")
    void newTypeGAIfCityAfterCity() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA("city");
        assertEquals(1, size());

        ctrl1.createTypeGA("city");
        assertEquals(1, size());
    }

    /*
     * Add new types of geographical area in a list with same and different types and get the size of the list.
     * The repetitions are not considered.
     */
    @Test
    @DisplayName("Add  new types of geographical area in a list with same and different types")
    void newTypeGAIfRepeatAndDifferentTypes() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA("city");
        assertEquals(1, size());

        ctrl1.createTypeGA("village");
        assertEquals(2, size());

        ctrl1.createTypeGA("city");
        assertEquals(2, size());

        ctrl1.createTypeGA("country");
        assertEquals(3, size());

        ctrl1.createTypeGA("country");
        assertEquals(3, size());
    }

    /*
     * Add spaces has type of geographical area in an empty list and confirm that is not possible to add that spaces
     * into the list
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in an empty list")
    void newTypeGAIfSpaceNotAddedEmptyList() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());
        ctrl1.createTypeGA(" ");

        assertEquals(0, size());
    }

    /*
     * Add spaces has type of geographical area in an list with one TypeGA and confirm that is not possible to add that
     * space into the list but is possible to add a correct one after
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in a list")
    void newTypeGAIfSpaceNotAdded() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA("village");
        assertEquals(1, size());

        ctrl1.createTypeGA("            ");
        assertEquals(1, size());

        ctrl1.createTypeGA("city");
        assertEquals(2, size());
    }

    @Test
    @DisplayName("Add a string with an unaccepted character 'Ç' type of GA")
    void newTypeGAIfSpaceNotAdded2() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA("village");
        assertEquals(1, size());

        ctrl1.createTypeGA("VillageÇ");
        assertEquals(1, size());

        ctrl1.createTypeGA("            ");
        assertEquals(1, size());

        ctrl1.createTypeGA("city");
        assertEquals(2, size());
    }

    @Test
    @DisplayName("Add a repetitive string with lower case and a title case and a upper case type of GA")
    void newTypeGAIfSpaceNotAdded3() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        assertTrue(ctrl1.createTypeGA("village"));
        assertEquals(1, size());

        assertFalse(ctrl1.createTypeGA("Village"));
        assertEquals(1, size());

        assertFalse(ctrl1.createTypeGA("VILLAGE"));
        assertEquals(1, size());

        assertFalse(ctrl1.createTypeGA("VILlage"));
        assertEquals(1, size());

        assertFalse(ctrl1.createTypeGA("village"));
        assertEquals(1, size());

        assertTrue(ctrl1.createTypeGA("city"));
        assertEquals(2, size());
    }

    @Test
    @DisplayName("Add a null type of GA")
    void newTypeGAIfSpaceNotAdded4() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertEquals(0, size());

        ctrl1.createTypeGA(null);
        assertEquals(0, size());

        assertFalse(ctrl1.createTypeGA("            "));
        assertEquals(0, size());

    }
}