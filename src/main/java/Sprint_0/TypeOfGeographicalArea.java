package Sprint_0;


public class TypeOfGeographicalArea {



    private String mTypeArea0;


    public TypeOfGeographicalArea (String typeArea) {

        this.mTypeArea0 = typeArea;

    }

        public String getTypeArea() {

        return this.mTypeArea0;
    }


    public void setTypeArea (String typeArea) {

        this.mTypeArea0 = typeArea;

    }


    typeArea STREET = typeArea.STREET;

    typeArea village = typeArea.VILLAGE;

    typeArea city = typeArea.CITY;

    typeArea district = typeArea.DISTRICT;

    typeArea country = typeArea.COUNTRY;



   enum typeArea {

        STREET, VILLAGE, CITY, DISTRICT, COUNTRY
   }

}

/*
b. Funcionalidades:
        - determinar distância linear entre duas áreas geográficas (medido a partir do ponto central);
        - determinar distância linear entre dois dispositivos/sensores;
        - determinar os últimos valores da temperatura/pluviosidade/humidade/vento/visibilidade lidas pelos sensores disponíveis na área geográfica;
        - determinar temperatura/pluviosidade/humidade/vento/visibilidade médias mínima e máxima mensais num dispositivo/sensor;


        */