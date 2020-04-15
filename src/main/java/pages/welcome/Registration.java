package main.java.pages.welcome;
import java.util.*; 
import java.io.*;
import main.java.pages.welcome.*;
import main.java.object.member.*;
import main.java.dao.*;

public class Registration{
    private static String tusername;
    private static String fullname; 
    private static String password; 
    private static String cpassword;

    private static final Scanner sc = new Scanner(System.in); 

    public static void executeRegister(){
        System.out.println("== Social Magnet :: Registration ==");

        boolean error = false;
        do{
        System.out.print("Enter your username > ");
        tusername = sc.nextLine();
        
        String alphaNum = "^[a-zA-Z0-9]*$";
        
        if(!tusername.matches(alphaNum)){
            error = true;
            System.out.println("Please enter a alphanumeric input!");
            continue;
        }

        System.out.print("Enter your Full name> ");
        fullname = sc.nextLine();
        
        System.out.print("Enter your password >");
        password = sc.nextLine();
        
        System.out.print("Confirm your password >");
        cpassword = sc.nextLine(); 
        

        if(!password.equals(cpassword)){
            error = true;
            System.out.println("The confirmation was not the same");
            continue;
        }
        
        error = false;
    }while(error);

    addUser(tusername, fullname, password);

    System.out.println();
    
    Welcome.executeWelcome();
    }
    
    public static boolean addUser(String tusername, String fullname, String password){
        User u = new User(tusername, fullname, password);
        UserDAO uDAO = new UserDAO();

        try{
            uDAO.addUser(u);
            System.out.println(tusername + ", your account is successfully created!");
            // throw new RuntimeException("Creation Error");
            return true;
        }catch(Exception e){
            System.out.println("This username is already taken");
        }
        return false;
    }

}
