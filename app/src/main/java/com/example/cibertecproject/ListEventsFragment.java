package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cibertecproject.Modelo.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEventsFragment extends Fragment {
    RecyclerView recyclerviewEventList;
    ArrayList<Event> testList=new ArrayList<Event>();

    public ListEventsFragment() {
        // Required empty public constructor
        //
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_events, container, false);

        Event Item1=new Event("Asesoria de JavaScript","En este evento se va estudiar acerca de javascript");
        testList.add(Item1);
        Event Item2=new Event("creando nuestra primera app con Android","En este evento se va repasar elementos basicos de android");
        testList.add(Item2);
        recyclerviewEventList=rootView.findViewById(R.id.recyclerviewEventList);
        recyclerviewEventList.setAdapter(new EventListAdapter(testList));
        LinearLayoutManager layoutManager=new LinearLayoutManager(rootView.getContext());
        recyclerviewEventList.setLayoutManager(layoutManager);

        return rootView;
    }

    public static ListEventsFragment newInstance() {

        Bundle args = new Bundle();

        ListEventsFragment fragment = new ListEventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
