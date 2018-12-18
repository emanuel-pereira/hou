package pt.ipp.isep.dei.smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class DataTypeList {

    private List<DataType> mDataTypeList;


    /**
     * Constructor method that creates a new list to save data type objects
     */
    public DataTypeList() {
        mDataTypeList = new ArrayList<>();
    }


    /**
     * Method to create Data Type object and assign a designation to it
     *
     * @param dataTypeDesignation - String that names the type of data
     * @return new data type object with designation
     */
    public DataType newDataType(String dataTypeDesignation) {
        if (dataTypeDesignation != null)
            dataTypeDesignation = dataTypeDesignation.toLowerCase();
        if (this.dataTypeDesignationIsValid(dataTypeDesignation))
            return new DataType(dataTypeDesignation);
        return null;
    }


    /**
     * Method to add a data type object to Data Type list, if it is not on the list yet
     *
     * @param newDataType - new Data Type object that will or not be added to the list
     * @return true if the object is added to the list
     */
    public boolean addDataType(DataType newDataType) {
        if (mDataTypeList.contains(newDataType) || (newDataType == null))
            return false;
        return mDataTypeList.add(newDataType);
    }



    /**
     * Method to return the data types included in the list
     *
     * @return list of data types created
     */
    public List<DataType> getDataTypeList() {
        return mDataTypeList;
    }


    /**
     * Method to validate the designation given to a type of data
     *
     * @param dataTypeDesignation - String that names the type of data
     * @return true if the designation is not null or empty after trimming the spaces
     */
    public boolean dataTypeDesignationIsValid(String dataTypeDesignation) {
         if(dataTypeDesignation == null || dataTypeDesignation.trim().isEmpty())
            return false;
        return dataTypeDesignation.matches("[a-zA-Z]*");
    }



}

