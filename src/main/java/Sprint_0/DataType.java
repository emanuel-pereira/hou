package Sprint_0;

public class DataType {

    //Atributos da classe
    private String _meteorologicalTypeDesignation;

    //Construtor
    public DataType(String designation) {
        this.setMeteorologicalTypeDesignation (designation);
    }


    //Métodos

    public String getMeteorologicalTypeDesignation() {
        return this._meteorologicalTypeDesignation;
    }


    public boolean setMeteorologicalTypeDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            return false;
        }
        this._meteorologicalTypeDesignation = designation;
        return true;
    }


}
