package smarthome.model;

import java.util.Objects;

public class House {

    private Address mAddress;
    private String mID;
    private GeographicalArea mGA;
    private RoomList mRoomList;
    private HouseGridList mHGListInHouse;


    public House() {
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(Address houseAddress, GeographicalArea GA) {
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }

    public House(String id, Address houseAddress, GeographicalArea GA) {
        mID = id;
        mAddress = houseAddress;
        mGA = GA;
        mRoomList = new RoomList();
        mHGListInHouse = new HouseGridList();
    }


    public void setHouseGA(GeographicalArea houseGA) {
        mGA = houseGA;
    }

    public GeographicalArea getHouseGA() {
        return mGA;
    }


    public void setHouseAddress(String streetName, String houseNumber, String zipCode, double latitude, double longitude, double altitude) {
        mAddress = new Address(streetName, houseNumber, zipCode, latitude, longitude, altitude);
    }

    public Address getAddress() {

        return mAddress;
    }


    public Location getHouseLocation() {
        return mAddress.getGPSLocation();

    }


    public Sensor findClosestGASensorByType(String type) {
        SensorList listSensorsOfType = mGA.getGASensorsByType(type);
        Sensor nearestSensor = mGA.getGASensorsByType(type).getSensorList().get(0);

        double minDistance = getHouseLocation().calcLinearDistanceBetweenTwoPoints(listSensorsOfType.getSensorList().get(0).getLocation(), mAddress.getGPSLocation());

        for (Sensor sensor : listSensorsOfType.getSensorList()) {
            if (minDistance > getHouseLocation().calcLinearDistanceBetweenTwoPoints(mAddress.getGPSLocation(), sensor.getLocation())) {
                minDistance = getHouseLocation().calcLinearDistanceBetweenTwoPoints(mAddress.getGPSLocation(), sensor.getLocation());
                nearestSensor = sensor;
            }

        }

        return nearestSensor;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(mAddress, house.mAddress) &&
                Objects.equals(mID, house.mID) &&
                Objects.equals(mGA, house.mGA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress, mID, mGA);
    }

    public RoomList getRoomList() {
        return mRoomList;
    }

    public HouseGridList getHGListInHouse() {
        return mHGListInHouse;
    }

    /**
     *
     * @param houseGrid
     * @return
     */
    public RoomList getRoomsWithoutGrid(HouseGrid houseGrid){
        RoomList roomListWithoutHouseGrid= new RoomList();
        RoomList roomListInHouseGrid=houseGrid.getRoomListInAGrid();
        for(Room room:mRoomList.getRoomList()){
            if(!(roomListInHouseGrid.getRoomList().contains(room)))
                roomListWithoutHouseGrid.addRoom(room);
        }
        return roomListWithoutHouseGrid;
    }


    public String showRoomsWithoutHouseGrid(HouseGrid houseGrid) {
        RoomList listOfRoomsWithHouseGrid = getRoomsWithoutGrid (houseGrid);
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsWithHouseGrid.getRoomList ()) {
            result.append (number++);
            result.append (element);
            result.append (r.getName ());
            result.append ("\n");
        }
        return result.toString ();
    }







}



