package com.seva_buddyv2;

public class Uploads {
    private String Title;
    private String Location;
    private String H_rate;
    private String Rate;
    private  String Key;
    private String Image_url;
    private  String date;
    private  String message;
    private String desc;

    public Uploads(){}
    public  Uploads(String title,String location,String h_rate,String image_url,String rate,String key){
        Title = title;
        Key = key;
        Location = location;
        H_rate = h_rate;
        Rate = rate;
        Image_url = image_url;
    }

    public String getrate(){
        return  Rate;
    }
    public void setrate(String rate){
        Rate = rate;
    }

    public String gettitle(){
        return  Title;
    }
    public void settitle(String title){
        Title = title;
    }

    public String getlocation(){
        return  Location;
    }
    public void setlocation(String location){
        Location = location;
    }

    public String geth_rate(){
        return  H_rate;
    }
    public void seth_rate(String h_rate){
        H_rate = h_rate;
    }
    public String getimage_url(){
        return  Image_url;
    }
    public void setimage_url(String image_url){
        Image_url = image_url;
    }

    public String getKey() {
        return  Key;
    }
    public void setKey(String key) {
        Key = key;
    }

    public void setdate(String date) {
        date = date;
    }

    public void setmessage(String message) {
        message = message;
    }

    public String getdesc() {
        return  desc;
    }
}
