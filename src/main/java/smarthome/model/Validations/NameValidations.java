package smarthome.model.Validations;

public class NameValidations {

    public boolean nameIsValid(String inputName) {
        if (inputName.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return false;
        }
        if (!inputName.matches("^[A-Za-z -]+$")) {
            System.out.println("Please insert only alphabetic characters with spaces or hyphens.");
            return false;
        }
        return true;
    }


    public boolean alphanumericName(String inputName) {
        if (inputName.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return false;
        }
        if (!inputName.matches("^[A-Za-z0-9 -]+$")) { //accepts alphanumeric characters, spaces
            System.out.println("Please insert only alphabetic characters with spaces or hyphens.");
            return false;
        }
        return true;
    }


}
