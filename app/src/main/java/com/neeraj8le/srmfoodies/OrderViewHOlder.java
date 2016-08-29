package com.neeraj8le.srmfoodies;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView menuItemName, menuItemPrice, menuItemDescription;
    Button addButton;

    public OrderViewHolder(View itemView) {
        super(itemView);
        menuItemName = (TextView) itemView.findViewById(R.id.menu_item_name);
        menuItemPrice = (TextView) itemView.findViewById(R.id.menu_item_price);
        menuItemDescription = (TextView) itemView.findViewById(R.id.menu_item_description);
        addButton = (Button) itemView.findViewById(R.id.add_button);
    }
}
