package smarthome.io.ui;

import java.util.*;

public final class UtilsUI {

    /**
     * Private constructor of UtilsUI class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private UtilsUI() {
    }


    public static void printLnInsertValidOption() {
        System.out.println("Please insert a valid option.");
    }

    public static void printLnInsertValidParameter(final String parameter) {
        System.out.println("Please insert a valid " + parameter + ".");
    }


    /**
     * Reads user input from the console and returns it as a string
     *
     * @return
     */
    private static String getUserInput() {
        Scanner read = new Scanner(System.in);
        String input = read.nextLine();
        return input;
    }


    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param input any String
     * @return true if it's a valid representation of a double
     */

    private static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param input a String containing a date in "yyyy-mm-dd" format
     * @return true if it's a valid date representation
     */

    private static boolean isDate(String input) {
        String userInput = input.trim(); // remove any spaces from input as the user may be stupid.
        String[] splitInput = new String[3];

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
        String[] splitInput = new String[3];
        splitInput = userInput.split("-", 3);
        System.out.println(Integer.parseInt(splitInput[field]));

        return Integer.parseInt(splitInput[field]);
    }

    private static boolean isTime(String input) {
        String userInput = input.trim(); // remove any spaces from input as the user may be stupid.
        String[] splitInput = new String[2];

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

        if (hour > 23 || hour < 0 || minute > 59 || minute < 0) {
            return false;
        }

        return true;
    }

    /**
     * @param input a string that represents a date and time in "YYYY-MM-DD hh:mm" format
     * @return true if the string is a valid date and time
     */
    private static boolean isDateTime(String input) {
        String[] dateAndTime = new String[2];
        dateAndTime = input.split(" ", 2);

        String sDate = dateAndTime[0];
        String sTime = dateAndTime[1];

        return (isDate(sDate) && isTime(sTime));
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
     * UI method that requests a double in a preset interval
     *
     * @param minimum
     * @param maximum
     * @param errorMessage
     * @return
     */
    public static double requestDoubleInInterval(double minimum, double maximum, String errorMessage) {
        String userInput = "-";
        double parsedUserInput = minimum - 1;

        while (!isDouble(userInput)) {
            userInput = getUserInput();
            if (isDouble(userInput)) {
                parsedUserInput = Double.parseDouble(userInput);
            }
            if (parsedUserInput >= minimum && parsedUserInput <= maximum) {
                break;
            }
            System.out.println(errorMessage);
            userInput = "-";
        }

        return parsedUserInput;
    }

    /**
     * UI method that requests a date input from the user and validates it.
     *
     * @param errorMessage
     * @return a GregorianClass object set to the user defined date
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
        month = getFieldValueFromDate(userInput, 1) - 1; // have to correct for the way months are stored [0,11] and not [1,12}
        day = getFieldValueFromDate(userInput, 2);

        GregorianCalendar c = new GregorianCalendar(year, month, day);

        return c;
    }

    /**
     * Wrapper for TextStyle Enum class. Prints an ANSI escape code at the current cursor position.
     *
     * @param format a string representing one of the values in the enum
     */
    public static void format(String format) {
        System.out.print(TextStyle.valueOf(format).toString());
    }

    public static void format(String format1, String format2) {
        System.out.print(TextStyle.valueOf(format1).toString());
        System.out.print(TextStyle.valueOf(format2).toString());
    }

    public static void format(String format1, String format2, String format3) {
        System.out.print(TextStyle.valueOf(format1).toString());
        System.out.print(TextStyle.valueOf(format2).toString());
        System.out.print(TextStyle.valueOf(format2).toString());
    }

    public static void showList(String title, List<String> listToShow) {
        //boolean numbered, int leftAlignment, int padding

        int tableWidth;
        int widestString = listToShow.get(0).length();
        int numberOfItems = listToShow.size();

        for (String item : listToShow
        ) {
            if (item.length() > widestString) {
                widestString = item.length();
            }
        }
        if (title.length() > widestString) {
            widestString = title.length();
        }

        //tableWidth = widestString + padding * 2;

        format("BG_BLUE");

        System.out.println("\n"+title+"\n");
        format("RESET");
        format("REVERSED");

        for (String item:listToShow
             ) {
            System.out.println(item);
        }

    }

    private static String padWithSpaces(String string, int length) {
        StringBuilder output = new StringBuilder();
        output.append(string);
        return "";
    }

}
