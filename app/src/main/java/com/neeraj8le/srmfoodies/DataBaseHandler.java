package com.neeraj8le.srmfoodies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.neeraj8le.srmfoodies.model.Restaurant;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, CONSTANTS.DATABASE_NAME, null, CONSTANTS.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + CONSTANTS.TABLE_NAME + "("
                + CONSTANTS.KEY_RESTAURANT_ID + " INTEGER PRIMARY KEY, "
                + CONSTANTS.KEY_RESTAURANT_NAME + " TEXT, "
                + CONSTANTS.KEY_RESTAURANT_CUISINE + " TEXT, "
                + CONSTANTS.KEY_RESTAURANT_LOCATION + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + CONSTANTS.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    // Adding new restaurant
    public void addRestaurant(Restaurant restaurant)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONSTANTS.KEY_RESTAURANT_ID, restaurant.getId());
        values.put(CONSTANTS.KEY_RESTAURANT_NAME, restaurant.getName());
        values.put(CONSTANTS.KEY_RESTAURANT_CUISINE, restaurant.getCuisine());
        values.put(CONSTANTS.KEY_RESTAURANT_LOCATION, restaurant.getLocation());

        db.insert(CONSTANTS.TABLE_NAME, null, values);
        db.close();
    }

    // Getting All restaurants
    public ArrayList<Restaurant> getAllRestaurants()
    {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CONSTANTS.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant();

                restaurant.setId(Integer.parseInt(cursor.getString(0)));
                restaurant.setName(cursor.getString(1));
                restaurant.setCuisine(cursor.getString(2));
                restaurant.setLocation(cursor.getString(3));

                // Adding restaurant to list
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return restaurant list
        return restaurants;
    }

    // Getting restaurant Count
    public int getRestaurantsCount()
    {
        String countQuery = "SELECT  * FROM " + CONSTANTS.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // Deleting single restaurant
    public void deleteRestaurant(Restaurant restaurant)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONSTANTS.TABLE_NAME, CONSTANTS.KEY_RESTAURANT_ID + " = ?",
                new String[] { String.valueOf(restaurant.getId()) });
        db.close();
    }


}
