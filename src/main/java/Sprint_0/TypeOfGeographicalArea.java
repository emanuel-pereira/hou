package Sprint_0;


public class TypeOfGeographicalArea {



    private String _typeArea;


    public TypeOfGeographicalArea (String typeArea) {

        this._typeArea = typeArea;

    }

        public String getTypeArea() {

        return this._typeArea;
    }


    public void setTypeArea (String typeArea) {

        this._typeArea = typeArea;

    }


    typeArea street = typeArea.street;

    typeArea village = typeArea.village;

    typeArea city = typeArea.city;

    typeArea district = typeArea.district;



   enum typeArea {

        street, village, city, district
   }

}

