package com.example.jfk.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class    MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button bLogout;
    EditText etName, etUsername, etAge, etPhoneNumber;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(authenticate()){
            displayUserDetails();
        }
        else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etName.setText(user.name);
        etPhoneNumber.setText(user.phoneNumber);//referencing null object
        etAge.setText(user.age+"");
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
        }
    }
}