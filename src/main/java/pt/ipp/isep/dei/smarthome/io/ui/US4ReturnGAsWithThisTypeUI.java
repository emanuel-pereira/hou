package pt.ipp.isep.dei.smarthome.io.ui;



/*
public class US4ReturnGAsWithThisTypeUI {

    private GAList mGAList;
    private TypeGA mTypeGA
    private US4ReturnGAsWithThisTypeCTRL mCtrlUS4;

    public US4ReturnGAsWithThisTypeUI (TypeGA inputType) {
        mTypeGA = inputType;
        mCtrlUS4 = new US4ReturnGAsWithThisTypeCTRL (mTypeGA);
    }

   //sets an object 'keyboard' to invoke user inputs methods in order to read the keyboard inputs
    Scanner keyboard = new Scanner (System.in);

   public void run () {
       while (true) {
           System.out.println("Insert the Type of Geographical Area you wish to see (or click r to return to Main Menu):");
           String typeName = typeInputIsValid();
           if ("r".equals (typeName)) {
               System.out.println("Return to Main Menu");
               break;
           }
           if(mCtrlUS4.//metodo do CTRL (typeName))
               System.out.println("Here are the Geographical Areas from" + typeName ":" + GAFromTypeList);
           if(mCtrlUS4.//metodo validacao que ve se esta vazio/não ha matches)
               System.out.println("No results were found. Please try again.");
           if (typeName != typeInputIsValid())
               System.out.println("The Geogrphical Area Type you inserted is not valid. Please try again.");
       }
   }


    public String typeInputIsValid() {
        String inputTypeName = keyboard.nextLine ();
        if (inputTypeName == null || inputTypeName.trim ().isEmpty ()) {//verification of empty and null parameters
            return null;
        }
        //match returns true if the input string "inputTypeName" matches the given regular expression
        if (!inputTypeName.matches("[a-zA-Z]*"))
            return null;
        return inputTypeName.toLowerCase();//set input name to lower case
    }

}*/
