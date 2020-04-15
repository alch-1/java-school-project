package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;
import java.text.*;

import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class NewsFeed{
    

    public static void executeNewsFeed(User user){
        System.out.println("== Social Magnet :: News Feed ==");

        ArrayList<UserPosts> userposts = getTop5(user);
    
        //have to get own, and sort by time :)
        
        Iterator iterposts = userposts.iterator();

        int counter = 1;
        while (iterposts.hasNext()) { 
            UserPosts post = (UserPosts) iterposts.next();

            // System.out.println(post.getUsername());
            printThreadBasic(user, post, counter);
            counter++;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("[M]ain | [T]hread > ");
        String userinp = sc.next();

        
        while(true){
            if(userinp.equals("M")){
                System.out.println();
                MainMenu.executeMainMenu(user);
                
                break;
            }
            else if(Character.toString(userinp.charAt(0)).equals("T")){
                String requestinp = userinp.substring(1);

                // System.out.println();
                try{
                    NewsFeed_Thread.executeThread(user, userposts.get(Integer.valueOf(requestinp)-1), Integer.valueOf(requestinp));
                    break;
                }catch(Exception e){
                    System.out.println("Input does not match any existing post.");
                    System.out.print("[M]ain | [T]hread > ");
                    userinp = sc.next();
                }
            }
            
            else{
                System.out.println("Input Incorrect, please select an option");
                System.out.print("[M]ain | [T]hread > ");
                userinp = sc.next();
            }
        }
        
        
    }
    public static ArrayList<UserPosts> getTop5(User user){
        //take friends and search walls 
        //create final array with all posts from friends & himself
        //sort through the array to make sure its in descending order
        //return top 5; 
        
        ArrayList<User> friend = FriendDAO.retrieveAllFriends(user.getUserName());
        Iterator iter = friend.iterator(); 
        ArrayList<UserPosts> large = new ArrayList<UserPosts>();

        while(iter.hasNext()){
            User myfriend = (User) iter.next();
            ArrayList<UserPosts> friendpost = UserPostsDAO.retrieveByWall(myfriend.getUserName());

            large.addAll(friendpost);
        }

        


        ArrayList<UserPosts> userposts = UserPostsDAO.retrieveByWall(user.getUserName());
        
        System.out.println(user.getUserName());
        ArrayList<UserPosts> x = new ArrayList<UserPosts>();

        for(int i=0; i < large.size(); i++){
            if(!(large.get(i).getTag() == 1 && large.get(i).getUsername().equals(user.getUserName()))){
                x.add(large.get(i));
            }
        }
        
        ArrayList<UserPosts> temp = sortPosts(x);

        return temp;
    }
    public static ArrayList<UserPosts> sortPosts(ArrayList<UserPosts> inp){
        
        

        int n = inp.size(); 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                UserPosts temp = (UserPosts) inp.get(j);
                UserPosts temp2 = (UserPosts) inp.get(j+1);
                if (temp.getTimeStamp().compareTo(temp2.getTimeStamp()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    String t = temp.getTimeStamp(); 
                    inp.set(j, temp2);
                    inp.set(j+1, temp);
                }
            }
        }
        return inp;


        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

    }

    public static void printThreadBasic(User user, UserPosts post, int counter){
        System.out.println(counter + "  " + post.getUsername() + ": \"" + post.getContent() + "\"");
        

        System.out.println("[ " + amountOfLikes(post.getPostID()) + " likes, " + amountOfDislikes(post.getPostID()) + " dislike ]");

        replies(counter, post.getPostID());
    }

    public static void replies(int countStart, int post_id){
        ArrayList<Reply> replies = ReplyDAO.retrieveByPostID(post_id);

        Iterator iter = replies.iterator();
        int counter = 1;
        while(iter.hasNext()){
            Reply curr_reply = (Reply) iter.next();
            
            System.out.println("  " + countStart + "." + counter + " " + curr_reply.getFriendUserName() + ": " + curr_reply.getContent());
            counter++;
            if(counter == 4){
                break;
            }
        }
        
    }

    public static int amountOfLikes(int post_id){
        ArrayList<String> likes = ThreadDAO.retrieveFriends(post_id, "liked");
        // System.out.println(String.join(", ", likes));
        return likes.size();
    }
    public static int amountOfDislikes(int post_id){
        ArrayList<String> dislikes = ThreadDAO.retrieveFriends(post_id, "disliked");
        // System.out.println(String.join(", ", dislikes));
        return dislikes.size();
    }
}