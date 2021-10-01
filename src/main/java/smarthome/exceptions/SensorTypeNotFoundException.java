package smarthome.exceptions;

import java.io.Serializable;

public class SensorTypeNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 7096597082954830719L;

    public SensorTypeNotFoundException(String exception) {
        super(exception);
    }
}