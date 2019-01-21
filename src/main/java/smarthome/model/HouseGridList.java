package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {

    private List<HouseGrid> mHGList;

    public HouseGridList() {
        mHGList = new ArrayList<> ();
    }

    /**
     * Receives the String ID of the new grid and calls the HouseGrid class constructor that
     * creates the new house electric grid
     *
     * @param ID String object designation of the grid
     * @return new HouseGrid object
     */
    public HouseGrid newHouseGrid(String ID) {
        return new HouseGrid (ID);
    }

    /**
     * Adds a house grid to the list of grids of a house if it isn't null or
     * already contained in the houseGrid list of the respective house instance.
     *
     * @param inputHouseGrid houseGrid to be added to list of HouseGrids of a house instance.
     * @return true if houseGrid is added to list or false otherwise.
     */
    public boolean addHouseGrid(HouseGrid inputHouseGrid) {
        if (!mHGList.contains (inputHouseGrid)) {
            mHGList.add (inputHouseGrid);
            return true;
        } else return false;
    }

    /**
     * Method that gets the List of electric house grids as a new ArrayList object
     * in order to pass the objects as a clone
     *
     * @return the list of house grids in the house as ArrayList
     */
    public List<HouseGrid> getHouseGridList() {
        List<HouseGrid> gridList = new ArrayList<> ();
        for (HouseGrid grid : mHGList) {
            gridList.add (grid);
        }
        return gridList;
    }

    /**
     * Method that gets the electric grid list size
     *
     * @return int form value that represents the electric grid list size
     */
    public int getSize() {
        return mHGList.size ();
    }

    public HouseGrid get(int i) {
        return this.mHGList.get (i);
    }


    /**
     * Transforms a list of house grids in a numbered list of strings with the ids of the house grids
     *
     * @return List of house grids in string
     */
    public String showHouseGridListInString() {
        List<HouseGrid> list = getHouseGridList ();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (HouseGrid houseGrid : list) {
            result.append (number++);
            result.append (element);
            result.append (houseGrid.getGridID ());
            result.append ("\n");
        }
        return result.toString ();
    }
}
