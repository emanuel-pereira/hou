package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.List;


public class US7SetParentOfGACTRL {

    private GAList mGAList;

    public US7SetParentOfGACTRL(GAList inputList) {
        mGAList = inputList;
    }

    public void setParentofGA(int indexGA1, int indexGA2) {
        GeographicalArea GA = mGAList.getGAList ().get (indexGA1 - 1);
        GeographicalArea GA2 = mGAList.getGAList ().get (indexGA2 - 1);
        if (!(GA.getGeographicalAreaType ().equals (GA2.getGeographicalAreaType ())))
            GA.setmParentGA (GA2);

    }


    public List<GeographicalArea> getGAList() {
        return mGAList.getGAList ();
    }

    public String showListInString() {
        return mGAList.showGAListInString ();
    }
}
