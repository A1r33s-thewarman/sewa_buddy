package com.seva_buddyv2;

public class p_Upload {
    String Title;
    String Img_url;
    String Location;
    String Desc;
    String Price;

    public p_Upload() {
        //empty constructor needed
    }
    public p_Upload(String title, String img_url ,String location,String desc,String price) {

        Title = title;
        Img_url = img_url;
        Location = location;
        Desc = desc;
        Price = price;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getImg_url() {
        return Img_url;
    }
    public void setImg_url(String img_url) {
        Img_url = img_url;
    }

    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }

    public String getDesc() {
        return Desc;
    }
    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPrice() {
        return Price;
    }
    public void setPrice(String price) {
        Price = price;
    }

















}
