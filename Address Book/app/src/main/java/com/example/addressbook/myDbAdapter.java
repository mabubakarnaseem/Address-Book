package com.example.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long addData(String FirstName, String LastName, String Email, String PhoneNum, String Address) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.FirstName, FirstName);
        contentValues.put(myDbHelper.LastName, LastName);
        contentValues.put(myDbHelper.Email, Email);
        contentValues.put(myDbHelper.PhoneNum, PhoneNum);
        contentValues.put(myDbHelper.Address, Address);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public ArrayList getList() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<Data> nameList = new ArrayList();
        String[] columns = {myDbHelper.FirstName, myDbHelper.LastName, myDbHelper.Email, myDbHelper.PhoneNum, myDbHelper.Address};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String FirstName = cursor.getString(cursor.getColumnIndex(myDbHelper.FirstName));
            String LastName = cursor.getString(cursor.getColumnIndex(myDbHelper.LastName));
            String Email = cursor.getString(cursor.getColumnIndex(myDbHelper.Email));
            String PhoneNum = cursor.getString(cursor.getColumnIndex(myDbHelper.PhoneNum));
            String Address = cursor.getString(cursor.getColumnIndex(myDbHelper.Address));
            Data bo = new Data(FirstName, LastName, Email, PhoneNum, Address);
            nameList.add(bo);
        }
        return nameList;
    }

    public  int delete(String FirstName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={FirstName};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.FirstName+" = ?",whereArgs);
        return  count;
    }

    public int updateData(String FirstName, String LastName, String Email, String PhoneNum, String Address)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.FirstName, FirstName);
        contentValues.put(myDbHelper.LastName, LastName);
        contentValues.put(myDbHelper.Email, Email);
        contentValues.put(myDbHelper.PhoneNum, PhoneNum);
        contentValues.put(myDbHelper.Address, Address);
        String[] whereArgs= {FirstName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.FirstName+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String FirstName = "FirstName";
        private static final String LastName= "LastName";
        private static final String Email = "Email";
        private static final String PhoneNum = "PhoneNum";
        private static final String Address = "Address";




        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FirstName+" VARCHAR(255), "+LastName+" VARCHAR(225), "+Email+" VARCHAR(255), "+PhoneNum+" VARCHAR(255), "+Address+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "OnUpgrade", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }
    }
}