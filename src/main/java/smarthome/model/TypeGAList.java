package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.model.validations.NameValidations;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class TypeGAList {

    private List<TypeGA> typeOfGAList;
    static final Logger log = Logger.getLogger(TypeGAList.class);

    /**
     * Constructor for TypeGAList where the List of GA's is instantiated
     */
    public TypeGAList() {
        this.typeOfGAList = new ArrayList<>();
    }

    public static boolean contains(TypeGA type) {
        return typeOfGAList.contains(type);
    }

    /**
     * Method to create a new object Type of GA with the user's input String designation
     *
     * @param inputName user's input String designation
     * @return a new object Type of GA with the user's input or null if the input was not valid
     */
    public TypeGA newTypeGA(String inputName) {
        String inputNameLowerCase = null;
        NameValidations validations = new NameValidations();
        if (inputName != null)
            inputNameLowerCase = inputName.toLowerCase();
        if (validations.nameIsValid(inputNameLowerCase))
            return new TypeGA(inputNameLowerCase);
        return null;
    }

    /**
     * Method to add the previous created new object into the typeGA ArrayList of GA's types
     *
     * @param inputType object Type of GA with the user's input
     * @return true if it was possible to add the user new input, false if the input type is
     * already contained.
     */
    public boolean addTypeGA(TypeGA inputType) {
        if (inputType != null && !this.typeOfGAList.contains(inputType)) {
            this.typeOfGAList.add(inputType);

            //Repository call
            try {
                Repositories.getTypeGARepository().save(inputType);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
            return true;
        } else return false;
    }

    /**
     * Method to return the the list of previously entered GA type's
     *
     * @return the list of previously entered GA type's
     */
    public List<TypeGA> getTypeGAList() {
        return this.typeOfGAList;
    }

    public int size() {
        return this.typeOfGAList.size();
    }

    public TypeGA get(int index) {
        return this.typeOfGAList.get(index);
    }

}
