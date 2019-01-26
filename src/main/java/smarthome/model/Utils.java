package smarthome.model;

public final class Utils {

    /**
     * Private constructor of Utils class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private Utils() {
    }

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
}
