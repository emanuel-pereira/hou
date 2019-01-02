package smarthome.io.ui;

import smarthome.controller.US6CreateSensorCTRL;
import smarthome.model.*;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class US6CreateSensorUI {
    private GAList mGAList;
    private DataTypeList mDataTypeList;
    private US6CreateSensorCTRL mCtrlUS6;


    public US6CreateSensorUI(DataTypeList dataTypeList, GAList listOfGA) {
        mCtrlUS6 = new US6CreateSensorCTRL(dataTypeList, listOfGA);
        mGAList = listOfGA;
        mDataTypeList = dataTypeList;
    }

    Scanner read = new Scanner(System.in);

    String tempYear;
    String tempDay;
    String tempMonth;


    public void run() {
        if (mGAList.getGAList().size() != 0) {
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
                    System.out.println(mCtrlUS6.showDataTypeListInString());
                    dataTypeIndex = read.nextInt();
                    if (dataTypeIndex > mDataTypeList.getDataTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }

                int indexGA;
                while (true) {
                    System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
                    System.out.println(mCtrlUS6.showGAListInString());
                    indexGA = read.nextInt();
                    if (indexGA > mDataTypeList.getDataTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }
                mCtrlUS6.addNewSensorToGA(name,calendar,dataTypeIndex, indexGA);
                System.out.println("Success. Sensor: " + mGAList.get(indexGA - 1).getListOfSensors().get(mGAList.get(indexGA - 1).getListOfSensors().size()-1).getDesignation() + " added" +
                        " to Geographical Area: " + mGAList.get(indexGA - 1).getGeographicalAreaDesignation());
            } else
                System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
        } else
            System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US6.");
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
