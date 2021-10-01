package com.seva_buddyv2;

public class p_Upload {
    String Title;
    String Image_url;
    String Location;
    String Desc;
    String H_rate;
    String  Rate;

    public p_Upload() {
        //empty constructor needed
    }
    public p_Upload(String title, String image_url ,String location,String desc,String h_rate,String rate) {

        Title = title;
        Image_url = image_url;
        Location = location;
        Desc = desc;
        H_rate = h_rate;
        Rate = rate;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getImage_url() {
        return Image_url;
    }
    public void setImage_url(String image_url) {
        Image_url = image_url;
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

    public String getH_rate() {
        return H_rate;
    }
    public void setH_rate(String h_rate) {
        H_rate = h_rate;
    }

    public String getRate() {
        return Rate;
    }
    public void setRate(String rate) {
        Rate = rate;
    }
















}
