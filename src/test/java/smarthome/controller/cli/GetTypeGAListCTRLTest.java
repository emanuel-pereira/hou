package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static smarthome.model.TypeGAList.getTypeGAListInstance;
import static smarthome.model.TypeGAList.size;


class GetTypeGAListCTRLTest {

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
     * Add new types of geographical area in a list and then confirm that the content is the same when using
     * the getTypeGAList method.
     */
    @Test
    void getTypeGAListCorrectContent() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();
        ctrl1.createTypeGA("village");
        ctrl1.createTypeGA("city");

        TypeGA type1 = new TypeGA("village");
        TypeGA type2 = new TypeGA("city");
        List<TypeGA> expected = Arrays.asList(type1, type2);

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        List<TypeGA> result = ctrl2.getTypeGAListCTRL();

        assertEquals(expected, result);
    }

    @Test
    void getTypeGAListIncorrectContent() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();
        ctrl1.createTypeGA("village");
        ctrl1.createTypeGA("country");

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        TypeGA type1 = new TypeGA("village");
        TypeGA type2 = new TypeGA("city");
        List<TypeGA> expected = Arrays.asList(type1, type2);

        List<TypeGA> result = ctrl2.getTypeGAListCTRL();

        assertNotEquals(expected, result);
    }


    /*
     * Add new types of geographical area in a list and then create a new list and test if it has de same size.
     * Using the Debug is possible to see that the content of the list is the same.
     */
    @Test
    void getTypeGAListCorrectSize() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        ctrl1.createTypeGA("village");
        assertEquals(1, size());
        ctrl1.createTypeGA("city");

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        List<TypeGA> list2 = ctrl2.getTypeGAListCTRL();
        assertEquals(2, list2.size());
    }

    @Test
    void getTypeGAListIncorrectSize() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();

        assertTrue(ctrl1.createTypeGA("village"));
        assertEquals(1, size());
        assertTrue(ctrl1.createTypeGA("city"));
        assertEquals(2, size());

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        List<TypeGA> list2 = ctrl2.getTypeGAListCTRL();
        assertEquals(2, list2.size());
    }


    @Test
    void showListInString() {

        NewTypeGACTRL ctrl1 = new NewTypeGACTRL();
        ctrl1.createTypeGA("village");
        ctrl1.createTypeGA("city");

        String expected = "1 - village\n2 - city\n";

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL();
        String result = ctrl2.showListInString();

        assertEquals(expected, result);
    }

}