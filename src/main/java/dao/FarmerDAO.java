package main.java.dao;
import java.util.*;
import java.sql.*;
import main.java.object.cityfarmer.*;

public class FarmerDAO{
    public static Farmer retrieveByName(String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from farmers where username =\'"+username+"\'";
            String sql2 = "select * from login where username =\'"+username+"\'";
            ResultSet rset = stmt.executeQuery(sql);
            ResultSet rset1 = stmt.executeQuery(sql2);
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String fullname = rset.getString("fullname");
                String pword = rset1.getString("pword");
                int xp = rset.getInt("experience");
                String title = rset.getString("title");
                double gold = rset.getDouble("gold");
                int gift = rset.getInt("gifts_left");
                java.util.Date last_date = rset.getDate("last_date");
                Farmer farmer = new Farmer(username,fullname,pword,xp,title,gold,gift,last_date);
                return farmer;
            }
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    } 

    public static Boolean createFarmer(Farmer farmer){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            FarmerDAO fDAO = new FarmerDAO();
                String sql = "insert into farmers(userName,fullname,experience,title,gold,gifts_left,last_date) values ( \'" + farmer.getUserName() + "\',\'" + farmer.getFullName() + "\'," +0+",\'" + "Novice" + "\',\'" + 50.0 + "\'," +5+ ")";
                if (stmt.executeUpdate(sql) ==1){
                    return true;
                }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean sendGift(String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            FarmerDAO fDAO = new FarmerDAO();
                String sql = "update farmers set gifts_left = gifts_left -1 where username=\'" + username + "\'";
                if (stmt.executeUpdate(sql) ==1){
                    return true;
                }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean rankUp(String title,String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            FarmerDAO fDAO = new FarmerDAO();
                String sql = "update farmers set title = \'" + title + "\' where username=\'" + username + "\'";
                if (stmt.executeUpdate(sql) ==1){
                    return true;
                }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static Boolean buyCrop(int cost, String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            FarmerDAO fDAO = new FarmerDAO();
                String sql = "update farmers set gold = gold -" + cost + " where username=\'" + username + "\'";
                if (stmt.executeUpdate(sql) ==1){
                    return true;
                }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static boolean increaseXP(Integer exp, String username) {
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "update farmers set experience = experience +" + exp + " where username=\'" + username + "\'";
            if (stmt.executeUpdate(sql) ==1){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean increaseGold(Double gold, String username) {
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "update farmers set gold = gold +" + gold + " where username=\'" + username + "\';";
            if (stmt.executeUpdate(sql) ==1){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}