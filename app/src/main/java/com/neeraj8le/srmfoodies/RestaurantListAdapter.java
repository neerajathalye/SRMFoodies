package com.neeraj8le.srmfoodies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neeraj8le.srmfoodies.model.Customization;
import com.neeraj8le.srmfoodies.model.Mapping;
import com.neeraj8le.srmfoodies.model.MenuItem;
import com.neeraj8le.srmfoodies.model.Restaurant;
import com.neeraj8le.srmfoodies.model.Size;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListViewHolder> {

    Context context;
    ArrayList<Restaurant> restaurantList;
    ArrayList<MenuItem> menuItemArrayList;
    ArrayList<Size> sizeArrayList;
    ArrayList<Customization> customizationArrayList;
    ArrayList<Mapping> mappingArrayList, tempMapping;

    public RestaurantListAdapter(Context context, ArrayList<Restaurant> restaurantList, ArrayList<MenuItem> menuItemArrayList, ArrayList<Size> sizeArrayList, ArrayList<Customization> customizationArrayList, ArrayList<Mapping> mappingArrayList) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.menuItemArrayList = menuItemArrayList;
        this.sizeArrayList = sizeArrayList;
        this.customizationArrayList = customizationArrayList;
        this.mappingArrayList = mappingArrayList;
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

                tempMapping = new ArrayList<>();

                for(Mapping mapping : mappingArrayList)
                {
                        if(mapping.getRestaurant().getId() == restaurantList.get(holder.getAdapterPosition()).getId())
                        {
                            tempMapping.add(mapping);
                            //Toast.makeText(context, mapping.getId()+"", Toast.LENGTH_SHORT).show();
                        }
                }


                Intent intent = new Intent(context, RestaurantPageActivity.class);
                intent.putExtra("restaurantName", restaurantList.get(holder.getAdapterPosition()).getName());

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("tempMapping", tempMapping);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
