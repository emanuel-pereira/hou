package smarthome.io.ui;

import java.util.Scanner;

public final class  UtilsUI {

    /**
     * Private constructor of UtilsUI class, which is a collection of static members, hence is not meant to be instantiated.
     */
    private UtilsUI(){}

    public static void printLnInsertValidOption() {
        System.out.println("Please insert a valid option.");
    }

    public static void printLnInsertValidParameter(final String parameter) {
        System.out.println("Please insert a valid " + parameter + ".");
    }

    public static void backToMenu() {
        Scanner read = new Scanner(System.in);
        System.out.println("---\nPress Enter to return to the previous Menu");
        read.nextLine();
        return;
    }
}
