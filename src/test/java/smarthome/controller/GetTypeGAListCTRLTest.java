package smarthome.controller;

import org.junit.Test;
import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetTypeGAListCTRLTest {

    /*
     * Add new types of geographical area in a list and then confirm that the content is the same when using
     * the getTypeGAList method.
     */
    @Test
    void getTypeGAListCorrectContent() {
        TypeGAList list = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(list);
        ctrl1.createTypeGA ("village");
        ctrl1.createTypeGA ("city");

        TypeGA type1 = new TypeGA ("village");
        TypeGA type2 = new TypeGA ("city");
        List<TypeGA> expected = Arrays.asList (type1, type2);

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(list);
        List<TypeGA> result = ctrl2.getTypeGAList ();

        assertEquals (expected, result);
    }

    @Test
    void getTypeGAListIncorrectContent() {
        TypeGAList list = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(list);
        ctrl1.createTypeGA ("village");
        ctrl1.createTypeGA ("country");

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(list);
        TypeGA type1 = new TypeGA ("village");
        TypeGA type2 = new TypeGA ("city");
        List<TypeGA> expected = Arrays.asList (type1, type2);

        List<TypeGA> result = ctrl2.getTypeGAList ();

        assertNotEquals (expected, result);
    }


    /*
     * Add new types of geographical area in a list and then create a new list and test if it has de same size.
     * Using the Debug is possible to see that the content of the list is the same.
     */
    @Test
    void getTypeGAListCorrectSize() {
        TypeGAList list = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(list);

        ctrl1.createTypeGA ("village");
        assertEquals (1, list.getTypeGAList ().size ());
        ctrl1.createTypeGA ("city");

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(list);
        List<TypeGA> list2 = ctrl2.getTypeGAList ();
        assertEquals (2, list2.size ());
    }

    @Test
    void getTypeGAListIncorrectSize() {
        TypeGAList list = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(list);

        assertTrue(ctrl1.createTypeGA ("village"));
        assertEquals (1, list.getTypeGAList ().size ());
        assertTrue(ctrl1.createTypeGA ("city"));
        assertEquals (2, list.getTypeGAList ().size ());

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(list);
        List<TypeGA> list2 = ctrl2.getTypeGAList ();
        assertEquals (2, list2.size ());
    }


    @Test
    void showListInString() {
        TypeGAList list = new TypeGAList ();
        NewTypeGACTRL ctrl1 = new NewTypeGACTRL(list);
        ctrl1.createTypeGA ("village");
        ctrl1.createTypeGA ("city");

        String expected = "1 - village\n2 - city\n";

        GetTypeGAListCTRL ctrl2 = new GetTypeGAListCTRL(list);
        String result = ctrl2.showListInString ();

        assertEquals (expected, result);
    }
}