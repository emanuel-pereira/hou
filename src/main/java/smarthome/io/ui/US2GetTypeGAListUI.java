package smarthome.io.ui;

import smarthome.controller.US2GetTypeGAListCTRL;
import smarthome.model.TypeGAList;

public class US2GetTypeGAListUI {


    TypeGAList mTypeGAList;
    US2GetTypeGAListCTRL mCtrlUS2;


    public US2GetTypeGAListUI(TypeGAList inputList) {
        mTypeGAList = inputList;
        mCtrlUS2 = new US2GetTypeGAListCTRL (mTypeGAList);
    }


    /**
     * Ask US2CTRL for the list and show each element in several lines.
     * If size 0 print message empty.
     * If not, print the list, in which every position is converted from TypeGA to String.
     * The last statement allows the numbering of each element.
     */
    public void run() {
        if (mCtrlUS2.getTypeGAList().isEmpty()) {
            System.out.println ("List of type of geographical area is empty, please insert one first");
        } else {
            System.out.println ("Current list of types of geographical area:");
                System.out.println (mCtrlUS2.showListInString ());
            }
        }
}
