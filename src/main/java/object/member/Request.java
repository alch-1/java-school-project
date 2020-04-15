package main.java.object.member;
public class Request{
    private String uname;
    private String reqUName;

    public Request(String u,String r){
        uname = u;
        reqUName = r;
    }

    public String getUsername(){
        return uname;
    }

    public String getReqUsername(){
        return reqUName;
    }

}