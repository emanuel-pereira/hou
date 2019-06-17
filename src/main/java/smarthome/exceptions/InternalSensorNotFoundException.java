package smarthome.exceptions;


public class InternalSensorNotFoundException extends RuntimeException implements java.io.Serializable {

    private static final long serialVersionUID = -5249542747889980560L;

    public InternalSensorNotFoundException(String exception) {

        super(exception);
    }
}
