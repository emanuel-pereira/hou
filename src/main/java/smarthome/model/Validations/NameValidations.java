package smarthome.model.Validations;

import java.util.logging.Logger;

public class NameValidations {

    public boolean nameIsValid(String inputName) {
        if (inputName.trim().isEmpty()) {
            Logger.getLogger("Empty spaces are not accepted.");
            return false;
        }
        if (!inputName.matches("^[A-Za-z -]+$")) {
            Logger.getLogger("Please insert only alphabetic characters with spaces or hyphens.");
            return false;
        }
        return true;
    }


    public boolean alphanumericName(String inputName) {
        if (inputName.trim().isEmpty()) {
            Logger.getLogger("Empty spaces are not accepted.");
            return false;
        }
        if (!inputName.matches("^[A-Za-z0-9 -]+$")) { //accepts alphanumeric characters, spaces
            Logger.getLogger("Please insert only alphabetic characters with spaces or hyphens.");
            return false;
        }
        return true;
    }


}
