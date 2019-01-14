package smarthome.model;

public class Dishwasher implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mCapatity;
    private double mEnergyConsumption;
    private ProgramList mProgramListInDW;


    public Dishwasher(DeviceType deviceType, int capacity){
        mDeviceType=deviceType;
        mCapatity=capacity;
        mProgramListInDW= new ProgramList();
    }

    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
    }


    /*
    Dishwasher quer adicionar um novo programa (y/n)?
    Y- add new program to ProgramList (duração, energyConsumption KWh)*/

}
