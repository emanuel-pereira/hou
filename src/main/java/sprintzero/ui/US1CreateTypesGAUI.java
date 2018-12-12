package sprintzero.ui;

import sprintzero.controllers.US1CreateTypeGACTRL;
import sprintzero.model.TypeGAList;

import java.util.Scanner;

public class US1CreateTypesGAUI {

    //Atributos

    TypeGAList mTypeGAList;
    US1CreateTypeGACTRL mCtrlUS1;


    //Contrutor

    public US1CreateTypesGAUI(TypeGAList inputList) {
        mTypeGAList = inputList;
        mCtrlUS1 = new US1CreateTypeGACTRL (inputList);
    }

    //Métodos


    public void run() {
        Scanner read = new Scanner (System.in);
        while (true) {
            System.out.println ("Insert the name of the new type of geographical area (or click r to return to Main Menu):");
            String name = read.nextLine ();
            if ("r".equals (name)) {
                System.out.println ("Return to Main Menu");
                MainUI.main (null);
            }
            if (mCtrlUS1.newTypeGA (name)) System.out.println ("Success: " + name + " added.");
            else System.out.println ("Failure. Please try again.");
        }
    }


}
