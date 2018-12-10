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
        System.out.println ("Insert the name of the new type of geographical area:");
        Scanner read = new Scanner (System.in);
        String name = read.nextLine ();
        if (mCtrlUS1.newTypeGA (name) == true) System.out.println ("Success");
        else System.out.println ("Failure. Please try again");
    }


}
