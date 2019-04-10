package smarthome.io.ui;

import smarthome.controller.AddPowerSourceToGridCTRL;

import java.util.ArrayList;

public class AddPowerSourceToGridUI {

    private AddPowerSourceToGridCTRL ctrl;
    private int indexOfHG;
    private String name;
    private String type;
    private double maxPower;
    private double storageCapacity;

    public AddPowerSourceToGridUI() {
        this.ctrl = new AddPowerSourceToGridCTRL();
    }

    public void checkIfHGListIsEmpty() {
        if (this.ctrl.getHGListSizeCtrl() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid first.");
            return;
        }
        this.selectSubMenu();
    }

    private void selectSubMenu() {
        int option = -1;
        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Add a Power Source to a Grid ");
            options.add("[2] List Power Sources in a Grid");
            options.add("[0] Exit");

            UtilsUI.showList("Choose an option:", options, false,5);

            option = UtilsUI.requestIntegerInInterval(0,2,"Please choose a valida option(1 or 2), or 0 to return to the previous Menu");

            switch (option) {
                case 1:
                    this.selectHGAddPS();
                    break;
                case 2:
                    this.selectHGListPS();
                    break;
                default:
                    //no action needed
            }
        }
    }

    private void selectHGListPS() {
        this.showAndSelectHG();
        this.listPSinHG();

    }

    private void listPSinHG() {
        if (hgPSListIsEmpty()) {
            UtilsUI.backToMenu();
        }
        else{
            System.out.println("List of Power Sources attached to " + this.ctrl.getHouseGridName(this.indexOfHG) + ":");
            System.out.println(this.ctrl.showPowerSourceListInString(this.indexOfHG));
            UtilsUI.backToMenu();
        }
    }

    private boolean hgPSListIsEmpty() {
        if (ctrl.getPSListSizeCtrl(this.indexOfHG) == 0) {
            System.out.println("List of Power Sources in " + this.ctrl.getHouseGridName(this.indexOfHG) + " is empty. Please add one first.");
            return true;
        }
        return false;
    }

    private void selectHGAddPS() {
        this.showAndSelectHG();
        this.insertPSName();
    }

    private void insertPSName(){
        System.out.println("Insert the Power Source name:");
        this.name = UtilsUI.requestText("Please insert a valid name");
        this.insertPSType();
    }

    private void insertPSType(){
        System.out.println("Insert the Power Source type:");
        this.type = UtilsUI.requestText("Please insert a valid type");
        this.insertPSMaxPower();
    }

    private void insertPSMaxPower() {
        System.out.println("Insert the Maximum Power (kW):");
        this.maxPower = UtilsUI.requestDoubleInInterval(-1,Double.MAX_VALUE,"Please insert a numeric positive value");
        this.insertPSStorageCapacity();
    }

    private void insertPSStorageCapacity() {
        System.out.println("Insert the Storage Capacity (kW):");
        this.storageCapacity = UtilsUI.requestDoubleInInterval(0,Double.MAX_VALUE-100,"Please insert a numeric positive value");
        this.addPowerSource();
    }

    private void addPowerSource() {
        if(UtilsUI.confirmOption("Continue?(y/n)\n", "Please type y for Yes or n for No.")){
            this.ctrl.addNewPSToGrid(this.indexOfHG, this.name, this.type, this.maxPower, this.storageCapacity);
            System.out.println("The following Power Source was successfully created:" +
                    "\n[NAME]: " + this.name +
                    "\n[TYPE]: " + this.type +
                    "\n[MAX POWER]: " + UtilsUI.formatDecimal(this.maxPower,2) +
                    "\n[STORAGE CAPACITY]: " + UtilsUI.formatDecimal(this.storageCapacity,2));
            UtilsUI.backToMenu();
        }
        else{
            System.out.println("Operation was canceled!");
            UtilsUI.backToMenu();
        }

    }

    private void showAndSelectHG() {
        System.out.println("Choose a house grid from the list below to add a Power Source to it:");
        System.out.println(this.ctrl.getHGListInStringCtrl());
        this.indexOfHG = UtilsUI.requestIntegerInInterval(1, this.ctrl.getHGListSizeCtrl(),"Please insert a valid house grid index");
    }
}
