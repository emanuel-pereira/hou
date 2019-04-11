package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.model.validations.NameValidations;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class TypeGAList {

    //TODO: mudar nome
    private static List<TypeGA> listOfGaTypes;
    private static TypeGAList typeGaList = null;

    static final Logger log = Logger.getLogger(TypeGAList.class);

    /**
     * Constructor for TypeGAList where the List of GA's is instantiated
     */

    public static TypeGAList getTypeGAListInstance() {

        if (typeGaList == null){
            typeGaList = new TypeGAList();
        }

        return typeGaList;
    }

    private TypeGAList() {
        listOfGaTypes = new ArrayList<>();
    }

    /**
     * Method to create a new object Type of GA with the user's input String designation
     *
     * @param inputName user's input String designation
     * @return a new object Type of GA with the user's input or null if the input was not valid
     */
    public static TypeGA newTypeGA(String inputName) {
        String inputNameLowerCase = null;
        NameValidations validations = new NameValidations();
        //TODO: Throw Exception
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
    public static boolean addTypeGA(TypeGA inputType) {
        if (inputType != null && !listOfGaTypes.contains(inputType)) {
            listOfGaTypes.add(inputType);

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
    public static  List<TypeGA> getTypeGAList() {
        return listOfGaTypes;
    }

    public static  int size() {
        return listOfGaTypes.size();
    }

    public static TypeGA get(int index) {
        return listOfGaTypes.get(index);
    }

}
