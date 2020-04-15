package main.java.pages.mainmenu;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;
import main.java.pages.mainmenu.*;

public class Post {
    public static void executePost(User user, User Wall){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your message > ");
        String inp = sc.nextLine();
        ArrayList<String> temp = tag(inp);
        // System.out.println(temp.toString());
        boolean result = UserPostsDAO.post(Wall.getUserName(), user.getUserName(), temp, inp);
        if(result){
            System.out.println("Message successfully posted");
        }
    }
    public static ArrayList<String> tag(String inp){
        String[] plugin = inp.split(" ");
        ArrayList<String> result = new ArrayList<String>();

        for(int i=0; i<plugin.length; i++){
            if(Character.toString(plugin[i].charAt(0)).equals("@")){
                result.add(plugin[i].substring(1));
            }
        }
        
        return result;
    }
}