package sprintzero;

public class OccupationArea {

    private double mLength;
    private double mWidth;
    private double mOccupation;

    // Constructors
    public OccupationArea() {
    }

    public OccupationArea(double inputLenght, double inputWidth) {
        this.mLength = inputLenght;
        this.mWidth = inputWidth;
    }

    // Methods
    public void setOccupationArea(double inputLength, double inputWidth) {

        if (positiveLengthAndWidth(inputLength, inputWidth)) {
            this.mLength = inputLength;
            this.mWidth = inputWidth;
        }
    }

    public double getOccupationArea() {
        this.mOccupation = calculOccupationArea();
        return this.mOccupation;
    }

    public double calculOccupationArea() {
        return this.mLength * this.mWidth;
    }

    /**
     *
     * @param inputLength - length of the occupation area
     * @param inputWidth - width of the occupation area
     * @return true if both expressions are positive or false if one of them is equal or less than zero
     */
    public boolean positiveLengthAndWidth(double inputLength, double inputWidth) {
        return (inputLength > 0 && inputWidth > 0);
    }

    public void convertMetersToKm(double inputLenghtMeters, double inputWidthMeters) {
        this.mWidth = inputWidthMeters * 0.001;
        this.mLength = inputLenghtMeters * 0.001;
    }
}