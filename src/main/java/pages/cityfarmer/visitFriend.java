package main.java.pages.cityfarmer;

import main.java.dao.*;
import main.java.object.cityfarmer.*;
import main.java.object.member.*;
import main.java.pages.mainmenu.*;

import java.util.*;
import java.io.*;
import java.text.*;

/**
 * visitFriend
 */

public class visitFriend {
    // private String username;
    private static final Scanner sc = new Scanner(System.in);

    private static void visitFriendInterface(Farmer visited, Farmer visitor) {
        String username = visited.getUserName();
        String title = visited.getTitle();
        int gold = (int) visited.getGold();
        String goldprint = NumberFormat.getNumberInstance(Locale.US).format(gold);
        System.out.println("Name: " + username);
        System.out.println("Title: " + title);
        System.out.println("Gold: " + goldprint);

        ArrayList<Land> lands = LandDAO.retrieveLandsByUsername(username);
        int counter = 1;
        for (Land land : lands) {
            String progress = land.getProgress();
            System.out.println(counter + ". " + progress);
        }
        System.out.println("[M]ain | City [F]amers | [S]teal > ");
        String input = sc.next();
        if (input.equals("M")) {
            MainMenu.executeMainMenu((User) visitor);
        } else if (input.equals("F")) {
            cityfarmerMenu.executeCityfarmerMenu(visitor);
        } else if (input.equals("S")) {
            VisitLogic.stealFrom(visitor, visited);
        } else {
            System.out.println("Invalid input!");
        }
    }

    public static void executeVisitFriend(Farmer farmer) {
        // get current user
        // Farmer farmer = FarmerDAO.retrieveByName(username);
        String username = farmer.getUserName();
        // get friends
        ArrayList<User> friends = VisitLogic.getFriends(username);
        // print out interface
        System.out.println("== Social Magnet :: City Farmers :: Visit Friend ==");
        System.out.println("Welcome " + username +" !");
        System.out.println("Title: " + farmer.getTitle() + "\t Gold: " + farmer.getGold() + " gold");
        System.out.println();
        System.out.println("My Friends:");

        TreeMap<Integer, User> tmap = new TreeMap<>();
        for (Integer i = 0; i < friends.size(); i++) {
            User friend = friends.get(i);
            tmap.put(i, friend);
            String friendname = friend.getFullName();
            String friendusername = friend.getUserName();
            System.out.println(i + ". " + friendname + " (" + friendusername + ")");
        }

        System.out.println("[M]ain | [C]ity Farmer Main | Select choice > ");
        String input = sc.nextLine();
        if (input.equals("M")) {
            MainMenu.executeMainMenu((User) farmer);
        } else if (input.equals("C")) {
            cityfarmerMenu.executeCityfarmerMenu(farmer);
        } else {
            int choice = Integer.parseInt(input);
            if (tmap.containsKey(choice)) {
                Farmer farmerfriend = FarmerDAO.retrieveByName(username);
                
                visitFriendInterface(farmerfriend, farmer);
            }
        }
    }
}