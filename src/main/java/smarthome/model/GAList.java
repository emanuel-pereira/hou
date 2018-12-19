package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> mGAList;

    //Construtor

    public GAList() {

        mGAList = new ArrayList<>();
    }

    //Métodos

    public GeographicalArea newGA(String inputDesignation, String typeArea, double width, double height, double latitude, double longitude, double altitude) {
        return new GeographicalArea(inputDesignation, typeArea, width, height, latitude, longitude, altitude);
    }

    public boolean addGA(GeographicalArea inputGA) {
        if (mGAList.contains(inputGA))
            return false;
        return mGAList.add(inputGA);
    }

    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

    /**
     * Method to get Geographical Area in index position i
     *
     * @param i index position of Geographical Area
     * @return Geographical Area in index position i
     */

    public GeographicalArea get(int i) {
        return this.mGAList.get(i);
    }

    /**
     * @return
     */
    /*public int size() {
        return this.mGAList.size();
    }*/

    /**
     * Method that checks if a specific location is within one or more Geographical Areas and returns the
     * list of Geographical Areas that contain that location. If no match is found, then this method returns null.
     *
     * @param latitude  latitude of the location
     * @param longitude longitude of the location
     * @return a list of Geographical Areas that contain a specific location
     */
    public List<GeographicalArea>  listOfGAsContainingLocation(double latitude, double longitude) {
        List<GeographicalArea>  listOfGAsContainingLocation = new ArrayList<>();
        for (int i = 0; i < mGAList.size(); i++) {
            if (mGAList.get(i).locationIsInAG(latitude, longitude)) {
                listOfGAsContainingLocation.add(mGAList.get(i));
            }
        }
        return listOfGAsContainingLocation;
    }


    /**
     * Method to add a sensor to smallest Geographical Area in the list of Geographical Areas that contains a specific sensor's location.
     * @param sensor sensor to be added to Geographical Area with the smallest occupation area
     * @return true is sensor is added to the smallest Geographical Area that contains the sensor
     */
    public GeographicalArea getSmallestGAContainingSensor(Sensor sensor) {
        List<GeographicalArea>  l = listOfGAsContainingLocation(sensor.getLocation().getLatitude(),sensor.getLocation().getLongitude());
        if(l.size()==0){return null;}
        GeographicalArea smallerGA = l.get(0);
        double smallerArea = l.get(0).getOcupation().getOccupationArea();
        for (int i = 1; i < l.size(); i++){
            if (smallerArea>l.get(i).getOcupation().getOccupationArea()) {
                smallerArea=l.get(i).getOcupation().getOccupationArea();
                smallerGA=l.get(i);
            }}

        return smallerGA;
    }

    public boolean addSensorToSmallestGA (Sensor sensor){
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
