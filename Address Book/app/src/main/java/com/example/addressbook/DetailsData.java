package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsData extends AppCompatActivity {

    EditText firstName, lastName, email, phoneNum, address;
    public myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_data);
        helper = new myDbAdapter(this);
        firstName = (EditText) findViewById(R.id.FirstName1);
        lastName = (EditText) findViewById(R.id.LastName1);
        email = (EditText) findViewById(R.id.Email1);
        phoneNum = (EditText) findViewById(R.id.PhoneNum1);
        address = (EditText) findViewById(R.id.Address1);

        Data obj =getIntent().getExtras().getParcelable("obj");
        String FirstName = obj.FirstName;
        String LastName = obj.LastName;
        String Email = obj.Email;
        String PhoneNum = obj.PhoneNum;
        String Address = obj.Address;

        firstName.setText(FirstName);
        lastName.setText(LastName);
        email.setText(Email);
        phoneNum.setText(PhoneNum);
        address.setText(Address);
    }

    public void Update(View v) {
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
            long a= helper.updateData( u1, u2, u3, u4, u5);
            if(a<=0)
            {
                Toast.makeText(getApplicationContext(), "Unsuccessful" , Toast.LENGTH_SHORT).show();
            } else {
                String info = " "+ u1 +" Updated!!!";
                Toast.makeText(getApplicationContext(), info , Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
                startActivity(myIntent);
            }
        }
    }

    public void Delete (View v) {
        String u1 = firstName.getText().toString();
        int a =helper.delete(u1);
        if(a<=0)
        {
            Toast.makeText(getApplicationContext(), "Unsuccessful" , Toast.LENGTH_SHORT).show();
        } else {
            String info = " "+ u1 +" Deleted!!!";
            Toast.makeText(getApplicationContext(), info , Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
            startActivity(myIntent);
        }
    }
}
