package Sprint_0;

public class DataType {

    //Atributos da classe
    private String mDataTypeDesignation;

    //Construtor
    public DataType(String designation) {
        setDataTypeDesignation (designation);
    }


    //Métodos

    public String getDataTypeDesignation() {
        return this.mDataTypeDesignation;
    }


    public boolean dataTypeValidation(String designation) {
        if (designation == null || designation.trim ().isEmpty ()) {
            return false;
        }
        this.mDataTypeDesignation = designation;
        return true;
    }


    public void setDataTypeDesignation(String designation) {
        if (this.dataTypeValidation (designation) == true){
            this.mDataTypeDesignation = designation;
        }
    }




}


