package smarthome.exceptions;

public class ExternalSensorNotFoundException extends RuntimeException implements java.io.Serializable  {
    private static final long serialVersionUID = 2796801915020942857L;

    public ExternalSensorNotFoundException(String exception) {

        super(exception);
    }
}
