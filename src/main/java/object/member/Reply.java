package main.java.object.member;
public class Reply{
    private int post_id;
    private int reply_id;
    private String timeStamp;
    private String friendUsername;
    private String content;

    public Reply(int p,int r,String ts,String fu,String c){
        post_id = p;
        reply_id = r;
        timeStamp = ts;
        friendUsername = fu;
        content = c;
    }

    public int getPostID(){
        return post_id;
    }

    public int getReplyID(){
        return reply_id;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getFriendUserName(){
        return friendUsername;
    }

    public String getContent(){
        return content;
    }
}