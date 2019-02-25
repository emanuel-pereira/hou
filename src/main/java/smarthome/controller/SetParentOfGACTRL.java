package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.List;


public class SetParentOfGACTRL {

    private GAList gaList;


    public SetParentOfGACTRL(GAList inputList) {
        gaList = inputList;
    }

    public void setParentofGA(int indexGA1, int indexGA2) {
        GeographicalArea ga = gaList.getGAList().get(indexGA1 - 1);
        GeographicalArea ga2 = gaList.getGAList().get(indexGA2 - 1);
        if (!(ga.getGeographicalAreaType().equals(ga2.getGeographicalAreaType())))
            ga.setParentGA(ga2);
    }


    public List<GeographicalArea> getGAList() {
        return gaList.getGAList();
    }

    public int getGAListSize() {
        return gaList.size();
    }

    public String getGaName(int indexGa) {

        return gaList.get(indexGa -1).getGAName();
    }

    public String showListInString() {
        return gaList.showGAListInString();
    }
}