package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class NewsFeed_Thread {
    public static void executeThread(User user, UserPosts post, int counter) {
        System.out.println("== Social Magnet :: View a Thread ==");
        System.out.println();
        NewsFeed.printThreadBasic(user, post, counter);

        System.out.println("Who likes this post:");
        printLikes(post, "liked");

        System.out.println("Who dislikes this post:");
        printLikes(post, "disliked");

        Scanner sc = new Scanner(System.in);
        System.out.print("[M]ain | [K]ill | [R]eply | [L]ike | [D]islike > ");
        String inp = sc.nextLine();

        while(true){
            if(inp.equals("M")){
                System.out.println();
                MainMenu.executeMainMenu(user);
                break;
            }else if(inp.equals("K")){
                //kill doesnt exist
                kill(user, post);
                
                System.out.println();
                MainMenu.executeMainMenu(user);
                
            }else if(inp.equals("R")){
                System.out.print("Input Reply >  ");
                String content = sc.nextLine();
                // ----
                //reply ID Sign off is weird as hell
                // ----
                ReplyDAO.reply(post.getPostID(), user.getUserName(), content);
                System.out.println("Successfully Replied!");


            }else if(inp.equals("L")){
                ThreadDAO.likeDislike(post.getPostID(), user.getUserName(), "liked");
                System.out.println("Successfully Liked!");
                
            }else if(inp.equals("D")){
                ThreadDAO.likeDislike(post.getPostID(), user.getUserName(), "disliked");
                System.out.println("Successfully DisLiked!");

                
            }else{
                System.out.println("Input Incorrect, please select an option");
                System.out.print("[M]ain | [K]ill | [R]eply | [L]ike | [D]islike > ");
                inp = sc.nextLine();
                
            }
            System.out.print("[M]ain | [K]ill | [R]eply | [L]ike | [D]islike > ");
            inp = sc.nextLine();
        }

    }
    public static void kill(User user, UserPosts post){
        
        if(post.getWall().equals(user.getUserName())){
            boolean result = UserPostsDAO.delete(post.getPostID());
            if(result){
                System.out.println("Kill Failed");
            }
            else{
                System.out.println("Killed Successfully!");
            }
        }
        else{
            System.out.println("Cannot kill this post because it is not on your wall.");
        }
        
    }

    public static void printLikes(UserPosts post, String choice){
        ArrayList<String> likes = ThreadDAO.retrieveFriends(post.getPostID(), choice);
        Iterator iter = likes.iterator();

        int counter = 1;
        while(iter.hasNext()){
            String username = (String) iter.next();
            // System.out.println(username);
            UserDAO uDAO = new UserDAO();
            User user = uDAO.retrieveByUName(username);

            System.out.println("  " + counter + ". " +  user.getFullName() + " (" + username + ")");
            counter++;
        }

        
    }
    
}