package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.List;


public class SetParentOfGACTRL {

    private GAList mGAList;

    public SetParentOfGACTRL(GAList inputList) {
        mGAList = inputList;
    }

    public void setParentofGA(int indexGA1, int indexGA2) {
        GeographicalArea ga = mGAList.getGAList().get(indexGA1 - 1);
        GeographicalArea ga2 = mGAList.getGAList().get(indexGA2 - 1);
        if (!(ga.getGeographicalAreaType().equals(ga2.getGeographicalAreaType())))
            ga.setParentGA(ga2);
    }


    public List<GeographicalArea> getGAList() {
        return mGAList.getGAList ();
    }

    public String showListInString() {
        return mGAList.showGAListInString ();
    }
}
