package smarthome.model;


public class Name {


    private String name;

    public Name(String name) {

        if ((nameIsValid(name)) && (alphanumericName(name)))

            this.name = name;

    }


    /**
     * Method to validate if input name is correct
     * @param  name is used to see as a reference to check.
     * @return false if name is null otherwise true.
     */

    public boolean nameIsValid(String name) {
        if (name == null)
            return false;
        if (name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[A-Za-z -]+$");
    }

    /**
     * Method to verify alphanumeric characters.
     * @param Name is used to see as a reference to check.
     * @return false if is empty.
     */
    public boolean alphanumericName(String Name) {
        if (Name.trim().isEmpty()) {
            return false;
        }

        return Name.matches("^[A-Za-z0-9 -]+$");
    }

    /**
     * Convert Name to string.
     * @return name in string.
     */
    @Override
    public String toString() {
        return name;
    }
}

