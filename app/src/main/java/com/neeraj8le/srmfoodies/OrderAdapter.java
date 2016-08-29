package com.neeraj8le.srmfoodies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neeraj8le.srmfoodies.model.Mapping;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    Context context;
    ArrayList<Mapping> mappings;

    public OrderAdapter(Context context, ArrayList<Mapping> mappings) {
        this.context = context;
        this.mappings = mappings;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_row, parent, false);

        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.menuItemName.setText(mappings.get(position).getMenuItem().getName());
        holder.menuItemPrice.setText("₹" + String.valueOf(mappings.get(position).getBasePrice()));
    }

    @Override
    public int getItemCount() {
        return mappings.size();
    }
}
