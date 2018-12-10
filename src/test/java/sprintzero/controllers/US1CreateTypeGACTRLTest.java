package sprintzero.controllers;

import org.junit.jupiter.api.Test;
import sprintzero.model.TypeGA;
import sprintzero.model.TypeGAList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class US1CreateTypeGACTRLTest {

    @Test
    void newTypeGAIfSuccessVillage() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals(0, list.getGAList ().size ());
        ctrl1.newTypeGA ("village");

        assertEquals(1, list.getGAList ().size ());
    }



}