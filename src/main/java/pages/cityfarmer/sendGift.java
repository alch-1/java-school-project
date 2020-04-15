package main.java.pages.cityfarmer;
// import main.java.object.cityfarmer.*;
import main.java.dao.*;
import main.java.object.cityfarmer.*;

import java.util.*;
import java.io.*;

public class sendGift{
    private static final GiftDAO r = new GiftDAO();
    private static final CropDAO c = new CropDAO();
    private static final FarmerDAO f = new FarmerDAO();
    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner temp = new Scanner(System.in);
    public static void executeSendGift(Farmer farmer){
        System.out.println("== Social Magnet :: City Farmers :: Send a Gift ==");
        System.out.println("Welcome, "+farmer.getUserName()+"!");
        System.out.println("Title: "+farmer.getTitle()+"\tGold: "+farmer.getGold()+"gold");
        System.out.println();
        System.out.println("Gifts Available:");
        System.out.println("1. 1 Bag of Papaya Seeds");
        System.out.println("2. 1 Bag of Pumpkin Seeds");
        System.out.println("3. 1 Bag of Sunflower Seeds");
        System.out.println("4. 1 Bag of Watermelon Seeds");
        System.out.print("[R]eturn to main | Select choice >");
        String in = sc.next();
        String line = "";
        String[] inp= null;
        if (in.equals("R")) {
            cityfarmerMenu.executeCityfarmerMenu(farmer);
            System.out.println();
        }
        else if(isInteger(in)){
            int input = Integer.parseInt(in);
            switch (input) {
                case 1 :
                    System.out.print("Send to>");
                    line = temp.next();
                    inp = line.split(",");
                    try{
                        for(int i = 0; i < inp.length;i++){
                            r.addGift(inp[i],"Papaya", 1, farmer.getUserName());
                            f.sendGift(farmer.getUserName());
                        }
                        System.out.println("Gift posted to your friends' wall.");
                        

                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                    break;
                case 2 :
                    System.out.print("Send to>");
                    line = temp.next();
                    inp = line.split(",");
                    try{
                        for(int i = 0; i < inp.length;i++){
                            r.addGift(inp[i],"Pumpkin", 1, farmer.getUserName());
                            f.sendGift(farmer.getUserName());
                        }
                        System.out.println("Gift posted to your friends' wall.");
          
                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                    break;
                case 3 :
                    System.out.print("Send to>");
                    line = temp.next();
                    inp = line.split(",");
                    try{
                        for(int i = 0; i < inp.length;i++){
                            r.addGift(inp[i],"Sunflower", 1, farmer.getUserName());
                            f.sendGift(farmer.getUserName());
                        }
                        System.out.println("Gift posted to your friends' wall.");
                  
                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                    break;
                case 4 :
                    System.out.print("Send to>");
                    line = temp.next();
                    inp = line.split(",");
                    try{
                        for(int i = 0; i < inp.length;i++){
                            r.addGift(inp[i],"Watermelon", 1, farmer.getUserName());
                            f.sendGift(farmer.getUserName());
                        }
                        System.out.println("Gift posted to your friends' wall.");
           
                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
        else{
            System.out.println("Please enter valid input");
        }
    }
    public static boolean isInteger(String s) {
        return isInteger(s,4);
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