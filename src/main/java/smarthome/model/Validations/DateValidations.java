package smarthome.model.Validations;

public class DateValidations {

    /**
     * Method that checks if year inputted by the user is valid considering a range between 2010 and 2099
     *
     * @param year input year
     * @return year if input meets regex criteria, otherwise returns false
     */
    public boolean yearIsValid(String year) {
        checkIfInputIsEmpty(year);
        if (!year.matches("^201[0-9]|20[2-9][0-9]$")) {
            System.out.println("Please insert a valid year between 2010 and 2099.");
            return false;
        }
        return true;
    }


    /**
     * Method that checks if month inputted by the user is valid considering a range regex values between 1 to 12
     *
     * @param inputMonth input month
     * @return month if input meets regex criteria, otherwise returns false
     */
    public boolean monthIsValid(String inputMonth) {
        checkIfInputIsEmpty(inputMonth);
        if (!inputMonth.matches("^([1-9]|1[0-2])$")) { //only accepts values between 1 and 12
            System.out.println("Please insert a valid month.");
            return false;
        }
        return true;
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     *
     * @param day        input day
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear  previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns false
     */
    public boolean dayIsValid(String day, int inputMonth, int inputYear) {
        checkIfInputIsEmpty(day);
        if (!day.matches("^(3[01]|[12][0-9]|[1-9])$")) { //only accepts values between 1 and 31
            System.out.println("Please insert a valid day.");
            return false;
        }
        if (inputMonth == 4 || inputMonth == 6 || inputMonth == 9 || inputMonth == 11) {
            if (day.matches("^31$")) {
                System.out.println("Please insert a valid day for the selected month: (" + inputMonth + ")");
                return false;
            }
        }
        if (inputMonth == 2) {
            if (inputYear % 4 != 0) {
                if (day.matches("^(29|30|31)$")) {
                    System.out.println("Please insert a valid day for the selected month: (" + inputMonth + ")");
                    return false;
                }
            } else if (day.matches("^(30|31)$")) {
                System.out.println("Please insert a valid day for the selected month: (" + inputMonth + ")");
                return false;
            }
        }
        return true;
    }

    private boolean checkIfInputIsEmpty(String inputString) {
        if (inputString.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
        }
        return false;
    }

    /**
     * Method that checks if hour inputted by the user is valid
     *
     * @param hour input hour
     * @return hour if input meets regex criteria, otherwise returns false
     */
    public boolean hourIsValid(String hour) {
        checkIfInputIsEmpty(hour);
        if (!hour.matches("^(2[0-3]|[1][0-9]|[0-9])")) {
            System.out.println("Please insert a valid hour.");
            return false;
        }
        return true;
    }

    /**
     * Method that checks if minute inputted by the user is valid
     *
     * @param minute input hour
     * @return hour if input meets regex criteria, otherwise returns false
     */
    public boolean minuteIsValid(String minute) {
        checkIfInputIsEmpty(minute);
        if (!minute.matches("^([0-9]|[1-5][0-9])")) {
            System.out.println("Please insert a valid minute.");
            return false;
        }
        return true;
    }
}
