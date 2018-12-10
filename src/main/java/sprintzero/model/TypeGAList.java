package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class TypeGAList {

    //Atributos

    private List<TypeGA> mTypeGA;


    //Construtor

    public TypeGAList() {
        mTypeGA = new ArrayList<> ();
    }

    //Métodos

    public TypeGA newTypeGA(String inputName) {
        return new TypeGA (inputName);
    }


    public boolean addTypeGA(TypeGA inputType) {
        if (!mTypeGA.contains (inputType)) {
            mTypeGA.add (inputType);
            return true;
        } else return false;
    }


    public List<TypeGA> getGAList() {
        return mTypeGA;
    }

}
