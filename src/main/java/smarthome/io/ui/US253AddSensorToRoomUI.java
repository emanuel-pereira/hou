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


    public US253AddSensorToRoomUI(House house, SensorTypeList sensorTypeList) {
        mCtrlUS253 = new US253AddSensorToRoomCTRL (house, sensorTypeList);
        mHouse = house;
        mSensorTypeList = sensorTypeList;
        mRoomList = mHouse.getRoomList();
    }

    Scanner read = new Scanner (System.in);

    ReadingList readingList = new ReadingList();

    String tempYear;
    String tempMonth;
    String tempDay;


    String tempYearOfReading;
    String tempMonthOfReading;
    String tempDayOfReading;
    String tempHourOfReading;

    public void run() {
        if (mRoomList.getRoomList ().size () != 0) {
            if (mSensorTypeList.getSensorTypeList ().size () != 0) {
                String name;
                while (true) {
                    System.out.println ("Insert a name for the sensor:");
                    name = nameIsValid ();
                    if (name != null)
                        break;
                    else
                        System.out.println ("Please insert a valid name");
                }
                int year;
                while (true) {
                    System.out.println ("Insert the year when the sensor will start (equal or greater than current year):");
                    tempYear = yearIsValid ();
                    if (tempYear != null)
                        break;
                }
                int month;
                while (true) {
                    System.out.println ("Insert the month when the sensor will start(insert values between 1 and 12):");
                    tempMonth = monthIsValid ();
                    if (tempMonth != null)
                        break;
                }
                int day;
                while (true) {
                    System.out.println ("Insert the day when the sensor will start:");
                    tempDay = dayIsValid ();
                    if (tempDay != null)
                        break;
                }
                year = Integer.parseInt (tempYear);
                month = Integer.parseInt (tempMonth);
                day = Integer.parseInt (tempDay);

                GregorianCalendar calendar = new GregorianCalendar (year, month, day);
                int dataTypeIndex;
                while (true) {
                    System.out.println ("Choose a data type for the sensor from one of the data types below:");
                    System.out.println (mSensorTypeList.showSensorTypeListInString ());
                    dataTypeIndex = read.nextInt ();
                    if (dataTypeIndex > mSensorTypeList.getSensorTypeList ().size ())
                        System.out.println ("Please insert a valid option \n.");
                    else break;
                }

                String unit;
                while (true) {
                    System.out.println ("Insert the unit the sensor will read:");
                    unit = unitIsValid ();
                    if (unit != null)
                        break;
                    else
                        System.out.println ("Please insert a valid name");
                }

                String option;
                while (true) {
                    System.out.println ("Do you want to insert readings for the sensor(y/n)?");
                    option = read.nextLine ();
                    if (option.matches ("n")) {
                        break;
                    }
                    if (option.matches ("y")) {
                        int yearOfReading;
                        int monthOfReading;
                        int dayOfReading;
                        int hourOfReading;
                        double readingValue;

                        while (true) {
                            System.out.println ("Insert the year when the reading was made:");
                            tempYearOfReading = yearIsValid ();
                            if (tempYearOfReading != null)
                                break;
                        }
                        while (true) {
                            System.out.println ("Insert the month when the reading was made:");
                            tempMonthOfReading = monthIsValid ();
                            if (tempMonthOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println ("Insert the day when the reading was made:");
                            tempDayOfReading = dayIsValid ();
                            if (tempDayOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println ("Insert the hour when the reading was made:");
                            tempHourOfReading = hourIsValid ();
                            if (tempHourOfReading != null)
                                break;
                        }

                        System.out.println ("Insert the value of the reading:");
                        readingValue = read.nextDouble ();
                        read.nextLine ();

                        yearOfReading = Integer.parseInt (tempYearOfReading);
                        monthOfReading = Integer.parseInt (tempMonthOfReading);
                        dayOfReading = Integer.parseInt (tempDayOfReading);
                        hourOfReading = Integer.parseInt (tempHourOfReading);

                        GregorianCalendar date = new GregorianCalendar (yearOfReading, monthOfReading, dayOfReading, hourOfReading, 0);

                        Reading r = readingList.newReading (readingValue, date);
                        readingList.addReading (r);
                    }
                }

                int indexRoom;
                while (true) {
                    System.out.println ("Choose the Room for which you want add this sensor, from the list below:");
                    System.out.println (mRoomList.showRoomListInString ());
                    indexRoom = read.nextInt ();
                    if (indexRoom > mRoomList.getRoomList().size ())
                        System.out.println ("Please insert a valid option \n.");
                    else break;
                }
                mCtrlUS253.addNewSensorToRoom (name, calendar, dataTypeIndex, indexRoom, unit, readingList);
                System.out.println ("Success");
            } else
                System.out.println ("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println ("List of Rooms is empty. Please insert at least one Room.");
    }

    public String yearIsValid() {
        String year = read.nextLine ();
        if (year == null || year.trim ().isEmpty ()) {
            System.out.println ("Empty spaces are not accepted");
            return null;
        }
        if (!year.matches ("^201[8-9]|20[2-9][0-9]$")) { //only accepts years between 2018 and 2099
            System.out.println ("Please insert a valid year between 2018 and 2099.");
            return null;
        }
        return year;
    }

    public String monthIsValid() {
        String month = read.nextLine ();
        if (month == null || month.trim ().isEmpty ()) {
            System.out.println ("Empty spaces are not accepted");
            return null;
        }
        if (!month.matches ("^([1-9]|1[0-2])$")) { //only accepts values between 1 and 12
            System.out.println ("Please insert a valid month.");
            return null;
        }
        return month;
    }

    private String dayIsValid() {
        String day = read.nextLine ();
        if (day == null || day.trim ().isEmpty ()) {
            System.out.println ("Empty spaces are not accepted");
            return null;
        }
        if (!day.matches ("^(3[01]|[12][0-9]|[1-9])$")) { //only accepts values between 1 and 31
            System.out.println ("Please insert a valid day.");
            return null;
        }
        if (tempMonth.matches ("^(4|6|9|11)$")) {
            if (day.matches ("^31$")) {
                System.out.println ("Please insert a valid day for the selected month: (" + tempMonth + ")");
                return null;
            }
        }
        if (tempMonth.matches ("^2$")) {
            if (day.matches ("^(29|30|31)$")) {
                System.out.println ("Please insert a valid day for the selected month: (" + tempMonth + ")");
                return null;
            }
        }
        return day;
    }

    private String hourIsValid() {
        String hour = read.nextLine();
        if (hour == null || hour.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!hour.matches("^(2[0-4]|[1][0-9]|[1-9])")) { //only accepts values between 1 and 12
            System.out.println("Please insert a valid hour.");
            return null;
        }
        return hour;
    }

    public String nameIsValid() {
        String name = read.nextLine ();
        if (name == null || name.trim ().isEmpty ()) {
            System.out.println ("Empty spaces are not accepted.");
            return null;
        }
        if (!name.matches ("[A-Za-z0-9]*")) {
            System.out.println ("Please insert only alphanumeric characters.");
            return null;
        }
        return name;
    }

    public String unitIsValid() {
        String unit = read.nextLine ();
        if (unit == null || unit.trim ().isEmpty ()) {
            System.out.println ("Empty spaces are not accepted.");
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
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }
                System.out.println("The sensors in the " + mRoomList.getRoomList().get(indexRoom-1).getName() + " are: ");
                System.out.println(mRoomList.getRoomList().get(indexRoom - 1).getSensorListInRoom().showSensorListInString());
                break;
            }
        } else
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
    }
}
