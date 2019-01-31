package smarthome.io.ui;

import smarthome.controller.EnergyConsumptionInPeriodCTRL;
import smarthome.model.House;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class EnergyConsumptionInPeriodUI {

    private EnergyConsumptionInPeriodCTRL mCtrl;
    private Scanner mRead = new Scanner(System.in);
    private int mIndexOfDevice;
    private int mIndexOfHG;
    private int mIndexOfRoom;
    private int mOption;
    private Calendar mStartDate;
    private Calendar mEndDate;
    private String mStartDateStr="start date in the time interval:";
    private String mEndDateStr="end date in the time interval:";
    private String mTimePeriodStr="[Time Period]: [";
    private String mEConsumptionStr="[Energy Consumption]: ";


    public EnergyConsumptionInPeriodUI(House house) {
        mCtrl = new EnergyConsumptionInPeriodCTRL(house);
    }


    public void selectOption() {
        mOption = -1;
        while (mOption != 0) {
            System.out.println("Get the total energy consumption, in a time interval, of a:");
            System.out.println("1 - Device");
            System.out.println("2 - Room");
            System.out.println("3 - Grid");
            System.out.println("0 - Exit");
            mOption = mRead.nextInt();
            mRead.nextLine();
            switch (mOption) {
                case 1:
                    this.selectDevice();
                    break;
                case 2:
                    this.selectRoom();
                    break;
                case 3:
                    this.selectHouseGrid();
                    break;
                default:
                    UtilsUI.printLnInsertValidOption();
            }
        }
    }

    private void selectDevice() {
        while (true) {
            System.out.println("Choose a device from the list below:");
            System.out.println(mCtrl.showMeteredDevicesInStr());
            mIndexOfDevice = mRead.nextInt();
            mRead.nextLine();
            if (mIndexOfDevice <= mCtrl.getMeteredDevicesInHouseSize()) {
                break;
            }
            UtilsUI.printLnInsertValidOption();
        }
        this.getStartDate();
    }

    private void selectRoom() {
        while (true) {
            System.out.println("Choose a room from the list below:");
            System.out.println(mCtrl.showRoomListInStr ());
            mIndexOfRoom = mRead.nextInt();
            mRead.nextLine();
            if (mIndexOfRoom <= mCtrl.getRoomListSize ()) {
                break;
            }
            UtilsUI.printLnInsertValidOption();
        }
        this.getStartDate();
    }

    private void selectHouseGrid() {
        while (true) {
            System.out.println("Choose a grid from the list below:");
            System.out.println(mCtrl.showHouseGridListInString());
            mIndexOfHG = mRead.nextInt();
            mRead.nextLine();
            if (mIndexOfHG <= mCtrl.getHouseGridListSize()) {
                break;
            }
            UtilsUI.printLnInsertValidOption();
        }
        this.getStartDate();
    }

    private void getStartDate() {
        int year = getYear(mStartDateStr);
        int month = getMonth(mStartDateStr);
        int day = getDay(mStartDateStr, year, month);
        int hour = getHour(mStartDateStr);
        int minute = getMinute(mStartDateStr);
        mStartDate = new GregorianCalendar(year, month, day, hour, minute);
        this.getEndDate();
    }

    private void getEndDate() {
        int year = getYear(mEndDateStr);
        int month = getMonth(mEndDateStr);
        int day = getDay(mEndDateStr, year, month);
        int hour = getHour(mEndDateStr);
        int minute = getMinute(mEndDateStr);
        mEndDate = new GregorianCalendar(year, month, day, hour, minute);
        switch (mOption) {
            case 1:
                this.getDeviceEnergyConsumption();
                break;
            case 2:
                this.getRoomEnergyConsumption();
                break;
            case 3:
                this.getHouseGridEnergyConsumption();
                break;
            default:
                UtilsUI.printLnInsertValidOption();
        }
    }

    private void getDeviceEnergyConsumption() {
        int startDay = mStartDate.get(Calendar.DAY_OF_MONTH);
        int startMonth = mStartDate.get(Calendar.MONTH) + 1;
        int startYear = mStartDate.get(Calendar.YEAR);
        int endDay = mEndDate.get(Calendar.DAY_OF_MONTH);
        int endMonth = mEndDate.get(Calendar.MONTH) + 1;
        int endYear = mEndDate.get(Calendar.YEAR);
        String deviceName = mCtrl.getDeviceName(mIndexOfDevice);
        System.out.println("Total Energy Consumption of the device in the time period:");
        System.out.println("[Device]: " + deviceName);
        System.out.println("[Time Period]: " + startDay + "/" + startMonth + "/" + startYear + " - " + endDay + "/" + endMonth + "/" + endYear);
        System.out.println(mTimePeriodStr + startDay + "/" + startMonth + "/" + startYear + " - " + endDay + "/" + endMonth + "/" + endYear + "]");
        System.out.println(mEConsumptionStr+ mCtrl.getEnergyConsumptionInPeriod(mIndexOfDevice, mStartDate, mEndDate) + "\n");
    }

    private void getHouseGridEnergyConsumption() {
        int startDay = mStartDate.get(Calendar.DAY_OF_MONTH);
        int startMonth = mStartDate.get(Calendar.MONTH) + 1;
        int startYear = mStartDate.get(Calendar.YEAR);
        int endDay = mEndDate.get(Calendar.DAY_OF_MONTH);
        int endMonth = mEndDate.get(Calendar.MONTH) + 1;
        int endYear = mEndDate.get(Calendar.YEAR);
        String houseGrid = mCtrl.getHGName(mIndexOfHG);
        System.out.println("Total Energy Consumption of the grid in the time period:");
        System.out.println("[Grid]: " + houseGrid);
        System.out.println("[Time Period]: " + startDay + "/" + startMonth + "/" + startYear + " - " + endDay + "/" + endMonth + "/" + endYear);
        System.out.println("Total Energy Consumption of the device in the time period:");
        System.out.println("[Device]: " + houseGrid);
        System.out.println(mTimePeriodStr + startDay + "/" + startMonth + "/" + startYear + " - " + endDay + "/" + endMonth + "/" + endYear + "]");
        System.out.println(mEConsumptionStr + mCtrl.getHouseGridEnergyConsumptionInPeriod(mIndexOfHG, mStartDate, mEndDate) + "\n");
    }

    private void getRoomEnergyConsumption() {
        int startDay = mStartDate.get(Calendar.DAY_OF_MONTH);
        int startMonth = mStartDate.get(Calendar.MONTH);
        int startYear = mStartDate.get(Calendar.YEAR);
        int endDay = mEndDate.get(Calendar.DAY_OF_MONTH);
        int endMonth = mEndDate.get(Calendar.MONTH);
        int endYear = mEndDate.get(Calendar.YEAR);
        String room = mCtrl.getRoomName (mIndexOfRoom);
        System.out.println("Total Energy Consumption of the room in the time period:");
        System.out.println("[Room]: " + room);
        System.out.println(mTimePeriodStr  + startDay + "/" + startMonth + "/" + startYear + " - " + endDay + "/" + endMonth + "/" + endYear);
        System.out.println(mEConsumptionStr + mCtrl.getRoomEnergyConsumptionInPeriod (mIndexOfRoom, mStartDate, mEndDate) + "\n");
    }


    private int getYear(final String input) {
        int year;
        while (true) {
            System.out.println("Insert the year for the " + input);
            String inputYearOfReading = mRead.nextLine();
            if (mCtrl.yearIsValid(inputYearOfReading)) {
                year = Integer.parseInt(inputYearOfReading);
                break;
            }
        }
        return year;
    }

    private int getMonth(final String input) {
        int month;
        while (true) {
            System.out.println("Insert the month for the " + input);
            String inputMonthOfReading = mRead.nextLine();
            if (mCtrl.monthIsValid(inputMonthOfReading)) {
                month = Integer.parseInt(inputMonthOfReading) - 1;
                break;
            }
        }
        return month;
    }

    private int getDay(final String input, int yearOfReading, int monthOfReading) {
        int day;
        while (true) {
            System.out.println("Insert the day for the " + input);
            String inputDayOfReading = mRead.nextLine();
            if (mCtrl.dayIsValid(inputDayOfReading, monthOfReading + 1, yearOfReading)) {
                day = Integer.parseInt(inputDayOfReading);
                break;
            }
        }
        return day;
    }

    private int getHour(final String input) {
        int hour;
        while (true) {
            System.out.println("Insert the hour for the " + input);
            String inputHour = mRead.nextLine();
            if (mCtrl.hourIsValid(inputHour)) {
                hour = Integer.parseInt(inputHour);
                break;
            }
        }
        return hour;
    }


    private int getMinute(final String input) {
        int minute;
        while (true) {
            System.out.println("Insert the minute for the " + input);
            String inputMinute = mRead.nextLine();
            if (mCtrl.minuteIsValid(inputMinute)) {
                minute = Integer.parseInt(inputMinute);
                break;
            }
        }
        return minute;
    }




}
