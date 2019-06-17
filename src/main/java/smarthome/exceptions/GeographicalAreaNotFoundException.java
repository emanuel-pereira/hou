package smarthome.exceptions;

import java.io.Serializable;

public class GeographicalAreaNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5402321702426209091L;

    public GeographicalAreaNotFoundException(String exception) {
        super(exception);
    }
}