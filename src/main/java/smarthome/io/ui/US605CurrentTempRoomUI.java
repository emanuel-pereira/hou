package smarthome.io.ui;

import smarthome.controller.US605CurrentTempRoomCTRL;
import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class US605CurrentTempRoomUI {

    Scanner read = new Scanner (System.in);

    private House mHouse;
    private US605CurrentTempRoomCTRL mCTRL605;
    private String mTemperature = "temperature";
    private int mRoomIndex;


    public US605CurrentTempRoomUI(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mCTRL605 = new US605CurrentTempRoomCTRL (house, sensorTypeList);
    }



    /**
     * Checks if the required sensor type Temperature was created by the Administrator
     */
    public void run() {
        if (mCTRL605.checkIfRequiredSensorTypeExists (mTemperature)) {
            this.checkIfRoomExists ();
        } else System.out.println ("Please ask the Administrator to create a Temperature Sensor Type in the System");
    }

    /**
     * Checks if there are any rooms created. If so, shows a list of rooms that the user can choose from
     */
    public void checkIfRoomExists() {

        if (mCTRL605.getRoomList ().size () > 0) {
            while (true) {
                System.out.println ("Choose the Room for which you want add this sensor, from the list below:");
                System.out.println (mCTRL605.showRoomListInString ());
                mRoomIndex = read.nextInt ();
                if (mRoomIndex > mCTRL605.getRoomList ().size ())
                    System.out.println ("Please insert a valid option \n");
                else break;
            }
            this.checkIfTempSensorExistInRooms ();
        } else System.out.println ("Please ask the House Administrator to create a Room");
    }

    /**
     * Checks if the sensor type Temperature exist in the chosen room. If so, shows the current temperature of that room
     */
    private void checkIfTempSensorExistInRooms() {
        if (mCTRL605.checkIfSensorTypeExistsInRoom (mRoomIndex, mTemperature)) {
            System.out.println ("Current temperature in the room: " + mCTRL605.getCurrentTemp (mRoomIndex));
        } else System.out.println ("Please ask the House Administrator to add a Temperature Sensor to this Room");
    }


}
