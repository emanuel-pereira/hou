package smarthome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OccupationArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double occupation = Double.NaN;
    private double length;
    private double width;

    protected OccupationArea() {
    }

    /**
     * Constructor method that determines a length and width for an occupational area and it verifies if
     * both attributes have positive values
     *
     * @param inputLength - length of the occupation area
     * @param inputWidth  - width of the occupation area
     */
    public OccupationArea(double inputLength, double inputWidth) {
        this.length = inputLength;
        this.width = inputWidth;
        if (inputWidth > 0 && inputLength > 0) {
            this.occupation = inputLength * inputWidth;
        }
    }

    //to add inputs verification
    //true if both expressions are positive or false if one of them is equal or less than zero

    /**
     * Method to get width of an occupation area
     *
     * @return GA Width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Method to get length of an occupation area
     *
     * @return GA Length
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Method that returns the area occupied by a geographical area
     * @return - value of occupation area
     */
    public double getOccupationArea() {
        return this.occupation;
    }

    public void setOccupation(double occupation) {
        this.occupation = occupation;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}