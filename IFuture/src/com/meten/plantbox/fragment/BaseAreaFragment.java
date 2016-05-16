package com.meten.plantbox.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meten.plantbox.R;

/**
 * A simple {@link Fragment} subclass.
 * 基地
 */
public class BaseAreaFragment extends Fragment {


    public BaseAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_area, container, false);
    }

}
