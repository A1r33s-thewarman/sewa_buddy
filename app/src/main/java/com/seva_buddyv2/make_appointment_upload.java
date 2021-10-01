package com.seva_buddyv2;

public class make_appointment_upload {
    private  String Date;
    private  String Message;
    private  String Key;
    make_appointment_upload(){

    }
    public  make_appointment_upload(String date,String message,String key){
        Key = key;
        Date = date;
        Message = message;
    }
    public String getdate() {
        return  Date;
    }
    public void setdate(String date) {
        Date = date;
    }
    public String getmessage() {
        return  Message;
    }
    public void setmessage(String message) {
        Message = message;
    }
    public String getKey() {
        return  Key;
    }
    public void setKey(String key) {
        Key = key;
    }

}
