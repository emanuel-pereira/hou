package smarthome.io.ui;

import smarthome.controller.US135AddPowerSourceToGridCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US135AddPowerSourceToGridUI {

    private House mHouse;
    private HouseGrid mHouseGrid;
    private HouseGridList mHGList;
    private PowerSource mPowerSource;
    private PowerSourceList mPSList;
    private US135AddPowerSourceToGridCTRL mCtrlUS135;

    Scanner read = new Scanner(System.in);

    public US135AddPowerSourceToGridUI(House house, HouseGridList hglist, PowerSourceList pslist) {
        mHouse = house;
        mHGList = hglist;
        //mPowerSource = powerSource;
        mPSList = pslist;
        mCtrlUS135 = new US135AddPowerSourceToGridCTRL(house,hglist,pslist);
    }

    public void addPowerSourceToHouseGrid() {
        if(mCtrlUS135.getHouseGridList().size() != 0) {

            System.out.println("Please select the House Grid to which you wish to add your Power Source to:");
            System.out.println(mCtrlUS135.showHouseGridListInString());
            int indexHG;
            while (true) {
                Scanner read1 = new Scanner(System.in);
                indexHG = read1.nextInt();
                if (indexHG > mCtrlUS135.getHouseGridList().size())
                    System.out.println("Please insert a valid option \n.");
                else
                    break;
            }

            String name;
            while (true) {
                System.out.println("Insert the name of the power source:");
                name = nameOrTypeIsValid();
                if (name != null)
                    break;
                else
                    this.tryAgainMessage();
            }
            String type;
            while (true) {
                System.out.println("Insert the type of power source:");
                type = nameOrTypeIsValid();
                if (type != null)
                    break;
                else
                    this.tryAgainMessage();
            }
            String tempMaxPower;
            double maxPower;
            while (true) {
                System.out.println("Insert the maximum power of the power source (in watts):");
                tempMaxPower = maxPowerOrStorageCapacityIsValid();
                if (tempMaxPower != null)
                    break;
                else
                    this.tryAgainMessage();
            }

            String tempStorageCapacity;
            double storageCapacity;
            while (true) {
                System.out.println("Insert the storage capacity of the power source (in kilowatts)");
                tempStorageCapacity = maxPowerOrStorageCapacityIsValid();
                if (tempStorageCapacity != null)
                    break;
                else
                    this.tryAgainMessage();
            }
            maxPower = Double.parseDouble(tempMaxPower);
            storageCapacity = Double.parseDouble(tempStorageCapacity);
            if (mCtrlUS135.addNewPSToGrid(indexHG, name, type, maxPower, storageCapacity)) {
                System.out.println("Success! The power source " + name + " of the " + type +
                        " type, with maximum power of " + maxPower + " watts and storage capacity of "
                        + storageCapacity + " kilowatts was created.\n\n" /*+mCtrlUS135.showPowerSourceListInString()+ "\n"*/);
                /*while(mCtrlUS135.getPowerSourceListCtrl().size()!= 0){
                    System.out.println(mCtrlUS135.showPowerSourceListInString()+"\n");
                }*/
            }
            /*if(mCtrlUS135.getPowerSourceList().size() != 0){
                System.out.println("Here is complete list of Power Sources in the current grid:\n"+mCtrlUS135.showPowerSourceListInString()+"\n\n");
            }*/
            else {
                System.out.println("Fail! Please try again.");
            }
        }
        else {
            System.out.println( "No House Grids have been found, please create (at least)one in US130");
        }
    }

    public void tryAgainMessage(){
        System.out.print ( "Try again. " );
    }

    public String nameOrTypeIsValid() {
        String name = read.nextLine ();
        if (name == null || name.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ( "Empty spaces are not accepted" );
            return null;
        }
        if (!name.matches ( "^(?![\\s]).*" )) {
            System.out.println ( "Please start with words." );
            return null;
        }
        return name;
    }

    public String maxPowerOrStorageCapacityIsValid() {
        String number = read.nextLine ();
        if (number == null || number.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ( "Empty spaces aren't accepted." );
            return null;
        }
        if (!number.matches ( "[0-9]+([,.][0-9]{1,2})?" )) {
            System.out.println ( "Please use only numbers and dots if necessary." );
            return null;
        }
        return number;
    }

}