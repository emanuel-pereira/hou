package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertEquals("village", village.toString());
    }

    @Test
    void addTypeGATest() {
        TypeGA city = newTypeGA("city");

        addTypeGA(city);
        List<TypeGA> expectedResult = Arrays.asList(city);
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @DisplayName("set type of GA for village")
    @Test
    public void defineTypesOfGeographicalAreaVillage() {
        TypeGA village = newTypeGA("village");

        assertEquals(0, getTypeGAList().size());
        addTypeGA(village);
        assertEquals(1, getTypeGAList().size());

        List<TypeGA> expectedResult = Arrays.asList(village);
        List<TypeGA> result = getTypeGAList();

        assertEquals(expectedResult, result);
    }

    @DisplayName("set Already Contained type of GA for village")
    @Test
    public void defineAlreadyContainedTypeOfGeograficalAreaVillage() {

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