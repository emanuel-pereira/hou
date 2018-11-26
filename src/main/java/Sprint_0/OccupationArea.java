package Sprint_0;

public class OccupationArea {

    private double _lenght;
    private double _width;
    private double _occupation;

    public void setOccupationArea(double lenght, double width) {
        this._lenght = lenght;
        this._width = width;
    }

    public double getOccupationArea() {

        return this._occupation;
    }

    public double calculOccupationArea() {
        if (positiveLenghtWidth() == true)
            this._occupation = this._lenght * this._width;
        return this._occupation;
    }

    public boolean positiveLenghtWidth() {
        if (this._lenght < 0 | this._width < 0) return false;
        else return true;
    }
}
