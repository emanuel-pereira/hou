package smarthome.model;

import java.util.Objects;

public class House {

    private Address mAddress;
    private GeographicalArea mGA;


    public House(Address houseAddress, GeographicalArea GA) {

        mAddress = houseAddress;
        mGA = GA;

    }

    public Address getAddress() {

        return mAddress;
    }


    public GeographicalArea getGA() {
        return mGA;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(mAddress, house.mAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAddress);
    }
}
