package sprintzero;

public class OccupationArea {

    private double mLength = Double.NaN;
    private double mWidth = Double.NaN;
    private double mOccupation = Double.NaN;


    /**
     * Constructor method that determines a length and width for an occupational area and it verifies if
     * both attributes have positive values
     * @param inputLength - length of the occupation area
     * @param inputWidth  - width of the occupation area
     * @return true if both expressions are positive or false if one of them is equal or less than zero
     */
    public OccupationArea(double inputLength, double inputWidth) {
        if (inputLength > 0 && inputWidth > 0) {
            this.mLength = inputLength;
            this.mWidth = inputWidth;
            this.mOccupation = this.mLength * this.mWidth;
        }
    }

    /**
     * Method that returns the area occupied by a geographical area
     * @return - value of occupation area
     */
    public double getOccupationArea() {
        return this.mOccupation;
    }


    /*public void convertMetersToKm(double inputLengthMeters, double inputWidthMeters) {
        this.mWidth = inputWidthMeters * 0.001;
        this.mLength = inputLengthMeters * 0.001;
    }*/
}