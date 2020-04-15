package main.java.dao;

import java.util.*;
import java.sql.*;
import java.sql.Timestamp;
import main.java.object.member.*;

public class UserPostsDAO{
    // only rerieve top 5 record
    public static ArrayList<UserPosts> retrieveAll(String userName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from user_posts where userName = \'"+userName + "\' order by ts desc limit " + 5;
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<UserPosts> posts = new ArrayList<>();
            while(rset.next()){
                int postId = rset.getInt("post_id");
                String wall = rset.getString("wall");
                String username = rset.getString("username");
                String timeStamp = rset.getString("ts");
                String content = rset.getString("content");
                int tag = rset.getInt("tag");
                posts.add(new UserPosts(postId, wall, username,content,timeStamp, tag));
                rowCount ++;
            }
            return posts;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }


    //view post
    public UserPosts retrieveByPostID(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from user_posts where post_id = " + post_id;
            ResultSet rset = stmt.executeQuery(sql);
            
            if(rset.next()){
                int postId = rset.getInt("post_id");
                String wall = rset.getString("wall");
                String username = rset.getString("username");
                String timeStamp = rset.getString("ts");
                String content = rset.getString("content");
                int tag = rset.getInt("tag");
                UserPosts post = new UserPosts(postId, wall, username,content,timeStamp, tag);
                return post;
            }
            return null;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<UserPosts> retrieveByWall(String w){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from user_posts WHERE wall = '" + w +"'";
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<UserPosts> posts = new ArrayList<>();
            while(rset.next()){
                int postId = rset.getInt("post_id");
                String wall = rset.getString("wall");
                String username = rset.getString("username");
                String timeStamp = rset.getString("ts");
                String content = rset.getString("content");
                int tag = rset.getInt("tag");
                posts.add(new UserPosts(postId, wall, username,content,timeStamp,tag));
                rowCount ++;
            }
            return posts;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
        
    }


    //friends only contains ppl being tagged
    public static Boolean post(String wall, String username,ArrayList<String> friends,String content){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sql = "insert into user_posts(wall, ts,username, content, tag) values (\'" + wall + "\',\'"+ timestamp +"\',\'" + username + "\' ,\'" + content + "\', " + 0 + ")" ;
            // // tag friends
            // TagIDDAO tDAO = new TagIDDAO();
            // tDAO.TagID(username,timestamp);
            int userPostID = retrievePostID(username, timestamp);
            
            if (stmt.executeUpdate(sql) != 0 && tag(username,friends,content,timestamp,userPostID)){
                return true;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    // used by Post method
    public static Boolean tag(String author ,ArrayList<String> friends,String content,Timestamp timeStamp,int userPostID){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            TagIDDAO tDAO = new TagIDDAO();
            // ArrayList<Integer> post_id = new ArrayList<>();
            for(String friend:friends){
                String sql = "insert into user_posts(wall,ts,username, content, tag) values (\'" + friend + "\',\'" + timeStamp +"\',\'" + author + "\' ,\'" + content +  "\', " + 1 + ")";
                if (stmt.executeUpdate(sql) == 0){
                    System.out.println("insertion fails");
                }
                // get the post id
                int friendPostID = retrievePostID(friend,timeStamp);
                // save the relation in the 
                tDAO.TagID(userPostID,friendPostID);  
            }
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // used by Tag
    public static int retrievePostID(String username,Timestamp timeStamp){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from user_posts where username = \'"+ username +"\' and ts = \'" + timeStamp+"\'";
            ResultSet rset = stmt.executeQuery(sql);
            if (rset.next()){
                int post_id = rset.getInt("post_id");
                return post_id;
            }
            return 0;
        }catch(SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }
    // delete person's post with the tagged people's post
    public static Boolean delete(int post_id){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            // delete content
            TagIDDAO tDAO = new TagIDDAO();
            ArrayList<Integer> postIDList = tDAO.retrieveTags(post_id);
            postIDList.add(post_id);
            for(int eachPostID : postIDList){
                String sql = "delete from user_posts where post_id = " + eachPostID ;
                if(stmt.executeUpdate(sql) == 0){
                    System.out.println("post id " + eachPostID +" doesnt been deleted!");
                }
            } 

            // delete reply and tags
            ReplyDAO rpDAO = new ReplyDAO();
            ThreadDAO thDAO = new ThreadDAO();
            if(tDAO.deleteTags(post_id) && rpDAO.deleteByPostID(post_id) && thDAO.delete(post_id)){
                return true;
            }

            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}