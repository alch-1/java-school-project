package main.java.pages.cityfarmer;
// import main.java.Object.CityFarmer.*;
import java.util.*;

import main.java.object.cityfarmer.*;
import main.java.object.member.*;
import main.java.pages.mainmenu.*;
import main.java.dao.*;
import main.java.pages.*;

import java.io.*;
public class myStore{
    private static final Scanner sc = new Scanner(System.in);
    private static final CropDAO cdao = new CropDAO();
    private static final FarmerDAO fdao = new FarmerDAO();
    private static final Crop papaya = new Papaya();
    private static final Crop pumpkin = new Pumpkin();
    private static final Crop sunflower = new Sunflower();
    private static final Crop watermelon = new Watermelon();
    public static void executeMyStore(Farmer farmer){
        System.out.println("== Social Magnet :: City Farmers :: My Store ==");
        System.out.println("Welcome, "+farmer.getUserName()+"!");
        System.out.println("Title: "+farmer.getTitle()+"\tGold: "+farmer.getGold()+"gold");
        System.out.println("Seeds Available:");
        System.out.println("1. Papaya     - 20 gold");
        System.out.println("Harvest in: 30 mins");
        System.out.println("XP Gained: 175");
        System.out.println("2. Pumpkin    - 30 gold ");
        System.out.println("Harvest in: 60 mins");
        System.out.println("XP Gained: 200");
        System.out.println("3. Sunflower  - 40 gold");
        System.out.println("Harvest in: 2 hours");
        System.out.println("XP Gained: 300");
        System.out.println("4. Watermelon  - 50 gold");
        System.out.println("Harvest in: 4 hours");
        System.out.println("XP Gained: 500");
        System.out.print("[M]ain | City [F]armers | Select choice >");
        int quantity =0;
        double cost = 0;
        int seedquantity = 0;
        String in = sc.next();
        if (in.equals("M")) {
            MainMenu.executeMainMenu((User) farmer);
        }
        else if (in.equals("F")){
            cityfarmerMenu.executeCityfarmerMenu(farmer);
            System.out.println();
        }
        else if(isInteger(in)){
            int input = Integer.parseInt(in);
            switch (input) {
                case 1 :
                    System.out.print("Enter quantity > ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    if(farmer.getGold() < quantity*papaya.getCost()){
                        System.out.print("Insufficient gold.");
                    }
                    else{
                        if (quantity > 1){
                            System.out.print(quantity + " bags of seeds purchased for "+ (quantity*papaya.getCost())+" gold.");
                        }
                        else{
                            System.out.print(quantity + " bag of seeds purchased for "+ (quantity*papaya.getCost())+" gold.");
                        }
                        cost = quantity*papaya.getCost();
                        seedquantity = cdao.checkCrop(farmer.getUserName(), "Papaya");
                        if(seedquantity != 0){
                            cdao.plusSeed(farmer.getUserName(), "Papaya");
                        }
                        else{
                            cdao.addCrop(farmer.getUserName(), "Papaya", 1);
                        }
                        fdao.buyCrop(papaya.getCost(), farmer.getUserName());
                    }
                    break;
                case 2 :
                    System.out.print("Enter quantity > ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    if(farmer.getGold() < quantity*pumpkin.getCost()){
                        System.out.print("Insufficient gold.");
                    }
                    else{
                        if (quantity > 1){
                            System.out.print(quantity + " bags of seeds purchased for "+ quantity*pumpkin.getCost()+" gold.");
                        }
                        else{
                            System.out.print(quantity + " bag of seeds purchased for "+ quantity*pumpkin.getCost()+" gold.");
                        }
                        cost = quantity*pumpkin.getCost();
                        seedquantity = cdao.checkCrop(farmer.getUserName(), "Pumpkin");
                        if(seedquantity != 0){
                            cdao.plusSeed(farmer.getUserName(), "Pumpkin");
                        }
                        else{
                            cdao.addCrop(farmer.getUserName(), "Pumpkin", 1);
                        }
                        fdao.buyCrop(pumpkin.getCost(), farmer.getUserName());
                    }
                    break;
                case 3 :
                    System.out.print("Enter quantity > ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    if(farmer.getGold() < quantity*sunflower.getCost()){
                        System.out.print("Insufficient gold.");
                    }
                    else{
                        if (quantity > 1){
                            System.out.print(quantity + " bags of seeds purchased for "+ quantity*sunflower.getCost()+" gold.");
                        }
                        else{
                            System.out.print(quantity + " bag of seeds purchased for "+ quantity*sunflower.getCost()+" gold.");
                        }
                        cost = quantity*sunflower.getCost();
                        seedquantity = cdao.checkCrop(farmer.getUserName(), "Sunflower");
                        if(seedquantity != 0){
                            cdao.plusSeed(farmer.getUserName(), "Sunflower");
                        }
                        else{
                            cdao.addCrop(farmer.getUserName(), "Sunflower", 1);
                        }
                        fdao.buyCrop(sunflower.getCost(), farmer.getUserName());
                        
                    }
                    break;
                case 4 :
                    System.out.print("Enter quantity > ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    if(farmer.getGold() < quantity*watermelon.getCost()){
                        System.out.print("Insufficient gold.");
                    }
                    else{
                        if (quantity > 1){
                            System.out.print(quantity + " bags of seeds purchased for "+ quantity*watermelon.getCost()+" gold.");
                        }
                        else{
                            System.out.print(quantity + " bag of seeds purchased for "+ quantity*watermelon.getCost()+" gold.");
                        }
                        cost = quantity*watermelon.getCost();
                        seedquantity = cdao.checkCrop(farmer.getUserName(), "Watermelon");
                        if(seedquantity != 0){
                            cdao.plusSeed(farmer.getUserName(), "Watermelon");
                        }
                        else{
                            cdao.addCrop(farmer.getUserName(), "Watermelon", 1);
                        }
                        fdao.buyCrop(watermelon.getCost(), farmer.getUserName());
                    }
                    break;
                default :
                    System.out.println("Enter a choice between 1 to 4");
            }
        }
        else{
            System.out.println("Enter valid input");
        }
    }
    public static boolean isInteger(String s) {
        return isInteger(s,5);
    }
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}