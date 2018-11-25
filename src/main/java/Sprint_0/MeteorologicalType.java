package Sprint_0;

public class MeteorologicalType {

    //Atributos da classe
    private String _meteorologicalTypeDesignation;

    //Construtor
    public MeteorologicalType(String designation) {
        setMeteorologicalTypeDesignation (designation);
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
