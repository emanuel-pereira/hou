package smarthome.io.ui;

import smarthome.controller.US6CreateSensorCTRL;
import smarthome.model.*;

import java.time.Year;
import java.time.temporal.ValueRange;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class US6CreateSensorUI {
    private GAList mGAList;
    private DataTypeList mDataTypeList;
    private SensorList mSensorList;
    private US6CreateSensorCTRL mCtrlUS6;
    private US5DefineSensorDataTypeUI ui5; //in order to invoke methods from ui5 to print out the list of Data types for the user to select


    public US6CreateSensorUI(DataTypeList dataTypeList, GAList listOfGA, SensorList sensorList) {
        mCtrlUS6 = new US6CreateSensorCTRL(dataTypeList, listOfGA, sensorList);
        mGAList = listOfGA;
        mSensorList = sensorList;
        mDataTypeList=dataTypeList;
        /*ui3 = new US3CreateGAUI(listOfGA, typeOfGAList);*/
    }


    public void run() {
        Scanner read = new Scanner(System.in);

        String name;
        while (true) {
            System.out.println("Insert a name for the sensor(or click r to return to Main Menu):");
            name = read.nextLine();
            if ((name == null || name.trim().isEmpty())) {
                System.out.println("Please insert a valid name.");
            }
            if ("r".equals(name)) {
                System.out.println("Return to Main Menu");
                break;
            } else System.out.println("Success " + name + " added as sensor name.");
            break;
        }

        System.out.println("Insert a name for the sensor(or click r to return to Main Menu):");
        name = read.nextLine();
        System.out.println("Success " + name + " added as sensor name.");

        int year;
        System.out.println("Insert the year when the sensor will start(or click r to return to Main Menu):");
        year = read.nextInt();
        System.out.println("Success");

        /*while (true) {
            System.out.println("Insert the year when the sensor will start(or click r to return to Main Menu):");
            year = read.nextInt();
            if (year < Year.now().getValue()) {
                System.out.println("The year " + year + " is not valid. Please insert a year equal or greater than current year");
            }

            if ("r".equals(year)) {
                System.out.println("Return to Main Menu");
                break;
            } else System.out.println("Success");
        }
*/
        int month;
       /* while (true) {
            System.out.println("Insert the month (between 1 and 12) when the sensor will start(or click r to return to Main Menu):");
            month = read.nextInt();
            if (!ValueRange.of(1, 12).equals(month)) {
                System.out.println("The value " + month + " inserted as month is not valid. Please insert a month between 1 and 12.");
            }
            if ("r".equals(month)) {
                System.out.println("Return to Main Menu");
                break;
            } else System.out.println("Success");
        }*/
        System.out.println("Insert the month (between 1 and 12) when the sensor will start(or click r to return to Main Menu):");
        month = read.nextInt();
        System.out.println("Success");

        int day;
        /*while (true) {
            System.out.println("Insert the day when the sensor will start(or click r to return to Main Menu):");
            day = read.nextInt();
            if (!ValueRange.of(1, 31).equals(day)) {
                System.out.println("The value " + day + " inserted as day is not valid. Please insert a day between 1 and 31");
            }
            if ("r".equals(day)) {
                System.out.println("Return to Main Menu");
                break;
            } else System.out.println("Success");
        }*/
        System.out.println("Insert the day when the sensor will start(or click r to return to Main Menu):");
        day = read.nextInt();
        System.out.println("Success");

        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        System.out.println("Start date of sensor is: " + year + "/" + month + "/" + day);

        if(returnDataTypeStringList()){
            int dataTypeIndex = read.nextInt();
            if(dataTypeIndex==0)
            {System.out.println("Return to main menu");}
            else{returnDataTypeStringList();}


        }
       /* System.out.println("Choose a data type for the sensor from one of the data types below:");
        System.out.println(mCtrlUS6.showDataTypeListInString());*/

       /* System.out.println("Success");
        mCtrlUS6.newSensor(name, calendar, dataTypeIndex);*/

        /*System.out.println("Insert the latitude of the sensor:");
        double latitude = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the longitude of the sensor:");
        double longitude = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the altitude of the sensor:");
        double altitude = read.nextDouble();
        System.out.println("Success");*/


        System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
        System.out.println(mCtrlUS6.showGAListInString());
        int indexGA = read.nextInt();
        System.out.println("Success. Sensor: " + mSensorList.getSensorList().get(mSensorList.getSensorList().size()-1).getDesignation() + " added" +
                " to Geographical Area: " + mGAList.get(indexGA-1).getGeographicalAreaDesignation());
        mCtrlUS6.addNewSensorToGA(indexGA);
        System.out.println("Name: " + mGAList.getGAList().get(mGAList.getGAList().size() - 1).getGeographicalAreaDesignation()
                + ", Type: " + mGAList.getGAList().get(mGAList.getGAList().size() - 1).getGeographicalAreaType());
    }

    public boolean returnGAStringList() {
        if (mCtrlUS6.getGAListSize() == 0) {
            System.out.println("List of geographical areas is empty, please insert one first in US3 \n \n");
            return false;
        } else {
            System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
            System.out.println(mCtrlUS6.showGAListInString());
            //ask the user for a number input to check the Parent GA of the GA he chooses
            return true;
        }
    }

    public boolean returnDataTypeStringList() {
        if (mCtrlUS6.getDataTypeListSize() == 0) {
            System.out.println("List of data types that the sensor might read is empty, please insert one first in US5 \n \n");
            return false;
        } else {
            System.out.println("Choose a data type for the sensor from one of the data types below:");
            return true;
        }
    }


}
