package sprintzero.ui;

import sprintzero.model.GAList;
import sprintzero.model.TypeGAList;

import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        TypeGAList typeGAList = new TypeGAList();
        GAList GA = new GAList();
        int option = -1;
        Scanner keyboard = new Scanner(System.in);
        while (option != 0) {
            System.out.println("Click 1. US01: As System Administrator I want to define a new type of geographical area to later classify the geographical areas");
            System.out.println("Click 2. US02: As System Administrator I want to get the list of previously defined types of geographical areas");
            System.out.println("Click 3. US03: As System Administrator I want create a new geographical area");

            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US1CreateTypesGAUI ui1 = new US1CreateTypesGAUI(typeGAList);
                    ui1.run();
                    break;
                case 2:
                    US2GetTypeGAListUI ui2 = new US2GetTypeGAListUI (typeGAList);
                    ui2.run ();
                    break;
                case 3:
                    US3CreateGAUI ui3 = new US3CreateGAUI(GA);
                    ui3.run();
                    break;
            }
        }
    }

}
