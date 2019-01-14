package smarthome.model;

import java.util.ArrayList;
import java.util.List;
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

    public GeographicalArea getHouseGA (){
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


    //TODO Corrigir método através do teste na HouseTest; incluir verificação com método sensorHasReadings da classe Sensor
    //verificar logo aqui se tem readings para o intervalo pretendido?
    ///change method to findClosestGASensorByTypeWithReadings

    public Sensor findClosestGASensorByType(String type) {
        SensorList listSensorsOfType = mGA.getGASensorsByType(type);
        Sensor nearestSensor = mGA.getGASensorsByType(type).getSensorList().get(0);

        double minDistance = getHouseLocation().calcLinearDistanceBetweenTwoPoints(listSensorsOfType.getSensorList().get(0).getLocation(), mAddress.getGPSLocation());

        for (Sensor sensor : listSensorsOfType.getSensorList()) {
            if (minDistance > getHouseLocation().calcLinearDistanceBetweenTwoPoints(mAddress.getGPSLocation(),sensor.getLocation())&& !sensor.getReadingList().getReadingList().isEmpty()) {
                minDistance = getHouseLocation().calcLinearDistanceBetweenTwoPoints( mAddress.getGPSLocation(),sensor.getLocation());
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

//RoomList

    public RoomList getRoomListFromHouse() {
        return mRoomList;
    }

    public HouseGridList getHGListInHouse () {
        return mHGListInHouse;
    }

}



