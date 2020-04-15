// package main.java.pages;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;
// import java.util.Scanner;
// public class RegistrationTest {
//     private static final Scanner sc = new Scanner(System.in);
//     public static void main(String[] args) {
//         System.out.println("== Social Magnet :: Registration ==");
//         System.out.print("Enter your username: ");
//         String uname = sc.nextLine();
//         uname = uname.replaceAll("\\s+", ""); // remove all spaces
//         if (uname == null || uname.trim().isEmpty()) {
//             System.out.println("Please type in correct format");
//         }
//         Pattern p = Pattern.compile("[^A-Za-z0-9]");
//         // Pattern p = Pattern.compile("[A-Za-z0-9]+");
//         Matcher m = p.matcher(uname);
//         boolean b = m.find();
//         if (b){
//             System.out.println("There is a special character in my string ");
//         }
//         else{
//             System.out.print("Enter your Full name: ");
//             String fname = sc.nextLine();
//             System.out.print("Enter your password: ");
//             String password = sc.nextLine();
//             System.out.print("Confirm your password: ");
//             String npassword = sc.nextLine();
//             if(password.equals(npassword)){
//                 System.out.println(uname+", your account is successfully created!");
//             }
//             else{
//                 System.out.println("Please type the same password");
//             }
//         }
//     }   
// }
