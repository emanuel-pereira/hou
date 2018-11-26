package Sprint_0;

public class OccupationArea {

    private double _lenght;
    private double _width;
    private double _occupation;

    public OccupationArea() {
    }

    public OccupationArea(double lenght, double width) {
        this._lenght = lenght;
        this._width = width;
    }

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

    public void convertMetersToKm(double lenghtM, double widthM) {
        this._width = widthM * 1000;
        this._lenght = lenghtM * 1000;
    }
}


    //metodo para devolver occupation area com string da GA.
