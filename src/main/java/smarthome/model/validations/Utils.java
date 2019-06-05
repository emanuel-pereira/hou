package smarthome.model.validations;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Utils {
    private Utils(){}

    /**
     * Method that rounds a double to specified number of decimal places.
     *
     * @param value         Value to be rounded.
     * @param decimalPlaces Number of decimal places.
     * @return Double with the rounded value.
     */
    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, decimalPlaces);
        double multipliedValue = value * factor;
        long tmp = Math.round(multipliedValue);
        return (double) tmp / factor;
    }

    /**
     * Boolean method to validate double values
     *
     * @param value double value
     * @return true if value is positive(greater than zero), otherwise returns false
     */
    public static boolean valueIsPositive(double value) {
        return value > 0;
    }

    public static Calendar convertStringToCalendar (String input) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try{
            Date date = df.parse(input);
        }

        catch (ParseException e){
            return new GregorianCalendar(1000, Calendar.JANUARY, 1);
        }

        Date date = df.parse(input);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
