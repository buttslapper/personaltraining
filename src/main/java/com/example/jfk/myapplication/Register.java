package com.example.jfk.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    TextView tvLoginLink;
    EditText etName, etUsername, etPassword, etAge, etPhoneNumber;
    //CheckBox c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText)findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAge = (EditText) findViewById(R.id.etAge);
        etPhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);
        bRegister = (Button) findViewById(R.id.bRegister);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);

        //Cool. Got a linked list with two checkbox elements in it.
        //Let's set the onclick listener for each element in the linked list.

        /*for(int i = 0; i < checkBoxList.size(); i++){
            checkBoxList.get(i).setOnClickListener(this);
        }*/

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:


                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                //now let's initialize the checkboxes and put them into a LinkedList

                List<CheckBox> checkBoxList = new LinkedList<>();
                checkBoxList.add((CheckBox)findViewById(R.id.checkBox));
                checkBoxList.add((CheckBox)findViewById(R.id.checkBox2));

                if(allFormsFilled()) {
                    //User registeredData = new User(name, username, password, phoneNumber, age);
                    User registeredData = new User(name, username, password, phoneNumber, age, checkBoxList);

                    registerUser(registeredData);

                    break;
                }
                else{
                    repromptRegisterInfo();
                    break;
                }

            case R.id.tvLoginLink:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    //if any of the fields are empty we will return false
    private boolean allFormsFilled() {


        if(etName.getText().toString() == null || etAge.getText().toString() == null || etPassword.getText().toString() == null || etPhoneNumber.getText().toString() == null || etUsername.getText().toString() == null){
            return false;
        }
        return true;
    }


    //creates an alert dialogue that tells user to fill out all fields.
    private void repromptRegisterInfo(){
        AlertDialog.Builder reprompt = new AlertDialog.Builder(Register.this);
        reprompt.setMessage("Please fill out all fields");
        reprompt.setPositiveButton("Okay", null);
        reprompt.show();
    }

    private void registerUser(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}
