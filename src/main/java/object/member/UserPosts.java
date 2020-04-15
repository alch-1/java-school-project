package main.java.object.member;
public class UserPosts{
    private int postId;
    private String wall;
    private String username;
    private String content;
    private String timeStamp;
    private int tag;

    public UserPosts(int pid, String w, String u,String c,String t, int ta){
        postId = pid;
        wall = w;
        username = u;
        content = c;
        timeStamp = t;
        tag = ta;
    }

    public int getPostID(){
        return postId;
    }
    public String getWall(){
        return wall;
    }

    public String getUsername(){
        return username;
    }

    public String getContent(){
        return content;
    }

    public String getTimeStamp(){
        return timeStamp;
    }
    public int getTag(){
        return tag;
    }

}