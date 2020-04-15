package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.object.cityfarmer.*;
import main.java.dao.*;
import main.java.pages.cityfarmer.*;

public class MainMenu{

    private static final Scanner sc = new Scanner(System.in); 
    // private static String username; 
    
    public static void executeMainMenu(User user){
        
        System.out.println("== Social Magnet :: Main Menu ==");
        System.out.println("Welcome, " + user.getFullName());
        System.out.println("1. News Feed\n2. My Wall\n3. My Friends\n4. City Farmers\n5. Logout\n");
        int choice = 0;

        do{
        System.out.print("Enter your choice >");
            try{
                choice = sc.nextInt();
                System.out.println();
            }catch (InputMismatchException e){
                System.out.print("Please choose between 1 & 5 > ");
                sc.nextLine();
                choice = sc.nextInt();
            }
        }while(choice < 1 || choice > 5);
        
        if (choice == 1) {
            // register code
            System.out.println();
            NewsFeed.executeNewsFeed(user);
        } else if (choice == 2){
            System.out.println();
            MyWall.executeMyWall(user);
        }
        else if (choice == 3) {
            // login 
            System.out.println();
            MyFriends.executeMyFriends(user);
        } else if (choice == 4){
            String username = user.getUserName();
            Farmer farmer = FarmerDAO.retrieveByName(username);
            cityfarmerMenu.executeCityfarmerMenu(farmer);
        } else if (choice == 5){
            Welcome.executeWelcome();
        }
        else { 
            return; 
        }
    }
}