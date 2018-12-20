package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> mGAList;

    /**
     * Constructor method to set the attribute of the GA's List as an ArrayList
     */
    public GAList() {
        mGAList = new ArrayList<>();
    }

    /**
     * Method Constructor to set up new Geographical Areas
     *
     * @param inputDesignation GA String designation
     * @param typeArea         GA Type String designation
     * @param width            GA width
     * @param height           GA height
     * @param latitude         GA latitude coordinate
     * @param longitude        GA longitude coordinate
     * @param altitude         GA altitude coordinate
     * @return the newly created Geographical Area
     */
    public GeographicalArea newGA(String inputDesignation, String typeArea, double width, double height, double latitude, double longitude, double altitude) {
        return new GeographicalArea(inputDesignation, typeArea, width, height, latitude, longitude, altitude);
    }

    /**
     * Method to add a new GA into the GA's List
     * Before the placement the method will first verify the existence of a similar GA in the list
     * If none is found the method truly adds this new one to the list
     *
     * @param inputGA Geographical Area to be added
     * @return boolean value, true if correctly added, false if not added
     */
    public boolean addGA(GeographicalArea inputGA) {
        if (mGAList.contains(inputGA))
            return false;
        return mGAList.add(inputGA);
    }

    /**
     * Method similar to the get method for Lists but as the List is private in the class it is
     * needed to exist a method that publicly returns the List of GA's to other methods to read
     *
     * @return List of Geographical Areas
     */
    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

    /**
     * Method to get a specific Geographical Area in index position i
     *
     * @param i index position of the GA's List
     * @return the specific requested Geographical Area
     */
    public GeographicalArea get(int i) {
        return this.mGAList.get(i);
    }

    /**
     * Method that checks if a specific location is within one or more Geographical Areas and returns the
     * list of Geographical Areas that contain that location. If no match is found, then this method returns null.
     *
     * @param latitude  latitude of the location
     * @param longitude longitude of the location
     * @return a list of Geographical Areas that contain a specific location
     */
    public GAList listOfGAsContainingLocation(double latitude, double longitude) {
        GAList listOfGAsContainingLocation = new GAList();
        for (int i = 0; i < mGAList.size(); i++) {
            if (mGAList.get(i).locationIsInAG(latitude, longitude)) {
                listOfGAsContainingLocation.addGA(mGAList.get(i));
            }
        }
        return listOfGAsContainingLocation;
    }

    /**
     * Method to add a sensor to smallest Geographical Area in the list of Geographical Areas that contains a specific sensor's location.
     *
     * @param sensor sensor to be added to Geographical Area with the smallest occupation area
     * @return true is sensor is added to the smallest Geographical Area that contains the sensor
     */
    public GeographicalArea getSmallestGAContainingSensor(Sensor sensor) {
        GAList l = listOfGAsContainingLocation(sensor.getLocation().getLatitude(), sensor.getLocation().getLongitude());
        if (l.getGAList().size() == 0) {
            return null;
        }
        GeographicalArea smallerGA = l.get(0);
        double smallerArea = l.get(0).getOcupation().getOccupationArea();
        for (int i = 1; i < l.getGAList().size(); i++) {
            if (smallerArea > l.get(i).getOcupation().getOccupationArea()) {
                smallerArea = l.get(i).getOcupation().getOccupationArea();
                smallerGA = l.get(i);
            }
        }

        return smallerGA;
    }

    public boolean addSensorToSmallestGA(Sensor sensor) {
        return getSmallestGAContainingSensor(sensor).addSensor(sensor);
    }

    /**
     * US04
     * Method that returns a list of Geographical Areas of a certain Type.
     * The user inputs the TypeGA he wishes to obtain, for example, "street" and will receive a list of GAs from that Type.
     * A 'for each' was used for simpler and easier list iteration.
     *
     * @param inputTypeGA string inserted by user
     */
    public List<GeographicalArea> GAFromThisType(String inputTypeGA) {
        List<GeographicalArea> GAFromTypeList = new ArrayList<>();
        for (GeographicalArea ga : mGAList) {
            if (ga.getmTypeArea().getTypeGA().equals(inputTypeGA)) {
                GAFromTypeList.add(ga);
            }
        }
        return GAFromTypeList;
    }
}
