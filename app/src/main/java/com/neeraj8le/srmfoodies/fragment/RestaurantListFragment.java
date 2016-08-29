package com.neeraj8le.srmfoodies.fragment;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.neeraj8le.srmfoodies.CONSTANTS;
import com.neeraj8le.srmfoodies.R;
import com.neeraj8le.srmfoodies.RestaurantListAdapter;
import com.neeraj8le.srmfoodies.model.Customization;
import com.neeraj8le.srmfoodies.model.Mapping;
import com.neeraj8le.srmfoodies.model.MenuItem;
import com.neeraj8le.srmfoodies.model.Restaurant;
import com.neeraj8le.srmfoodies.model.Size;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    RecyclerView restaurantListRecyclerView;
    RestaurantListAdapter restaurantListAdapter;
    ArrayList<Restaurant> restaurantList;
    ArrayList<MenuItem> menuItemArrayList;
    ArrayList<Size> sizeArrayList;
    ArrayList<Customization> customizationArrayList;
    ArrayList<Mapping> mappingArrayList;
    ProgressDialog progressDialog;
    EditText searchBar;
//    DataBaseHandler db;

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        restaurantListRecyclerView = (RecyclerView) view.findViewById(R.id.restaurant_list_recycler_view);
        searchBar = (EditText) view.findViewById(R.id.searchBar);
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setTitle("Please Wait");
//        progressDialog.setMessage("Fetching Restaurant List...");
//        progressDialog.show();
//        getRestaurantList();

        restaurantList = new ArrayList<>();
        menuItemArrayList = new ArrayList<>();
        sizeArrayList = new ArrayList<>();
        customizationArrayList = new ArrayList<>();
        mappingArrayList = new ArrayList<>();

        restaurantList.add(new Restaurant(1, "Disco Chettinad", "Indian, Chinese", "Guduvancheri"));
        restaurantList.add(new Restaurant(2, "Biryani House", "Indian, Chinese", "SRM"));
        restaurantList.add(new Restaurant(3, "Real Food Mall", "Indian, Chinese", "Potheri"));

        menuItemArrayList.add(new MenuItem(1, "Pizza"));
        menuItemArrayList.add(new MenuItem(2, "Burger"));
        menuItemArrayList.add(new MenuItem(3, "Pasta"));
        menuItemArrayList.add(new MenuItem(4, "Pav Bhaji"));
        menuItemArrayList.add(new MenuItem(5, "Dosa"));

        sizeArrayList.add(new Size(1, "Small"));
        sizeArrayList.add(new Size(2, "Medium"));
        sizeArrayList.add(new Size(3, "Large"));
        sizeArrayList.add(new Size(4, "Extra Large"));

        customizationArrayList.add(new Customization(1, "Extra Cheese"));
        customizationArrayList.add(new Customization(2, "Extra Mayo"));
        customizationArrayList.add(new Customization(3, "Extra Onion"));
        customizationArrayList.add(new Customization(4, "Extra Tomato"));

        mappingArrayList.add(new Mapping(1, restaurantList.get(0), menuItemArrayList.get(0), sizeArrayList.get(0), customizationArrayList.get(0), 30, 100));
        mappingArrayList.add(new Mapping(2, restaurantList.get(0), menuItemArrayList.get(0), sizeArrayList.get(0), customizationArrayList.get(2), 20, 200));
        mappingArrayList.add(new Mapping(3, restaurantList.get(0), menuItemArrayList.get(0), sizeArrayList.get(0), customizationArrayList.get(3), 20, 300));

        mappingArrayList.add(new Mapping(4, restaurantList.get(1), menuItemArrayList.get(1), sizeArrayList.get(0), customizationArrayList.get(3), 20, 400));
        mappingArrayList.add(new Mapping(5, restaurantList.get(1), menuItemArrayList.get(0), sizeArrayList.get(0), customizationArrayList.get(3), 20, 500));


        initializeAdapter(restaurantList);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                charSequence = charSequence.toString().toLowerCase();
                if (!charSequence.equals("")) {
                    searchBar.setBackgroundResource(android.R.color.white);
                    searchBar.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                    searchBar.setHintTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        searchBar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, R.drawable.ic_clear_black_24dp, 0);
                    }
                }
                else if(charSequence.equals(""))
                {
                    searchBar.setBackgroundResource(R.color.colorPrimary);
                    searchBar.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    searchBar.setHintTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        searchBar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_white_24dp, 0, R.drawable.ic_clear_white_24dp, 0);
                    }
                }
                final ArrayList<Restaurant> searchedList = new ArrayList<>();
                for(Restaurant restaurant : restaurantList)
                {
                    if(restaurant.getName().toLowerCase().contains(charSequence) || restaurant.getCuisine().toLowerCase().contains(charSequence) || restaurant.getLocation().toLowerCase().contains(charSequence))
                    {
                        searchedList.add(restaurant);
                    }
                }
                initializeAdapter(searchedList);
                restaurantListAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        searchBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (searchBar.getRight() - searchBar.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - searchBar.getPaddingRight())) {
                        // your action here
                        searchBar.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    public void getRestaurantList()
    {
        StringRequest stringRequest = new StringRequest(CONSTANTS.JSON_RESTAURANT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Unable to connect to the server",Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    public void showJSON(String json)
    {
        //db = new DataBaseHandler(getContext());

//        if (db.getRestaurantsCount() > 0) {
//            for (int i = 0; i < db.getRestaurantsCount(); i++) {
//                db.deleteRestaurant(restaurantList.get(i));
//            }
//        }

        JSONObject jsonObject;
        JSONArray jsonArray;
        try {
            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray(CONSTANTS.JSON_ARRAY);
            restaurantList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                Restaurant rest = new Restaurant(object.optInt(CONSTANTS.KEY_RESTAURANT_ID), object.optString(CONSTANTS.KEY_RESTAURANT_NAME), object.optString(CONSTANTS.KEY_RESTAURANT_CUISINE), object.optString(CONSTANTS.KEY_RESTAURANT_LOCATION));
                restaurantList.add(rest);
                //db.addRestaurant(rest);
            }
            initializeAdapter(restaurantList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initializeAdapter(ArrayList<Restaurant> restaurantList)
    {
        restaurantListAdapter = new RestaurantListAdapter(getContext(), restaurantList, menuItemArrayList, sizeArrayList, customizationArrayList, mappingArrayList);
        restaurantListRecyclerView.setAdapter(restaurantListAdapter);
        restaurantListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
