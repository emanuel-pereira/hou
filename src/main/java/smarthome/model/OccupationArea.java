package smarthome.model;

public class OccupationArea {

    private double mOccupation = Double.NaN;
    private double mHeight;
    private double mWidth;


    /**
     * Constructor method that determines a length and width for an occupational area and it verifies if
     * both attributes have positive values
     *
     * @param inputHeight - height of the occupation area
     * @param inputWidth  - width of the occupation area
     */
    public OccupationArea(double inputHeight, double inputWidth) {
        this.mHeight = inputHeight;
        this.mWidth = inputWidth;
        if (inputWidth > 0 && inputHeight > 0) {
            this.mOccupation = inputHeight * inputWidth;
        }
    }

    //to add inputs verification
    //true if both expressions are positive or false if one of them is equal or less than zero

    /**
     * Method to get height of an occupation area
     *
     * @return GA Height
     */
    public double getmHeight() {
        return mHeight;
    }

    /**
     * Method to get width of an occupation area
     *
     * @return GA Width
     */
    public double getmWidth() {
        return mWidth;
    }

    /**
     * Method that returns the area occupied by a geographical area
     * @return - value of occupation area
     */
    public double getOccupationArea() {
        return this.mOccupation;
    }
}