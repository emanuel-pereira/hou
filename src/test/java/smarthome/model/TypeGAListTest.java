package smarthome.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static smarthome.model.TypeGAList.*;

class TypeGAListTest {

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
    void newTypeGATest() {

        TypeGA village = newTypeGA("village");

        assertEquals("village", village.getType());
    }

    @Test
    void addTypeGATest() {
        TypeGA city = newTypeGA("city");

        assertTrue(addTypeGA(city));

        List<TypeGA> expectedResult = Arrays.asList(city);
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @Test
    void getTypeGAByStringTest() {
        TypeGA city = newTypeGA("city");
        TypeGA village = newTypeGA("village");
        TypeGA street = newTypeGA("street");

        addTypeGA(city);
        addTypeGA(village);
        addTypeGA(street);

        TypeGA result = TypeGAList.get("street");

        assertEquals(street, result);
    }

    @Test
    void getTypeGAByStringTestNull() {
        TypeGA city = newTypeGA("city");
        TypeGA village = newTypeGA("village");
        TypeGA street = newTypeGA("street");

        addTypeGA(city);
        addTypeGA(village);
        addTypeGA(street);

        TypeGA result = TypeGAList.get("country");

        assertEquals(null, result);
    }

    @Test
    void getTypeGAByIndexTest() {
        TypeGA city = newTypeGA("city");
        TypeGA village = newTypeGA("village");
        TypeGA street = newTypeGA("street");

        addTypeGA(city);
        addTypeGA(village);
        addTypeGA(street);

        TypeGA result = TypeGAList.get(2);

        assertEquals(street, result);
    }

    @Test
    void checkIfContainsTypeTestTrue() {
        TypeGA city = newTypeGA("city");
        TypeGA village = newTypeGA("village");
        TypeGA street = newTypeGA("street");

        addTypeGA(city);
        addTypeGA(village);
        addTypeGA(street);

        boolean result = TypeGAList.contains(street);

        assertTrue(result);
    }

    @Test
    void checkIfContainsTypeTestFalse() {
        TypeGA city = newTypeGA("city");
        TypeGA village = newTypeGA("village");
        TypeGA street = newTypeGA("street");

        addTypeGA(city);
        addTypeGA(street);

        boolean result = TypeGAList.contains(village);

        assertFalse(result);
    }

    @DisplayName("set type of GA for village")
    @Test
    public void defineTypesOfGeographicalAreaVillage() {
        TypeGA village = newTypeGA("village");

        assertEquals(0, size());
        addTypeGA(village);
        assertEquals(1, size());

        List<TypeGA> expectedResult = Arrays.asList(village);
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @DisplayName("set Already Contained type of GA for village")
    @Test
    public void defineAlreadyContainedTypeOfGeographicalAreaVillage() {

        TypeGA village1 = newTypeGA("village");
        TypeGA village2 = newTypeGA("village");

        assertEquals(0, getTypeGAList().size());
        addTypeGA(village1);
        assertEquals(1, getTypeGAList().size());
        addTypeGA(village2);
        assertEquals(1, getTypeGAList().size());

        List<TypeGA> expectedResult = Arrays.asList(village1);
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @DisplayName("empty type of GA")
    @Test
    public void nameEmpty() {
        TypeGA village1 = newTypeGA(" ");

        assertEquals(0, getTypeGAList().size());
        addTypeGA(village1);
        assertEquals(0, getTypeGAList().size());

        List<TypeGA> expectedResult = Arrays.asList();
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @DisplayName("Null type of GA")
    @Test
    public void nullTypeGA() {

        boolean thrown = false;
        try {
            newTypeGA(null);
        } catch (NullPointerException e) {
            thrown = false;
        }
        assertFalse(thrown);

    }

}