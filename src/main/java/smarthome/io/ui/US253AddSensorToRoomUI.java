package smarthome.io.ui;

import smarthome.controller.US253AddSensorToRoomCTRL;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class US253AddSensorToRoomUI {

    private SensorTypeList mSensorTypeList;
    private House mHouse;
    private RoomList mRoomList;
    private US253AddSensorToRoomCTRL mCtrlUS253;
    private Scanner read = new Scanner(System.in);
    private ReadingList mReadingList = new ReadingList();
    private String mTempYear;
    private String mTempMonth;
    private String mTempDay;
    private String mTempYearOfReading;
    private String mTempMonthOfReading;
    private String mTempDayOfReading;
    private String mTempHourOfReading;
    private String mEmptySpacesMsg = "Empty spaces are not accepted";

    public US253AddSensorToRoomUI(House house, SensorTypeList sensorTypeList) {
        mCtrlUS253 = new US253AddSensorToRoomCTRL(house, sensorTypeList);
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
                        UtilsUI.printLnInsertValidParameter("name");
                }
                int year;
                while (true) {
                    System.out.println("Insert the year when the sensor will start (equal or greater than current year):");
                    mTempYear = yearIsValid();
                    if (mTempYear != null)
                        break;
                }
                int month;
                while (true) {
                    System.out.println("Insert the month when the sensor will start(insert values between 1 and 12):");
                    mTempMonth = monthIsValid();
                    if (mTempMonth != null)
                        break;
                }
                int day;
                while (true) {
                    System.out.println("Insert the day when the sensor will start:");
                    mTempDay = dayIsValid();
                    if (mTempDay != null)
                        break;
                }
                year = Integer.parseInt(mTempYear);
                month = Integer.parseInt(mTempMonth);
                day = Integer.parseInt(mTempDay);

                GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                int dataTypeIndex;
                while (true) {
                    System.out.println("Choose a data type for the sensor from one of the data types below:");
                    System.out.println(mSensorTypeList.showSensorTypeListInString());
                    dataTypeIndex = read.nextInt();
                    if (dataTypeIndex > mSensorTypeList.getSensorTypeList().size())
                        UtilsUI.printLnInsertValidOption();
                    else break;
                }

                String unit;
                while (true) {
                    System.out.println("Insert the unit the sensor will read:");
                    unit = unitIsValid();
                    if (unit != null)
                        break;
                    else
                        UtilsUI.printLnInsertValidParameter("name");
                }

                String option;
                while (true) {
                    System.out.println("Do you want to insert readings for the sensor(y/n)?");
                    option = read.nextLine();
                    if (option.matches("n")) {
                        break;
                    }
                    if (option.matches("y")) {
                        int yearOfReading;
                        int monthOfReading;
                        int dayOfReading;
                        int hourOfReading;
                        double readingValue;

                        while (true) {
                            System.out.println("Insert the year when the reading was made:");
                            mTempYearOfReading = yearIsValid();
                            if (mTempYearOfReading != null)
                                break;
                        }
                        while (true) {
                            System.out.println("Insert the month when the reading was made:");
                            mTempMonthOfReading = monthIsValid();
                            if (mTempMonthOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println("Insert the day when the reading was made:");
                            mTempDayOfReading = dayIsValid();
                            if (mTempDayOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println("Insert the hour when the reading was made:");
                            mTempHourOfReading = hourIsValid();
                            if (mTempHourOfReading != null)
                                break;
                        }

                        System.out.println("Insert the value of the reading:");
                        readingValue = read.nextDouble();
                        read.nextLine();

                        yearOfReading = Integer.parseInt(mTempYearOfReading);
                        monthOfReading = Integer.parseInt(mTempMonthOfReading);
                        dayOfReading = Integer.parseInt(mTempDayOfReading);
                        hourOfReading = Integer.parseInt(mTempHourOfReading);

                        GregorianCalendar date = new GregorianCalendar(yearOfReading, monthOfReading, dayOfReading, hourOfReading, 0);

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
                        UtilsUI.printLnInsertValidOption();
                    else break;
                }
                mCtrlUS253.addNewSensorToRoom(name, calendar, dataTypeIndex, indexRoom, unit, mReadingList);
                System.out.println("Success");
            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
    }

    public String yearIsValid() {
        String year = read.nextLine();
        if (year == null || year.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        if (!year.matches("^201[8-9]|20[2-9][0-9]$")) { //only accepts years between 2018 and 2099
            UtilsUI.printLnInsertValidParameter("year between 2018 and 2099");
            return null;
        }
        return year;
    }

    public String monthIsValid() {
        String month = read.nextLine();
        if (month == null || month.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        if (!month.matches("^([1-9]|1[0-2])$")) { //only accepts values between 1 and 12
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        return month;
    }

    private String dayIsValid() {
        String day = read.nextLine();
        if (day == null || day.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        if (!day.matches("^(3[01]|[12][0-9]|[1-9])$")) { //only accepts values between 1 and 31
            UtilsUI.printLnInsertValidParameter("day");
            return null;
        }
        if (mTempMonth.matches("^(4|6|9|11)$") && day.matches("^31$")) {
            UtilsUI.printLnInsertValidParameter("day for the selected month: (" + mTempMonth + ")");
            return null;
        }
        if (mTempMonth.matches("^2$") && day.matches("^(29|30|31)$")) {
            UtilsUI.printLnInsertValidParameter("day for the selected month: (" + mTempMonth + ")");
            return null;
        }
        return day;
    }

    private String hourIsValid() {
        String hour = read.nextLine();
        if (hour == null || hour.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        if (!hour.matches("^(2[0-4]|[1][0-9]|[1-9])")) { //only accepts values between 1 and 12
            UtilsUI.printLnInsertValidParameter("hour");

            return null;
        }
        return hour;
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

    public String unitIsValid() {
        String unit = read.nextLine();
        if (unit == null || unit.trim().isEmpty()) {
            System.out.println(mEmptySpacesMsg);
            return null;
        }
        return unit;
    }


    public void run2() {
        if (mRoomList.getRoomList().size() != 0) {
            while (true) {
                int indexRoom;
                while (true) {
                    System.out.println("Choose the Room for which you want to list all sensor, from the list below:");
                    System.out.println(mRoomList.showRoomListInString());
                    indexRoom = read.nextInt();
                    if (indexRoom > mRoomList.getRoomList().size())
                        UtilsUI.printLnInsertValidOption();
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
