package com.neeraj8le.srmfoodies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RestaurantListViewHolder extends RecyclerView.ViewHolder {

    TextView restaurantName, restaurantCuisine, restaurantLocation;
    RelativeLayout restaurantRowContainer;
    public RestaurantListViewHolder(View itemView) {
        super(itemView);

        restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        restaurantCuisine = (TextView) itemView.findViewById(R.id.restaurant_cuisine);
        restaurantLocation = (TextView) itemView.findViewById(R.id.restaurant_location);
        restaurantRowContainer = (RelativeLayout) itemView.findViewById(R.id.restaurant_list_row);
    }
}
