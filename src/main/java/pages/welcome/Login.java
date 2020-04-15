package main.java.pages.welcome;

import java.util.*;
import java.io.*;

import main.java.pages.welcome.*;
import main.java.pages.mainmenu.*;
import main.java.object.member.*;
import main.java.dao.*;

public class Login{
    private static final Scanner sc = new Scanner(System.in); 
    private static final UserDAO uDAO = new UserDAO();
    private static String username;

    public static void executeLogin(){
        int counter = 0;
        System.out.println("== Social Magnet :: Login ==");
        //there is no way to exit the login page, maybe should add
        boolean error = false;
        do{
        System.out.print("Enter your username > ");
        username = sc.nextLine();
        System.out.print("Enter your password > ");
        String password = sc.nextLine();
    
        if(!loginLogic(username, password)){
            error = true; 
            System.out.println("Username or Password Incorrect! Please Re-enter");
            continue;
        }
        //CommunicationsException catch plz
        error = false;
        }while(error);

        System.out.println("Success!");
        System.out.println();
        MainMenu.executeMainMenu(uDAO.retrieveByUName(username));
    } 
    public static boolean loginLogic(String username, String password){
        
        User u = uDAO.retrieveByUName(username); //problem
        
        if(u != null){
            if(password.equals(u.getPassWord())){
                return true;
            }
        }
        return false;
    }

}