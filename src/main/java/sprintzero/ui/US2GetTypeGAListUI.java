package sprintzero.ui;

import sprintzero.controllers.US2GetTypeGAListCTRL;
import sprintzero.model.TypeGA;
import sprintzero.model.TypeGAList;

import java.util.List;

public class US2GetTypeGAListUI {

    TypeGAList mTypeGAList;
    US2GetTypeGAListCTRL mCtrlUS2;

    public US2GetTypeGAListUI(TypeGAList inputList) {
        mTypeGAList = inputList;
        mCtrlUS2 = new US2GetTypeGAListCTRL (mTypeGAList);
    }

    //List in one line
    /*public void run() {
        List<TypeGA> list = mCtrlUS2.getTypeGAList ();
        System.out.println (list);
        }
        */

    //List, each element in one line
    public void run() {
        List<TypeGA> list = mCtrlUS2.getTypeGAList ();
        if (list.size () == 0) {
            System.out.println ("List of type of geographical area is empty, please insert one first");
        } else {
            System.out.println ("Current list of type of geographical area:");
            for (int position = 0; position < list.size (); position++) {
                int number = position + 1;
                System.out.println (number + " - " + list.get (position).toString ());
            }
        }
    }

}
