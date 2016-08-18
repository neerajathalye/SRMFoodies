package com.neeraj8le.srmfoodies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neeraj8le.srmfoodies.model.Restaurant;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListViewHolder> {

    Context context;
    ArrayList<Restaurant> restaurantList;

    public RestaurantListAdapter(Context context, ArrayList<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_list_row, parent, false);
        return new RestaurantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RestaurantListViewHolder holder, int position) {
        holder.restaurantName.setText(restaurantList.get(position).getName());
        holder.restaurantCuisine.setText(restaurantList.get(position).getCuisine());
        holder.restaurantLocation.setText(restaurantList.get(position).getLocation());
        holder.restaurantRowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RestaurantPageActivity.class);
                intent.putExtra("restaurantName", restaurantList.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
