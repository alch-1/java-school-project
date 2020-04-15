package main.java.object.member;

public class Friend{
    private String myUserName;
    private String myFriend;

    public Friend(String m,String f){
        myUserName = m;
        myFriend = f;
    }

    public String getFriendUserName(){
        return myFriend;
    }
}