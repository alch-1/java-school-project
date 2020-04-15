package main.java.dao;

import java.util.*;

import javax.management.RuntimeErrorException;
import main.java.errorclasses.*;

import java.sql.*;
import main.java.object.member.*;

public class UserDAO{
    public ArrayList<User> retrieveAll(){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from User;";
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<User> userLogin = new ArrayList<>();
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String username = rset.getString("username");
                String full_name = rset.getString("full_name");
                String pword = rset.getString("pword");
                userLogin.add(new User(username,full_name,pword));
                ++rowCount;
            }

            return userLogin;
        }catch(SQLException ex){
            throw new UserDAO_Error("Error");
        }
    }

    public User retrieveByUName(String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from login where username = \'" + username + "\'";
            ResultSet rset = stmt.executeQuery(sql);
            
            if(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String full_name = rset.getString("full_name");
                String pword = rset.getString("pword");
                User user = new User(username,full_name,pword);
                return user;
            } 
                // this shouldn't happen 
            return null;
            
        }catch(SQLException ex){
            // ex.printStackTrace();
            throw new UserDAO_Error("Error");
        }
    }

    public Boolean addUser(User user){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            UserDAO uDAO = new UserDAO();
            // IDK somehow the below code doesnt work
            // if(uDAO.retrieveByUName(user.getUserName()) == null){
                String sql = "insert into login(username,full_name,pword) values ( \'" + user.getUserName() + "\',\'" + user.getFullName() + "\',\'" + user.getPassWord()+"\');";
                if (stmt.executeUpdate(sql) ==1){
                    return true;
                }
            // }
            // System.out.println(user.getUserName() + " has been used");
            return false;
        }catch(SQLException ex){
            // ex.printStackTrace();
            throw new UserDAO_Error("Error");
        }
    }
}