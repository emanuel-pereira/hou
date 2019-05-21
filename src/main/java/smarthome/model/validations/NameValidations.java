package smarthome.model.validations;


public class NameValidations {

    public boolean nameIsValid(String inputName) {
        if (inputName == null)
            return false;
        if (inputName.trim().isEmpty()) {
            return false;
        }
        return inputName.matches ("^[A-Za-z -]+$");
    }


    public boolean alphanumericName(String inputName) {
        if (inputName.trim().isEmpty()) {
            return false;
        }
        //accepts alphanumeric characters, spaces
        return inputName.matches ("^[A-Za-z0-9 -]+$");
    }

    public boolean idIsValid(String inputId) {
        if (inputId == null)
            return false;
        if (inputId.trim().isEmpty()) {
            return false;
        }
        return inputId.matches ("^[A-Za-z0-9]+$");
    }

}
