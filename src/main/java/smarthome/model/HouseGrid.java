package smarthome.model;

import smarthome.model.validations.Utils;

import java.util.Calendar;
import java.util.Objects;

public class HouseGrid implements Metered{
    private double contractedMaximumPower = Double.NaN;
    private String designation;
    private PowerSourceList psListInHG;
    private RoomList roomList;


    public HouseGrid(String designation) {
        this.designation = designation;
        this.psListInHG = new PowerSourceList ();
        this.roomList = new RoomList ();
    }


    public double getContractedMaximumPower() {
        return this.contractedMaximumPower;
    }

    public void setContractedMaximumPower(double contractedMaximumPower) {
        if (Utils.valueIsPositive (contractedMaximumPower))
            this.contractedMaximumPower = contractedMaximumPower;
    }

    public String getName() {
        return this.designation;
    }

    public PowerSourceList getPSListInHG() {
        return this.psListInHG;
    }

    /**
     * Attach a room to a house grid if the room doesn't exists in the grid room list
     *
     * @param inputRoom Room that will be attached to a grid
     * @return true if the room is added
     */
    public boolean attachRoomToGrid(Room inputRoom) {
        if (this.roomList.getRoomList ().contains (inputRoom)) {
            return false;
        } else this.roomList.addRoom (inputRoom);
        return true;
    }

    public boolean detachRoomFromGrid(Room inputRoom) {
        if (this.roomList.getRoomList ().contains (inputRoom)) {
            this.roomList.getRoomList ().remove (inputRoom);
            return true;
        } else return false;
    }


    /**
     *
     * @return list of rooms in a grid
     */
    public RoomList getRoomListInAGrid() {
       return this.roomList;
    }

    public int getRoomListInAGridSize() {
        return this.roomList.getRoomListSize ();
    }

    /**
     * Nominal power of a grid is the sum of nominal power of all the rooms in tat grid
     *
     * @return nominal power of a grid
     */
    public double getNominalPower() {
        double sum = 0;
        for (Room room : getRoomListInAGrid ().getRoomList ()) {
            sum += room.getNominalPower ();
        }
        return sum;
    }


    public String showRoomsInHouseGrid() {
        RoomList listOfRoomsInHG = getRoomListInAGrid();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (Room r : listOfRoomsInHG.getRoomList ()) {
            result.append (number++);
            result.append (element);
            result.append (r.getName ());
            result.append ("\n");
        }
        return result.toString ();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HouseGrid)){
            return false;
        }
        HouseGrid houseGrid = (HouseGrid) o;
        return Objects.equals (this.designation, houseGrid.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash (this.designation);
    }

    @Override
    public double getEnergyConsumption(Calendar startHour, Calendar endHour) {
        double total=0;
        for(Room room : this.roomList.getRoomList()){
            total+=room.getEnergyConsumption(startHour,endHour);
        }
        return Utils.round(total,2);
    }
}
