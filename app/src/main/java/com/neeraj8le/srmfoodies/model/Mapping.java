package com.neeraj8le.srmfoodies.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Mapping implements Parcelable{


    private int id;

    private Restaurant restaurant;

    private MenuItem menuItem;

    private Size size;

    private Customization customization;

    private int customizationPrice;

    private int basePrice;


    public Mapping(int id, Restaurant restaurant, MenuItem menuItem, Size size, Customization customization, int customizationPrice, int basePrice) {
        this.id = id;
        this.restaurant = restaurant;
        this.menuItem = menuItem;
        this.size = size;
        this.customization = customization;
        this.customizationPrice = customizationPrice;
        this.basePrice = basePrice;
    }

    public Mapping() {
    }

    protected Mapping(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Mapping> CREATOR = new Creator<Mapping>() {
        @Override
        public Mapping createFromParcel(Parcel in) {
            return new Mapping(in);
        }

        @Override
        public Mapping[] newArray(int size) {
            return new Mapping[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Customization getCustomization() {
        return customization;
    }

    public void setCustomization(Customization customization) {
        this.customization = customization;
    }

    public int getCustomizationPrice() {
        return customizationPrice;
    }

    public void setCustomizationPrice(int customizationPrice) {
        this.customizationPrice = customizationPrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putParcelable("restaurant", restaurant);
        bundle.putParcelable("menuItem", menuItem);
        bundle.putParcelable("size", size);
        bundle.putParcelable("customization", customization);
        bundle.putInt("customizationPrice", customizationPrice);
        bundle.putInt("basePrice", basePrice);
        parcel.writeBundle(bundle);
    }

    public void readFromParcel(Parcel in)
    {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        id = bundle.getInt("id");
        restaurant = bundle.getParcelable("restaurant");
        menuItem = bundle.getParcelable("menuItem");
        size = bundle.getParcelable("size");
        customization = bundle.getParcelable("customization");
        customizationPrice = bundle.getInt("customizationPrice");
        basePrice = bundle.getInt("basePrice");
    }

}
