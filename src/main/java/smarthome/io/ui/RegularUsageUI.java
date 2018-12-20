package smarthome.io.ui;

import java.util.Scanner;

public class RegularUsageUI {

    public static void regularUsage() {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Regular Users UI");

        while (option != 0) {
            System.out.println("Click 1. US600: As System Administrator I want to .........");
            System.out.println("Click 2. US605: As System Administrator I want to .........");
            System.out.println("Click 3. US610: As System Administrator I want to .........");
            System.out.println("Click 4. US620: As System Administrator I want to .........");
            System.out.println("Click 5. US623: As System Administrator I want to .........");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    System.out.println("US600");
                    break;
                case 2:
                    System.out.println("US605");
                    break;
                case 3:
                    System.out.println("US610");
                    break;
                case 4:
                    System.out.println("US620");
                    break;
                case 5:
                    System.out.println("US623");
                    break;
            }

        }

    }
}
