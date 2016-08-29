package com.neeraj8le.srmfoodies.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant implements Parcelable{

    private int id;
    private String name;
    private String description;
    private String address;
    private String cuisine;
    private String timings;
    private String location;
    private int minimumDeliveryAmount;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> deliveryLocations;
    private ArrayList<String> restaurantImages;
    private ArrayList<String> menuImages;
    private boolean active;

    public Restaurant() {
    }

    public Restaurant(int id, String name, String cuisine, String location) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.location = location;
    }

    public Restaurant(int id, String name, String description, String address, String cuisine, String timings, String location, int minimumDeliveryAmount, ArrayList<String> phoneNumbers, ArrayList<String> deliveryLocations, ArrayList<String> restaurantImages, ArrayList<String> menuImages, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.cuisine = cuisine;
        this.timings = timings;
        this.location = location;
        this.minimumDeliveryAmount = minimumDeliveryAmount;
        this.phoneNumbers = phoneNumbers;
        this.deliveryLocations = deliveryLocations;
        this.restaurantImages = restaurantImages;
        this.menuImages = menuImages;
        this.active = active;
    }

    protected Restaurant(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public int getMinimumDeliveryAmount() {
        return minimumDeliveryAmount;
    }

    public void setMinimumDeliveryAmount(int minimumDeliveryAmount) {
        this.minimumDeliveryAmount = minimumDeliveryAmount;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public ArrayList<String> getRestaurantImages() {
        return restaurantImages;
    }

    public void setRestaurantImages(ArrayList<String> restaurantImages) {
        this.restaurantImages = restaurantImages;
    }

    public ArrayList<String> getMenuImages() {
        return menuImages;
    }

    public void setMenuImages(ArrayList<String> menuImages) {
        this.menuImages = menuImages;
    }

    public ArrayList<String> getDeliveryLocations() {
        return deliveryLocations;
    }

    public void setDeliveryLocations(ArrayList<String> deliveryLocations) {
        this.deliveryLocations = deliveryLocations;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        bundle.putString("description", description);
        bundle.putString("address", address);
        bundle.putString("cuisine", cuisine);
        bundle.putString("timings", timings);
        bundle.putString("location", location);
        bundle.putInt("minimumDeliveryAmount", minimumDeliveryAmount);
        bundle.putStringArrayList("phoneNumbers", phoneNumbers);
        bundle.putStringArrayList("deliveryLocations", deliveryLocations);
        bundle.putStringArrayList("restaurantImages", restaurantImages);
        bundle.putStringArrayList("menuImages", menuImages);
        bundle.putBoolean("active", active);

        parcel.writeBundle(bundle);
    }

    public void readFromParcel(Parcel in)
    {
        Bundle bundle = in.readBundle(getClass().getClassLoader());

        id = bundle.getInt("id");
        name = bundle.getString("name");
        description = bundle.getString("description");
        address = bundle.getString("address");
        cuisine = bundle.getString("cuisine");
        timings = bundle.getString("timings");
        location = bundle.getString("location");
        minimumDeliveryAmount = bundle.getInt("minimumDeliveryAmount");
        phoneNumbers = bundle.getStringArrayList("phoneNumbers");
        deliveryLocations = bundle.getStringArrayList("deliveryLocations");
        restaurantImages = bundle.getStringArrayList("restaurantImages");
        menuImages = bundle.getStringArrayList("menuImages");
        active = bundle.getBoolean("active");
    }
}
