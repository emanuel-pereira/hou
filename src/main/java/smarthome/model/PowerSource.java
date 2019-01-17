package smarthome.model;

public class PowerSource {

    private String mName;
    private String mTypePS;
    private double mMaxPower; //watts per hour
    private double mStorageCapacity;//in kWh


    public PowerSource (String inputName,String inputType, double inputMaxPower, double inputStorageCapacity){
        mName = inputName;
        mTypePS = inputType;
        mMaxPower = inputMaxPower;
        mStorageCapacity = inputStorageCapacity;
    }

    public String getName(){
        return mName;
    }

    public String getTypePS(){
        return mTypePS;
    }

    public double getMaxPower(){
        return mMaxPower;
    }

    public double getStorageCapacity(){
        return mStorageCapacity;
    }

    public boolean nameIsValid(String name) {
        if (name == null || name.trim ().isEmpty ()) {
            return false;
        }
        return name.matches ("[A-Za-z0-9]*");
    }


}
