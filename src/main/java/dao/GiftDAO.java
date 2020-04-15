package main.java.dao;

import java.sql.*;
import java.util.*;
import main.java.object.cityfarmer.*;

public class GiftDAO{
public static ArrayList<Gift> retrieveAllGifts(String userName) {
    try(
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
        Statement stmt = conn.createStatement();
    ) {
        String sql1 = "select * from gifts where username = \'"+userName + "\'";
        ResultSet rset = stmt.executeQuery(sql1);
        int rowCount = 0;
        ArrayList<Gift> g = new ArrayList<>();
        while(rset.next()) {
            String gift_name = rset.getString("gift_name");
            int gift_quantity = rset.getInt("gift_quantity");
            String stat = rset.getString("stat");
            String sender = rset.getString("sender");
            g.add(new Gift(gift_name,gift_quantity,stat,sender));
            rowCount ++;
        }
        return g;
    } catch(SQLException ex) {
        ex.printStackTrace();
        return null;
    }
}
public static Boolean addGift(String username,String gift_name,int gift_quantity,String sender){
    try(
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
        Statement stmt = conn.createStatement();
    ){
        String sql = "insert into gifts(username,gift_name,gift_quantity,stat,sender) values (\'"+ username +"\',\'"+gift_name+"\',"+gift_quantity+",\'"+"Pending"+"\',\'"+sender+"\')";
        if (stmt.executeUpdate(sql) != 0){
            return true;
        }
        return false;
    }catch(SQLException ex){
        ex.printStackTrace();
        return false;
    }
}
public static Boolean acceptGift(String username, String sender){
    
    try(
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
        Statement stmt = conn.createStatement();
    ){
        String sql = "UPDATE `gifts` set `gifts`.`stat` = '"+ "Accepted" + "' where `gifts`.`username`='"+username+"' AND `gifts`.`sender`='"+sender+"'";

        if (stmt.executeUpdate(sql) != 0){
            return true;
        }
        return false;
    }catch(SQLException ex){
        ex.printStackTrace();
        return false;
    }
}
public static Boolean rejectGift(String username, String sender){
    
    try(
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
        Statement stmt = conn.createStatement();
    ){
        String sql = "UPDATE `gifts` set `gifts`.`stat` = '"+ "Rejected" + "' where `gifts`.`username`='"+username+"' AND `gifts`.`sender`='"+sender+"'";

        if (stmt.executeUpdate(sql) != 0){
            return true;
        }
        return false;
    }catch(SQLException ex){
        ex.printStackTrace();
        return false;
    }
}
public static Boolean deleteGift(String username, String sender){
    
    try(
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "");
        Statement stmt = conn.createStatement();
    ){
        String sql = "delete from gifts where `gifts`.`username` = '" + username+"' and `gifts`.`sender` = '"+sender+"'";

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

