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

//RoomList

    public RoomList getRoomListFromHouse() {
        return mRoomList;
    }

    public HouseGridList getHGListInHouse() {
        return mHGListInHouse;
    }

    /**
     * Sets volume of water for all devices of type Electric Water Heater that may be installed in any room of the house.
     *
     * @param volumeOfWater double value parameter to set the volume of water of every Electric Water Heater installed in all rooms of the house.
     */
    public void setVolumeOfWaterToHeatInEWHList(double volumeOfWater) {
        for (Room room : mRoomList.getRoomList())
            room.getDeviceList().setVolumeOfWaterToHeat(volumeOfWater);
    }

    /**
     * Sets cold water temperature for all devices of type Electric Water Heater that may be installed in any room of the house.
     *
     * @param coldWaterTemperature double value parameter to set the cold water temperature of every Electric Water Heater installed in all rooms of the house.
     */
    public void setColdWaterTemperatureInGlobalEWHList(double coldWaterTemperature) {
        for (Room room : mRoomList.getRoomList())
            room.getDeviceList().setColdWaterTemperatureEWHList(coldWaterTemperature);
    }

    /**
     * @return the total energy consumed by all electric water heaters installed in the house.
     */
    public double getEnergyConsumptionOfEWHGlobalList() {
        double totalEnergyConsumption = 0;
        for (Room room : mRoomList.getRoomList())
            totalEnergyConsumption += room.getDeviceList().getEnergyConsumptionOfEWHList();
        return totalEnergyConsumption;
    }

    /**
     * Method to ensure that cold water value inputted by the user must be lower than the lowest hot
     * temperature of a Electric Water Heater
     * @param coldWaterTemperature double inputted as cold water temperature
     * @return true if cold water temperature inputted by the user is lower than any hot temperature value
     * of all devices of type Electric Water Heater
     */
    public boolean isLowerThanHotWater(double coldWaterTemperature) {
        for (Room room : mRoomList.getRoomList()) {
            if (room.getDeviceList().isLowerThanHotWater(coldWaterTemperature)==false)
                return false;
        }
        return true;
    }

    /**
     * @return Displays all devices of type Electric Water Heater in string and respective attributes
     */
    public String showElectricWaterHeaterList() {
        StringBuilder result = new StringBuilder();
        String element = "ELECTRIC WATER HEATER \n";
        for (Room room : mRoomList.getRoomList()) {
            if (room.getDeviceList().getElectricWaterHeaterList().size() != 0) {
                result.append(element);
                result.append("Room: ");
                result.append(room.getName());
                result.append("\n");
                result.append(room.getDeviceList().showElectricWaterHeaterList());
            }
        }
        return result.toString();
    }


}



