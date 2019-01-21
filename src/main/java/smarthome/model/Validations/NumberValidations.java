package smarthome.model.Validations;

public class NumberValidations {

    /**
     * Boolean method to validate double values
     * @param value double value
     * @return true if value is positive(greater than zero), otherwise returns false
     */
    public boolean valueIsPositive(double value) {
        if (value > 0)
            return true;
        return false;
    }
}
