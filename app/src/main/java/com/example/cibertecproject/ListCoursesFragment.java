package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCoursesFragment extends Fragment {


    public ListCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_courses, container, false);
        return rootView;
    }

    public static ListCoursesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ListCoursesFragment fragment = new ListCoursesFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
