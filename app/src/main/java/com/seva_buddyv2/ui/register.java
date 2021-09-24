package com.seva_buddyv2.ui;

public class register {
    private  String name,address,email,phone,type;

    public register(){ }

    public String getname(){
        return name;
    }
    public String getaddress(){
        return address;
    }
    public String getemail(){
        return email;
    }
    public String getphone(){
        return phone;
    }
    public String gettype(){
        return type;
    }


    public void setname(String name){this.name = name;}

    public void setaddress(String address){this.address = address;}

    public void setemail(String email){this.email = email;}

    public void setphone(String phone){this.phone = phone;}

    public void settype(String type){this.type = type;}
}
