package smarthomes.model;


import java.util.Objects;

public class DataType {


    private String mDataType;


    /**
     * Constructor method that defines a designation for a type of data
     *
     * @param dataTypeDesignation - String that names the type of data
     */
    public DataType(String dataTypeDesignation) {
            this.mDataType = dataTypeDesignation;
    }


    /**
     * Method to return a data type designation
     *
     * @return designation of a data type
     */
    public String getDataTypeDesignation() {
        return this.mDataType;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DataType))
            return false;
        DataType dataType = (DataType) o;
        return this.mDataType.equals(dataType.getDataTypeDesignation());
    }

    /**
     * hashCode() just returns the object's address in memory
     * This method OverRides the hashCode method defined by class
     * Object does also return distinct integers for distinct objects.
     * This is typically implemented by converting the internal
     * address of the object into an integer
     *
     * @return hashcode int value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(mDataType);
    }
}


