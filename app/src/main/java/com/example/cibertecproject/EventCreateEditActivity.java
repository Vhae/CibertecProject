package com.example.cibertecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cibertecproject.Modelo.Event;
import com.example.cibertecproject.Modelo.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventCreateEditActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnmapa, btnfecha, btnhora;
    double latitud, longitud;
    private EditText txtlatitud, txtlongitud,txtfecha, txthorainicial, txthorafinal;
    private Spinner spinnerpersona, spinnercurso;
    RecyclerView mRecyclerViewcursopersona;
    RecyclerView.LayoutManager layoutManager;
    RecyclerCursoPersona adapter;
    ArrayList<Evento> Listaevento=new ArrayList<Evento>();
    private ArrayAdapter<Evento> adapterpersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create_edit);
        inicializarControles();

    }


    private void inicializarControles()
    {
        btnmapa = findViewById(R.id.btnmapa);
        txtlatitud = findViewById(R.id.txtlatitud);
        txtlongitud = findViewById(R.id.txtlongitud);
        btnfecha = findViewById(R.id.btnfecha);
        btnhora = findViewById(R.id.btnhora);
        txtfecha = findViewById(R.id.txtfecha);
        txthorainicial = findViewById(R.id.txthorainicial);
        txthorafinal = findViewById(R.id.txthorafinal);
        btnmapa.setOnClickListener(this);
        btnfecha.setOnClickListener(this);
        btnhora.setOnClickListener(this);
        Bundle datos = this.getIntent().getExtras();
        if(datos != null)
        {
            latitud = getIntent().getExtras().getDouble("latitud");
            longitud = getIntent().getExtras().getDouble("longitud");
            txtlatitud.setText(String.valueOf(latitud));
            txtlongitud.setText(String.valueOf(longitud));
        }


        Evento Item1=new Evento(0,"a","b",
            "c","d","e","f","Luis","Android");
        Listaevento.add(Item1);
        Evento Item2=new Evento(1,"a","b",
                "c","d","e","f","Andre","Algoritmo");
        Listaevento.add(Item2);
        Evento Item3=new Evento(2,"a","b",
                "c","d","e","f","Juan","Base Datos");
        Listaevento.add(Item3);


        mRecyclerViewcursopersona = findViewById(R.id.recyclerViewCP);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new RecyclerCursoPersona(Listaevento);
        mRecyclerViewcursopersona.setHasFixedSize(true);
        mRecyclerViewcursopersona.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewcursopersona.setLayoutManager(layoutManager);
        mRecyclerViewcursopersona.setAdapter(adapter);

        spinnerpersona = findViewById(R.id.spinnerpersona);
        spinnercurso = findViewById(R.id.spinnercurso);


        adapterpersona = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        Evento Item4=new Evento(2,"a","b",
                "c","d","e","f","Juan","Base Datos");
        adapterpersona.add(Item4);
        spinnercurso.setAdapter(adapterpersona);

        String[] lenguajes = {"   Luis","Andre","Juan"};
        spinnerpersona.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,lenguajes));

    }


    private int dia,mes,año,hora,minutos, horaampm;
    String am_pm = "";
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnmapa:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnfecha:
                final Calendar c= Calendar.getInstance();
                dia=c.get(Calendar.DAY_OF_MONTH);
                mes=c.get(Calendar.MONTH);
                año=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtfecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,dia,mes,año);
                datePickerDialog.show();
                break;
            case R.id.btnhora:
                final Calendar ca= Calendar.getInstance();
                hora=ca.get(Calendar.HOUR_OF_DAY);
                minutos=ca.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialogfinal = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay==0)
                        {
                            horaampm = 12;
                            am_pm = "AM";
                        }
                        else if (hourOfDay == 12)
                        {
                            horaampm = 12;
                            am_pm = "PM";
                        }
                        else if (hourOfDay>12)
                        {
                            horaampm = hourOfDay - 12;
                            am_pm = "PM";
                        }
                        else
                        {
                            horaampm = hourOfDay;
                            am_pm = "AM";
                        }
                        txthorafinal.setText("Fin: " + horaampm+":"+minute + " " + am_pm);
                    }
                },hora,minutos,false);
                timePickerDialogfinal.show();


                TimePickerDialog timePickerDialoginicio = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay==0)
                        {
                            horaampm = 12;
                            am_pm = "AM";
                        }
                        else if (hourOfDay == 12)
                        {
                            horaampm = 12;
                            am_pm = "PM";
                        }
                        else if (hourOfDay>12)
                        {
                            horaampm = hourOfDay - 12;
                            am_pm = "PM";
                        }
                        else
                        {
                            horaampm = hourOfDay;
                            am_pm = "AM";
                        }
                        txthorainicial.setText("Ini: " + horaampm+":"+minute + " " + am_pm);
                    }
                },hora,minutos,false);
                timePickerDialoginicio.show();
                break;
        }
    }
}
