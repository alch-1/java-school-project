package main.java.object.cityfarmer;

import main.java.object.member.*;
// import java.sql.Time;
import java.util.*;

public class Farmer extends User {
    private int xp;
    private String title;
    private double gold;
    private int gift;
    private Date last_date;

    public Farmer(String userName, String fullName, String passWord, int xp, String title, double gold, int gift, Date last_date) {
        super(userName,fullName,passWord);
        this.xp = xp;
        this.title = title;
        this.gold = gold;
        this.gift = gift;
        this.last_date = last_date;
    }

    public String getUserName(){
        return super.getFullName();
    }

    public int getXp(){
        return xp;
    }

    public String getTitle(){
        return title;
    }

    public double getGold(){
        return gold;
    }

    public int getgift(){
        return gift;
    }

    public Date getDate() {
        return last_date;
    }

    public void setXp(int xp){
        this.xp = xp;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setGold(double gold){
        this.gold = gold;
    }
    public void setgift(int gift){
        this.gift = gift;
    }
}