package Sprint_0;

public class DataType {

    //Atributos da classe
    private String _dataTypeDesignation;

    //Construtor
    public DataType(String designation) {
        setDataTypeDesignation (designation);
    }


    //Métodos

    public String getDataTypeDesignation() {
        return this._dataTypeDesignation;
    }


    public boolean setDataTypeDesignation(String designation) {
        if (designation == null || designation.trim ().isEmpty ()) {
            return false;
        }
        this._dataTypeDesignation = designation;
        return true;
    }


}
    

