package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class ProgramList {
    private List<Program> mProgramList;

    /**
     * Constructor initializing an empty program list that can be associated to programmable devices.
     */
    public ProgramList(){
       mProgramList=new ArrayList<>();
    }

    /**
     * Method to add a new program to the ProgramList of a specific device
     * @param newProgram of the device
     * @return true if program is added to the ProgramList. False otherwise.
     */
    public boolean addProgram(Program newProgram){
        if(!mProgramList.contains(newProgram)){
            mProgramList.add(newProgram);
            return true;
        } else return false;
    }

    /**
     * Method that creates a local instance of a program
     * @param duration time duration parameter of the program
     * @param energyConsumption consumption of the program
     * @return an instance of a Program
     */
    public Program newProgram(int duration, double energyConsumption){
        return new Program(duration,energyConsumption);
    }

    /**
     * @return the program list
     */
    public List<Program> getProgramList() {
        return mProgramList;
    }
}
