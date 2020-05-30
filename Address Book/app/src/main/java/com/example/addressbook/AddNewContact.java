package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContact extends AppCompatActivity {

    EditText firstName, lastName, email, phoneNum, address;
    public myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        helper = new myDbAdapter(this);
        firstName = (EditText) findViewById(R.id.FirstName);
        lastName = (EditText) findViewById(R.id.LastName);
        email = (EditText) findViewById(R.id.Email);
        phoneNum = (EditText) findViewById(R.id.PhoneNum);
        address = (EditText) findViewById(R.id.Address);
    }

    public void Add(View v) {
        String u1 = firstName.getText().toString();
        String u2 = lastName.getText().toString();
        String u3 = email.getText().toString();
        String u4 = phoneNum.getText().toString();
        String u5 = address.getText().toString();

        if(u1.isEmpty() || u2.isEmpty() || u3.isEmpty() || u4.isEmpty() || u5.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter Complete Data" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            long a= helper.addData( u1, u2, u3, u4, u5);
            if(a<=0)
            {
                Toast.makeText(getApplicationContext(), "Unsuccessful" , Toast.LENGTH_SHORT).show();
            } else {
                String info = " "+ u1 +" Added!!!";
                Toast.makeText(getApplicationContext(), info , Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
                startActivity(myIntent);
            }
        }
    }
}
