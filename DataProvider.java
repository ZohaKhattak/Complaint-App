package com.example.complaint_app;

public class DataProvider {

    private  String fullname;
    private  String username;
    private  String email;
    private  String phone;
    private  String password;

    public DataProvider(String fullname, String usernaem, String email, String phone, String password) {
    this.fullname=fullname;
    this.username=usernaem;
    this.email=email;
    this.phone=phone;
    this.password=password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
