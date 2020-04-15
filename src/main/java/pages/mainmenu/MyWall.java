package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
// import main.java.object.cityfarmer.Gift;
import main.java.object.member.*;
import main.java.object.cityfarmer.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class MyWall{
    private static int counter = 1;

    public static void executeMyWall(User user) {
        System.out.println("== Social Magnet :: My Wall ==");
        
        ArrayList<UserPosts> userposts = about(user);
        ArrayList<Gift> gifts = printGifts(user);


        Scanner sc = new Scanner(System.in);
        System.out.print("[M]ain | [T]hread | [A]ccept Gift | [P]ost >  ");
        String userinp = sc.next();

        while(true){
            if(userinp.equals("M")){
                System.out.println();
                MainMenu.executeMainMenu(user);
                
                break;
            }
            else if(Character.toString(userinp.charAt(0)).equals("T")){
                String requestinp = userinp.substring(1);

                try{
                NewsFeed_Thread.executeThread(user, userposts.get(Integer.valueOf(requestinp)-1), Integer.valueOf(requestinp));
                }catch(Exception e){
                    System.out.println("Input does not match any existing post.");
                    System.out.print("[M]ain | [T]hread | [A]ccept Gift | [P]ost >  ");
                    userinp = sc.next();
                }
            }
            else if(Character.toString(userinp.charAt(0)).equals("A")){
                String requestinp = userinp.substring(1);
                acceptGift(user, gifts.get(Integer.valueOf(requestinp)-1));
                break;
            }
            else if(userinp.equals("P")){
                Post.executePost(user, user);                
                break;
            }
            else{
                System.out.println("Input Incorrect, please select an option");
                System.out.print("[M]ain | [T]hread | [A]ccept Gift | [P]ost >  ");
                userinp = sc.next();
            }
            
        }
        System.out.print("[M]ain | [T]hread | [A]ccept Gift | [P]ost >  ");
        userinp = sc.next();

        if(userinp.equals("P")){
            executeMyWall(user);
        }
    }
    public static ArrayList<Gift> printGifts(User user){
        ArrayList<Gift> gifts = GiftDAO.retrieveAllGifts(user.getUserName());

        for(int i=0; i < gifts.size(); i++){
            Gift temp = gifts.get(i);
            
            System.out.println(counter + " " + temp.getSender() + ": Here is a bag " + temp.getGiftName() + " seeds for you. - City Farmers");
            
        }
        return gifts;
    }
    public static void acceptGift(User user, Gift gift){
        try{
            GiftDAO gDAO = new GiftDAO();
            gDAO.acceptGift(user.getUserName(), gift.getSender());
            CropDAO.addCrop(user.getUserName(), gift.getSender(), gift.getGiftQuantity());
            gDAO.deleteGift(user.getUserName(), gift.getSender());
            System.out.println("Gift Accepted");
        }catch(Exception e){
            System.out.println("Error accepting gift.");
        }
    }

    public static ArrayList<UserPosts> about(User user){
        System.out.println("About " + user.getUserName());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println();

        //insert Farmer game info, Level + Rank 


        counter = 1; 

        ArrayList<UserPosts> userposts = UserPostsDAO.retrieveByWall(user.getUserName());
        userposts = NewsFeed.sortPosts(userposts);

        Iterator iter = userposts.iterator();
        while (iter.hasNext()) { 
            UserPosts post = (UserPosts) iter.next();

            NewsFeed.printThreadBasic(user, post, counter);
            counter++;
            }
        
        return userposts;

    }
}