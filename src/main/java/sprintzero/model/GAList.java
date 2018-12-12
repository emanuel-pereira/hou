package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List <GeographicalArea> mGAList;

    //Construtor

    public GAList() {

        mGAList = new ArrayList<>();
    }

    //Métodos

    public GeographicalArea newGA(String inputDesignation, String typeArea, double width,double lenght,double latitude,double longitude,double altitude ) {
        return new GeographicalArea (inputDesignation,typeArea,width,lenght,latitude,longitude,altitude);
    }


    public boolean addGA(GeographicalArea inputGA) {
        if (!mGAList.contains (inputGA)) {
            mGAList.add (inputGA);
            return true;
        } else return false;
    }

    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

}
