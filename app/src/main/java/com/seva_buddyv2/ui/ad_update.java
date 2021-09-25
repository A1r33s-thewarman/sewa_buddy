package com.seva_buddyv2.ui;

public class ad_update {
    private  String title,location,rate;

    public ad_update(){ }

    public String gettitle(){
        return title;
    }
    public String getlocation(){
        return location;
    }
    public String getrate(){
        return rate;
    }



    public void settitle(String name){this.title = title;}

    public void setlocation(String address){this.location = location;}

    public void setrate(String email){this.rate = rate;}

}
