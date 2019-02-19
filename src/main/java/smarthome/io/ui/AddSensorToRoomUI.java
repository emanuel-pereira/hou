package smarthome.io.ui;

import smarthome.controller.AddSensorToRoomCTRL;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class AddSensorToRoomUI {

    private SensorTypeList mSensorTypeList;
    private House mHouse;
    private RoomList mRoomList;
    private AddSensorToRoomCTRL mCtrlUS253;
    private Scanner read = new Scanner(System.in);
    private ReadingList mReadingList = new ReadingList();
    private String mEmptySpacesMsg = "Empty spaces are not accepted";

    public AddSensorToRoomUI(House house, SensorTypeList sensorTypeList) {
        mCtrlUS253 = new AddSensorToRoomCTRL(house, sensorTypeList);
        mHouse = house;
        mSensorTypeList = sensorTypeList;
        mRoomList = mHouse.getRoomList();
    }


    public void run() {
        if (!mRoomList.getRoomList().isEmpty()) {
            if (!mSensorTypeList.getSensorTypeList().isEmpty()) {
                String name;
                while (true) {
                    System.out.println("Insert a name for the sensor:");
                    name = nameIsValid();
                    if (name != null)
                        break;
                    else
                        System.out.println(UtilsUI.insertValidParameter("name"));
                }

                System.out.println("Please insert a start date of sensor");
                GregorianCalendar calendar = UtilsUI.requestDate("Please insert a valid date");

                int dataTypeIndex;
                while (true) {
                    System.out.println("Choose a data type for the sensor from one of the data types below:");
                    System.out.println(mSensorTypeList.showSensorTypeListInString());
                    dataTypeIndex = read.nextInt();
                    if (dataTypeIndex > mSensorTypeList.getSensorTypeList().size())
                        System.out.println(UtilsUI.insertValidOptionMsg());
                    else break;
                }

                String unit;
                while (true) {
                    System.out.println("Insert the unit the sensor will read:");
                    unit = unitIsValid();
                    if (unit != null)
                        break;
                    else
                        System.out.println(UtilsUI.insertValidParameter("name"));
                }

                String option;
                while (true) {
                    System.out.println("Do you want to insert readings for the sensor(y/n)?");
                    option = read.nextLine();
                    if (option.matches("n")) {
                        break;
                    }
                    if (option.matches("y")) {

                        double readingValue;


                        System.out.println("Insert the value of the reading:");
                        readingValue = read.nextDouble();
                        read.nextLine();

                        System.out.println("Please insert date and time of reading");
                        GregorianCalendar date = UtilsUI.requestDateTime("Please insert a valid date and time in yyyy-mm-dd hh:mm format");

                        Reading r = mReadingList.newReading(readingValue, date);
                        mReadingList.addReading(r);
                    }
                }

                int indexRoom;
                while (true) {
                    System.out.println("Choose the Room for which you want add this sensor, from the list below:");
                    System.out.println(mRoomList.showRoomListInString());
                    indexRoom = read.nextInt();
                    if (indexRoom > mRoomList.getRoomList().size())
                        System.out.println(UtilsUI.insertValidOptionMsg());
                    else break;
                }
                mCtrlUS253.addNewSensorToRoom(name, calendar, dataTypeIndex, indexRoom, unit, mReadingList);
                System.out.println("Success");
            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
    }


    public String nameIsValid() {
        String name = read.nextLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        if (!name.matches("[A-Za-z0-9]*")) {
            System.out.println("Please insert only alphanumeric characters.");
            return null;
        }
        return name;
    }

    private String unitIsValid() {
        String unit = read.nextLine();
        if (unit == null || unit.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        return unit;
    }


    public void run2() {
        if (!mRoomList.getRoomList().isEmpty()) {
            while (true) {
                int indexRoom;
                while (true) {
                    System.out.println("Choose the Room for which you want to list all sensor, from the list below:");
                    System.out.println(mRoomList.showRoomListInString());
                    indexRoom = read.nextInt();
                    if (indexRoom > mRoomList.getRoomList().size())
                        System.out.println(UtilsUI.insertValidOptionMsg());
                    else break;
                }
                System.out.println("The sensors in the " + mRoomList.getRoomList().get(indexRoom - 1).getName() + " are: ");
                System.out.println(mRoomList.getRoomList().get(indexRoom - 1).getSensorListInRoom().showSensorListInString());
                break;
            }
        } else
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
    }
}
