package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListExpositorsFragment extends Fragment {


    public ListExpositorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_expositors, container, false);
        return rootView;
    }

    public static ListExpositorsFragment newInstance() {

        Bundle args = new Bundle();

        ListExpositorsFragment fragment = new ListExpositorsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
