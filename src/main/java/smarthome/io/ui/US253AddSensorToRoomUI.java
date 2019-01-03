package smarthome.io.ui;

import smarthome.controller.US253AddSensorToRoomCTRL;
import smarthome.model.DataTypeList;
import smarthome.model.Room;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class US253AddSensorToRoomUI {

    private DataTypeList mDataTypeList;
    private US253AddSensorToRoomCTRL mCtrlUS253;
    private List<Room> mRoomList;


    public US253AddSensorToRoomUI(List<Room> inputList, DataTypeList dataTypeList) {
        mCtrlUS253 = new US253AddSensorToRoomCTRL(dataTypeList, inputList);
        mRoomList = inputList;
        mDataTypeList = dataTypeList;
    }

    Scanner read = new Scanner(System.in);

    String tempYear;
    String tempDay;
    String tempMonth;


    public void run() {
        if (mRoomList.size() != 0) {
            if (mDataTypeList.getDataTypeList().size() != 0) {
                String name;
                while (true) {
                    System.out.println("Insert a name for the sensor:");
                    name = nameIsValid();
                    if (name != null)
                        break;
                    else
                        System.out.println("Please insert a valid name");
                }
                int year;
                while (true) {
                    System.out.println("Insert the year when the sensor will start(equal or greater than current year):");
                    tempYear = yearIsValid();
                    if (tempYear != null)
                        break;
                }
                int month;
                while (true) {
                    System.out.println("Insert the month when the sensor will start(insert values between 1 and 12):");
                    tempMonth = monthIsValid();
                    if (tempMonth != null)
                        break;
                }
                int day;
                while (true) {
                    System.out.println("Insert the day when the sensor will start:");
                    tempDay = dayIsValid();
                    if (tempDay != null)
                        break;
                }
                year = Integer.parseInt(tempYear);
                month = Integer.parseInt(tempMonth);
                day = Integer.parseInt(tempDay);

                GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                int dataTypeIndex;
                while (true) {
                    System.out.println("Choose a data type for the sensor from one of the data types below:");
                    System.out.println(mCtrlUS253.showDataTypeListInString());
                    dataTypeIndex = read.nextInt();
                    if (dataTypeIndex > mDataTypeList.getDataTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }

                int indexRoom;
                while (true) {
                    System.out.println("Choose the Room for which you want add this sensor, from the list below:");
                    System.out.println(mCtrlUS253.showRoomListInString());
                    indexRoom = read.nextInt();
                    if (indexRoom > mDataTypeList.getDataTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }
                mCtrlUS253.addNewSensorToRoom(name, calendar, dataTypeIndex, indexRoom);
                System.out.println("Success");
            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US6.");
        } else
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
    }

    public String yearIsValid() {
        String year = read.nextLine();
        if (year == null || year.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!year.matches("^201[8-9]|20[2-9][0-9]$")) { //only accepts years between 2018 and 2099
            System.out.println("Please insert a valid year between 2018 and 2099.");
            return null;
        }
        return year;
    }

    public String monthIsValid() {
        String month = read.nextLine();
        if (month == null || month.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!month.matches("^([1-9]|1[0-2])$")) { //only accepts values between 1 and 12
            System.out.println("Please insert a valid month.");
            return null;
        }
        return month;
    }

    public String dayIsValid() {
        String day = read.nextLine();
        if (day == null || day.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!day.matches("^(3[01]|[12][0-9]|[1-9])$")) { //only accepts values between 1 and 31
            System.out.println("Please insert a valid day.");
            return null;
        }
        if (tempMonth.matches("^(4|6|9|11)$")) {
            if (day.matches("^31$")) {
                System.out.println("Please insert a valid day for the selected month: (" + tempMonth + ")");
                return null;
            }
        }
        if (tempMonth.matches("^2$")) {
            if (day.matches("^(29|30|31)$")) {
                System.out.println("Please insert a valid day for the selected month: (" + tempMonth + ")");
                return null;
            }
        }
        return day;
    }

    public String nameIsValid() {
        String name = read.nextLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!name.matches("[A-Za-z0-9]*")) {
            System.out.println("Please insert only alphanumeric characters.");
            return null;
        }
        return name;
    }
}
