package smarthome.io.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public final class UtilsUI {

    /**
     * Private constructor of UtilsUI class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private UtilsUI() {
    }


    /**
     * Reads user input from the console and returns it as a string
     *
     * @return the input
     */
    private static String getUserInput() {
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }


    private static boolean isInteger(String input) {
        int i;
        try {
            i = Integer.parseInt(input);
        } catch (Exception e) {
            i = Integer.MIN_VALUE;
        }

        return i != Integer.MIN_VALUE;
    }

    /**
     * @param input any String
     * @return true if it's a valid representation of a double
     */

    private static boolean isDouble(String input) {
        double d;
        try {
            d = Double.parseDouble(input);
        } catch (Exception e) {
            d = Double.MIN_VALUE;
        }


        return (d >= (Double.MIN_VALUE + 1));
    }

    /**
     * @param input a String containing a date in "yyyy-mm-dd" format
     * @return true if it's a valid date representation
     */

    private static boolean isDate(String input) {
        String userInput = input.trim(); // remove any spaces from input as the user may be stupid.
        String[] splitInput;

        splitInput = userInput.split("-", 3);

        //Are all 3 fields filled?
        if (splitInput.length < 3) {
            return false;
        }

        String sYear = splitInput[0];
        String sMonth = splitInput[1];
        String sDay = splitInput[2];

        //For a date to be valid, all 3 fields must be integers
        if (!(isInteger(sYear) && isInteger(sMonth) && isInteger(sDay))) {
            return false;
        }

        int year = Integer.parseInt(sYear);
        int month = Integer.parseInt(sMonth) - 1; // subtract 1 because of the way this field is used in GregorianCalendar
        int day = Integer.parseInt(sDay);

        if (month > 11 || day > 31) { // quick check but redundant, I guess.
            return false;
        }

        GregorianCalendar c = new GregorianCalendar(year, month, day);
        int dayCheck = c.get(Calendar.DAY_OF_MONTH);
        int monthCheck = c.get(Calendar.MONTH);
        int yearCheck = c.get(Calendar.YEAR);

        return ((dayCheck == day) && (monthCheck == month) && (yearCheck == year));
    }

    /**
     * @param input a String representing a date (in format "YYYY-MM-DD")
     * @param field an integer between 0 and 2 representing the year, month and day
     * @return the year, month or day. Note month is between 0 and 11. -1 signifies an invalid date, -2 and invalid field request.
     */
    private static int getFieldValueFromDate(String input, int field) {
        if (field < 0 || field > 2) {
            return -2;
        }
        if (!isDate(input)) {
            return -1;
        }
        String userInput = input.trim();
        String[] splitInput;
        splitInput = userInput.split("-", 3);


        return Integer.parseInt(splitInput[field]);
    }

    private static int getFieldValueFromTime(String input, int field) {
        if (field < 0 || field > 1) {
            return -2;
        }
        if (!isTime(input)) {
            return -1;
        }
        String userInput = input.trim();
        String[] splitInput;
        splitInput = userInput.split(":", 2);


        return Integer.parseInt(splitInput[field]);
    }

    private static boolean isTime(String input) {
        String userInput = input.trim(); // remove any spaces from input as the user may be stupid.
        String[] splitInput;

        splitInput = userInput.split(":", 2);

        //Are all fields filled?
        if (splitInput.length < 2) {
            return false;
        }

        String sHour = splitInput[0];
        String sMinute = splitInput[1];

        if (!(isInteger(sHour) && isInteger(sMinute))) {
            return false;
        }

        //Check hour and minute boundaries
        int hour = Integer.parseInt(sHour);
        int minute = Integer.parseInt(sMinute);

        return !(hour > 23 || hour < 0 || minute > 59 || minute < 0);
    }


    public static String requestTime() {
        String time;
        do {
            time = getUserInput();
        }
        while (!isTime(time));
        return time;
    }

    /**
     * @param input a string that represents a date and time in "YYYY-MM-DD hh:mm" format
     * @return true if the string is a valid date and time
     */
    private static boolean isDateTime(String input) {
        String[] dateAndTime = input.split("[ ]", 2);

        if (dateAndTime.length != 2) {
            return false;
        }

        String sDate = dateAndTime[0];
        String sTime = dateAndTime[1];

        return (isDate(sDate) && isTime(sTime));
    }

    /**
     * Method that calls the Console Text Input method (validateText) which then verifies
     * if the user input matches a default regular expression, enabling the usage of this
     * method in any situation where a user input is requested.
     *
     * @param errorMessage custom error message
     * @return in case of success, the finish result is the print of the users text input
     */
    public static String requestText(String errorMessage) {
        String defaultRegEx = "[A-Za-z ]*";
        return validateText(errorMessage, defaultRegEx);
    }

    /**
     * Method that calls the Console Text Input method (validateText) which then verifies
     * if the user input matches a custom regular expression, enabling the usage of this
     * method in any situation where a user input is requested.
     *
     * @param errorMessage custom error message
     * @param dynamicRegEx custom regular expression to match with the user input
     * @return in case of success, the finish result is the print of the users text input
     */
    public static String requestText(String errorMessage, String dynamicRegEx) {
        return validateText(errorMessage, dynamicRegEx);
    }

    /**
     * Method that verifies if the user input matches a custom regular expression,
     * enabling the usage of this method in any situation where a user input is requested.
     *
     * @param errorMessage custom error message
     * @param regEx        custom regular expression to match with the user input
     * @return in case of success, the finish result is the print of the users text input
     */
    private static String validateText(String errorMessage, String regEx) {
        String input = "";
        boolean loop = true;

        while (loop) {
            input = getUserInput();
            input = input.trim();
            if (!input.trim().matches(regEx))
                printAndReset(errorMessage);
            else
                loop = false;
        }
        return input;
    }

    /**
     * UI for requesting an integer from the user. Displays a custom error message.
     *
     * @param errorMessage the error message to display if the input is not an integer
     * @return the valid user input as an integer
     */
    public static int requestInteger(String errorMessage) {
        String userInput;
        int parsedUserInput;

        while (true) {
            userInput = getUserInput();
            if (isInteger(userInput)) {
                parsedUserInput = Integer.parseInt(userInput);
                break;
            }
            System.out.println(errorMessage);
        }
        return parsedUserInput;
    }

    /**
     * UI for requesting an integer from the user in a given interval. Displays a custom error message.
     *
     * @param minimum      lower limit for the integer
     * @param maximum      higher limit for the integer
     * @param errorMessage the error message to display if the input is not an integer or it's out of bounds
     * @return the valid user input as an integer
     */
    public static int requestIntegerInInterval(int minimum, int maximum, String errorMessage) {

        String userInput = "-";
        int parsedUserInput = minimum - 1;

        while (!isInteger(userInput)) {
            userInput = getUserInput();

            if (isInteger(userInput)) {
                parsedUserInput = Integer.parseInt(userInput);
            }
            if ((parsedUserInput >= minimum) && (parsedUserInput <= maximum)) {
                break;
            }
            System.out.println(errorMessage);
            userInput = "-";
        }

        return parsedUserInput;
    }

    /**
     * UI method that requests any double
     *
     * @param errorMessage custom error message to be displayed to the user
     * @return parsed User Input to Double
     */
    public static double requestDouble(String errorMessage) {
        String userInput;
        double parsedUserInput;

        while (true) {
            userInput = getUserInput();
            if (isDouble(userInput)) {
                parsedUserInput = Double.parseDouble(userInput);
                break;
            }
            System.out.println(errorMessage);
        }
        return parsedUserInput;
    }


    /**
     * UI method that requests a double in a preset interval
     *
     * @param minimum      lower limit of the interval
     * @param maximum      higher limit of the interval
     * @param errorMessage custom error message
     * @return parsed User Input to Double
     */
    public static double requestDoubleInInterval(double minimum, double maximum, String errorMessage) {

        double parsedUserInput;

        while (true) {
            parsedUserInput = requestDouble(errorMessage);
            if (parsedUserInput >= minimum && parsedUserInput <= maximum) {
                break;
            }
            System.out.println(errorMessage);

        }
        return parsedUserInput;
    }

    /**
     * UI method that requests a date input from the user and validates it.
     *
     * @param errorMessage a custom error message if the date is not valid
     * @return a GregorianCalendar object set to the user defined date
     */
    public static GregorianCalendar requestDate(String errorMessage) {
        String userInput = "";

        int year;
        int month;
        int day;

        while (!isDate(userInput)) {
            userInput = getUserInput();
            if (!isDate(userInput)) {
                System.out.println(errorMessage);
            }
        }

        year = getFieldValueFromDate(userInput, 0);
        month = getFieldValueFromDate(userInput, 1) - 1; // correction for the way months are stored i.e., 0-11 and not 1-12
        day = getFieldValueFromDate(userInput, 2);

        return new GregorianCalendar(year, month, day);
    }

    public static GregorianCalendar requestDateTime(String errorMessage) {
        String date;
        int year;
        int month;
        int day;
        String time;
        int hour;
        int minute;

        String userInput;

        while (true) {
            userInput = getUserInput();
            if (isDateTime(userInput)) {
                break;
            }
            printAndReset(errorMessage);
        }

        String[] dateAndTime;
        dateAndTime = userInput.split(" ", 2);
        date = dateAndTime[0];
        time = dateAndTime[1];

        year = getFieldValueFromDate(date, 0);
        month = getFieldValueFromDate(date, 1) - 1; // correction for the way months are stored i.e., 0-11 and not 1-12
        day = getFieldValueFromDate(date, 2);
        hour = getFieldValueFromTime(time, 0);
        minute = getFieldValueFromTime(time, 1);

        return new GregorianCalendar(year, month, day, hour, minute);

    }

    /**
     * UI method that returns a GregorianCalendar date as string in yyyy-MM-dd format
     *
     * @param calendar a Gregorian Calendar parameter to be set in string format
     * @return date as string in yyyy-MM-dd format
     */
    public static String dateToString(Calendar calendar) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
        return df.format(calendar.getTime());
    }


    /**
     * Wrapper for TextStyle Enum class. Prints an ANSI escape code at the current cursor position.
     *
     * @param format a string representing one of the values in the enum
     */
    private static void format(String format) {
        System.out.print(TextStyle.valueOf(format).toString());
    }

    private static void format(String format1, String format2) {
        System.out.print(TextStyle.valueOf(format1).toString());
        System.out.print(TextStyle.valueOf(format2).toString());
    }

    private static void format(String format1, String format2, String format3) {
        System.out.print(TextStyle.valueOf(format1).toString());
        System.out.print(TextStyle.valueOf(format2).toString());
        System.out.print(TextStyle.valueOf(format3).toString());
    }


    public static void showList(String title, List<String> listToShow, boolean numbered, int padding) {
        showFormattedList(title, listToShow, numbered, padding);
    }

    public static void showList(String title, List<String> listToShow, boolean numbered) {
        showFormattedList(title, listToShow, numbered, 1);
    }

    public static void showList(String title, List<String> listToShow) {
        showFormattedList(title, listToShow, false, 1);
    }

    /**
     * UI method for displaying a formatted list to the user
     *
     * @param title      of the list/table
     * @param listToShow the list containing the items to show
     * @param numbered   shows the item as a numbered list
     * @param padding    the amount of spaces to the left and right of the widest element in the table
     */

    private static void showFormattedList(String title, List<String> listToShow, boolean numbered, int padding) {

        int tableWidth;
        int widestString = listToShow.get(0).length();

        for (String item : listToShow
        ) {
            if (item.length() > widestString) {
                widestString = item.length();
            }
        }

        if (title.length() > widestString)
            widestString = title.length();

        int extraPadding = 0;

        if (numbered) {
            extraPadding = 6;
        }
        tableWidth = widestString + padding * 2 + extraPadding;
        if (tableWidth % 2 == 1) {
            tableWidth++;
        }


        //Display a title
        final String a = "BG_BLUE";
        final String b = "BOLD";
        final String c = "BLACK";

        format(a, b, c);
        printAndReset(padWithSpaces("", tableWidth, padding));
        format(a, b, c);
        printAndReset(padWithSpaces(title, tableWidth, padding));
        format(a, b, c);
        printAndReset(padWithSpaces("", tableWidth, padding));

        // Display the items
        format("BG_WHITE", c);
        String paddedOutput;
        int number;
        for (String item : listToShow
        ) {
            if (numbered) {
                number = listToShow.indexOf(item);
                item = addNumberToItem(item, number);
            }

            paddedOutput = padWithSpaces(item, tableWidth, padding);

            format("BG_WHITE", c);
            printAndReset(paddedOutput);
        }

    }

    private static String addNumberToItem(String item, int number) {
        String currentNumber = "[" + number + "]";

        //Max number of items in list is 999 before formatting breaks.


        currentNumber = padWithSpaces(currentNumber, 6, 0);

        return currentNumber + item;
    }


    private static String createWhiteSpace(int spaces) {
        if (spaces <= 0) {
            return "";
        }
        StringBuilder output = new StringBuilder();

        for (int i = 1; i < spaces; i++) {
            output.append(" ");
        }

        return output.toString();

    }

    private static void printAndReset(String string) {
        System.out.print(string);
        format("RESET");
        System.out.print("\n");
    }

    private static void print(String string) {
        System.out.print(string);
    }

    private static String padWithSpaces(String string, int maxLength, int padding) {
        StringBuilder output = new StringBuilder();

        output.append(createWhiteSpace(padding));
        output.append(string);

        int spacesToAdd = maxLength - padding * 2 - string.length();

        output.append(createWhiteSpace(spacesToAdd));
        output.append(createWhiteSpace(padding));

        return output.toString();
    }

    public static void backToMenu() {
        Scanner read = new Scanner(System.in);
        format("BG_BLUE","BOLD","BLACK");
        System.out.println("---\nPress Enter to return to the previous Menu");
        read.nextLine();
    }

    public static void underMaintenanceMsg(String inputUS) {

        String customMsg = inputUS + " is under maintenance, it will be available shortly.";
        showError("Under maintenance",customMsg);

        backToMenu();
    }

    public static void printLnInsertValidOptionMsg() {
        showError("Warning","Please insert a valid option.");
    }

    public static void printLnInsertValidParameter(final String parameter) {
        showError("Warning","Please insert a valid " + parameter + ".");
    }

    public static String insertValidParameter(final String parameter) {
        return "Please insert a valid " + parameter + ".";
    }


    public static void showError(String errorTitle, String errorMessage) {
        int padding = 5;
        String title = padWithSpaces(errorTitle, errorTitle.length(), padding);
        String titlePretty = createWhiteSpace((errorTitle.length() - 1) + (padding * 2));
        String error = padWithSpaces(errorMessage, 999, 5);

        format("RESET");
        print("\n");
        format("BOLD", "BG_RED", "BLACK");
        printAndReset(titlePretty);
        format("BOLD", "BG_RED", "BLACK");
        printAndReset(title);
        format("BOLD", "BG_RED", "BLACK");
        printAndReset(titlePretty);
        format("RESET");
        format("REVERSED");
        print("\n");
        print(error);
        print("\n\n");
    }

}
