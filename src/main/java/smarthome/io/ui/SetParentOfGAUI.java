package smarthome.io.ui;

import smarthome.controller.CLI.SetParentOfGACTRL;
import smarthome.model.GAList;

public class SetParentOfGAUI {

    private final SetParentOfGACTRL ctrl;
    private int gaIndex1;
    private int gaIndex2;

    public SetParentOfGAUI(GAList inputList) {
        ctrl = new SetParentOfGACTRL(inputList);
    }

    public void run() {
        if(this.checkGAListSize()){
            UtilsUI.backToMenu();
        }
        else{
        System.out.println("Choose a Geographical Area from the list below to be inserted in another one (or insert 0 to return to Main Menu): ");
            System.out.println(ctrl.showListInString());
            gaIndex1 = UtilsUI.requestIntegerInInterval(1, ctrl.getGAListSize(), "Please insert a valid Geographical Area");
        this.run1();
        }
    }

    private boolean checkGAListSize() {
        if (ctrl.getGAListSize() == 0) {
            System.out.println("There no Geographical Areas. Please add at least one.");
            return true;
        }
        else{
            return false;
        }
    }

    private void run1() {

        boolean x = true;
        while (x) {


            System.out.println("Success!");
            System.out.println("Choose a Geographical Area from the list below to set as parent of " + ctrl.getGaName(gaIndex1));
            System.out.println(ctrl.showListInString());
            gaIndex2 = UtilsUI.requestIntegerInInterval(1, ctrl.getGAListSize(), "Please insert a valid geographical area");

            if (gaIndex1 == gaIndex2) {
                System.out.println("Error. Please choose a different area");
                x = true;
            } else {
                x = false;
            }

        }

        ctrl.setParentofGA(gaIndex1, gaIndex2);
        System.out.println("Success, the area " + ctrl.getGaName(gaIndex1) + " was added to " + ctrl.getGaName(gaIndex2));
        UtilsUI.backToMenu();
    }
}
