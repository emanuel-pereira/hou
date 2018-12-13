package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class TypeGAList {

    private List<TypeGA> mTypeGA;

    /**
     * Constructor for TypeGAList where the List of GA's is instantiated
     */
    public TypeGAList() {
        mTypeGA = new ArrayList<>();
    }

    /**
     * Method to create a new object Type of GA with the user's input String designation
     * @param inputName user's input String designation
     * @return a new object Type of GA with the user's input or null if the input was not valid
     */
    public TypeGA newTypeGA(String inputName) {
        if (inputName != null)
                inputName = inputName.toLowerCase();
        if (this.typeOfGAIsValid(inputName))
            return new TypeGA(inputName);
        return null;
    }

    /**
     * Method to add the previous created new object into the mTypeGA ArrayList of GA's types
     * @param inputType bject Type of GA with the user's input
     * @return true if it was possible to add the user new input, false if the input type is
     * already contained.
     */
    public boolean addTypeGA(TypeGA inputType) {
        if (!mTypeGA.contains(inputType)) {
            mTypeGA.add(inputType);
            return true;
        } else return false;
    }

    /**
     * Method to return the the list of previously entered GA type's
     * @return the list of previously entered GA type's
     */
    public List<TypeGA> getGAList() {
        return mTypeGA;
    }

    /**
     * Validation of the input used in the method newTypeGA
     * @param inputType The input string of the user
     * @return true if the user's input matches upper or lower case characters from A to Z.
     * false if the previous regular expression is not matched or if the input is blank or null
     */
    public boolean typeOfGAIsValid(String inputType) {
        if (inputType == null || inputType.trim().isEmpty())
            return false;
        return inputType.matches("[a-zA-Z]*");
    }


}
