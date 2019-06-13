package smarthome.model;

import org.apache.log4j.Logger;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class HouseGridList {

    //private final List<HouseGrid> hgListCopy;
    static final Logger log = Logger.getLogger(HouseGridList.class);
    private final List<HouseGrid> hgList;

    public HouseGridList() {
        this.hgList = new ArrayList<>();
        /*hgListCopy = new ArrayList<>();
        this.hgListCopy.addAll(hgList);*/
    }

    /**
     * Receives the String ID of the new grid and calls the HouseGrid class constructor that
     * creates the new house electric grid
     *
     * @param id String object designation of the grid
     * @return new HouseGrid object
     */
    public HouseGrid newHouseGrid(String id) {
        return new HouseGrid(id);
    }

    /**
     * Adds a house grid to the list of grids of a house if it isn't null or
     * already contained in the houseGrid list of the respective house instance.
     *
     * @param inputHouseGrid houseGrid to be added to list of HouseGrids of a house instance.
     * @return true if houseGrid is added to list or false otherwise.
     */
    //TODO due to DDD this method should only be called internally by the house, and never exposed publicly to other classes
    public boolean addHouseGrid(HouseGrid inputHouseGrid) {
        if (!this.hgList.contains(inputHouseGrid)) {
            this.hgList.add(inputHouseGrid);
            try {
                //Repository call
                Repositories.getGridsRepository().save(inputHouseGrid);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
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
        // List<HouseGrid> hgListCopy = new ArrayList<>(hgList);
        //hgListCopy.addAll(hgList);
        return new ArrayList<>(hgList);
        //return hgListCopy;
    }

    /**
     * Method that gets the electric grid list size
     *
     * @return int form value that represents the electric grid list size
     */
    public int getSize() {
        return this.hgList.size();
    }

    public HouseGrid get(int i) {
        return this.hgList.get(i);
    }


    /**
     * Transforms a list of electric grids in a numbered list of strings with the names of the  grids
     *
     * @return List of grids in string format
     */
    public String showHouseGridListInString() {
        List<HouseGrid> list = getHouseGridList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (HouseGrid grid : list) {
            result.append(number++);
            result.append(element);
            result.append(grid.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }

}
