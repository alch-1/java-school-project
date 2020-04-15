package main.java.dao;

import java.util.*;
import java.sql.*;
import main.java.object.member.*;
import main.java.object.cityfarmer.*;

public class LandDAO {
    public static Crop getCrop(String username, int landNumber) {
        /**
         * @param - username and lan number
         * @return - crop object from the given land number and username 
         */
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select crop_name from lands where username=" + username + "and land_number=" + landNumber + ";";
            try {
                ResultSet rs = stmt.executeQuery(sql); // get result set from sql database
                while (rs.next()) {
                    String cropName = rs.getString("crop_name");
                    Crop toReturn = Helper.getCrop(cropName);
                    return toReturn;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
            return null;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean addCrop(String username, int landNumber, String cropName) {
        // add crop to LandDAO when user plants
        /**
         * @return - boolean indicating success or failure
         */
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "root", "");
            Statement stmt = conn.createStatement();
        ){
            Crop crop = Helper.getCrop(cropName);
            // get a random number between two points
            int max = crop.getMaxYield();
            int min = crop.getMinYield();
            int quantity = Helper.getRandomNumberInRange(min, max);
            int time_needed_to_mature = crop.getTime(); // in minutes 
            // set harvest time & wilt time
            Timestamp harvest_time = new Timestamp(System.currentTimeMillis() + time_needed_to_mature * 60 * 1000); // convert to seconds, then to milliseconds
            Timestamp wilt_time =  new Timestamp(System.currentTimeMillis() + time_needed_to_mature * 60 * 1000 * 2); // convert to seconds, then to milliseconds
            
            String sql = "insert into lands(username,land_number,crop_name,quantity,harvest_time,wilt_time,stolen_count) values" +
            "('" + username + "'," + landNumber + ", " + cropName + ", " + quantity + ", " + harvest_time + ", " + wilt_time + "," + 0 + ")";
            if (stmt.executeUpdate(sql) != 0){ 
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // TODO - change the below code 
    // get lands by username
    public static ArrayList<Land> retrieveLandsByUsername(String input_username) {
        /** 
         * @return - ArrayList of Land objects
         */
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from lands where username=" + input_username;
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;

            ArrayList<Land> lands = new ArrayList<>();

            while(rset.next()){
                // String username = rset.getString(username); // redundant
                int land_number = rset.getInt("land_number");
                String crop_name = rset.getString("crop_name");
                int quantity = rset.getInt("quantity");
                Timestamp harvest_time = rset.getTimestamp("harvest_time");
                Timestamp wilt_time = rset.getTimestamp("wilt_time");
                lands.add(new Land(input_username, land_number, crop_name, quantity, harvest_time, wilt_time));
                rowCount++;
            } 
            return lands;
        } catch(SQLException ex){ 
            ex.printStackTrace();
            return null;
        }
    }

    // used by visitLogic.java
    public static boolean reduceCrop(int reduction, String username, int landNumber) {
        /** Reduce the crop by the reduction amount */
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "update lands set quantity=quantity -" + reduction + "where username=" + username + "and land_number=" + landNumber + ";";
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // used by UserPostDAO
    public static Boolean updateStolen(int stolenIncrement, String username, int land_number){
        /** Set the stolen count for the given land number */
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "update lands set stolen_count=stolen_count + " + stolenIncrement + "where username=" + username + "and land_number=" + land_number + ";";
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