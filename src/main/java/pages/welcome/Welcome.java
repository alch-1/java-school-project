package main.java.pages.welcome;
import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;

public class Welcome {
    private static final String username = "anonymous";
    private static final Scanner sc = new Scanner(System.in); // we only need one scanner for System.in most of the time

    // @SuppressWarnings("resource") // suppress sc close warning
    public static int printWelcome() {
        int userInput;
        System.out.println("== Social Magnet :: Welcome ==");
        System.out.println("Good morning, " + username);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        // handle input
        System.out.print("Enter your choice! > ");
        try {
            userInput = sc.nextInt();
            System.out.println();
        } catch (InputMismatchException e) {
            userInput = 0; 
        }
        sc.nextLine();
        return userInput;
    }
    public static void executeWelcome() {
        // ennsure user inputs only 1 to 3
        int userChoice = 0;
        do {
            userChoice = printWelcome();
            if (userChoice < 1 || userChoice > 3) {
                System.out.println("Please enter a choice between 1 & 3!");
                System.out.println(); // newline to make it look nicer
            }
        } while (userChoice < 1 || userChoice > 3);


        if (userChoice == 1) {
            // register code
            System.out.println();
            Registration.executeRegister();
        } else if (userChoice == 2) {
            // login 
            Login.executeLogin();
        } else if(userChoice == 3){
            System.exit(0);
        }
        else {
            // exit 
            return; // return control to the parent method
        }
    }
}