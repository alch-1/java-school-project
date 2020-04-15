package main.java.errorclasses;

public class UserDAO_Error extends RuntimeException{
    public UserDAO_Error(String msg){
        super(msg);
    }
}