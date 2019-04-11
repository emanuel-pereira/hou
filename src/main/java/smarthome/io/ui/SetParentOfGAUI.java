package smarthome.io.ui;

import smarthome.controller.SetParentOfGACTRL;
import smarthome.model.GAList;

public class SetParentOfGAUI {

    private SetParentOfGACTRL controlerUS7;
    private int gaIndex1;
    private int gaIndex2;

    public SetParentOfGAUI(GAList inputList) {
        controlerUS7 = new SetParentOfGACTRL(inputList);
    }

    public void run() {
        if(this.checkGAListSize()){
            UtilsUI.backToMenu();
        }
        else{
        System.out.println("Choose a Geographical Area from the list below to be inserted in another one (or insert 0 to return to Main Menu): ");
        System.out.println(controlerUS7.showListInString());
        gaIndex1 = UtilsUI.requestIntegerInInterval(1, controlerUS7.getGAListSize(), "Please insert a valid Geographical Area");
        this.run1();
        }
    }

    private boolean checkGAListSize() {
        if (controlerUS7.getGAListSize() == 0) {
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
            System.out.println("Choose a Geographical Area from the list below to set as parent of " + controlerUS7.getGaName(gaIndex1));
            System.out.println(controlerUS7.showListInString());
            gaIndex2 = UtilsUI.requestIntegerInInterval(1, controlerUS7.getGAListSize(), "Please insert a valid geographical area");

            if (gaIndex1 == gaIndex2) {
                System.out.println("Error. Please choose a different area");
                x = true;
            } else {
                x = false;
            }

        }

        controlerUS7.setParentofGA(gaIndex1, gaIndex2);
        System.out.println("Success, the area " + controlerUS7.getGaName(gaIndex1) + " was added to " + controlerUS7.getGaName(gaIndex2));
        UtilsUI.backToMenu();
    }
}
