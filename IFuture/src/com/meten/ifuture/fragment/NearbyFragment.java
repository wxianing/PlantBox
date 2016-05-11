package com.meten.ifuture.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meten.ifuture.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {


    public NearbyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        return view;
    }
}
