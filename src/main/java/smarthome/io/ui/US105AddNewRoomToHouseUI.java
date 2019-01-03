package smarthome.io.ui;

import smarthome.controller.US105AddNewRoomToHouseCTRL;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class US105AddNewRoomToHouseUI {

    Scanner read = new Scanner ( System.in );

    private US105AddNewRoomToHouseCTRL mUS105CTRL;
    private House mHouse;

    public US105AddNewRoomToHouseUI(House house) {
        mHouse = house;
        mUS105CTRL = new US105AddNewRoomToHouseCTRL(house);

    }

    /**
     * First: checks if the size of the room list in the house is 0, if so it doesn't exist and it needs to be create in the previous US.
     * Second: several cycles check if a validation method is not null. If true, the parameter is added to the Room.
     * If false (not validate) the cycle warns for the need for a new input until correct validation.
     * Third: All validations are for strings, so at this point they are "converted" to the correspondent type.
     * Fourth: The inputs are used to create a new Room and a success message is shown.
     */
    public void addRoomToTheHouse() {

            String name;
            while (true) {
                System.out.println ( "Insert the name of the room:" );
                name = nameIsValid ();
                if (name != null)
                    break;
                else
                    this.tryAgainMessage ();
            }
            String tempFloor;
            Integer floor;
            while (true) {
                System.out.println ( "Insert the floor where the room is:" );
                tempFloor = floorIsValid ();
                if (tempFloor != null)
                    break;
                else
                    this.tryAgainMessage ();
            }
            String tempLength;
            double length;
            while (true) {
                System.out.println ( "Insert the length of the room (in meters):" );
                tempLength = lengthOrWidthAreValid ();
                if (tempLength != null)
                    break;
                else
                    this.tryAgainMessage ();
            }
            String tempWidth;
            double width;
            while (true) {
                System.out.println ( "Insert the width of the room (in meters):" );
                tempWidth = lengthOrWidthAreValid ();
                if (tempWidth != null)
                    break;
                else
                    this.tryAgainMessage ();
            }
            floor = Integer.parseInt ( tempFloor );
            length = Double.parseDouble ( tempLength );
            width = Double.parseDouble ( tempWidth );


        if (mUS105CTRL.newRoom ( name, floor, length, width )) {
            System.out.println( "Success: The " + name + " on the " + floor + " floor with " + length*width + "m² was created.");
        } else {
            System.out.println("Fail! Please try again.");
        }

    }

    public void tryAgainMessage(){
        System.out.print ( "Try again. " );
    }


    public String nameIsValid() {
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


    public String floorIsValid() {
        String floor = read.nextLine ();
        if (floor == null || floor.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ( "Empty spaces are not accepted." );
            return null;
        }
        if (!floor.matches ( "^(?![\\s])\\d*" )) {
            System.out.println ( "Please use only numbers." );
            return null;
        }
        return floor;
    }


    public String lengthOrWidthAreValid() {
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
