package com.example.huangyiting.dreamtogether.ui.fragment;

import android.app.Activity;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangyiting.dreamtogether.R;


public class BackGroudSetFragment extends Fragment {


    public BackGroudSetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_back_groud_set, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}

