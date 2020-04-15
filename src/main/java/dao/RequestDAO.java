package main.java.dao;

import java.util.*;
import java.sql.*;
import main.java.object.member.*;
import main.java.dao.*;

public class RequestDAO{
    public ArrayList<User> retrieveAllRequest(String userName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "select * from requests where userName = \'"+userName+ "\'";
            ResultSet rset = stmt.executeQuery(sql);
            int rowCount = 0;
            ArrayList<User> RequestList = new ArrayList<>();
            while(rset.next()){
                String requestUName = rset.getString("request_username");
                UserDAO uDAO = new UserDAO();
                User requestUser = uDAO.retrieveByUName(requestUName);
                RequestList.add(requestUser);
                rowCount ++;
            }
            return RequestList;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean request(String userName,String requestUName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "insert into requests(username,request_username) values (\'" + userName + "\',\'" + requestUName +"\')";
            if (stmt.executeUpdate(sql) != 0){
                return true;
            }
            return false;            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean approveRequest(String userName, String requestUName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "delete from requests where username = \'"+userName + "\' and request_username = \'"+ requestUName+"\'" ;
            if(stmt.executeUpdate(sql) != 0){
                FriendDAO fDAO = new FriendDAO();
                if(fDAO.addFriend(userName,requestUName) && fDAO.addFriend(requestUName,userName)){
                    return true;
                }
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean rejectRequest(String userName,String requestUName){
        try(
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/magnet?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "");
            Statement stmt = conn.createStatement();
        ){
            String sql = "delete from requests where username = \'"+ userName + "\' and request_username = \'"+ requestUName + "\'";
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