package main.java.dao;

import java.util.*;
import java.sql.*;
import java.sql.Timestamp;
import main.java.object.member.*;

public class TagIDDAO{
    // used by UserPostsDAO
    public Boolean TagID(int postId,int tagId){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "insert into tag(post_id,tag_id) values (" + postId + "," + tagId +")";
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    // used by UserPostsDAO
    public static ArrayList<Integer> retrieveTags(int postId){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from tag where post_id = " + postId;
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<Integer> tagged_id = new ArrayList<>();
            while(rset.next()){
                tagged_id.add(rset.getInt("tag_id"));
                rowCount ++;
            }
            return tagged_id;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    // used by UserPostsDAO
    public Boolean deleteTags(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "delete from tag where post_id = " + post_id;
            if(stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}