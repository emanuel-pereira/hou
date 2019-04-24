package smarthome.model.readers;

public final class UtilsParser {


    /**
     * Private constructor of UtilsParser class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private UtilsParser(){

    }

    private static boolean isDouble(String input) {
        double d;
        try {
            d = Double.parseDouble(input);
        } catch (Exception e) {
            d = - Double.MAX_VALUE;
        }

        return (d > - Double.MAX_VALUE);
    }

    private static boolean isLong(String input) {
        long l;
        try {
            l = Long.parseLong(input);
        } catch (Exception e) {
            l = - Long.MAX_VALUE;
        }

        return (l > - Long.MAX_VALUE);
    }

    public static double ifLongTurnDouble(String value){

        if(isDouble(value)){
            return Double.parseDouble(value);
        }

        if(isLong(value)){
            long l = Long.parseLong(value);
            Long number = new Long(l);
            double d = number.doubleValue();
            return d;
        }

        else{
            return Double.NaN;
        }

    }


}
