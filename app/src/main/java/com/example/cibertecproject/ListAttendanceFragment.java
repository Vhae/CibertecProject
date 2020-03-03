package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAttendanceFragment extends Fragment {


    public ListAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_attendance, container, false);
        return rootView;
    }

    public static ListAttendanceFragment newInstance() {
        
        Bundle args = new Bundle();
        ListAttendanceFragment fragment = new ListAttendanceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
