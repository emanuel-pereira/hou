package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {

    private HouseGrid mHouseGrid;
    private List<HouseGrid> mHGList;

    public HouseGridList() {
        mHGList = new ArrayList<>();
    }

    //HouseGridList

    public HouseGrid newHouseGrid(String ID, double inputContractedMaximumPower) {
        if (isValidContractedPower(inputContractedMaximumPower)) {
            return mHouseGrid = new HouseGrid(ID,inputContractedMaximumPower);
        }
        return null;
    }

    /**
     * Adds a house grid to the list of grids of a house if it isn't null or
     * already contained in the houseGrid list of the respective house instance.
     *
     * @param inputHouseGrid houseGrid to be added to list of HouseGrids of a house instance.
     * @return true if houseGrid is added to list or false otherwise.
     */
    public boolean addHouseGrid(HouseGrid inputHouseGrid) {
        if (inputHouseGrid != null && !mHGList.contains(inputHouseGrid)) {
            mHGList.add(inputHouseGrid);
            return true;
        } else return false;
    }

    /**
     * @return the list of housegrids in the house
     */
    public List<HouseGrid> getHouseGridList() {
        return mHGList;
    }

    /**
     * checks if the inputted Maximum Power is Positive
     *
     * @param inputContractedMaximumPower Double number
     * @return
     */
    public boolean isValidContractedPower(double inputContractedMaximumPower) {
        return inputContractedMaximumPower > 0;
    }

    public HouseGrid get(int i) {
        return this.mHGList.get(i);
    }
}
