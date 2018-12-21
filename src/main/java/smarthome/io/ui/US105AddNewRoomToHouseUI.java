package smarthome.io.ui;

import smarthome.controller.US105AddNewRoomToHouseCTRL;
import smarthome.model.Room;

import java.util.List;
import java.util.Scanner;

public class US105AddNewRoomToHouseUI {

    private List<Room> mRoomList;
    private US105AddNewRoomToHouseCTRL mUS105CTRL;

    public US105AddNewRoomToHouseUI(List<Room> inputList) {
        mRoomList = inputList;

    }

    Scanner keyboard = new Scanner ( System.in );

    /**
     * First: checks if the size of the room list in the house is 0, if so it doesn't exist and it needs to be create in the previous US.
     * Second: several cycles check if a validation method is not null. If true, the parameter is added to the Room.
     * If false (not validate) the cycle warns for the need for a new input until correct validation.
     * Third: All validations are for strings, so at this point they are "converted" to the correspondent type.
     * Fourth: The inputs are used to create a new Room and a success message is shown.
     */
    public void run() {

        if (mRoomList.size () != 0) {

            String name;
            while (true) {
                System.out.println ( "Insert the name of the room:" );
                name = nameIsValid ();
                if (name != null)
                    break;
                else
                    System.out.print ( "Try again. " );
            }
            String tempFloor;
            Integer floor;
            while (true) {
                System.out.println ( "Insert the floor where the room is:" );
                tempFloor = floorIsValid ();
                if (tempFloor != null)
                    break;
                else
                    System.out.print ( "Try again. " );
            }
            String tempHeight;
            double height;
            while (true) {
                System.out.println ( "Insert the height of the room (in meters):" );
                tempHeight = heightOrWidthAreValid ();
                if (tempHeight != null)
                    break;
                else
                    System.out.print ( "Try again. " );
            }
            String tempWidth;
            double width;
            while (true) {
                System.out.println ( "Insert the width of the room (in meters):" );
                tempWidth = heightOrWidthAreValid ();
                if (tempWidth != null)
                    break;
                else
                    System.out.print ( "Try again. " );
            }
            floor = Integer.parseInt ( tempFloor );
            height = Double.parseDouble ( tempHeight );
            width = Double.parseDouble ( tempWidth );
            System.out.println ( "Success: The " + name + " on the " + floor + " floor with " + height*width + "m2 was created." );
            mUS105CTRL.newRoom ( name, floor, height, width );
        } else {
            System.out.println ( "No House was found, please insert one first in US101. \n" );
        }
    }


    public String nameIsValid() {
        String name = keyboard.nextLine ();
        if (name == null || name.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ( "Empty spaces are not accepted." );
            return null;
        }
        if (!name.matches ( "^(?![\\s]).*" )) {
            System.out.println ( "Please start with words." );
            return null;
        }
        return name;
    }


    public String floorIsValid() {
        String floor = keyboard.nextLine ();
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


    public String heightOrWidthAreValid() {
        String number = keyboard.nextLine ();
        if (number == null || number.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ( "Empty spaces are not accepted." );
            return null;
        }
        if (!number.matches ( "[0-9]+([,.][0-9]{1,2})?" )) {
            System.out.println ( "Please use only numbers and dots if necessary." );
            return null;
        }
        return number;
    }

}
