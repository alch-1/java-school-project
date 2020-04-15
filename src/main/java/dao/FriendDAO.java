package main.java.dao;

import java.util.*;

// import jdk.internal.module.SystemModuleFinders;

import java.sql.*;
import main.java.object.member.*;

public class FriendDAO{
    public static ArrayList<User> retrieveAllFriends(String userName) {
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ) {
            String sql1 = "select * from friends where userName = \'"+userName + "\'";
            ResultSet rset = stmt.executeQuery(sql1);
            int rowCount = 0;
            ArrayList<User> friend = new ArrayList<>();
            while(rset.next()) {
                String friendUsername = rset.getString("friend_username");
                // get the user object
                UserDAO uDAO = new UserDAO();
                friend.add(uDAO.retrieveByUName(friendUsername));
                rowCount ++;
            }
            return friend;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // this is used for RequestDAO
    public static Boolean addFriend(String userName,String friendUName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "insert into friends(username,friend_username) values (\'"+ userName +"\',\'"+friendUName+"\')";
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean removeFriend(String userName, String friendUName){
        
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "DELETE FROM `friends` WHERE `friends`.`username` = '"+ userName + "' AND `friends`.`friend_username` = '" + friendUName + "'";

            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}