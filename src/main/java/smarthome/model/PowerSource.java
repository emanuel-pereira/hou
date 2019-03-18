package smarthome.model;

public class PowerSource {

    private String name;
    private String typePS;
    private double maxPower; //watts per hour
    private double storageCapacity;//in kWh


    public PowerSource (String inputName,String inputType, double inputMaxPower, double inputStorageCapacity){
        this.name = inputName;
        this.typePS = inputType;
        this.maxPower = inputMaxPower;
        this.storageCapacity = inputStorageCapacity;
    }

    public String getName(){
        return this.name;
    }

    public String getTypePS(){
        return this.typePS;
    }

    public double getMaxPower(){
        return this.maxPower;
    }

    public double getStorageCapacity(){
        return this.storageCapacity;
    }

    public boolean nameIsValid(String name) {
        if (name == null || name.trim ().isEmpty ()) {
            return false;
        }
        return name.matches ("[A-Za-z0-9]*");
    }


}
