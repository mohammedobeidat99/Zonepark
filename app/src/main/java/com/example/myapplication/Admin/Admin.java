package com.example.myapplication.Admin;

public class Admin {
    public  String Firstname,Lastname,Email,Password;

    public Admin(String firstname, String email, String password){

        this.Firstname=firstname;
       // this.lastname=lastname;
        this.Email=email;
        this.Password=password;
    }

    public Admin(String firstname, String email, String lastname, String password){
        this.Firstname=firstname;
        this.Lastname=lastname;
        this.Email=email;
        this.Password=password;
    }



}
