package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class HouseList {

    private List<House> mHouseList;

    public HouseList() {
        mHouseList = new ArrayList<>();
    }


    public House newHouse(Address houseAddress,GeographicalArea GA) {
        return new House(houseAddress, GA);
    }

    public boolean addHouse(House inputHouse) {
        if (!mHouseList.contains(inputHouse)) {
            mHouseList.add(inputHouse);
            return true;
        } else return false;
    }

    public List<House> getHouseList (){
        return mHouseList;
    }

}
