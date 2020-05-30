package com.example.addressbook;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Data implements Parcelable {

    public String FirstName;
    public String LastName;
    public String Email;
    public String PhoneNum;
    public String Address;

    public Data() {

    }

    public Data(String FirstName, String LastName, String Email, String PhoneNum, String Address) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.Address = Address;
    }


    protected Data(Parcel in) {
        FirstName = in.readString();
        LastName = in.readString();
        Email = in.readString();
        PhoneNum = in.readString();
        Address = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(PhoneNum);
        dest.writeString(Address);
    }
}