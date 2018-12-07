package sprintzero.model;

public class TypeGA {
    private String mTypeOfGA;

    public TypeGA() {
    }


    public boolean typeOfGAIsValid(String typeOfGA) {
        return typeOfGA != null && !typeOfGA.trim ().isEmpty ();
    }


    public boolean addTypeOfGA(String typeOfGA) { //passou para listTypeGA
        if (typeOfGAIsValid(typeOfGA)) {
            this.mTypeOfGA = typeOfGA;
            return true;
        }
        return false;
    }



}





