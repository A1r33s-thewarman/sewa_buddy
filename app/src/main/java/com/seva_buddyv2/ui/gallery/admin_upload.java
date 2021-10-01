package com.seva_buddyv2.ui.gallery;

public class admin_upload {
    private  String Name,Address,Email,Phone,nic;


    public admin_upload(){}
    public  admin_upload(String name,String address,String email,String phone){
        Name = name;
        Address = address;
        Email = email;
        Phone = phone;

    }

    public String getname(){
        return  Name;
    }
    public void setname(String name){
        Name = Name;
    }

    public String getaddress(){
        return  Address;
    }
    public void setaddress(String address){
        Address = address;
    }

    public String getemail(){
        return  Email;
    }
    public void setemail(String email){
        Email = email;
    }

    public String getphone(){
        return  Phone;
    }
    public void setphone(String phone){
        Phone = phone;
    }

}



