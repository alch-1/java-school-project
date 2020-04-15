package main.java.pages.cityfarmer;

import java.util.*;
import main.java.object.cityfarmer.*;
import main.java.pages.cityfarmer.*;

public class cityfarmerMenu{
    private static final Scanner sc = new Scanner(System.in);
    private static final Date date = new Date(); // filler 
    public static void executeCityfarmerMenu(Farmer farmer){
        String username = farmer.getUserName();
        System.out.println("== Social Magnet :: City Farmers ==");
        System.out.println("Welcome "+username+" !");
        System.out.println("Title: "+farmer.getTitle()+"\t Gold: "+farmer.getGold()+" gold");
        System.out.println("");
        System.out.println("1. My Farmland");
        System.out.println("2. My Store");
        System.out.println("3. My Inventory");
        System.out.println("4. Visit Friend");
        System.out.println("5. Send Gift");
        int in = 0;
        do{
            System.out.print("[M]ain | Enter your choice >");
            in = sc.nextInt();
            switch (in) {
                case 1 :
                    System.out.println();
                    myFarmLand.executeMyFarmLand(farmer);;
                    break;
                case 2 :
                    System.out.println();
                    myStore.executeMyStore(farmer);
                    break;
                case 3 :
                    System.out.println();
                    myInventory.executeMyInventory(farmer);
                    break;
                case 4 :
                    System.out.println();
                    visitFriend.executeVisitFriend(farmer);
                    break;
                case 5 :
                    System.out.println();
                    sendGift.executeSendGift(farmer);
                    break;
                default :
                    System.out.println("Enter a choice between 1 to 5");
            }
        }while(in < 1 || in > 5);
    }
}