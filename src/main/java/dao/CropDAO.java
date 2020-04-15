package main.java.dao;

import java.util.*;
import java.sql.*;
import main.java.object.cityfarmer.*;

public class CropDAO{
    public ArrayList<Crop> retrieveAllCrops(String userName) {
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ) {
            String sql1 = "select seed_name,quantity from seeds where username = \'"+userName + "\'";
            ResultSet rset = stmt.executeQuery(sql1);
            int rowCount = 0;
            ArrayList<Crop> inventory = new ArrayList<>();
            while(rset.next()) {
                String seed = rset.getString("seed_name");
                int q = rset.getInt("quantity");
                Crop toAdd = Helper.getCrop(seed);
                inventory.add(toAdd);
                rowCount ++;
            }
            return inventory;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static int checkCrop(String userName,String seed){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select quantity from seeds where username = \'"+userName + "\' and seed_name = \'"+ seed +"\'";
            ResultSet rset = stmt.executeQuery(sql);
            int crop = 0;
            if(rset.getInt("quantity") != 0) {
                crop = rset.getInt("quantity");
            }
            return crop;
        }catch(SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }
    public static Boolean addCrop(String username,String seed,int qbag){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "insert into seeds(username,seed_name,quantity) values (\'"+ username +"\',\'"+seed+"\',"+qbag+")";
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean plusSeed(String username, String seed){
        
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "UPDATE `seeds` set `seeds`.`quantity` = `quantity` + 1  where `seeds`.`username`='"+username+"' AND `seeds`.`seed_name`='"+seed+"'";

            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public static Boolean minusSeed(String username, String seed){
        
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "UPDATE `seeds` set `seeds`.`quantity` = `quantity` - 1  where `seeds`.`username`='"+username+"' AND `seeds`.`seed_name`='"+seed+"'";

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