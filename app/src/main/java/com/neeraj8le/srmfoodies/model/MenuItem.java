package com.neeraj8le.srmfoodies.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {

    private int id;

    private String name;

    public MenuItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MenuItem() {
    }

    protected MenuItem(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name", name);
        parcel.writeBundle(bundle);
    }

    public void readFromParcel(Parcel in)
    {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        id = bundle.getInt("id");
        name = bundle.getString("name");
    }
}
