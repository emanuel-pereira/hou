package sprintzero.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sprintzero.model.GAList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class US3CreateGACTRLTest {

    @Test
    void newGA() {
        GAList list = new GAList ();
        US3CreateGACTRL ctrl1 = new US3CreateGACTRL (list);

        assertEquals(0, list.getGAList ().size ());
        assertTrue(ctrl1.newGA ("Porto", "district", 20, 20, 1, 3, -10));

        assertEquals(1, list.getGAList ().size ());
    }


    @Test
    @DisplayName("Add a new geographical area on a list with other GA's")
    void newGAonListwithOthersGAs() {
        GAList list = new GAList ();
        US3CreateGACTRL ctrl1 = new US3CreateGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA("Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (1, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (2, list.getGAList ().size ());
    }


    /**
     * Add a new geographical area in a list with the same GA and get the size of the list that remains as 1.
     */
    @Test
    @DisplayName("Add a new geographical area in a list with the same GA")
    void checkifGAalreadyExistsAndisNotAdded() {
        GAList list = new GAList ();
        US3CreateGACTRL ctrl1 = new US3CreateGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (1, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (1, list.getGAList ().size ());
    }

    @Test
    @DisplayName("Add new geographical area in a list with the same and different GA's")
    void newGAIfRepeatAndDifferentGAs() {
        GAList list = new GAList ();
        US3CreateGACTRL ctrl1 = new US3CreateGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (1, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Lisboa", "district", 60, 20, 100, 200, -11));
        assertEquals (2, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Porto", "district", 20, 20, 1, 3, -10));
        assertEquals (2, list.getGAList ().size ());

        assertTrue(ctrl1.newGA ("Braga", "district", 10, 10, 50, 30, 1));
        assertEquals (3, list.getGAList ().size ());

        assertFalse(ctrl1.newGA ("Braga", "district", 10, 10, 50, 30, 1));
        assertEquals (3, list.getGAList ().size ());
    }
}
