package main.java.pages.cityfarmer;

import main.java.dao.*;
import main.java.object.cityfarmer.*;
import main.java.pages.mainmenu.MainMenu;

import java.sql.*;

import java.util.*;
import java.io.*;
public class myFarmLand{
    private static final FarmerDAO f = new FarmerDAO();
    private static final Scanner sc = new Scanner(System.in);
    public static void executeMyFarmLand(Farmer farmer){
        String username = farmer.getUserName();
        System.out.println("== Social Magnet :: City Farmers :: My Farmland ==");
        System.out.println("Welcome "+username+" !");
        System.out.println("Title: "+farmer.getTitle()+"\t Gold: "+farmer.getGold()+" gold");
        System.out.println("");

        ArrayList<Land> lands = LandDAO.retrieveLandsByUsername(username);
        int numberOfLands = lands.size();
        System.out.println("You have " + numberOfLands + " lands");
        
        int counter = 1;
        for (Land land : lands) {
            String progress = land.getProgress();
            System.out.println(counter + ". " + progress);
        }
        System.out.println("[M]ain | City [F]amers | [P]lant | [C]lear | [H]arvest > ");
        String input = sc.next();
        System.out.println("You have plots of land.");
        int counter = 1;
        TreeMap<Integer, Land> tmap = new TreeMap<>();
        for (Land land : lands) {
            int landNumber = land.getLandNumber();
            System.out.println(landNumber + ". " + land.getProgress());
        }

        if (input.equals("M")) {
            MainMenu.executeMainMenu((User) farmer)
        } else if (input.equals("F")) {
            ;
        } else if (input.equals("C")) {
            ;
        } else if (input.equals("P")) {
            ;
        } else if (input.equals("H")) {
            int counter = 0;
            TreeMap<Integer, Land> tmap = new TreeMap<>();
            for (Land land : lands) {
                if (land.canHarvest()) { // if land can be harvested
                    int min = land.getMinYield();
                    int max = land.getMaxYield();
                    int harvest = Helper.getRandomNumberInRange(min, max);
                    int exp = land.getExp();
                    int salePrice = getSalePrice();
                    int totalExp = exp * harvest;
                    int totalSalePrice = salePrice * harvest;

                    boolean increaseSuccess = FarmerDAO.increaseXP(totalExp, username);
                    boolean priceSuccess = FarmerDAO.increaseGold((double) totalSalePrice);
                    if (increaseSuccess && priceSuccess) {
                        System.out.println("You have harvested " + harvest + " " + land.getCropName() + " for " + totalExp + " XP and " + totalSalePrice + " gold.");
                    }

                }
            }
            // System.out.println("Select the crop:");
            // for (Integer key : tmap.keySet()) {
            //     Land land = tmap.get(key);
            //     System.out.println(key + ". " + land.getCrop());
            // }

            // int selection = sc.nextInt();

            // Land selectedLand = tmap.get(selection);


        }
        
    }
}


