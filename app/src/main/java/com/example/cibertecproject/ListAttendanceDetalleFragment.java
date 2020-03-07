package com.example.cibertecproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cibertecproject.Adapters.AdapterAttendanceDetalle;
import com.example.cibertecproject.Modelo.Attendance;

import java.util.ArrayList;
import java.util.List;

public class ListAttendanceDetalleFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterAttendanceDetalle adapterAttendanceDetalle;
    private List<Attendance> lstAttendance;
    private TextView txtv_total_inscription;
    private Button btnGrabarAsistencia;

    public ListAttendanceDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_list_attendance_detalle, container, false);
        inicializarControles(view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
        txtv_total_inscription=view.findViewById(R.id.txtv_total_inscription);
        btnGrabarAsistencia=view.findViewById(R.id.btnGrabarAsistencia);
        btnGrabarAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Grabado Correctamente la Asistencia",Toast.LENGTH_SHORT).show();
            }
        });
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

        attendance=new Attendance();
        attendance.setApePaterno("Sanchez");
        attendance.setApeMaterno("Jimenez");
        attendance.setNombres("Jose Abelardo");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Ortiz");
        attendance.setApeMaterno("Figueroa");
        attendance.setNombres("Omar Javier");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Alarcon");
        attendance.setApeMaterno("Lopez");
        attendance.setNombres("Marcos");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Jimenez");
        attendance.setApeMaterno("Sanchez");
        attendance.setNombres("Rodolfo");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Lopez");
        attendance.setApeMaterno("Gamarra");
        attendance.setNombres("Santiago");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Solis");
        attendance.setApeMaterno("Trujillo");
        attendance.setNombres("Jesus");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Ortiz");
        attendance.setApeMaterno("Melgarejo");
        attendance.setNombres("Reynol");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Suarez");
        attendance.setApeMaterno("Lopez");
        attendance.setNombres("Williams");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Rivera");
        attendance.setApeMaterno("Salinas");
        attendance.setNombres("Alex");
        lstAttendance.add(attendance);

        attendance=new Attendance();
        attendance.setApePaterno("Camones");
        attendance.setApeMaterno("Sanchez");
        attendance.setNombres("Alexis");
        lstAttendance.add(attendance);

        adapterAttendanceDetalle=new AdapterAttendanceDetalle(lstAttendance,getContext());
        recyclerView.setAdapter(adapterAttendanceDetalle);
        txtv_total_inscription.setText("Total de alumnos inscritos en el Curso : " + String.valueOf(adapterAttendanceDetalle.getItemCount()));
    }


}
