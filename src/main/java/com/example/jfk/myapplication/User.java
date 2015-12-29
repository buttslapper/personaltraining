package com.example.jfk.myapplication;

import android.widget.CheckBox;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JFK on 5/14/2015.
 */
public class User {

    String name, username, password, phoneNumber;
    int age;
    List<CheckBox> medicalHistory;

    //constructor
    public User(String _name, String _username, String _password, String _phoneNumber, int _age){
        name = _name;
        username = _username;
        password = _password;
        phoneNumber = _phoneNumber;
        age = _age;
    }

    //overloaded constructor with linkedList
    public User(String _name, String _username, String _password, String _phoneNumber, int _age, List<CheckBox> _medicalHistory){
        name = _name;
        username = _username;
        password = _password;
        phoneNumber = _phoneNumber;
        age = _age;
        medicalHistory = _medicalHistory;
    }
}
