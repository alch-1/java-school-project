package main.java.pages.cityfarmer;

import java.sql.*;
// import lib.*;
import main.java.object.cityfarmer.*;
import main.java.dao.*;
import main.java.object.member.*;
import main.java.pages.mainmenu.*;



import java.util.*;
import java.util.Date;
import java.io.*;
public class myInventory{
    private static final CropDAO c = new CropDAO();
    private static final Scanner sc = new Scanner(System.in); 
    public static void executeMyInventory(Farmer farmer){
        System.out.println("== Social Magnet :: City Farmers :: My Inventory ==");
        System.out.println("Welcome, "+farmer.getUserName()+"!");
        System.out.println("Title: "+farmer.getTitle()+"\tGold: "+farmer.getGold()+"gold");
        System.out.println();
        System.out.println("My Seeds");
        System.out.println();
        int counter = seedsInInventory(farmer);
        System.out.println("[M]ain | City [F]armers | Select choice >");
        String in = sc.next();
        if (in.equals("M")) {
            MainMenu.executeMainMenu((User) farmer);
        }
        else if (in.equals("F")){
            cityfarmerMenu.executeCityfarmerMenu(farmer);
            System.out.println();
        }
        else{
            System.out.println("Please enter valid input");
        }
        
    }
    public static int seedsInInventory(Farmer farmer){
        ArrayList<Crop> inventory = c.retrieveAllCrops(farmer.getUserName());
        int counter = 1;
        Iterator iter = inventory.iterator();
        while (iter.hasNext()) { 
            Crop seed = (Crop) iter.next();
            
            System.out.println(counter + ". " + seed.getName());
            counter++;
        }
        return counter++;
    }
    
}



