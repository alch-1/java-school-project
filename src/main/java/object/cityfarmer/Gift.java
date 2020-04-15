package main.java.object.cityfarmer;

public class Gift {
    private String gift_name;
    private int gift_quantity;
    private String stat;
    private String sender;
    // private String username;

    public Gift(String gift_name,int gift_quantity, String stat, String sender){
        // this.username = username;
        this.gift_name = gift_name;
        this.gift_quantity = gift_quantity;
        this.stat = stat;
        this.sender = sender;
    }
    public String getGiftName(){
        return gift_name;
    }
    public int getGiftQuantity(){
        return gift_quantity;
    }
    public String getStat(){
        return stat;
    }
    public String getSender(){
        return sender;
    }
}