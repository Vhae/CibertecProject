package com.example.cibertecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cibertecproject.Course.Course;

import org.parceler.Parcels;

public class AddCourseEventActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGuardarCourse;
    private TextView txtTituloCrearCurso,txtnombre,txtDescripcion,txtCosto;
    private EditText ednombre,edDescripcion,edCosto;
    Course curso;
    String opcion = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_event);

        txtTituloCrearCurso = (TextView) findViewById(R.id.txtTituloCrearCurso);
        ednombre = (EditText) findViewById(R.id.edNombre);
        edDescripcion = (EditText) findViewById(R.id.edDescripcion);
        edCosto = (EditText) findViewById(R.id.edCosto);

        btnGuardarCourse = (Button) findViewById( R.id.btnGuardarCourse );

        if(getIntent().hasExtra("opcion")) {
            opcion = getIntent().getStringExtra("opcion");
            if(opcion.equalsIgnoreCase("1") ){
                txtTituloCrearCurso.setText("Editar Curso");
            }
        }
        if(getIntent().hasExtra("edit_curso")) {
            curso = Parcels.unwrap(getIntent().getParcelableExtra("edit_curso"));
            ednombre.setText(curso.getNombre());
            edDescripcion.setText(curso.getDescripcion());
        }



        btnGuardarCourse.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int v=view.getId();
        if(v==R.id.btnGuardarCourse ) {
            switch (view.getId()) {
                case R.id.btnGuardarCourse:
                    if(opcion.equalsIgnoreCase("0")){
                        Toast.makeText(view.getContext(), "Curso registrado.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(view.getContext(), "Curso Actualizado.", Toast.LENGTH_SHORT).show();
                    }
                    Intent addintent = new Intent(view.getContext(), MainActivity.class);
                    addintent.putExtra("origen", "curso");
                    view.getContext().startActivity(addintent);
                    break;
                case R.id.imgDeleteCourse:
                    // Se elimina
                    Toast.makeText(view.getContext(), "Se Elimina uno", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
