package smarthome.model;

public class NameValidations {

    public String nameIsValid(String inputName) {
        if (inputName.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!inputName.matches("^[A-Za-z -]+$")) {
            System.out.println("Please insert only alphabetic characters with spaces or hyphens.");
            return null;
        }
        return inputName;
    }
}
