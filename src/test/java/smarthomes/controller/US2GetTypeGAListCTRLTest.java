package smarthomes.controller;

import org.junit.jupiter.api.Test;
import smarthomes.model.TypeGA;
import smarthomes.model.TypeGAList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US2GetTypeGAListCTRLTest {


    /**
     * Add new types of geographical area in a list and then confirm that the content is the same when using
     * the getTypeGAList method.
     */
    @Test
    void getTypeGAListCorrectContent() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);
        ctrl1.newTypeGA ("village");
        ctrl1.newTypeGA ("city");

        TypeGA type1 = new TypeGA ("village");
        TypeGA type2 = new TypeGA ("city");
        List<TypeGA> expected = Arrays.asList (type1, type2);

        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL (list);
        List<TypeGA> result = ctrl2.getTypeGAList ();

        assertEquals (expected, result);
    }

    @Test
    void getTypeGAListIncorrectContent() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);
        ctrl1.newTypeGA ("village");
        ctrl1.newTypeGA ("country");

        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL (list);
        TypeGA type1 = new TypeGA ("village");
        TypeGA type2 = new TypeGA ("city");
        List<TypeGA> expected = Arrays.asList (type1, type2);

        List<TypeGA> result = ctrl2.getTypeGAList ();

        assertNotEquals (expected, result);
    }


    /**
     * Add new types of geographical area in a list and then create a new list and test if it has de same size.
     * Using the Debug is possible to see that the content of the list is the same.
     */
    @Test
    void getTypeGAListCorrectSize() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getTypeGAList ().size ());
        ctrl1.newTypeGA ("city");

        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL (list);
        List<TypeGA> list2 = ctrl2.getTypeGAList ();
        assertEquals (2, list2.size ());
    }

    @Test
    void getTypeGAListIncorrectSize() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertTrue(ctrl1.newTypeGA ("village"));
        assertEquals (1, list.getTypeGAList ().size ());
        assertTrue(ctrl1.newTypeGA ("city"));
        assertEquals (2, list.getTypeGAList ().size ());

        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL (list);
        List<TypeGA> list2 = ctrl2.getTypeGAList ();
        assertEquals (2, list2.size ());
    }


    @Test
    void showListInString() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);
        ctrl1.newTypeGA ("village");
        ctrl1.newTypeGA ("city");

        String expected = "1 - village\n2 - city\n";

        US2GetTypeGAListCTRL ctrl2 = new US2GetTypeGAListCTRL (list);
        String result = ctrl2.showListInString ();

        assertEquals (expected, result);
    }
}