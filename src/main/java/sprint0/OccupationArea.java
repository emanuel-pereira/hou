package sprint0;

public class OccupationArea {

    private double mLenght;
    private double mWidth;
    private double mOccupation;

    // Constructors
    public OccupationArea() {
    }

    public OccupationArea(double inputLenght, double inputWidth) {
        this.mLenght = inputLenght;
        this.mWidth = inputWidth;
    }

    // Methods
    public void setOccupationArea(double inputLenght, double inputWidth) {

        if (positiveLenghtAndWidth(inputLenght, inputWidth)) {
            this.mLenght = inputLenght;
            this.mWidth = inputWidth;
        }
    }

    public double getOccupationArea() {
        calculOccupationArea();
        return this.mOccupation;
    }

    public double calculOccupationArea() {
        this.mOccupation = this.mLenght * this.mWidth;
        return this.mOccupation;
    }

    public boolean positiveLenghtAndWidth(double inputLenght, double inputWidth) {
        if (inputLenght < 0 || inputWidth < 0) return false;
        else return true;
    }

    public void convertMetersToKm(double inputLenghtMeters, double inputWidthMeters) {
        this.mWidth = inputWidthMeters * 0.001;
        this.mLenght = inputLenghtMeters * 0.001;
    }


}
//metodo para devolver occupation area com string da GA.
