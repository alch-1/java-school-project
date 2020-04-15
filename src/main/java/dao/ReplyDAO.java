package main.java.dao;

import java.util.*;
import java.sql.*;
import main.java.object.member.*;

public class ReplyDAO{
    public static Boolean reply(int post_id,String friendUsername,String content){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sql = "insert into reply(post_id,ts,friend_username,reply) values (" + post_id + ",\'" + timestamp + "\' , \'" + friendUsername + "\',\'"+ content + "\')";
            if (stmt.executeUpdate(sql) != 0){ 
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    // view reply
    public static ArrayList<Reply> retrieveByPostID(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from reply where post_id  = '" + post_id + "' ORDER BY DATE(ts) DESC";
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<Reply> replies = new ArrayList<>();
            while(rset.next()){
                int reply_id = rset.getInt("reply_id");
                String ts = rset.getString("ts");
                String friendUsername =rset.getString("friend_username");
                String reply = rset.getString("reply");
                replies.add(new Reply(post_id,reply_id,ts,friendUsername,reply));
                rowCount ++;
            }
            return replies;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    // used by UserPostDAO
    public Boolean deleteByPostID(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "delete from reply where post_id = " + post_id;
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