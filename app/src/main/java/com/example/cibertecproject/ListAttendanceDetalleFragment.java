package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cibertecproject.Adapters.AdapterAttendanceDetalle;
import com.example.cibertecproject.Modelo.Attendance;

import java.util.ArrayList;
import java.util.List;

public class ListAttendanceDetalleFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterAttendanceDetalle adapterAttendanceDetalle;
    private List<Attendance> lstAttendance;

    public ListAttendanceDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_list_attendance_detalle, container, false);
        inicializarControles(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AddAttendance();
        return view;

    }

    public static ListAttendanceDetalleFragment newInstance() {
        Bundle args = new Bundle();
        ListAttendanceDetalleFragment fragment = new ListAttendanceDetalleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void inicializarControles(View view) {
        recyclerView=view.findViewById(R.id.recyclerviewAttendanceListDetalle);
        lstAttendance=new ArrayList<>();
    }

    private void AddAttendance() {
        Attendance attendance=new Attendance();
        attendance.setApePaterno("Solis");
        attendance.setApeMaterno("Navarro");
        attendance.setNombres("Luis Angel");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Gamarra");
        attendance.setApeMaterno("Sanchez");
        attendance.setNombres("Juan Fernando");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Lopez");
        attendance.setApeMaterno("Torrez");
        attendance.setNombres("Pablo Eduardo");
        lstAttendance.add(attendance);

        adapterAttendanceDetalle=new AdapterAttendanceDetalle(lstAttendance,getContext());
        recyclerView.setAdapter(adapterAttendanceDetalle);
    }


}
