package com.example.cibertecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cibertecproject.Course.ApiClient;
import com.example.cibertecproject.Course.ApiCourseService;
import com.example.cibertecproject.Course.Course;
import com.example.cibertecproject.Course.Lugar;
import com.example.cibertecproject.Course.ResponseRest;

import org.parceler.Parcels;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCourseEventActivity extends AppCompatActivity implements View.OnClickListener {

    //region Globales
    private ImageButton btnGuardarCourse, btnCancelCourse;
    private TextView txtTituloCrearCurso, txtnombre, txtDescripcion, txtCosto, txtIdCurso;
    private EditText ednombre, edDescripcion, edCosto,
            edFechaInicio, edHoraInicio, edhoraFin;

    private ImageButton imgbFechaInicio,imbHoraInicio,imbHoraFin;

    private Spinner spnLugar;

    private Course curso;
    private String opcion = "0";
    private int gloId_Curso = 0;
    private List<Lugar> olstLugar;

    //Para el calendario
    private int dia, mes, anio;

    //campos a validar
    private String strId_Curso = "";
    private String strNombre = "";
    private String strDescripcion = "";
    private Date strFechaInicio = null;
    private Time strHoraInicio = null;
    private Time strHoraFin = null;
    private float floCosto = 0;
    private int intEstado = 1;

    //Para el control hora
    Calendar cal= Calendar.getInstance();
    int gloHora = cal.get(Calendar.HOUR);
    int gloMin = cal.get(Calendar.MINUTE);

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_event);

        txtTituloCrearCurso = (TextView) findViewById(R.id.txtTituloCrearCurso);
        ednombre = (EditText) findViewById(R.id.edNombre);
        edDescripcion = (EditText) findViewById(R.id.edDescripcion);
        edCosto = (EditText) findViewById(R.id.edCosto);
        imgbFechaInicio = (ImageButton) findViewById(R.id.imbFechaInicio);
        imbHoraInicio = findViewById(R.id.imbHoraInicio);
        imbHoraFin = findViewById(R.id.imbHoraFin);
        edFechaInicio = (EditText) findViewById(R.id.edFechaInicio);
        edHoraInicio = findViewById(R.id.edHoraInicio);
        edhoraFin = findViewById(R.id.edHoraFin);
        txtIdCurso = (TextView) findViewById(R.id.txtIdCurso);
        spnLugar = findViewById(R.id.spnLugar);
        listarLugar();

        btnGuardarCourse = findViewById(R.id.btnGuardarCourse);
        btnCancelCourse = findViewById(R.id.btnCancelCourse);

        btnCancelCourse.setOnClickListener(this);
        btnGuardarCourse.setOnClickListener(this);
        imgbFechaInicio.setOnClickListener(this);
        imbHoraInicio.setOnClickListener(this);;
        imbHoraFin.setOnClickListener(this);

        setDefaultValues();
    }


    @Override
    public void onClick(View view) {

        int v = view.getId();
        //if(v==R.id.btnGuardarCourse ) {
        switch (view.getId()) {
            case R.id.btnGuardarCourse:

                Course oCourse = new Course();
                String strValidarMsg = validarDatos();
                if (strValidarMsg.equalsIgnoreCase("")) {

                    oCourse.setId_Curso(gloId_Curso);
                    oCourse.setNombre(strNombre);
                    oCourse.setDescripcion(strDescripcion);
                    oCourse.setFechaInicio(strFechaInicio);
                    oCourse.setHoraInicio(strHoraInicio);
                    oCourse.setHoraFin(strHoraFin);
                    oCourse.setCosto(floCosto);
                    oCourse.setEstado(intEstado);

                    if (opcion.equalsIgnoreCase("0")) {
                        registCourse(oCourse);
                        Toast.makeText(view.getContext(), "Curso registrado.", Toast.LENGTH_SHORT).show();
                    } else {
                        updateCourse(oCourse);
                        Toast.makeText(view.getContext(), "Curso Actualizado.", Toast.LENGTH_SHORT).show();
                    }

                    Intent addintent = new Intent(view.getContext(), MainActivity.class);
                    addintent.putExtra("origen", "curso");
                    view.getContext().startActivity(addintent);
                }else{
                    Toast.makeText(view.getContext(), strValidarMsg, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgDeleteCourse:
                // Se elimina
                Toast.makeText(view.getContext(), "Se Elimina uno", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancelCourse:
                Intent addintent = new Intent(view.getContext(), MainActivity.class);
                addintent.putExtra("origen", "curso");
                view.getContext().startActivity(addintent);
                break;
            case R.id.imbFechaInicio:

                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String strAnio="",strMes ="", strDia="";
                        strDia = formatCadena(dayOfMonth);
                        strMes = formatCadena(month + 1);
                        strAnio = String.valueOf(year);
                        edFechaInicio.setText(strDia + "/" + strMes + "/" + strAnio);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
                break;
            case R.id.imbHoraInicio:

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String strHora,strMin;
                        strHora = formatCadena( hourOfDay );
                        strMin = formatCadena( minute );

                        edHoraInicio.setText( strHora + ":" + strMin );
                    }
                }, gloHora, gloMin,true);
                timePickerDialog.show();

                break;
            case R.id.imbHoraFin:
                TimePickerDialog timePickerDial = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String strHora,strMin;
                        strHora = formatCadena( hourOfDay );
                        strMin = formatCadena( minute );

                        edhoraFin.setText( strHora + ":" + strMin );
                    }
                }, gloHora, gloMin,true);
                timePickerDial.show();
                break;

        }
        //}
    }

    //region Procedimientos

    private void setDefaultValues() {

        if (getIntent().hasExtra("opcion")) {
            opcion = getIntent().getStringExtra("opcion");
        }

        //Opcion = 0 es Registrar
        if (opcion.equalsIgnoreCase("0")) {
            ednombre.setText("Java Web");
            edDescripcion.setText("Programacion en lenguaje Java");
            edFechaInicio.setText("30/04/2020");
            edHoraInicio.setText("14:00:00");
            edhoraFin.setText("20:30:00");
            edCosto.setText("1400");
        } else {
            if (opcion.equalsIgnoreCase("1")) //Si vamos a editar el registro
            {
                txtTituloCrearCurso.setText("Editar Curso");
                //leeemos el obj seleccionado y seteamos los controles con los datos a editar
                if (getIntent().hasExtra("edit_curso")) {
                    curso = Parcels.unwrap(getIntent().getParcelableExtra("edit_curso"));
                    gloId_Curso = curso.getId_Curso();
                    //txtIdCurso.setText(String.valueOf(curso.getId_Curso()));
                    ednombre.setText(curso.getNombre());
                    edDescripcion.setText(curso.getDescripcion());
                    edFechaInicio.setText( dateToStr(curso.getFechaInicio()) );
                    edHoraInicio.setText(curso.getHoraInicio().toString());
                    edhoraFin.setText(curso.getHoraFin().toString());
                    edCosto.setText(String.valueOf(curso.getCosto()));
                }
            }
        }
    }

    private void listarLugar() {
        //final List<Lugar> lstLugar = new ArrayList<>();

        ApiCourseService apiCourseService = ApiClient.getApiClient().create(ApiCourseService.class);
        Call<ResponseRest> callLugar = apiCourseService.getLugar();
        callLugar.enqueue(new Callback<ResponseRest>() {
            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                olstLugar = response.body().getOlstLugar();

                /* */
                ArrayAdapter<Lugar> arrayAdapter = new ArrayAdapter<Lugar>(getBaseContext(),
                        android.R.layout.simple_spinner_item, olstLugar);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spnLugar.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                Log.e("Error WCF", t.getMessage());
            }
        });
        List<Lugar> lst = new ArrayList<Lugar>();
        lst = olstLugar;
    }

    private void registCourse(Course opCourse) {
        ApiCourseService api = ApiClient.getApiClient().create(ApiCourseService.class);
        Call<Integer> callRegCourse = api.registCourse(opCourse);
        callRegCourse.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int resInsert = response.body();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }

    private void updateCourse(Course poCourse) {
        ApiCourseService apiCourseService = ApiClient.getApiClient().create(ApiCourseService.class);
        Call<Integer> call = apiCourseService.updateCourse(poCourse);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int res = 0;
                res = response.body();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                int r = 0;
                r = 21;
            }
        });

    }

    private Date dateFecha(String pstrFecha) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(pstrFecha);
        } catch (Exception e) {
            Log.e("Error Fecha", e.getMessage());
        }

        return date;
    }

    private String dateToStr(Date date){
        String strFecha = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(date != null) {
            strFecha = sdf.format(date);
        }
        return strFecha;
    }

    private String validarDatos() {
        String strMsg = "";

        strId_Curso = txtIdCurso.getText().toString();

        if(ednombre.getText() != null && ednombre.getText().toString() != ""){
            strNombre = ednombre.getText().toString();
        }else{
            strMsg = "El campo nombre no puede quedar vacío.";
        }
        if( edDescripcion.getText() != null && edDescripcion.getText().toString() != "") {
            strDescripcion = edDescripcion.getText().toString();
        }else{
            strMsg = strMsg + "\n El campo Descripción no puede quedar vacío.";
        }
        if( edFechaInicio.getText() != null && edFechaInicio.getText().toString()!= ""  ){
            strFechaInicio = dateFecha(edFechaInicio.getText().toString());
        }else{
            strMsg = strMsg + "\n El campo Fecha de inicio no puede quedar vacío.";
        }
        if( edHoraInicio.getText() != null
                && !edHoraInicio.getText().toString().equalsIgnoreCase("") ){
            if( edHoraInicio.getText().toString().length() < 8 ){
                strHoraInicio = Time.valueOf(edHoraInicio.getText().toString()+":00" );
            }else{
                strHoraInicio = Time.valueOf(edHoraInicio.getText().toString());
            }

        }else{
            strMsg = strMsg + "\n El campo Hora de inicio no puede quedar vacío.";
        }
        if( edhoraFin.getText() != null
                && !edhoraFin.getText().toString().equalsIgnoreCase("") ){
            if( edhoraFin.getText().toString().length() < 8 ){
                strHoraFin = Time.valueOf(edhoraFin.getText().toString()+":00" ) ;
            }else{
                strHoraFin = Time.valueOf(edhoraFin.getText().toString());
            }
        }else{
            strMsg = strMsg + "\n El campo Hora de Fin no puede quedar vacío.";
        }
        if( edCosto.getText() != null && edCosto.getText().toString()!= ""  ){
            floCosto = Float.parseFloat(edCosto.getText().toString());
        }else{
            strMsg = strMsg + "\n El campo Costo no puede quedar vacío.";
        }

        return strMsg;
    }

    private String formatCadena(int intVal){
        String strVal = "";
        strVal = "00" + intVal;
        strVal = strVal.substring(strVal.length()-2);
        return strVal;
    }

    //endregion

}
