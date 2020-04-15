package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class FriendWall {
    public static void executeFriendWall(User user, User viewer){
        System.out.println("== Social Magnet :: " + user.getUserName() + "'s Wall ==");
        System.out.println("Welcome, " + viewer.getFullName() + "!");

        ArrayList<UserPosts> userposts = MyWall.about(user);
        System.out.println();

        System.out.println(user.getUserName() + "'s Friend");
        printFriendList(user, viewer);


        Scanner sc = new Scanner(System.in);
        System.out.print("[M]ain | [T]hread | [P]ost >  ");
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
                    break;
                }catch(Exception e){
                    System.out.println("Input does not match any existing post.");
                    System.out.print("[M]ain | [T]hread | [P]ost >  ");
                    userinp = sc.next();
                }
                
            }
            else if(userinp.equals("P")){
                Post.executePost(viewer, user);
                break;
            }
            else{
                System.out.println("Input Incorrect, please select an option");
                System.out.print("[M]ain | [T]hread | [P]ost >  ");
                userinp = sc.next();
            }
        }
        System.out.print("[M]ain | [T]hread | [P]ost >  ");
        userinp = sc.next();
        if(userinp.equals("P")){
            executeFriendWall(user, viewer);
        }
    }
    public static void printFriendList(User user, User viewer){
        ArrayList<User> friend = FriendDAO.retrieveAllFriends(user.getUserName());
        ArrayList<User> viewerfriends = FriendDAO.retrieveAllFriends(user.getUserName());

        int counter = 1;
        Iterator iter = friend.iterator();
        while (iter.hasNext()) { 
            User myfriend = (User) iter.next();
            System.out.print(counter + ". " + myfriend.getUserName());
            if(viewerfriends.contains(myfriend)){
                System.out.print(" (Common Friend)");
            }
            System.out.print("\n");
            
            counter++;
            if(counter == 6){
                break;
            }
        }

    }
}