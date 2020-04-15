package main.java.dao;

import java.util.*;
import java.sql.*;
import main.java.object.member.*;

public class ThreadDAO{
    // no. of like/dislike can count the result of this function
    // LikeOrDislike must be put in like/dislike, all lowercase
    // this function can be used to return the Array either liked or disliked
    public static ArrayList<String> retrieveFriends(int post_id,String LikeOrDislike){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from thread where post_id = " + post_id ;
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<String> likes = new ArrayList<>();
            while(rset.next()){
                int like = rset.getInt(LikeOrDislike);
                rowCount ++;
                if (like == 1){
                    likes.add(rset.getString("friend_username"));
                }
            }
            return likes;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    //  this function is used by like function
    // username is friend's username
    // return a List: [value of like, value of dislike]
    public static List<Integer> retrieveLikeAndDislike(int post_id, String username){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from thread where post_id = " + post_id + " and friend_username = \'" + username + "\'" ;
            ResultSet rset = stmt.executeQuery(sql);
            List<Integer> result = new ArrayList<>();
            if(rset.next()){
                result.add(rset.getInt("liked"));
                result.add(rset.getInt("disliked"));
                return result;
            }
            return null;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    // the username is the person who like the post
    // LikeOrDislike must be put in liked/disliked, all lowercase
    public static Boolean likeDislike(int post_id, String username,String LikeOrDislike){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            List likeDislike = retrieveLikeAndDislike(post_id,username);
            String sql = "";
            // if the user havent like/dislike a post
            if(likeDislike == null){
                if(LikeOrDislike.equals("liked")){
                    sql = "insert into thread(post_id, friend_username,liked,disliked) values (" + post_id + ",\'" + username + "\'," + 1 + "," + 0 + ")";
                }else{
                    sql = "insert into thread(post_id, friend_username,liked,disliked) values (" + post_id + ",\'" + username + "\'," + 0 + "," + 1 + ")";
                }
                if (stmt.executeUpdate(sql) != 0){
                    return true;
                }
                return false;
            }
            // if the person choose like/dislike alr
            if(LikeOrDislike.equals("liked")){
                sql = "update thread set liked = " + 1 + " where post_id = " + post_id +" and friend_username = \'" + username + "\'" ;
            }else{
                sql = "update thread set disliked = " + 1 + " where post_id = " + post_id +" and friend_username = \'" + username + "\'" ;
            }
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // used by UserPostsDAO
    public Boolean delete(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "delete from thread where post_id = " + post_id;
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