package main.java.object.member;

public class Thread{
    public int post_id;
    public String friendUsername;
    public int like;
    public int dislike;
    
    public Thread(int id,String u, String t, String c){
        post_id = id;
        friendUsername = u;
        like = 0;
        dislike =0;
    }

    public String getFriendUsername(){
        return friendUsername;
    }
// 0 if not like, 1 if like
    public int getLike(){
        return like;
    }
// 0 if not dislike, 1 if dislike
    public int getDislike(){
        return dislike;
    }

}