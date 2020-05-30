package com.example.addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<Data> {

    public DataAdapter(Context context, int res, ArrayList<Data> users) {
        super(context, res, users);
    }

    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        TextView FirstName = (TextView) convertView.findViewById(R.id.firstname);
        FirstName.setText(user.FirstName);
        return convertView;
    }

}