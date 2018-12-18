package smarthomes.controller;

import smarthomes.model.DataType;
import smarthomes.model.DataTypeList;

import java.util.List;


public class US5DefineSensorDataTypeCTRL {

    private DataTypeList mDataTypeList;

    /**
     * Controller constructor
     * @param dataTypeList the list object on which the user will be able to create new type of GA's
     */
    public US5DefineSensorDataTypeCTRL(DataTypeList dataTypeList) {
        mDataTypeList = dataTypeList;
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     * @param newDataType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean newDataType(String newDataType) {
        DataType dataType = mDataTypeList.newDataType(newDataType);
        if (dataType != null)
            return mDataTypeList.addDataType(dataType);
        return false;
    }


    public String returnDataTypeList() {
        List<DataType> DataTypeList = mDataTypeList.getDataTypeList();
        StringBuilder result = new StringBuilder();

        for (DataType datatype: DataTypeList){
            result.append(datatype.getDataTypeDesignation());
            result.append("\n");
        }
        return result.toString();
    }
}
