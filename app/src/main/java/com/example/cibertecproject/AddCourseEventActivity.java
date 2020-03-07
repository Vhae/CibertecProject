package com.example.cibertecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddCourseEventActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGuardarCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_event);

        btnGuardarCourse = (Button) findViewById( R.id.btnGuardarCourse );

        btnGuardarCourse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
