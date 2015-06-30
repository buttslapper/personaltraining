package com.example.jfk.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JFK on 5/14/2015.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context _context){
        userLocalDatabase = _context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User _user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", _user.name);
        spEditor.putString("username", _user.username);
        spEditor.putString("password", _user.password);
        spEditor.putInt("age", _user.age);
        spEditor.putString("phoneNumber", _user.phoneNumber);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        String password = userLocalDatabase.getString("password", "");
        String username = userLocalDatabase.getString("username", "");
        int age = userLocalDatabase.getInt("age", -1);
        String phoneNumber = userLocalDatabase.getString("phoneNumber", "");

        User storedUser = new User(name,username, password, phoneNumber, age);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false) == true) return true;
        else return false;
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
