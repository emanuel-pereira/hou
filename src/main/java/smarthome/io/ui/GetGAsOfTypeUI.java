package smarthome.io.ui;

import smarthome.controller.GetGAsOfTypeCTRL;
import smarthome.model.GAList;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class GetGAsOfTypeUI {

    private GAList mGAList;
    private TypeGAList mTypeGAList;
    private GetGAsOfTypeCTRL mCtrlUS4;

    public GetGAsOfTypeUI(GAList inputGAList, TypeGAList inputTypeList) {
        mGAList = inputGAList;
        mTypeGAList = inputTypeList;
        mCtrlUS4 = new GetGAsOfTypeCTRL(mGAList, mTypeGAList);
    }

    public void checkLists() {
        if(mTypeGAList.size() == 0){
            System.out.println("There are no Types of Geographical Area. Please create some first.");
            UtilsUI.backToMenu();
            return;
        }

        if(mGAList.size() == 0 && mTypeGAList.size() != 0){
            System.out.println("There are no Geographical Areas Please create some first.");
            UtilsUI.backToMenu();
            return;
        }

        else {
            this.run();
        }
    }

    public void run() {
        Scanner read = new Scanner(System.in);

        System.out.println("Choose the Type of Geographical Area you wish to see from the list below.");
        //calls the list of previously inserted types for the user to choose from.
        System.out.println ("Current list of types of geographical area:");
        System.out.println (mCtrlUS4.showListTypeGA());

        //user chooses the number of type he wishes to see.
        int typeIndex = UtilsUI.requestIntegerInInterval(1,mTypeGAList.size(),"Please choose a valid Type");
        String areaType = mTypeGAList.getTypeGAList().get(typeIndex - 1).toString();

        //if there are GAs from the chosen type they are returned
        if (!mCtrlUS4.getGAListFromType(typeIndex).isEmpty()) {
            System.out.println("Here are the Geographical Areas from " + areaType + " type:\n" + mCtrlUS4.showListGAFromType(typeIndex));
            UtilsUI.backToMenu();
        }
        //if there are no GAs from the chosen type the following message is shown
        else{
            System.out.println("No results were found. Please try again.\n\n");
            UtilsUI.backToMenu();
        }
    }

}
