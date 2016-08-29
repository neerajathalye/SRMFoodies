package com.neeraj8le.srmfoodies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neeraj8le.srmfoodies.OrderAdapter;
import com.neeraj8le.srmfoodies.R;
import com.neeraj8le.srmfoodies.model.Mapping;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    TextView testView;
    ArrayList<Mapping> tempMapping;
    RecyclerView orderRecyclerView;
    OrderAdapter orderAdapter;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        Bundle bundle = this.getArguments();
        tempMapping = new ArrayList<>();
        tempMapping = bundle.getParcelableArrayList("tempMapping");
        if (tempMapping.size() > 0)
        {
            orderRecyclerView = (RecyclerView) v.findViewById(R.id.order_recycler_view);
            orderAdapter = new OrderAdapter(getContext(), tempMapping);
            orderRecyclerView.setAdapter(orderAdapter);
            orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
            return v;
    }

}
