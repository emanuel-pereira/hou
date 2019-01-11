package smarthome.io.ui;

import smarthome.controller.US253AddSensorToRoomCTRL;
import smarthome.controller.US605CurrentTempRoomCTRL;
import smarthome.model.House;
import smarthome.model.RoomList;
import smarthome.model.SensorList;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class US605CurrentTempRoomUI {

    Scanner read = new Scanner (System.in);

    private House mHouse;
    private RoomList mRoomList;
    private SensorList mSensorList;
    private US605CurrentTempRoomCTRL mCTRL605;
    private SensorTypeList mSensorTypeList;

    private US253AddSensorToRoomCTRL mCtrlUS253;


    public US605CurrentTempRoomUI(House house, SensorTypeList sensorTypeList) {
        mHouse = house;
        mRoomList = mHouse.getRoomListFromHouse ();
        mCTRL605 = new US605CurrentTempRoomCTRL (house,sensorTypeList);
        mSensorTypeList = sensorTypeList;
        mCtrlUS253 = new US253AddSensorToRoomCTRL (house, sensorTypeList);

    }

    private String temp = "temperature";
    private int indexRoom;


    public void run() {
        if (mCTRL605.checkIfRequiredSensorTypeExists (temp)) {
            this.checkIfRoomExists ();
        } else System.out.println ("Please ask the Administrator to create a Temperature Sensor Type in the System");
    }


    public void checkIfRoomExists() {
        while (true) {
            System.out.println ("Choose the Room for which you want add this sensor, from the list below:");
            System.out.println (mCTRL605.showRoomListInString ());
            indexRoom = read.nextInt ();
            this.checkIfTempSensorExistInRooms ();
            if (indexRoom > mSensorTypeList.getSensorTypeList ().size ())
                System.out.println ("Please insert a valid option \n.");
            else break;
        }
    }

    private void checkIfTempSensorExistInRooms() {
         if (mRoomList.getSensorListInRoomByType (temp) != 0) {
            System.out.println ("Current temperature in the room: " + mCTRL605.getCurrentTemp (indexRoom));
        } else System.out.println ("Please ask the House Administrator to add a Temperature Sensor to this Room");
    }



}
