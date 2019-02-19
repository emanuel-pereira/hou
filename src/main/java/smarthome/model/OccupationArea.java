package smarthome.model;

public class OccupationArea {

    private double mOccupation = Double.NaN;
    private double mLength;
    private double mWidth;


    /**
     * Constructor method that determines a length and width for an occupational area and it verifies if
     * both attributes have positive values
     *
     * @param inputLength - length of the occupation area
     * @param inputWidth  - width of the occupation area
     */
    public OccupationArea(double inputLength, double inputWidth) {
        this.mLength = inputLength;
        this.mWidth = inputWidth;
        if (inputWidth > 0 && inputLength > 0) {
            this.mOccupation = inputLength * inputWidth;
        }
    }

    //to add inputs verification
    //true if both expressions are positive or false if one of them is equal or less than zero

    /**
     * Method to get width of an occupation area
     *
     * @return GA Width
     */
    public double getmWidth() {
        return mWidth;
    }

    /**
     * Method to get length of an occupation area
     *
     * @return GA Length
     */
    public double getmLength() {
        return mLength;
    }

    /**
     * Method that returns the area occupied by a geographical area
     * @return - value of occupation area
     */
    public double getOccupationArea() {
        return mOccupation;
    }
}