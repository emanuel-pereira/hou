package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class DataTypeList {

    private List<DataType> mDataType;


    /**
     * Constructor method that creates a new list to save data type objects
     */
    public DataTypeList() {
        mDataType = new ArrayList<>();
    }


    /**
     * Method to create Data Type object and assign a designation to it
     *
     * @param dataTypeDesignation - String that names the type of data
     * @return new data type object with designation
     */
    public DataType newDataType(String dataTypeDesignation) {
        return new DataType(dataTypeDesignation);
    }


    /**
     * Method to add a data type object to Data Type list, if it is not on the list yet
     *
     * @param newDataType - new Data Type object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addDataType(DataType newDataType) {
        if (!mDataType.contains(newDataType)) {
            mDataType.add(newDataType);
            return true;
        } else return false;
    }



    /**
     * Method to return the data types included in the list
     *
     * @return list of data types created
     */
    public List<DataType> getDataTypeList() {
        return mDataType;
    }



}

