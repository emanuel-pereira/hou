package Sprint_0;

public class OccupationArea {

    private double _lenght;
    private double _width;
    private double _occupation;

    public OccupationArea() {
    }
    public OccupationArea(double inputLenght, double inputWidth) {
        this._lenght = inputLenght;
        this._width = inputWidth;
    }

    public void setOccupationArea(double inputLenght, double inputWidth) {
        this._lenght = inputLenght;
        this._width = inputWidth;
    }

    public double getOccupationArea() {
        calculOccupationArea();
        return this._occupation;
    }

    public double calculOccupationArea() {
        if (positiveLenghtAndWidth() == true)
            this._occupation = this._lenght * this._width;
            return this._occupation;
    }

    public boolean positiveLenghtAndWidth() {
        if (this._lenght < 0 | this._width < 0) return false;
        else return true;
    }

    public void convertMetersToKm(double inputLenghtMeters, double inputWidthMeters) {
        this._width = inputWidthMeters * 0.001;
        this._lenght = inputLenghtMeters * 0.001;
    }
}
//metodo para devolver occupation area com string da GA.
