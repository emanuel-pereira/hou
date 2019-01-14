package smarthome.io.ui;


import smarthome.controller.US6CreateSensorCTRL;
import smarthome.model.*;

import java.util.*;

public class US6CreateSensorUI {
    private GAList mGAList;
    private SensorTypeList mSensorTypeList;
    private US6CreateSensorCTRL mCtrlUS6;


    public US6CreateSensorUI(SensorTypeList sensorTypeList, GAList listOfGA) {
        mCtrlUS6 = new US6CreateSensorCTRL(sensorTypeList, listOfGA);
        mGAList = listOfGA;
        mSensorTypeList = sensorTypeList;
    }


    Scanner read = new Scanner(System.in);
    GPSValidations v = new GPSValidations();

    ReadingList readingList = new ReadingList();

    String tempMonth;
    String tempMonthOfReading;

    public void run() {
        String tempYear;
        String tempDay;
        String tempYearOfReading;
        String tempDayOfReading;
        String tempHourOfReading;
        if (mGAList.getGAList().size() != 0) {
            if (mSensorTypeList.getSensorTypeList().size() != 0) {
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
                    System.out.println("Insert the year when the sensor will start:");
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
                int sensorTypeIndex;
                while (true) {
                    System.out.println("Choose a type for the sensor from one of the sensor types below:");
                    System.out.println(mCtrlUS6.showSensorTypeListInString());
                    sensorTypeIndex = read.nextInt();
                    read.nextLine();
                    if (sensorTypeIndex > mSensorTypeList.getSensorTypeList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
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
                            tempYearOfReading = yearIsValid();
                            if (tempYearOfReading != null)
                                break;
                        }
                        while (true) {
                            System.out.println("Insert the month when the reading was made:");
                            tempMonthOfReading = monthIsValid();
                            if (tempMonthOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println("Insert the day when the reading was made:");
                            tempDayOfReading = dayIsValid();
                            if (tempDayOfReading != null)
                                break;
                        }

                        while (true) {
                            System.out.println("Insert the hour when the reading was made:");
                            tempHourOfReading = hourIsValid();
                            if (tempHourOfReading != null)
                                break;
                        }

                        System.out.println("Insert the value of the reading:");
                        readingValue = read.nextDouble();
                        read.nextLine();

                        yearOfReading = Integer.parseInt(tempYearOfReading);
                        monthOfReading = Integer.parseInt(tempMonthOfReading);
                        dayOfReading = Integer.parseInt(tempDayOfReading);
                        hourOfReading = Integer.parseInt(tempHourOfReading);

                        GregorianCalendar date = new GregorianCalendar(yearOfReading, monthOfReading, dayOfReading, hourOfReading, 0);
                        Reading r = readingList.newReading(readingValue, date);
                        readingList.addReading (r);
                    }
                }

                String unit;
                System.out.println("Insert the unit the sensor will read:");
                unit = read.nextLine();

                double latitude;
                while (true) {
                    try {
                        System.out.println("Insert the latitude of the new sensor:");
                        latitude = read.nextDouble();
                        if (v.latitudeIsValid(latitude))
                            break;
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                double longitude;
                while (true) {
                    try {
                        System.out.println("Insert the longitude of the new sensor:");
                        longitude = read.nextDouble();
                        if (v.longitudeIsValid(longitude))
                            break;
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage());
                    }
                }


                double altitude;
                while (true) {
                    try {

                        System.out.println("Insert the altitude of the new sensor:");
                        altitude = read.nextDouble();
                        if (v.altitudeIsValid(altitude))
                            break;
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

                int indexGA;
                while (true) {
                    System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
                    System.out.println(mCtrlUS6.showGAListInString());
                    indexGA = read.nextInt();
                    if (indexGA > mGAList.getGAList().size())
                        System.out.println("Please insert a valid option \n.");
                    else break;
                }
                mCtrlUS6.addNewSensorToGA(name, calendar, sensorTypeIndex, unit, latitude, longitude, altitude, indexGA, readingList);
                System.out.println("The following sensor was successfully created: ");
                System.out.println("NAME: " + name);
                System.out.println("GEOGRAPHICAL AREA: "+ mGAList.get(indexGA - 1).getGeographicalAreaDesignation());
                System.out.println("START DATE: "+year+"/"+month+"/"+"/"+day);
                System.out.println("TYPE: "+ mSensorTypeList.getSensorTypeList().get(sensorTypeIndex-1).getSensorTypeDesignation());
                System.out.println("UNITS: "+unit);
                System.out.println("LIST OF READINGS:");
                for (Reading r : readingList.getReadingList ()) {
                    System.out.println("[timestamp: " + r.getDateAndTime().getTime() + " value: " + r.returnValueOfReading()+"]");
                }
                System.out.println("GPS LOCATION - [Latitude: "+latitude+" | Longitude: "+longitude+" | Altitude: "+altitude+"]");

            } else
                System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
        } else
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
    }


    private String yearIsValid() {
        String year = read.nextLine();
        if (year == null || year.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!year.matches("^201[0-9]|20[2-9][0-9]$")) { //only accepts years between 2010 and 2099
            System.out.println("Please insert a valid year between 2010 and 2099.");
            return null;
        }
        return year;
    }

    private String monthIsValid() {
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

    private String dayIsValid() {
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

    private String nameIsValid() {
        String name = read.nextLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!name.matches("^[A-Za-z -]+$")) { //accepts alphanumeric characters, spaces
            System.out.println("Please insert only alphabetic characters with spaces or hyphens.");
            return null;
        }
        return name;
    }
}
