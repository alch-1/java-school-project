package main.java.object.member;
public class User{
    private String userName;
    private String fullName;
    private String passWord;

    public User(String u, String f, String p){
        userName = u;
        fullName = f;
        passWord = p;
    }

    public String getUserName(){
        return userName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPassWord(){
        return passWord;
    }
}