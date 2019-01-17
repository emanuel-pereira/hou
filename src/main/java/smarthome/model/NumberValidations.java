package smarthome.model;

public class NumberValidations {

    public boolean valueIsPositive(double value) {
        if (value > 0)
            return true;
        return false;
    }
}
