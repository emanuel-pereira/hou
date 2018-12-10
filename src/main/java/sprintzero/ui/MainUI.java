package sprintzero.ui;

import sprintzero.model.TypeGAList;

import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        TypeGAList typeGAList = new TypeGAList ();
        int option = -1;
        Scanner read = new Scanner (System.in);
        while (option != 0) {
            System.out.println ("Click 1. US01: As System Administrator I want to define a new type of geographic area to later classify the geographical areas");
            System.out.println ("Click 0. Exit");
            break;
        }
        option = Integer.parseInt (read.nextLine ());
        switch (option) {
            case 1:
                US1CreateTypesGAUI ui1 = new US1CreateTypesGAUI (typeGAList);
                ui1.run ();
                break;
        }
    }

}
