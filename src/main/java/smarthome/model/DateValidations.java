package smarthome.model;

public class DateValidations {

    /**
     * Method that checks if year inputted by the user is valid considering a range between 2010 and 2099
     *
     * @param year input year
     * @return year if input meets regex criteria, otherwise returns null
     */
    public String yearIsValid(String year) {
        if (year.trim ().isEmpty ()) {
            this.emptySpacesMessage ();
            return null;
        }
        if (!year.matches ("^201[0-9]|20[2-9][0-9]$")) { //only accepts years between 2010 and 2099
            System.out.println ("Please insert a valid year between 2010 and 2099.");
            return null;
        }
        return year;
    }

    /**
     * Method that checks if month inputted by the user is valid considering a range regex values between 1 to 12
     *
     * @param inputMonth input month
     * @return month if input meets regex criteria, otherwise returns null
     */
    public String monthIsValid(String inputMonth) {
        if (inputMonth.trim ().isEmpty ()) {
            this.emptySpacesMessage ();
            return null;
        }
        if (!inputMonth.matches ("^([1-9]|1[0-2])$")) { //only accepts values between 1 and 12
            System.out.println ("Please insert a valid month.");
            return null;
        }
        return inputMonth;
    }

    /**
     * Method that checks if day inputted by the user is a valid day considering previously inputted month and year
     * considering leap years.
     *
     * @param day        input day
     * @param inputMonth previously inputted by the user parsed from string to integer
     * @param inputYear  previously inputted by the user parsed from string to integer
     * @return day if input meets regex criteria, otherwise returns null
     */
    public String dayIsValid(String day, int inputMonth, int inputYear) {
        if (day.trim ().isEmpty ()) {
            this.emptySpacesMessage ();
            return null;
        }
        if (!day.matches ("^(3[01]|[12][0-9]|[1-9])$")) { //only accepts values between 1 and 31
            System.out.println ("Please insert a valid day.");
            return null;
        }
        if (inputMonth == 4 || inputMonth == 6 || inputMonth == 9 || inputMonth == 11) {
            if (day.matches ("^31$")) {
                System.out.println ("Please insert a valid day for the selected month: (" + inputMonth + ")");
                return null;
            }
        }
        if (inputMonth == 2) {
            if (inputYear % 4 != 0) {
                if (day.matches ("^(29|30|31)$")) {
                    System.out.println ("Please insert a valid day for the selected month: (" + inputMonth + ")");
                    return null;
                }
            } else if (day.matches ("^(30|31)$")) {
                System.out.println ("Please insert a valid day for the selected month: (" + inputMonth + ")");
                return null;
            }
        }
        return day;
    }

    /**
     * Method that checks if hour inputted by the user is a valid
     *
     * @param hour input hour
     * @return hour if input meets regex criteria, otherwise returns null
     */
    public String hourIsValid(String hour) {
        if (hour.trim ().isEmpty ()) {
            this.emptySpacesMessage ();
            return null;
        }
        if (!hour.matches ("^(2[0-4]|[1][0-9]|[1-9])")) {
            System.out.println ("Please insert a valid hour.");
            return null;
        }
        return hour;
    }

    private void emptySpacesMessage() {
        System.out.print ("Empty spaces are not accepted.");
    }




}
