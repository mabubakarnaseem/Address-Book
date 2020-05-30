package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public myDbAdapter helper;
    ArrayList<Data> list = new ArrayList();
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new myDbAdapter(this);
        lv = (ListView) findViewById(R.id.list_view);
        list = helper.getList();
        DataAdapter adapter = new DataAdapter(this, R.layout.item_user, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data obj = (Data) lv.getAdapter().getItem(position);
                Intent intent = new Intent(getBaseContext(), DetailsData.class);
                intent.putExtra("obj", obj);
                startActivity(intent);
            }
        });
    }

    public void addNewContact(View v) {
        Intent myIntent = new Intent(getBaseContext(),   AddNewContact.class);
        startActivity(myIntent);
    }
}
