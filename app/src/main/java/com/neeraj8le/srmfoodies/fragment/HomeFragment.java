package com.neeraj8le.srmfoodies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.neeraj8le.srmfoodies.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    Button srmButton, potheriButton, guduvancheriButton;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        srmButton = (Button) v.findViewById(R.id.srm_button);
        potheriButton = (Button) v.findViewById(R.id.potheri_button);
        guduvancheriButton = (Button) v.findViewById(R.id.guduvancheri_button);

        srmButton.setOnClickListener(this);
        potheriButton.setOnClickListener(this);
        guduvancheriButton.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.srm_button:
                Toast.makeText(getContext(), "srm", Toast.LENGTH_SHORT).show();
                break;
            case R.id.potheri_button:
                Toast.makeText(getContext(), "potheri", Toast.LENGTH_SHORT).show();
                break;
            case R.id.guduvancheri_button:
                Toast.makeText(getContext(), "guduvancheri", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
