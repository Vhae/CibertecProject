package com.example.cibertecproject;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cibertecproject.Adapters.AdapterAttendance;
import com.example.cibertecproject.Modelo.UserEvents;

import java.util.ArrayList;
import java.util.List;

public class ListAttendanceFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterAttendance adapterAttendance;
    private List<UserEvents> lstUserEvents;

    public ListAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_list_attendance, container, false);
        inicializarControles(rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AddList();
        return rootView;
    }

    public static ListAttendanceFragment newInstance() {
        Bundle args = new Bundle();
        ListAttendanceFragment fragment = new ListAttendanceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void inicializarControles(View view) {
        recyclerView=view.findViewById(R.id.recyclerviewAttendanceList);
        lstUserEvents=new ArrayList<>();
    }
    private void AddList() {
        UserEvents modelEvents=new UserEvents();
        modelEvents.setId(1);
        modelEvents.setNombres("Programación Android");
        modelEvents.setNombcurso("Android Intermedio");
        modelEvents.setFecha("10/03/2020");
        lstUserEvents.add(modelEvents);

        modelEvents=new UserEvents();
        modelEvents.setId(2);
        modelEvents.setNombres("Diseño de Interiores");
        modelEvents.setNombcurso("Autocat");
        modelEvents.setFecha("15/03/2020");
        lstUserEvents.add(modelEvents);

        modelEvents=new UserEvents();
        modelEvents.setId(3);
        modelEvents.setNombres("Programación Orientada a Objeto ");
        modelEvents.setNombcurso("UML");
        modelEvents.setFecha("15/04/2020");
        lstUserEvents.add(modelEvents);

        modelEvents=new UserEvents();
        modelEvents.setId(4);
        modelEvents.setNombres("Motor de Bases de Datos ");
        modelEvents.setNombcurso("Fundamentos de Oracle");
        modelEvents.setFecha("15/06/2020");
        lstUserEvents.add(modelEvents);

        modelEvents=new UserEvents();
        modelEvents.setId(5);
        modelEvents.setNombres("Modelado de Datos ");
        modelEvents.setNombcurso("UML");
        modelEvents.setFecha("15/06/2020");
        lstUserEvents.add(modelEvents);

        modelEvents=new UserEvents();
        modelEvents.setId(6);
        modelEvents.setNombres("Programación Visual Studio.NET");
        modelEvents.setNombcurso("C# Avanzado");
        modelEvents.setFecha("15/07/2020");
        lstUserEvents.add(modelEvents);
        adapterAttendance=new AdapterAttendance(lstUserEvents,getContext());
        recyclerView.setAdapter(adapterAttendance);


    }


}
