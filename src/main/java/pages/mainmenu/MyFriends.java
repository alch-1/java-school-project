package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class MyFriends{
    // private static final FriendDAO f = new FriendDAO();
    private static final Scanner sc = new Scanner(System.in); 
    private static final RequestDAO r = new RequestDAO();

    public static void executeMyFriends(User user){
        System.out.println("== Social Magnet :: My Friends ==");
        System.out.println("Welcome, " + user.getFullName() + "!");
        System.out.println();

        System.out.println("My Friends");
        int counter = printFriendList(user);

        System.out.println();
        System.out.println("My Requests:");
        
        ArrayList<User> requests = r.retrieveAllRequest(user.getUserName());

        int tcounter = 1; 
        Iterator iter = requests.iterator();
        while (iter.hasNext()) {
            User myRequest = (User) iter.next();

            System.out.println(counter + ". " + myRequest.getUserName());
            counter++;
            tcounter++;
            if(tcounter == 6){
                break;
            }
        }
        
        
        System.out.print("[M]ain | [U]nfriend | re[Q]uest | [A]ccept | [R]eject | [V]iew > ");
        String userinp = sc.next();
        
        boolean test = false;
        while(!test){
            test = friendLogic(user, userinp);
            System.out.print("[M]ain | [U]nfriend | re[Q]uest | [A]ccept | [R]eject | [V]iew > ");
            userinp = sc.nextLine();
        }
        System.out.print("[M]ain | [U]nfriend | re[Q]uest | [A]ccept | [R]eject | [V]iew > ");
        userinp = sc.nextLine();
        test = friendLogic(user, userinp);
        System.out.println();
    }

    public static int printFriendList(User user){
        ArrayList<User> friend = FriendDAO.retrieveAllFriends(user.getUserName());

        int counter = 1;
        Iterator iter = friend.iterator();
        while (iter.hasNext()) { 
            User myfriend = (User) iter.next();
            
            System.out.println(counter + ". " + myfriend.getUserName());
            counter++;
            if(counter == 6){
                break;
            }
        }

        return counter;
    }


    //FRIEND LOGIC
    public static boolean friendLogic(User user, String userinp){
        if(userinp.equals("M")){
            MainMenu.executeMainMenu(user);
            System.out.println();
            return true;
        }
        //Unfriend
        if(Character.toString(userinp.charAt(0)).equals("U")){
            String requestinp = userinp.substring(1);
            try{
                if(FriendDAO.removeFriend(user.getUserName(), requestinp)){
                    System.out.println(requestinp + " is no longer your friend");
                    return true;
                }
                else{
                    System.out.println("Failed to remove friend");
                    return true;
                }
            }catch(Exception e){
                System.out.println("Failed to remove friend");
            }
        }
        //request
        else if(userinp.equals("Q")){
            System.out.print("Enter the username > ");
            String requestinp = sc.next();

            friendRequest(user, requestinp);

            
        }
        //Accept
        else if(Character.toString(userinp.charAt(0)).equals("A")){
            String requestinp = userinp.substring(1);
            try{
                if(r.approveRequest(user.getUserName(), requestinp)){
                    System.out.println("Friend request has been accepted");
                    return true;
                }
                else{
                    System.out.println("Failed to Accept friend request");
                    return true;
                }
                
            }catch(Exception e){
                System.out.println("Failed to Accept friend request");
            }
        }
        //Reject
        else if(Character.toString(userinp.charAt(0)).equals("R")){
            String requestinp = userinp.substring(1);
            try{
                if(r.rejectRequest(user.getUserName(), requestinp)){
                    System.out.println("Friend request has been rejected");
                    return true;
                }
                else{
                    System.out.println("Failed to reject friend request");
                    return true;
                }
                
            }catch(Exception e){
                System.out.println("Failed to reject friend request.");
            }
        }
        //View
        else if(Character.toString(userinp.charAt(0)).equals("V")){
            String requestinp = userinp.substring(1);
            try{
                UserDAO uDAO = new UserDAO();
                User temp = uDAO.retrieveByUName(requestinp);
                FriendWall.executeFriendWall(temp, user);
                return true;
            }catch(Exception e){
                System.out.println("User does not exist.");
            }
        }
        else{
            System.out.println("Input Incorrect, please select an option");
        }
        sc.nextLine();
        
        return false;
        
    }
    
    public static void accept(User user, User ruser){
        try{r.approveRequest(user.getUserName(), ruser.getUserName());}
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public static boolean friendRequest(User user,String reqname){
            UserDAO uDAO = new UserDAO();
            if(uDAO.retrieveByUName(reqname) == null){
                return false;
            }
            
            try{
                if(r.request(reqname, user.getUserName())){
                    System.out.println("A friend request is sent to " + reqname);
                    return true;
                }
                else{
                    System.out.println("Failed to request friend");
                    return true;
                }
            }catch(Exception e){
                System.out.println("Failed to request friend");
                return false;
            }
    }
    
}