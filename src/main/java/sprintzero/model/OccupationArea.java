package sprintzero.model;

public class OccupationArea {


    private double mHeight;
    private double mWidth;


    /**
     * Constructor method that determines a length and width for an occupational area and it verifies if
     * both attributes have positive values
     * @param inputHeight - height of the occupation area
     * @param inputWidth  - width of the occupation area
     * @return true if both expressions are positive or false if one of them is equal or less than zero
     */
    public OccupationArea(double inputHeight, double inputWidth) {
        this.mHeight=inputHeight;
        this.mWidth= inputWidth;
    }

    /**
     * Method to get height of an occupation area
     * @return
     */
    public double getmHeight() {
        return mHeight;
    }

    /**
     * Method to get width of an occupation area
     * @return
     */
    public double getmWidth() {
        return mWidth;
    }
}