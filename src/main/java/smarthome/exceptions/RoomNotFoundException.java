package smarthome.exceptions;

import java.io.Serializable;

public class RoomNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -6250201851359125236L;

    public RoomNotFoundException(String exception) {
        super(exception);
    }
}